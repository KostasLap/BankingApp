package net.javaguides.banking.dto;

public record TransferFundDto(Long fromAccountId,
                              Long toAccountId,
                              Double amount) {
}
