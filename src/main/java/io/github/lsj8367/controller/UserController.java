package io.github.lsj8367.controller;

import io.github.lsj8367.form.User;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @PostMapping("/user")
    public ResponseEntity<User> getUser(@Valid @RequestBody User user) {
        return ResponseEntity.ok(user);
    }
}
