package com.stokmanager.backend.controller;

import com.stokmanager.backend.entity.User;
import com.stokmanager.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    // Kayıt (Register)
    @PostMapping("/register")
    public User registerUser(@RequestBody Map<String, String> body) {
        String username = body.get("username");
        String password = body.get("password");
        String role = body.get("role"); // "ADMIN" ya da "USER"

        return userService.registerUser(username, password, role);
    }

    // Giriş (Login)
    @PostMapping("/login")
    public String loginUser(@RequestBody Map<String, String> body) {
        String username = body.get("username");
        String password = body.get("password");

        User user = userService.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı"));

        boolean isPasswordValid = userService.checkPassword(password, user.getPassword());

        if (!isPasswordValid) {
            throw new RuntimeException("Geçersiz şifre");
        }

        // Şimdilik JWT yerine basit cevap:
        return "Giriş başarılı: " + username + " (rol: " + user.getRole() + ")";
    }
}
