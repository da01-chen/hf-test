package com.example.demo;

import com.example.demo.mapper.TransferTransactionMapper;
import com.example.demo.mapper.UserAccountMapper;
import com.example.demo.model.TransferTransaction;
import com.example.demo.model.UserAccount;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class TestTransfer {

    @Autowired
    DemoController controller;

    @Autowired
    private RedisService redisService;

    @Autowired
    private TransferTransactionMapper mapper;

    @Autowired
    private UserAccountMapper userAccountMapper;

    @Test
    void testTransfer1() {
        //使用客户端transactionId做幂等，使用reids SetNX防多个实例同时处理
        TransferRequest request = new TransferRequest();
        request.setTransactionId(1L);

        String key = "Lock:" + request.getTransactionId();
        redisService.setValue(key, request.getTransactionId(), 600);
        ResponseEntity<String> response = controller.transfer(request);

        //获取锁失败,重复请求，其他实例处理中
        assertEquals("Duplicate", response.getBody());

        redisService.delete(key);
    }

    @Test
    void testTransfer2() {
        //同id重复提交，幂等
        mapper.deleteByTransactionId(2L);
        TransferTransaction transaction = new TransferTransaction();
        transaction.setTransactionId(2L);
        transaction.setProcessStatus("DONE");
        mapper.insertSelective(transaction);

        TransferRequest request = new TransferRequest();
        request.setTransactionId(2L);
        ResponseEntity<String> response = controller.transfer(request);

        //获取锁失败,重复请求，其他实例处理中
        assertEquals("DONE", response.getBody());
    }

    @Test
    void testTransfer3() {
        //成功
        mapper.deleteByTransactionId(3L);

        userAccountMapper.deleteByAccount(1L);
        UserAccount fromAccount = new UserAccount();
        fromAccount.setAccount(1);
        fromAccount.setUserName("1");
        fromAccount.setMoney(BigDecimal.valueOf(100));
        userAccountMapper.insertSelective(fromAccount);

        userAccountMapper.deleteByAccount(2L);
        UserAccount toAccount = new UserAccount();
        toAccount.setAccount(2);
        toAccount.setUserName("2");
        toAccount.setMoney(BigDecimal.ZERO);
        userAccountMapper.insertSelective(toAccount);

        TransferRequest request = new TransferRequest();
        request.setFromAccount(1);
        request.setToAccount(2);
        request.setTransactionId(3L);
        request.setMoney(10D);
        ResponseEntity<String> response = controller.transfer(request);

        //获取锁失败,重复请求，其他实例处理中
        assertEquals("success", response.getBody());
        UserAccount fromAccountLast = userAccountMapper.selectByAccount(1L);
        UserAccount toAccountLast = userAccountMapper.selectByAccount(2L);
        assertEquals(90, fromAccountLast.getMoney().intValue());
        assertEquals(10, toAccountLast.getMoney().intValue());
    }

    @Test
    void testTransfer4() {
        //账户余额不足
        mapper.deleteByTransactionId(4L);

        userAccountMapper.deleteByAccount(3L);
        UserAccount fromAccount = new UserAccount();
        fromAccount.setAccount(3);
        fromAccount.setUserName("3");
        fromAccount.setMoney(BigDecimal.valueOf(100));
        userAccountMapper.insertSelective(fromAccount);

        userAccountMapper.deleteByAccount(4L);
        UserAccount toAccount = new UserAccount();
        toAccount.setAccount(4);
        toAccount.setUserName("4");
        toAccount.setMoney(BigDecimal.ZERO);
        userAccountMapper.insertSelective(toAccount);

        TransferRequest request = new TransferRequest();
        request.setFromAccount(3);
        request.setToAccount(4);
        request.setTransactionId(4L);
        request.setMoney(200D);
        ResponseEntity<String> response = controller.transfer(request);

        //获取锁失败,重复请求，其他实例处理中
        assertEquals("no_money", response.getBody());
        UserAccount fromAccountLast = userAccountMapper.selectByAccount(3L);
        UserAccount toAccountLast = userAccountMapper.selectByAccount(4L);
        assertEquals(100, fromAccountLast.getMoney().intValue());
        assertEquals(0, toAccountLast.getMoney().intValue());
    }




}
