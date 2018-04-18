package com.lyh.demo.repository;

import com.lyh.demo.entity.GameRank;
import com.lyh.demo.entity.GameScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @Author:linyuanhuang
 * @Description:
 * @Date:2017/12/13 15:19
 */
public interface GameScoreRepository extends JpaRepository<GameScore,Long>{
    @Query("select u.userName as userName,gs.name as name,gs.score as score from GameScore gs,User u where gs.userId=u.id and gs.name=?1")
    List<GameRank> findRankByName(String name);
}
