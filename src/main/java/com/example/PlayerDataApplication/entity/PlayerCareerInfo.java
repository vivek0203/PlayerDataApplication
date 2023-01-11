package com.example.PlayerDataApplication.entity;



public class PlayerCareerInfo {
    private final Integer id;
    private final String playerName;
    private final String playerSpecification;
    private final Integer playerTotalMatches;
    private final Integer playerCenturies;
    private final Integer playerHalfCenturies;
    private final Integer playerFiveWickets;
    private final Integer playerTotalHatricks;
    private final Integer totalODIMatches;
    private final Integer totalTestMatches;
    private final Integer totalT20iMatches;
    private final Integer totalOdiWickets;
    private final Integer totalT20iWickets;
    private final Integer totalTestWickets;
    private final Integer playerOdiRuns;
    private final Integer playerT20iRuns;
    private final Integer playerTestRuns;

    public PlayerCareerInfo(Integer id, String playerName, String playerSpecification, Integer playerTotalMatches, Integer playerCenturies, Integer playerHalfCenturies, Integer playerFiveWickets, Integer playerTotalHatricks, Integer totalODIMatches, Integer totalTestMatches, Integer totalT20iMatches, Integer totalOdiWickets, Integer totalT20iWickets, Integer totalTestWickets, Integer playerOdiRuns, Integer playerT20iRuns, Integer playerTestRuns) {
        this.id = id;
        this.playerName = playerName;
        this.playerSpecification = playerSpecification;
        this.playerTotalMatches = playerTotalMatches;
        this.playerCenturies = playerCenturies;
        this.playerHalfCenturies = playerHalfCenturies;
        this.playerFiveWickets = playerFiveWickets;
        this.playerTotalHatricks = playerTotalHatricks;
        this.totalODIMatches = totalODIMatches;
        this.totalTestMatches = totalTestMatches;
        this.totalT20iMatches = totalT20iMatches;
        this.totalOdiWickets = totalOdiWickets;
        this.totalT20iWickets = totalT20iWickets;
        this.totalTestWickets = totalTestWickets;
        this.playerOdiRuns = playerOdiRuns;
        this.playerT20iRuns = playerT20iRuns;
        this.playerTestRuns = playerTestRuns;
    }

    @Override
    public String toString() {
        return "PlayerCareerInfo{" +
                "id=" + id +
                ", playerName='" + playerName + '\'' +
                ", playerSpecification='" + playerSpecification + '\'' +
                ", playerTotalMatches=" + playerTotalMatches +
                ", playerCenturies=" + playerCenturies +
                ", playerHalfCenturies=" + playerHalfCenturies +
                ", playerFiveWickets=" + playerFiveWickets +
                ", playerTotalHatricks=" + playerTotalHatricks +
                ", totalODIMatches=" + totalODIMatches +
                ", totalTestMatches=" + totalTestMatches +
                ", totalT20iMatches=" + totalT20iMatches +
                ", totalOdiWickets=" + totalOdiWickets +
                ", totalT20iWickets=" + totalT20iWickets +
                ", totalTestWickets=" + totalTestWickets +
                ", playerOdiRuns=" + playerOdiRuns +
                ", playerT20iRuns=" + playerT20iRuns +
                ", playerTestRuns=" + playerTestRuns +
                '}';
    }
}



