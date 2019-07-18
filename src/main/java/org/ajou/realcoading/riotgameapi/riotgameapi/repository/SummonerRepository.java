package org.ajou.realcoading.riotgameapi.riotgameapi.repository;

import org.ajou.realcoading.riotgameapi.riotgameapi.domain.Summoner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class SummonerRepository {

    @Autowired
    MongoTemplate mongoTemplate;

    public Summoner insertSummoner(Summoner summonerName){
        return mongoTemplate.save(summonerName);
    }

    public Summoner findSummoner(String summonerName){
        Query query = Query.query(Criteria.where("name").is(summonerName));
        return mongoTemplate.findOne(query,Summoner.class);
    }
}
