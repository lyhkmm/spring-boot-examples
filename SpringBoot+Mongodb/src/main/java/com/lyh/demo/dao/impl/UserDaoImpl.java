package com.lyh.demo.dao.impl;

import com.lyh.demo.dao.UserDao;
import com.lyh.demo.entity.UserEntity;
import com.mongodb.WriteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

@Component
public class UserDaoImpl implements UserDao{
    @Autowired
    private MongoTemplate mongoTemplate;
    /**
      * @Author:linyuanhuang
      * @Description:创建对象
      * @Date:17:44 2017/12/8
      * @param user
    */
    @Override
    public void saveUser(UserEntity user) {
        mongoTemplate.save(user);
    }

    /**
      * @Author:linyuanhuang
      * @Description:根据用户名查询对象
      * @Date:17:44 2017/12/8
      * @param userName
    */
    @Override
    public UserEntity findUserByUserName(String userName) {
        Query query=new Query(Criteria.where("userName").is(userName));
        UserEntity user =  mongoTemplate.findOne(query , UserEntity.class);
        return user;
    }
    /**
      * @Author:linyuanhuang
      * @Description:更新对象
      * @Date:17:45 2017/12/8
      * @param user
    */
    @Override
    public int updateUser(UserEntity user) {
        Query query=new Query(Criteria.where("id").is(user.getId()));
        Update update= new Update().set("userName", user.getUserName()).set("passWord", user.getPassword());
        //更新结果集的第一条数据
        WriteResult result =mongoTemplate.updateFirst(query,update,UserEntity.class);
        //更新结果集的全部数据
        // mongoTemplate.updateMulti(query,update,UserEntity.class);
        if(result!=null)
            return result.getN();
        else
            return 0;
    }
    /**
      * @Author:linyuanhuang
      * @Description:删除对象
      * @Date:17:45 2017/12/8
      * @Param:id
    */
    @Override
    public void deleteUserById(Long id) {
        Query query=new Query(Criteria.where("id").is(id));
        mongoTemplate.remove(query,UserEntity.class);
    }
}
