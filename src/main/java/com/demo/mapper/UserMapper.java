package com.demo.mapper;

import com.demo.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserMapper {

    User login(User user);

    int saveUser(User user);

    User userList(Integer id);
}
