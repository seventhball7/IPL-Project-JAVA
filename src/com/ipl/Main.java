package com.ipl;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

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
    private static final int EXTRA_RUNS = 16;
    private static final int TOTAL_RUNS = 17;

    public static void main(String[] args) throws IOException {
        List<Matches> matches=getMatchesData();
        List<Delivery> delivery=getDeliveryData();

        totalMatchesPlayedPerYear(matches);
        matchesWonAllTeamAllYear(matches);
        extraRunsConceded(matches,delivery);
        economicaLBowlersof2015(matches,delivery);
        runsMadeByBatsmenIn2016(matches,delivery);
        teamsWonTossAndMatchin2016(matches);
        playerOftheMatchin2010(matches);
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
                match.setMatchId(Integer.parseInt(data[MATCH_ID_DELIVERY]));
                match.setSeason(Integer.parseInt(data[MATCH_SEASON]));
                match.setCity(data[MATCH_CITY]);
                match.setToss_winner(data[MATCH_TOSS_WINNER]);
                match.setWinner(data[MATCH_WINNER]);
                match.setPlayerOfTheMatch(data[MATCH_PLAYER_OF_MATCH]);
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
                delivery.setExtraRuns(Integer.parseInt(data[EXTRA_RUNS]));
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

    private static void extraRunsConceded(List<Matches> matchesData, List<Delivery> deliveryData) {
        Map<String, Integer> extraRunsin2016 = new HashMap<>();
        for(Matches match:matchesData){
            if(match.getSeason()==2016){
                for(Delivery delivery:deliveryData){
                    if(delivery.getMatchId()==match.getMatchId()){
                        String s=delivery.getBowlingTeam();
                        int runs=delivery.getExtraRuns();
                        if (extraRunsin2016.containsKey(s)){
                            extraRunsin2016.put(s, extraRunsin2016.get(s) + runs);
                        } else {
                            extraRunsin2016.put(s,runs);
                        }
                    }
                }
            }
        }
        System.out.println("3. For the year 2016 the extra runs conceded per team: \n" + extraRunsin2016);
    }

   private static void economicaLBowlersof2015(List<Matches> matchData, List<Delivery> deliveryData) {
        Map<String, Integer> bowlersWithRuns = new HashMap<>();
        Map<String, Integer> bowlersWithBowl = new HashMap<>();
        Map<String, Float> economyBowlersin2015 = new HashMap<>();
        for(Matches match:matchData) {
            if (match.getSeason()==2015) {
                for(Delivery delivery:deliveryData){
                    if(delivery.getMatchId()==match.getMatchId()){
                        String bowler=delivery.getBowler();
                        int total_runs=delivery.getTotalRuns();
                        if (bowlersWithRuns.containsKey(bowler)) {
                            bowlersWithRuns.put(bowler, bowlersWithRuns.get(bowler) + total_runs);
                            bowlersWithBowl.put(bowler, bowlersWithBowl.get(bowler) + 1);
                        } else {
                            bowlersWithRuns.put(bowler,total_runs);
                            bowlersWithBowl.put(bowler, 1);
                        }
                        economyBowlersin2015.put(bowler, (bowlersWithRuns.get(bowler) * 6f) / bowlersWithBowl.get(bowler));
                    }
                }
            }
        }
        Set<Map.Entry<String, Float>> entrySet = economyBowlersin2015.entrySet();
        List<Map.Entry<String, Float>> sortingEconomyBowlers = new ArrayList<>(entrySet);
        Collections.sort(sortingEconomyBowlers, new Comparator<Map.Entry<String, Float>>() {
            @Override
            public int compare(Map.Entry<String, Float> o1, Map.Entry<String, Float> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
        });
        System.out.println("4. For the year 2015 the top economical bowlers \n" + sortingEconomyBowlers);
    }

    private static void runsMadeByBatsmenIn2016(List<Matches> matchdata,List<Delivery> deliveryData) {
        Map<String, Integer> RunsByBatsmenIn2016 = new HashMap<>();
        for (Matches match:matchdata) {
            if(match.getSeason()==2016){
                for(Delivery delivery:deliveryData){
                    if (delivery.getMatchId()==match.getMatchId()) {
                        String batsman=delivery.getBatsman();
                        int runs=delivery.getBatsmanRuns();
                        if (RunsByBatsmenIn2016.containsKey(batsman)) {
                            RunsByBatsmenIn2016.put(batsman, RunsByBatsmenIn2016.get(batsman) + runs);
                        } else {
                            RunsByBatsmenIn2016.put(batsman, runs);
                        }
                    }
                }
            }
        }
        System.out.println("5. Runs made by batsmen in 2016: \n" + RunsByBatsmenIn2016);
    }
   private static void teamsWonTossAndMatchin2016(List<Matches> matchesData) {
        ArrayList<String> teamsWonTossAndMatchin2016 = new ArrayList<>();
        for (Matches match:matchesData) {
            int season=match.getSeason();
            if (season ==2016){
                String tossWinner=match.getToss_winner();
                String matchWinner= match.getWinner();
                if (tossWinner.equals(matchWinner)) {
                    teamsWonTossAndMatchin2016.add(tossWinner);
                }
            }
        }
       System.out.println("6. Teams who won the toss and match in 2016: \n" + teamsWonTossAndMatchin2016);
    }
  private  static void playerOftheMatchin2010(List<Matches> matchesData) {
   ArrayList<String> playerOftheMatchin2010 = new ArrayList<>();
        for (Matches match:matchesData) {
            int season=match.getSeason();
            if (season==2016) {
                String player=match.getPlayerOfTheMatch();
                playerOftheMatchin2010.add(player);
            }
        }
      System.out.println("7. Player of the match in year 2010: \n" + playerOftheMatchin2010);
    }
}


