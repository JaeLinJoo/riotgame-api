package org.ajou.realcoading.riotgameapi.riotgameapi.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class League {

    private String queueType;
   @Id private String summonerName;
    private boolean hostStreak;
    private MiniSeriesDTO miniSeries;
    private int wins;
    private boolean veteran;
    private int losses;
    private String rank;
    private String leagueId;
    private boolean inactive;
    private boolean freshBlood;
    private String tier;
    private String summonerId;
    private int leaguePoints;

    @Data
    public static class MiniSeriesDTO{
        private String progress;
        private int losses;
        private int target;
        private int wins;
    }

}
