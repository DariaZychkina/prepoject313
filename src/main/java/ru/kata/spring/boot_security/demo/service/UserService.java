package ru.kata.spring.boot_security.demo.service;

import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.Collection;
import java.util.List;

public interface UserService {
    public boolean addUser(User user);
    public boolean deleteUser(Long id);
    public boolean updateUser(User user);
    @Query("from User u left join fetch u")
    public List<User> getUsersList();
    public User getById(Long id);
    public User getUserByName(String username);
}
