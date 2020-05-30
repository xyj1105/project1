package com.xyj.demo.dao;

import com.xyj.demo.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Integer> {

    public User findByUsername(String username);

    public User findByUsernameAndPassword(String username, String password);
}
