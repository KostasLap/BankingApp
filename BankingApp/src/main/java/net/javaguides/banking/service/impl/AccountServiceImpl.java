package net.javaguides.banking.service.impl;

import net.javaguides.banking.dto.AccountDto;
import net.javaguides.banking.dto.TransactionDto;
import net.javaguides.banking.mapper.TransactionMapper;
import net.javaguides.banking.dto.TransferFundDto;
import net.javaguides.banking.entity.Account;
import net.javaguides.banking.entity.Transaction;
import net.javaguides.banking.exception.AccountException;
import net.javaguides.banking.mapper.AccountMapper;
import net.javaguides.banking.repository.AccountRepository;
import net.javaguides.banking.repository.TransactionRepository;
import net.javaguides.banking.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;

    private TransactionRepository transactionRepository;

    private static final String TRANSACTION_TYPE_DEPOSIT = "DEPOSIT";
    private static final String TRANSACTION_TYPE_WITHDRAW="WITHDRAW";
    private static final String TRANSACTION_TYPE_TRANSFER="TRANSFER";

    public AccountServiceImpl(AccountRepository accountRepository, TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    @Override
    public AccountDto createAccount(AccountDto accountDto){
        Account account = AccountMapper.mapToAccount(accountDto);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);

    }

    @Override
    public AccountDto getAccountById(Long id){
        Account account=accountRepository
                .findById(id)
                .orElseThrow(()->new AccountException("Account does not exists"));
        return AccountMapper.mapToAccountDto(account);
    }

    @Override
    public AccountDto deposit(Long id, Double amount) {
        Account account = accountRepository
                .findById(id)
                .orElseThrow(()->new AccountException("Account does not exists"));

        account.setBalance(account.getBalance()+amount);

        Account savedAccount = accountRepository.save(account);

        Transaction transaction = new Transaction();
        transaction.setAccountId(account.getId());
        transaction.setAmount(amount);
        transaction.setTransactionType(TRANSACTION_TYPE_DEPOSIT);
        transaction.setTimestamp(LocalDateTime.now());

        transactionRepository.save(transaction);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto withdraw(Long id, Double amount) {
        Account account = accountRepository
                .findById(id)
                .orElseThrow(()->new AccountException("Account does not exists"));
        if(account.getBalance()<amount){
            throw new RuntimeException("Insufficient amount");
        }
        Double total = account.getBalance()-amount;

        account.setBalance(total);

        Account savedAccount  = accountRepository.save(account);

        Transaction transaction = new Transaction();
        transaction.setAccountId(account.getId());
        transaction.setTransactionType(TRANSACTION_TYPE_WITHDRAW);
        transaction.setAmount(amount);
        transaction.setTimestamp(LocalDateTime.now());
        transactionRepository.save(transaction);

        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public List<AccountDto> getAllAccounts() {
        List<Account>accounts=accountRepository.findAll();

        return accounts.stream().map(account -> AccountMapper.mapToAccountDto(account))
                .collect(Collectors.toList());

    }

    @Override
    public void deleteAccount(Long id) {
        Account account = accountRepository
                .findById(id)
                .orElseThrow(()->new AccountException("Account does not exists"));


        accountRepository.deleteById(id);

    }

    @Override
    public void transferFunds(TransferFundDto transferFundDto) {
        Account fromAccount = accountRepository.findById(transferFundDto.fromAccountId())
                .orElseThrow(()->new AccountException("Account does not exists"));

        Account toAccount= accountRepository.findById(transferFundDto.toAccountId())
                .orElseThrow(()->new AccountException("Account does not exists"));

        if(fromAccount.getBalance()>= transferFundDto.amount()){
            fromAccount.setBalance(fromAccount.getBalance()-transferFundDto.amount());

            toAccount.setBalance(toAccount.getBalance()+ transferFundDto.amount());

            accountRepository.save(fromAccount);
            accountRepository.save(toAccount);

            Transaction transaction = new Transaction();
            transaction.setAccountId(fromAccount.getId());
            transaction.setTransactionType(TRANSACTION_TYPE_TRANSFER);
            transaction.setTimestamp(LocalDateTime.now());
            transaction.setAmount(transferFundDto.amount());
            transactionRepository.save(transaction);
        } else{
            throw new RuntimeException("Balance not enough");
        }

    }

    @Override
    public List<TransactionDto> getAccountTransactions(Long id) {
        List<Transaction> transactions = transactionRepository
                .findByAccountIdOrderByTimestampDesc(id);

        return transactions.stream()
                .map((transaction -> TransactionMapper.mapToTransactionDto(transaction)))
                .collect(Collectors.toList());

    }
}
