package com.lyh.demo.entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @Author:linyuanhuang
 * @Description:
 * @Date:2017/12/13 14:51
 */
@Entity
public class GameScore {
    @Id
    private long id;
    private String name;
    private int score;
    private long userId;
    private Timestamp createTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }
}
