package com.zlx.community.dao;

import com.zlx.community.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {

    User selectById(int id);
    User selectByUsername(String username);
    User selectByEmail(String email);

    int insertUser(User user);

    int updateStatusById(int id, int status);
    int updateHeaderUrlById(int id, String headerUrl);
    int updatePasswordById(int id, String password);

}
