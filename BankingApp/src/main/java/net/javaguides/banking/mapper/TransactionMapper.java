package net.javaguides.banking.mapper;

import net.javaguides.banking.dto.TransactionDto;
import net.javaguides.banking.entity.Transaction;

public class TransactionMapper {
    public static TransactionDto mapToTransactionDto(Transaction transaction){
        TransactionDto transactionDto = new TransactionDto(transaction.getId(),transaction.getAccountId(),transaction.getAmount(),transaction.getTransactionType(),transaction.getTimestamp());

        return transactionDto;
    }

    public static Transaction mapToTransaction(TransactionDto transactionDto){
        Transaction transaction = new Transaction(transactionDto.id(), transactionDto.accountId(), transactionDto.amount(), transactionDto.transactionType(), transactionDto.timestamp());

        return transaction;
    }
}


