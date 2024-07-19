package com.kapasiya.springsecurity.services;

import com.kapasiya.springsecurity.entity.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService
{
    List<User> list = new ArrayList<User>();
    private UserService()
    {
        list.add(new User("Soyam Kapasiya","ppppl288r","soyamp232@gmail.com"));
        list.add(new User("Soyam Patel","992uvgq9y","soyampatel@gmail.com"));
        list.add(new User("Soyam Khan","3jn357f0","soyamkhan@gmail.com"));
        list.add(new User("Soyam Rajput","ncu846gak8","soyamraj@gmail.com"));
        list.add(new User("Soyam Das","hs629fj99g","soyamdas@gmail.com"));
    }


    public List<User> getAllUser()
    {
        return this.list;
    }

    public User getUserByUserName(String username)
    {
        return this.list.stream().filter(user -> user.getUsername().equals(username)).findFirst().orElse(null);
    }

    public void addUser(User user)
    {
        list.add(user);
    }

    public void deleteUserByUserName(String username)
    {
        list.removeIf(user -> user.getUsername().equals(username));
    }

    public List<User> updateUser(User user)
    {
        User userData = list.stream().filter(getUser -> getUser.getUsername().equals(user.getUsername())).findFirst().orElse(null);

        if(userData == null)
            return null;

        user.setUsername(userData.getUsername());
        user.setPassword(userData.getPassword());

        return list;
    }


}
