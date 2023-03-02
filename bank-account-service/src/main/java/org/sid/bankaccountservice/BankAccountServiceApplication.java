package org.sid.bankaccountservice;

import org.sid.bankaccountservice.entities.BankAccount;
import org.sid.bankaccountservice.enums.AccountType;
import org.sid.bankaccountservice.repositories.BankAccountRepositories;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.UUID;

@SpringBootApplication
public class BankAccountServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankAccountServiceApplication.class, args);

	}
	@Bean
	// @Bean ==> object will created in beginning of the service
	CommandLineRunner start(BankAccountRepositories bankAccountRepositories){
		return args -> {
			for (int i = 0; i <10 ; i++) {
				// BankAccount bankAccount = new BankAccount(); same job of ==> with arg
				BankAccount bankAccount = BankAccount.builder()
						.id(UUID.randomUUID().toString())
						.type(Math.random()>0.75? AccountType.CURRENT_ACCOUNT:AccountType.SAVING_ACCOUNT)
						.balance(Math.random()*70000+10000)
						.creatAt(new Date())
						.currency("DZD")
						.build();
				bankAccountRepositories.save(bankAccount);
			}

		};

	}
}
