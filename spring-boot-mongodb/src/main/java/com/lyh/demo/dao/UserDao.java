package com.lyh.demo.dao;

import com.lyh.demo.entity.UserEntity;
/**
  * @Author:linyuanhuang
  * @Description:用户dao层
  * @Date:17:39 2017/12/8
*/
public interface UserDao {

    public void saveUser(UserEntity user);

    public UserEntity findUserByUserName(String userName);

    public int updateUser(UserEntity user);

    public void deleteUserById(Long id);

}