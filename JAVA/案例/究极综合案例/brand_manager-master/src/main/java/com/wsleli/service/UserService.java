package com.wsleli.service;

import com.wsleli.mapper.BrandMapper;
import com.wsleli.mapper.UserMapper;
import com.wsleli.pojo.User;
import com.wsleli.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class UserService {
    SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();

    public User login(User user) {
        SqlSession sqlSession = factory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        User u = mapper.select(user.getUsername(), user.getPassword());

        sqlSession.close();

        return u;
    }

    public boolean register(User user) {
        SqlSession sqlSession = factory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        User u = mapper.selectByUsername(user.getUsername());

        if (u == null) {
            mapper.add(user);
            sqlSession.commit();
        }

        sqlSession.close();

        return u == null;
    }

    public int userStatus(String username) {
        SqlSession sqlSession = factory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        User u = mapper.selectByUsername(username);

        sqlSession.close();

        if (u == null && username != null) return 1;

        return 0;
    }

    public List<User> selectAllUser() {
        SqlSession sqlSession = factory.openSession();
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        List<User> users = mapper.selectAllUser();

        System.out.println(users);

        sqlSession.close();

        return users;
    }

    public boolean UPIsEmpty(User user) {
        return "".equals(user.getUsername()) || "".equals(user.getPassword());
    }
}
