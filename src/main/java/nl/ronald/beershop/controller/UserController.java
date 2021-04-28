package nl.ronald.beershop.controller;

import nl.ronald.beershop.exception.BadRequestException;
import nl.ronald.beershop.model.Authority;
import nl.ronald.beershop.model.User;
import nl.ronald.beershop.repository.AuthorityRepository;
import nl.ronald.beershop.repository.UserRepository;
import nl.ronald.beershop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/api/v1")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthorityRepository authorityRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping(value = "/users")
    public ResponseEntity<Object> getUsers() {
        return ResponseEntity.ok().body(userService.getUsers());
    }

    @GetMapping(value = "/users/{username}")
    public ResponseEntity<Object> getUser(@PathVariable("username") String username) {
        return ResponseEntity.ok().body(userService.getUser(username));
    }

    @PutMapping(value = "/users/{username}")
    public ResponseEntity<Object> updateUser(@PathVariable("username") String username, @RequestBody User user) {
        userService.updateUser(username, user);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/admin/users/{username}")
    public ResponseEntity<Object> deleteUser(@PathVariable("username") String username) {
        userService.deleteUser(username);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/users/{username}/authorities")
    public ResponseEntity<Object> getUserAuthorities(@PathVariable("username") String username) {
        return ResponseEntity.ok().body(userService.getAuthorities(username));
    }

    @PostMapping(value = "/create_authority")
    public ResponseEntity<Object> createUserAuthority(@RequestBody Authority authority) {
        authorityRepository.save(authority);
        URI location;
        return new ResponseEntity<>("Rol is toegevoegd", HttpStatus.CREATED);

//        try {
//            String authorityName = (String) fields.get("authority");
//            userService.addAuthority(username, authorityName);
//            return ResponseEntity.noContent().build();
//        }
//        catch (Exception ex) {
//            throw new BadRequestException();
//        }
    }

    @RequestMapping(value = {"/create_user", "/admin/create_user"}, method = POST)
    public ResponseEntity<Object> createUser(@RequestBody User user) {
        String password = user.getPassword();
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
        URI location;
        return new ResponseEntity<>("User is toegevoegd", HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{username}/authorities/{authority}")
    public ResponseEntity<Object> deleteUserAuthority(@PathVariable("username") String username, @PathVariable("authority") String authority) {
        userService.removeAuthority(username, authority);
        return ResponseEntity.noContent().build();
    }


    // auth fix
//    @GetMapping(value = "/api/v1/userprofiles")
//    public ResponseEntity<Object> getUsers() {
//        List<User> users = userRepository.findAll();
//        return new ResponseEntity<>(users, HttpStatus.OK);
//    }
//
//    //auth fix!/1
//    @GetMapping(value = "/api/v1/userprofile/{username}")
//    public ResponseEntity<Object> getUser(@PathVariable("username") String username) {
//        Optional<User> user = userRepository.findById(username);
//        return new ResponseEntity<>(user, HttpStatus.OK);
//    }
//


//
//    // auth fix & related authorities fix
//    @PutMapping(value = "/api/v1/admin/userprofile/{id}")
//    public User updateUser(@RequestBody User user, @PathVariable Long id) {
//        return userRepository.findById(id)
//        .map(updateUser -> {
//            updateUser.setUsername(user.getUsername());
//            updateUser.setExpiration_date(user.getExpiration_date());
//            updateUser.setPassword(passwordEncoder.encode(user.getPassword()));
//            return userRepository.save(updateUser);
//        })
//        .orElseGet(() -> {
//            return userRepository.save(user);
//        });
//    }
//
//    // auth fix
//    @DeleteMapping("/api/v1/admin/userprofile/{id}")
//    public ResponseEntity<Object> deleteUser(@PathVariable("id") long id) {
//        userRepository.deleteById(id);
//        return new ResponseEntity<>("Gebruiker is verwijderd", HttpStatus.OK);
//    }


}
