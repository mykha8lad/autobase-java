package com.example.AutoBase.service.registrationuserservice;

import com.example.AutoBase.model.Driver;

import java.util.Optional;

public interface RegistrationUserService {
    Optional<String> registerUser(Driver driver);
}
