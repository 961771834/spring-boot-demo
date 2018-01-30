package com.leon.controller;

import com.leon.entity.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @PostMapping("/postuser")
    public User postUser(@RequestBody  User user){
        user.setName("leon1");
        return user;
    }

    @RequestMapping("/{id}")
    public User view(@PathVariable("id") Long id){
        User u = new User();
        u.setId(id);
        u.setName("leon");
        return u;
    }


}
