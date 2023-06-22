package com.wsleli.mapper;

import com.wsleli.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    User select(@Param("username") String username, @Param("password") String password);

    User selectByUsername(String s);

    void add(User user);

    List<User> selectAllUser();
}
