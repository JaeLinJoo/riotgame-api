package org.ajou.realcoading.riotgameapi.riotgameapi.controller;

import org.ajou.realcoading.riotgameapi.riotgameapi.domain.League;
import org.ajou.realcoading.riotgameapi.riotgameapi.domain.Summoner;
import org.ajou.realcoading.riotgameapi.riotgameapi.service.RiotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class RiotController {

    @Autowired
    RiotService riotService;

    @GetMapping("/lol/summoner/v4/summoners/by-name")
    public List<String> getAvailableNames() throws IOException {
        return riotService.getAvailableSummonerName();
    }

    @GetMapping("/lol/summoner/v4/summoners/by-name/{summonerName}")
    public Summoner getSummoner(@PathVariable String summonerName){
        return riotService.getSummonerBySummonerName(summonerName);
    }

    @GetMapping("/lol/league/v4/entries/by-summoner/{encryptedSummonerId}")
    public League getLeague(@PathVariable String encryptedSummonerID){
        return riotService.getLeagueByEncryptedSummonerId(encryptedSummonerID);
    }

}
