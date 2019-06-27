package com.travel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class TravelWebServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TravelWebServiceApplication.class, args);
    }

}
