package com.boutiqaat.training.Online.Shop.Services;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.stereotype.Service;

import com.boutiqaat.training.Online.Shop.Entities.User;

@Service
public class UserService {

    public List<User> listUsers() {
    	
        List<User> users = new ArrayList<>();

        //create dummy users
        User a= User.builder().name("hawraa").surname("ateyah").email("haw@gmail").build();
        users.add(a);
        User b= User.builder().name("jamila").surname("ateyaat").email("Jam@gmail").build();
        users.add(b);
        User c= User.builder().name("sara").surname("atwah").email("sara@gmail").build();
        users.add(c);

        return users;
    }
}