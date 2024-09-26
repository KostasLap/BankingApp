package net.javaguides.banking.dto;
//
//import lombok.AllArgsConstructor;
//import lombok.Data;
//
//@Data
//@AllArgsConstructor
//public class AccountDto {
//    private Long id;
//    private String accountHolderName;
//    private Double balance;
//}

public record AccountDto(Long id,
                         String accountHolderName,
                         Double balance) {

}
