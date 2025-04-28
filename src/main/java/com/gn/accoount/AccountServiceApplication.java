package com.gn.accoount;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*@EnableJpaAuditing
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)*/
@SpringBootApplication
public class AccountServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountServiceApplication.class, args);
	}

}
