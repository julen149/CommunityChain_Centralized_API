package com.communitychain.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.communitychain.entity.User;
import com.communitychain.outputs.CommunityOutput;
import com.communitychain.outputs.UserOutput;
import com.communitychain.service.UserService;

@RestController
@RequestMapping("/api") 

public class UserRestController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public List<UserOutput> findAll(){
        //retornará todos los usuarios
        return userService.findAll();
    }

    @GetMapping("/users/{userId}")
    public UserOutput getUser(@PathVariable int userId){
        UserOutput user = userService.findById(userId);

        if(user == null) {
            throw new RuntimeException("User id not found -"+userId);
        }
        
        return user;
    }

    
    @GetMapping("/users/communities/{userId}")
    public List<CommunityOutput> getUserCommunities(@PathVariable int userId){
        
        List<CommunityOutput> uc= userService.getUserComm(userId);
        
        return uc;
    }
    

    @PostMapping("/users")
    public UserOutput addUser(@RequestBody User user) {
        
        user.setId(0);

        userService.save(user);

        UserOutput uo = new UserOutput(user);

        return uo;

    }

    @PutMapping("/users")
    @Transactional
    public UserOutput updateUser(@RequestBody User user) {

        userService.saveUpdate(user);

        UserOutput uo = new UserOutput(user);

        return uo;
    }

    
    @DeleteMapping("users/{userId}")
    @Transactional
    public String deteteUser(@PathVariable int userId) {

        UserOutput user = userService.findById(userId);

        if(user == null) {
            throw new RuntimeException("User id not found -"+userId);
        }

        userService.deleteById(userId);

        return "Deleted user id - "+userId;
    }

}