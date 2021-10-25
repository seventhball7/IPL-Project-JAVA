package com.ipl;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main {
    private static final int MATCH_ID = 0;
    private static final int MATCH_SEASON = 1;
    private static final int MATCH_CITY = 2;
    private static final int MATCH_TEAM1 = 4;
    private static final int MATCH_TEAM2 = 5;
    private static final int MATCH_TOSS_WINNER = 6;
    private static final int MATCH_WINNER = 10;
    private static final int MATCH_PLAYER_OF_MATCH = 13;

    private static final int MATCH_ID_DELIVERY = 0;
    private static final int BATTING_TEAM = 2;
    private static final int BOWLING_TEAM = 3;
    private static final int OVER = 4;
    private static final int BALL = 5;
    private static final int BATSMAN = 6;
    private static final int BOWLER = 8;
    private static final int BATSMAN_RUNS = 15;
    private static final int TOTAL_RUNS = 17;

    public static void main(String[] args) throws IOException {
        List<Matches> matches=getMatchesData();
        List<Delivery> delivery=getDeliveryData();

        totalMatchesPlayedPerYear(matches);
        matchesWonAllTeamAllYear(matches);
    }

    private static List<Matches> getMatchesData() throws IOException {
        List<Matches> matchesData = new ArrayList<>();
        try{
            String matchesPath = "/home/sameer/IdeaProjects/IPL-JAVA/src/matches.csv";
            String matchesDataPerLine;
            BufferedReader br = new BufferedReader(new FileReader(matchesPath));
            br.readLine();
            while ((matchesDataPerLine = br.readLine()) != null) {
                Matches match=new Matches();
                String[] data=matchesDataPerLine.split(",");
                match.setMatchId(Integer.parseInt(data[MATCH_ID]));
                match.setSeason(Integer.parseInt(data[MATCH_SEASON]));
                match.setCity(data[MATCH_CITY]);
                match.setToss_winner(data[MATCH_TOSS_WINNER]);
                match.setWinner(data[MATCH_WINNER]);
                matchesData.add(match);
            }

        }
        catch (FileNotFoundException e){
          e.printStackTrace();
        }
     return matchesData;
    }

    private static List<Delivery> getDeliveryData() throws IOException {
        ArrayList<Delivery> deliveryData = new ArrayList<>();
        try{
            String deliveryPath = "/home/sameer/IdeaProjects/IPL-JAVA/src/deliveries.csv";
            String deliveryDataPerLine = "";
            BufferedReader br = new BufferedReader(new FileReader(deliveryPath));
            br.readLine();
            while ((deliveryDataPerLine = br.readLine()) != null) {
                Delivery delivery=new Delivery();
                String[] data=deliveryDataPerLine.split(",");
                delivery.setMatchId(Integer.parseInt(data[MATCH_ID]));
                delivery.setBattingTeam(data[BATTING_TEAM]);
                delivery.setBowlingTeam(data[BOWLING_TEAM]);
                delivery.setOver(Integer.parseInt(data[OVER]));
                delivery.setBall(Integer.parseInt(data[BALL]));
                delivery.setBatsman(data[BATSMAN]);
                delivery.setBowler(data[BOWLER]);
                delivery.setBatsmanRuns(Integer.parseInt(data[BATSMAN_RUNS]));
                delivery.setTotalRuns(Integer.parseInt(data[TOTAL_RUNS]));

                deliveryData.add(delivery);

            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return deliveryData;
    }

   private static void totalMatchesPlayedPerYear(List<Matches> matchData) {
        HashMap<Integer, Integer> totalMatchesPerYear = new HashMap<>();
        for(Matches match:matchData){
            int a=match.getSeason();
           if(totalMatchesPerYear.containsKey(a)){
               totalMatchesPerYear.put(a,totalMatchesPerYear.get(a)+1);
           }else{
               totalMatchesPerYear.put(a,1);
           }
        }
       System.out.println("1. matches played per year of all the years in IPL: \n" + totalMatchesPerYear);
    }

    private static void matchesWonAllTeamAllYear(List<Matches> matchData) {
         HashMap<String, Integer> matchesWonAllTeamAllYear = new HashMap<>();
        for(Matches match:matchData){
            String a=match.getWinner();
            if (a != "") {
                if (matchesWonAllTeamAllYear.containsKey(a)) {
                    matchesWonAllTeamAllYear.put(a, matchesWonAllTeamAllYear.get(a) + 1);
                } else {
                    matchesWonAllTeamAllYear.put(a, 1);
                }
            }
        }
        System.out.println("2. Number of matches won of all teams over all the years of IPL: \n" + matchesWonAllTeamAllYear);
    }
}


