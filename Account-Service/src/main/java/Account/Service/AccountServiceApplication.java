package Account.Service;

import Account.Service.client.CustomerRestClient;
import Account.Service.entities.BankAccount;
import Account.Service.entities.Customer;
import Account.Service.enums.AccountType;
import Account.Service.repository.AccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.UUID;

@SpringBootApplication
@EnableFeignClients
public class AccountServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountServiceApplication.class, args);

	}

	@Bean
	CommandLineRunner start(AccountRepository accountRepository, CustomerRestClient client){
		return args -> {

		client.findAllCustomers().forEach(customer ->{

					BankAccount bankAccount = BankAccount.builder()
							.id(UUID.randomUUID().toString())
							.balance(Math.random()*80000)
							.createdAt(LocalDate.now())
							.type(AccountType.CURRENT_ACCOUNT)
							.currency("MAD")
							.customerId(customer.getId())
							.build();

					BankAccount bankAccount2 = BankAccount.builder()
							.id(UUID.randomUUID().toString())
							.balance(Math.random()*80000)
							.createdAt(LocalDate.now())
							.type(AccountType.SAVING_ACCOUNT)
							.currency("MAD")
							.customerId(customer.getId())
							.build();


					accountRepository.save(bankAccount);
					accountRepository.save(bankAccount2);

					}
				);
		};


}
}
