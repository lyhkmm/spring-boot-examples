package com.lyh.demo.mapper;

import com.lyh.demo.entity.UserEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserMapper {
    @Select("SELECT * FROM user")
    @Results({
            @Result(property = "userName", column = "userName")
    })
    List<UserEntity> getAll();
    @Insert("INSERT INTO user(id,userName,password) VALUES(#{id},#{userName},#{password})")
    void insert(UserEntity user);

    @Update("UPDATE user SET userName=#{userName} WHERE id =#{id}")
    void update(UserEntity user);

    @Delete("DELETE FROM user WHERE id =#{id}")
    void delete(Long id);
}
