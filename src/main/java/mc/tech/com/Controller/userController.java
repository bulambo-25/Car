package mc.tech.com.Controller;

import lombok.extern.slf4j.Slf4j;
import mc.tech.com.domain.User;
import mc.tech.com.service.domain.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("car/")
@Slf4j
public class userController {

    private final UserService Service;
    @Autowired
    public userController(UserService service) {
        Service = service;
    }
    @PostMapping("register")
    public ResponseEntity<User> save(@RequestBody @Valid User user)
    {

        User save= this.Service.create(user);
        log.info("User created::",user);
        return  ResponseEntity.ok(save);
    }

    @GetMapping("read/{id}")
    public ResponseEntity<User> read(@PathVariable  Long id) {
        User read=Service.read(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
        return ResponseEntity.ok(read);
    }

    @GetMapping("findByEmail//")
    public ResponseEntity<User> findByEmail(@PathVariable  String email) {
        User read=Service.getUserByEmail(email);
        return ResponseEntity.ok(read);
    }
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User userCredentials) {
        User user = Service.getUserByEmail(userCredentials.getEmail());

        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }

        if (!user.getPassword().equals(userCredentials.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }

        return ResponseEntity.ok("Login successful");
    }
}
