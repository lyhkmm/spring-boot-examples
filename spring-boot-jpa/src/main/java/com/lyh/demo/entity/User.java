package com.lyh.demo.entity;

import com.lyh.demo.entity.enums.Sex;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
@Entity
public class User implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    private long id;
    private String userName;
    private String password;
    private int age;
    private Sex sex;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                '}';
    }
}
