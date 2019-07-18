package org.ajou.realcoading.riotgameapi.riotgameapi.repository;

import org.ajou.realcoading.riotgameapi.riotgameapi.domain.League;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LeagueRepository {

    @Autowired
    MongoTemplate mongoTemplate;

    public void insertLeague(List<League> leagues){
        for(int i = 0 ; i < leagues.size(); i++) {
            mongoTemplate.save(leagues.toArray()[i]);
        }
    }

    public League findLeague(String encryptedSummonerId){
        Query query = Query.query(Criteria.where("summonerId").is(encryptedSummonerId));
        return mongoTemplate.findOne(query, League.class);
    }
}
