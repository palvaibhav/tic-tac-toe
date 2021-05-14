package models;

import lombok.Getter;

import java.util.UUID;

@Getter
public class User {

    private String id;
    private String name;
    private char symbol;

    public User(String name, char symbol) {
        id = UUID.randomUUID().toString();
        this.name = name;
        this.symbol = symbol;
    }
}
