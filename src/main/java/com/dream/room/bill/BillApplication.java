package com.dream.room.bill;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories("com.dream.room.bill.repository")
@EnableJpaAuditing
@SpringBootApplication
public class BillApplication {

	public static void main(String[] args) {
		SpringApplication.run(BillApplication.class, args);
	}
}
