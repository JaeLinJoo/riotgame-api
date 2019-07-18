package org.ajou.realcoading.riotgameapi.riotgameapi.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import org.ajou.realcoading.riotgameapi.riotgameapi.api.OpenRiotApiClient;
import org.ajou.realcoading.riotgameapi.riotgameapi.domain.League;
import org.ajou.realcoading.riotgameapi.riotgameapi.domain.Summoner;
import org.ajou.realcoading.riotgameapi.riotgameapi.repository.LeagueRepository;
import org.ajou.realcoading.riotgameapi.riotgameapi.repository.SummonerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

@Service
@Slf4j
public class RiotService {

    List<String> summonerNames = null;

    @Autowired
    OpenRiotApiClient openRiotApiClient;
    @Autowired
    ObjectMapper objectMapper;

    @PostConstruct
    public List<String> loadAvailableSummonerNamesFromFile() throws IOException{
        File file = new File("availableSummonerNames");
        return objectMapper.readValue(file,new TypeReference<List<String>>(){});
    }

    public List<String> getAvailableSummonerName(){
        return summonerNames;
    }


    public Summoner getSummonerBySummonerName(String summonerName){
        return summonerRepository.findSummoner(summonerName);
    }

    public League getLeagueByEncryptedSummonerId(String encryptedSummonerId){
        return leagueRepository.findLeague(encryptedSummonerId);
    }

    LinkedList<String> summonerNamesQueue = new LinkedList<>();

    @Autowired
    SummonerRepository summonerRepository;
    @Autowired
    LeagueRepository leagueRepository;

    @Scheduled(fixedDelay = 2000L)
    public void getSummoner() throws IOException{
        if(summonerNamesQueue.isEmpty()){
            summonerNamesQueue.addAll(loadAvailableSummonerNamesFromFile());
        }
        String summonerName = summonerNamesQueue.pop();
        summonerNamesQueue.addLast(summonerName);

        Summoner summoner = openRiotApiClient.requestSummoner(summonerName);
        summonerRepository.insertSummoner(summoner);
        log.info("Summoner has been inserted successfully. {}",summoner);

        String encryptedSummonerId = summoner.getId();

        List<League> encryptedSummoner = openRiotApiClient.requestLeague(encryptedSummonerId);
        leagueRepository.insertLeague(encryptedSummoner);
        log.info("League has been inserted successfully. {}",encryptedSummoner);

    }




}
