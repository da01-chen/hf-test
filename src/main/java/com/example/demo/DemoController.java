package com.example.demo;

import com.example.demo.mapper.TransferTransactionMapper;
import com.example.demo.mapper.UserAccountMapper;
import com.example.demo.model.TransferTransaction;
import com.example.demo.model.UserAccount;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class DemoController {

    @Autowired
    private UserAccountMapper userAccountMapper;

    @Autowired
    private TransferTransactionMapper transactionMapper;

    @Autowired
    private RedisService redisService;

    @Autowired
    private TransferTransactionService Service;

    @GetMapping("/getDemo")
    public String getDemo(@RequestParam String param) {
        UserAccount userAccount = userAccountMapper.selectByPrimaryKey(Long.valueOf(param));
        log.info("test key: test");
        Boolean test = redisService.setNX("test", param, 600);
        log.info("redis setNX: {}", test);
        return "Hello, Spring Boot!"+userAccount.getMoney()+",setNX:"+test;
    }

    @PostMapping("/transfer")
    public ResponseEntity<String> transfer(@RequestBody TransferRequest request) {
        // 处理请求，例如打印请求参数
        StopWatch stopWatch = new StopWatch();
        stopWatch.start("setNX");
        log.info("transfer begin with data: {}", request.toString());
        //获取redis锁
        Boolean test = redisService.setNX("Lock:"+request.getTransactionId(), request.getTransactionId(), 600);
        if(!test){
            //获取锁失败,重复请求，其他实例处理中
            log.info("Duplicate: {}", request.getTransactionId());
            return ResponseEntity.ok("Duplicate");
        }
        stopWatch.stop();
        log.info("transfer setNX done: {}", request.getTransactionId());

        stopWatch.start("queryTransaction");
        TransferTransaction transaction = transactionMapper.selectByTransactionId(request.getTransactionId());
        if(transaction != null){
            //同id重复提交，幂等
            log.info(" transactionId:{} status:{}", request.getTransactionId(), transaction.getProcessStatus());
            redisService.delete("Lock:"+request.getTransactionId());
            return ResponseEntity.ok(transaction.getProcessStatus());
        }
        stopWatch.stop();
        log.info("transfer queryTransaction done: {}", request.getTransactionId());

        stopWatch.start("transfer");
        int count = Service.transferAccount(request);
        //释放redis锁
        redisService.delete("Lock:"+request.getTransactionId());

        stopWatch.stop();
        log.info("{} 操作耗时: {}", request.getTransactionId(), stopWatch.prettyPrint());
        for (StopWatch.TaskInfo task : stopWatch.getTaskInfo()) {
            log.info("Task Name: {}, Time: {} ms", task.getTaskName(), task.getTimeMillis());
        }

        if(count > 0){
            //处理成功
            log.info("transfer success: {}", request.getTransactionId());
            return ResponseEntity.ok("success");
        }else{
            //临界，资金不足
            log.info("no money: {}", request.getTransactionId());
            return ResponseEntity.ok("no_money");
        }

    }

}
