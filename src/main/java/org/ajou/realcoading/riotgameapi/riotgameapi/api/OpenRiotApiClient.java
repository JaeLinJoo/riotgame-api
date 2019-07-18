package org.ajou.realcoading.riotgameapi.riotgameapi.api;

import org.ajou.realcoading.riotgameapi.riotgameapi.domain.League;
import org.ajou.realcoading.riotgameapi.riotgameapi.domain.Summoner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class OpenRiotApiClient {
    private final String apikey = "RGAPI-07e2e8e1-d3f6-4261-84a7-fd7380076874";
    private final String openRiotSummonerNameUrl = "https://kr.api.riotgames.com/lol/summoner/v4/summoners/by-name/{summonerName}?api_key={api_key}";
    private final String openRiotEncryptedSummonerIdUrl = "https://kr.api.riotgames.com/lol/league/v4/entries/by-summoner/{encryptedSummonerId}?api_key={api_key}";

    private final ParameterizedTypeReference<List<League>> leagueType = new ParameterizedTypeReference<List<League>>() {};

    @Autowired
    RestTemplate restTemplate;

  public Summoner requestSummoner(String summonerName){
      return restTemplate.exchange(openRiotSummonerNameUrl,HttpMethod.GET,null,Summoner.class,summonerName,apikey)
              .getBody();
  }

    public List<League> requestLeague(String encryptedSummonerId){
      return restTemplate.exchange(openRiotEncryptedSummonerIdUrl,HttpMethod.GET,null,leagueType,encryptedSummonerId,apikey)
                .getBody();
    }
}
