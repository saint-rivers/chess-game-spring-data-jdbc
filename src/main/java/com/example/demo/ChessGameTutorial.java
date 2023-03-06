package com.example.demo;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;

@SpringBootApplication
@RequiredArgsConstructor
@EnableJdbcRepositories
public class ChessGameTutorial {

    public static void main(String[] args) {
        SpringApplication.run(ChessGameTutorial.class, args);
    }

}
