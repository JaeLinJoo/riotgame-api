package org.ajou.realcoading.riotgameapi.riotgameapi.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Summoner {

    private int profileIconId;
    @Id private String name;
    private String puuid;
    private long summonerLevel;
    private String id;
    private String accountId;
    private long revisionDate;

}
