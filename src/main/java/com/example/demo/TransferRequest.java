package com.example.demo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransferRequest {
    private Long transactionId;
    private Integer fromAccount;
    private Integer toAccount;
    private Double money;
}
