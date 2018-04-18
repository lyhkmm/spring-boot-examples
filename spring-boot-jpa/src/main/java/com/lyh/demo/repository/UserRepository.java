package com.lyh.demo.repository;

import com.lyh.demo.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author:linyuanhuang
 * @Description:表User的仓库
 * @Date:2017/12/12 17:23
 */
public interface UserRepository extends JpaRepository<User,Long> {
    List<User> findByUserName(String userName);

    User findByUserNameAndPassword(String userName, String password);

    User findTopByOrderByAgeDesc();

    Page<User> findAll(Pageable pageable);

    Page<User> findByUserName(String userName,Pageable pageable);

    List<User> findFirst2ByAge(int age, Sort sort);

    List<User> findTop3ByAge(int age, Pageable pageable);

    Page<User> queryFirst3ByAge(int age, Pageable pageable);

    @Query("update User set userName=?2 where id=?1")
    @Transactional(timeout = 100)
    @Modifying()
    int updateUserNameById(long id,String userName);
}
