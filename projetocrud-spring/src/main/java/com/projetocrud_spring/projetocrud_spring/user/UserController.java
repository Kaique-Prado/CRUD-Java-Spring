package com.projetocrud_spring.projetocrud_spring.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    UserRepository repository;

    @PostMapping()
    public ResponseEntity<UserCreateResponse> createUser(@RequestBody UserRequest payload) {
        User user = new User(payload);
        this.repository.save(user);
        return ResponseEntity.ok(new UserCreateResponse("Usuario criado"));
    }

    @GetMapping("/getallusers")
    public ResponseEntity<List<User>> getusers() {
        List<User> users = this.repository.findAll();
        return ResponseEntity.ok(users);
    }

    @DeleteMapping("/deleteuser")
    public ResponseEntity<UserDeleteResponse> deleteUser(@RequestParam("id") Long idUser) {
        this.repository.deleteById(idUser);
        return ResponseEntity.ok(new UserDeleteResponse("Usuario deletado"));
    }

    @PutMapping("/updateuser")
    public ResponseEntity<User> updateUser(@RequestParam("id") Long id , @RequestBody UserRequest payload) {
        Optional<User> user = this.repository.findById(id);

        if (user.isPresent()) {
            User updateUser = user.get();
            updateUser.setName(payload.name());
            updateUser.setAge(payload.age());

            this.repository.saveAndFlush(updateUser);

            return ResponseEntity.ok(updateUser);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/findbyid")
    public ResponseEntity<User> findById(@RequestParam("id") Long idUser){
        User user = this.repository.findById(idUser).get();
        return ResponseEntity.ok(user);
    }

    @GetMapping("/findbyname")
    public ResponseEntity<List<User>> findByName(@RequestBody @RequestParam(name = "name") String name){
        List<User> user = this.repository.findByName(name);
        return ResponseEntity.ok(user);
    }

}
