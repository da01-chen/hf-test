package com.example.demo;

import com.example.demo.mapper.TransferTransactionMapper;
import com.example.demo.mapper.UserAccountMapper;
import com.example.demo.model.TransferTransaction;
import com.example.demo.model.UserAccount;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@Slf4j
public class TransferTransactionService {

    @Autowired
    private UserAccountMapper userAccountMapper;

    @Autowired
    private TransferTransactionMapper transactionMapper;

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Transactional
    public int transferAccount(TransferRequest request){
        int transferOut = userAccountMapper.transferOut(request.getFromAccount(), BigDecimal.valueOf(request.getMoney()));
        log.info("transfer transferOut done: {}", request.getTransactionId());
        if(transferOut > 0){
            //乐观锁成功
            TransferTransaction transaction = getTransaction(request);
            transactionMapper.insertSelective(transaction);
            log.info("transfer transaction saved: {}", request.getTransactionId());
            userAccountMapper.transferIn(request.getToAccount(), BigDecimal.valueOf(request.getMoney()));
        }
        return transferOut;
    }

    private static TransferTransaction getTransaction(TransferRequest request) {
        TransferTransaction transaction = new TransferTransaction();
        transaction.setFromAccount(request.getFromAccount());
        transaction.setToAccount(request.getToAccount());
        transaction.setTransactionId(request.getTransactionId());
        transaction.setMoney(BigDecimal.valueOf(request.getMoney()));
        transaction.setProcessStatus("DONE");
        return transaction;
    }

    @Transactional
    public void batchDelete(int beginIndex, int endIndex){
        userAccountMapper.deleteByAccountRange(beginIndex,endIndex);
    }

//    @Transactional
//    public void batchSave(List<UserAccount> accounts){
//        userAccountMapper.insertBatch(accounts);
//    }

    @Transactional
    public void batchInsert(List<UserAccount> accounts) {
        try (SqlSession session = sqlSessionFactory.openSession(ExecutorType.BATCH)) {
            UserAccountMapper mapper = session.getMapper(UserAccountMapper.class);
            mapper.insertBatch(accounts);
            session.commit();
        }
    }



}
