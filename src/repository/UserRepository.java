package repository;

import models.User;

import java.util.HashMap;

public class UserRepository {

    private static HashMap<String, User> userHashMap = new HashMap<>();

    public static User get(String id) {
        return userHashMap.get(id);
    }

    public static void add(User user) {
        userHashMap.putIfAbsent(user.getId(), user);
    }
}
