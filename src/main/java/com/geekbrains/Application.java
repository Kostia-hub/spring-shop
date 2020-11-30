package com.geekbrains;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.UUID;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

// TODO:

// 3. Зацикливание при сохранение, сохраняет слишком много продуктов. Поправить. В ОрдерСервисе??
