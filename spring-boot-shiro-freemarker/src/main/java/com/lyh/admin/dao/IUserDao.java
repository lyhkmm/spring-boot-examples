package com.lyh.admin.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.lyh.admin.dao.support.IBaseDao;
import com.lyh.admin.entity.User;

@Repository
public interface IUserDao extends IBaseDao<User, Integer> {

    User findByUserName(String username);

    Page<User> findAllByNickNameContaining(String searchText, Pageable pageable);

}
