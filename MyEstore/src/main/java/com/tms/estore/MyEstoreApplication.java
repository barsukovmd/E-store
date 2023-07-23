package com.tms.estore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableJpaRepositories(basePackages = "com.tms.estore.repository")
@EnableTransactionManagement
@SpringBootApplication
public class MyEstoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyEstoreApplication.class, args);
    }

}
