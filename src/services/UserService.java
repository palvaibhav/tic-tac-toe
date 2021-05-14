package services;

import models.User;
import repository.UserRepository;

public class UserService {

    public User createUser(String name, char symbol) {
        User newUser = new User(name, symbol);
        UserRepository.add(newUser);
        return newUser;
    }
}
