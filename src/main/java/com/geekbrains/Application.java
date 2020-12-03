package com.geekbrains;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

// TODO:
// 1. Добавить Аспект на логирование метода создания заказа через аннотацию Around, скорость выполнения метода в мс
