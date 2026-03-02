package khaos.project.task.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @PostMapping("/login")
    public ResponseEntity<String> login() {
        // In a real app, you would verify credentials here
        return ResponseEntity.ok("Welcome to home page!");
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signup() {
        // In a real app, you would save the user here
        return ResponseEntity.ok("You registered successfully!");
    }
}
