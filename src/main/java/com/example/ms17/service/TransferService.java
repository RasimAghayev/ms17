package com.example.ms17.service;

import java.math.BigDecimal;

public interface TransferService {

    void doTransfer(BigDecimal amount) throws Exception;

    void doTransferWithoutTransactional(BigDecimal amount) throws Exception;
}
