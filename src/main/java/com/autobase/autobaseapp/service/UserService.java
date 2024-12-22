package com.autobase.autobaseapp.service;

import org.springframework.stereotype.Service;
import com.autobase.autobaseapp.model.User;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private List<User> users = new ArrayList<>();

    public UserService() {
        // Предзаполненные пользователи
        users.add(new User("dispatcher1", "password123", "Dispatcher"));
        users.add(new User("driver1", "driverpass", "Driver"));
    }

    // Регистрация пользователя
    public boolean register(String username, String password, String role) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return false; // Пользователь с таким именем уже существует
            }
        }
        users.add(new User(username, password, role));
        return true;
    }

    // Авторизация пользователя
    public User login(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }
}