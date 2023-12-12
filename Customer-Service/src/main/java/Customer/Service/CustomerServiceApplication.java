package Customer.Service;

import Customer.Service.config.GlobalConfig;
import Customer.Service.entities.Customer;
import Customer.Service.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigurationProperties(GlobalConfig.class)
public class CustomerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(CustomerRepository customerRepository){
		return args -> {
			customerRepository.save(Customer.builder()
							.firstName("Mehdi")
							.lastName("TestM")
							.email("MehTest@gmail.com")
					.build());

			customerRepository.save(Customer.builder()
					.firstName("Ino")
					.lastName("InoTest")
					.email("InoTest@gmail.com")
					.build());

		};
	}
}
