package com.example.demo;

import com.example.demo.model.UserAccount;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class InitData {

    @Autowired
    private TransferTransactionService service;

    @Test
    void init1() {
        //测试组1 account范围 10001 - 20000，金额统一10w 场景无冲突
        service.batchDelete(10001,20000);
        List<UserAccount> temp = new ArrayList<>();
        for (int i =10001;i<=20000;i++){
            UserAccount account = new UserAccount();
            account.setAccount(i);
            account.setUserName(i+"");
            account.setMoney(BigDecimal.valueOf(100000));
            temp.add(account);
        }
        service.batchInsert(temp);
    }

}
