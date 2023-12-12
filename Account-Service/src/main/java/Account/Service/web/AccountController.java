package Account.Service.web;

import Account.Service.client.CustomerRestClient;
import Account.Service.entities.BankAccount;
import Account.Service.entities.Customer;
import Account.Service.repository.AccountRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AccountController {
    private final AccountRepository accountRepository;
    private final CustomerRestClient client;

    public AccountController(AccountRepository accountRepository, CustomerRestClient client) {
        this.accountRepository = accountRepository;
        this.client = client;
    }

    @GetMapping("/accounts/{accountId}")
    public ResponseEntity<BankAccount> getBankAccountById (@PathVariable String accountId){
        BankAccount bankAccount = accountRepository.findById(accountId).get();
        Customer customer = client.findCustomerById(bankAccount.getCustomerId());
        System.out.println(customer);
        bankAccount.setCustomer(customer);
        return ResponseEntity.ok(bankAccount);
    }

    @GetMapping("/accounts")
    public List<BankAccount> accountList(){
        List<BankAccount> accountList = accountRepository.findAll();
        accountList.forEach(acc -> {
            acc.setCustomer(client.findCustomerById(acc.getCustomerId()));
        });
        return accountList;
    }


}
