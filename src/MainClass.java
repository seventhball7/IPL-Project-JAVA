
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class MainClass {
    static HashMap<String, Integer> totalMatchesPerYear = new HashMap<>();
    static HashMap<String, Integer> matchesWonAllTeamAllYear = new HashMap<>();
    static Map<String, Integer> extraRunsin2016 = new HashMap<>();
    static Map<String, Float> economyBowlersin2015 = new HashMap<>();
    static Map<String, Integer> RunsByBatsmenIn2016 = new HashMap<>();
    static ArrayList<String> teamsWonTossAndMatchin2016=new ArrayList<>();
    static ArrayList<String>  playerOftheMatchin2010=new ArrayList<>();
    static Map<String, Integer> matchesWonInBangalorePerTeam=new HashMap<>();
    static int totalSixesByDhoni;
    static Map<String,Float> strikeRateOfKohli=new HashMap<>();

    static void totalMatchesPlayedPerYear(String line) {
        String[] value = line.split(",");
        if (totalMatchesPerYear.containsKey(value[1])) {
            totalMatchesPerYear.put(value[1], totalMatchesPerYear.get(value[1]) + 1);
        } else {
            totalMatchesPerYear.put(value[1], 1);
        }
    }


     static void matchesWonAllTeamAllYear(String value) {
        String[] newValue = value.split(",");
        if (newValue[10] != "") {
            if (matchesWonAllTeamAllYear.containsKey(newValue[10])) {
                matchesWonAllTeamAllYear.put(newValue[10], matchesWonAllTeamAllYear.get(newValue[10]) + 1);
            } else {
                matchesWonAllTeamAllYear.put(newValue[10], 1);
            }
        }
    }


    static void extraRunsConceded(ArrayList<String> listIdof2016, ArrayList<String> deliveryData) {
        for (int i = 0; i < deliveryData.size(); i++) {
            String[] arr = deliveryData.get(i).split(",");
            if (listIdof2016.contains(arr[0])) {
                if (extraRunsin2016.containsKey(arr[3])) {
                    extraRunsin2016.put(arr[3], extraRunsin2016.get(arr[3]) + Integer.parseInt(arr[16]));
                } else {
                    extraRunsin2016.put(arr[3], Integer.parseInt(arr[16]));
                }
            }
        }
    }


    static void economicaLBowlersof2015(List<String> listIdof2015, List<String> deliveryData) {
        Map<String, Integer> bowlersWithRuns = new HashMap<>();
        Map<String, Integer> bowlersWithBowl = new HashMap<>();
        for (int i = 0; i < deliveryData.size(); i++) {
            String[] arr = deliveryData.get(i).split(",");
            if (listIdof2015.contains(arr[0])) {
                if (bowlersWithRuns.containsKey(arr[8])) {
                    bowlersWithRuns.put(arr[8], bowlersWithRuns.get(arr[8]) + Integer.parseInt(arr[17]));
                    bowlersWithBowl.put(arr[8], bowlersWithBowl.get(arr[8]) + 1);
                } else {
                    bowlersWithRuns.put(arr[8], Integer.parseInt(arr[17]));
                    bowlersWithBowl.put(arr[8], 1);
                }
                economyBowlersin2015.put(arr[8], (bowlersWithRuns.get(arr[8]) * 6f) / bowlersWithBowl.get(arr[8]));
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


    static void runsMadeByBatsmenIn2016(ArrayList<String> listIdof2016, ArrayList<String> deliveryData) {
        for (int i = 0; i < deliveryData.size(); i++) {
            String[] arr = deliveryData.get(i).split(",");
            if (listIdof2016.contains(arr[0])) {
                if (RunsByBatsmenIn2016 .containsKey(arr[6])) {
                    RunsByBatsmenIn2016.put(arr[6], RunsByBatsmenIn2016 .get(arr[6]) + Integer.parseInt(arr[15]));
                } else {
                    RunsByBatsmenIn2016 .put(arr[6], Integer.parseInt(arr[15]));
                }
            }
        }
    }

    static void teamsWonTossAndMatchin2016( ArrayList<String> matchesData){
        for(int i=0;i<matchesData.size();i++){
            String[] arr=matchesData.get(i).split(",");
              if(arr[1].equals("2016")){
                  if(arr[6].equals(arr[10])){
                      teamsWonTossAndMatchin2016.add(arr[6]);
                  }
              }
        }
    }

    static void  playerOftheMatchin2010(ArrayList<String> matchesData){
        for(int i=0;i<matchesData.size();i++){
            String[] arr=matchesData.get(i).split(",");
            if(arr[1].equals("2010")){
                playerOftheMatchin2010.add(arr[13]);
            }
        }
    }

 static void  matchesWonInBangalorePerTeam(ArrayList<String > matchesData){
        for(int i=0;i<matchesData.size();i++){
            String[] arr=matchesData.get(i).split(",");
            if(arr[2].equals("Bangalore")){
                if(matchesWonInBangalorePerTeam.containsKey(arr[10])){
                    matchesWonInBangalorePerTeam.put(arr[10],matchesWonInBangalorePerTeam.get(arr[10])+1);
                }
                else{
                    matchesWonInBangalorePerTeam.put(arr[10],1);
                }
            }
        }
 }

 static  void totalSixesByMsDhoni(ArrayList<String> deliveryData){
     for(int i=0;i<deliveryData.size();i++){
         String[] arr=deliveryData.get(i).split(",");
         if(arr[6].equals("MS Dhoni")){
             if(arr[15].equals("6")){
                 totalSixesByDhoni += 1;
             }
         }
     }
 }
static void strikeRateOfViratKohli(ArrayList<String> deliveryData){
        Map<String,Integer> totalRunsByKohli=new HashMap<>();
        Map<String,Integer> totalBallsByKohli=new HashMap<>();
        for(int i=0;i<deliveryData.size();i++){
            String[] arr=deliveryData.get(i).split(",");
            if(arr[6].equals("V Kohli")){
                if(totalBallsByKohli.containsKey(arr[6])){
                    totalBallsByKohli.put(arr[6],totalBallsByKohli.get(arr[6])+1);
                    totalRunsByKohli.put(arr[6],totalRunsByKohli.get(arr[15])+Integer.parseInt(arr[15]));
                }
                else{
                    totalBallsByKohli.put(arr[6],1);
                    totalRunsByKohli.put(arr[6],Integer.parseInt(arr[15]));
                }
                strikeRateOfKohli.put(arr[6],totalRunsByKohli.get(arr[15])*100/totalBallsByKohli.get(arr[6]));
            }


        }
}

    public static void main(String[] args) throws FileNotFoundException {
        String matchesPath = "/home/sameer/IdeaProjects/IPL-JAVA/src/matches.csv";
        String deliveriesPath = "/home/sameer/IdeaProjects/IPL-JAVA/src/deliveries.csv";
        String matchesDataPerLine = "";
        String deliveriesDataPerLine = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(matchesPath));
            BufferedReader in = new BufferedReader(new FileReader(deliveriesPath));
            br.readLine();
            in.readLine();
            ArrayList<String> matchesidOf2016 = new ArrayList<>();
            ArrayList<String> matchesidof2015 = new ArrayList<>();
            ArrayList<String> matchesData = new ArrayList<>();
            ArrayList<String> deliveryData = new ArrayList<>();
            while ((matchesDataPerLine = br.readLine()) != null) {
                matchesData.add(matchesDataPerLine);
                totalMatchesPlayedPerYear(matchesDataPerLine);
                matchesWonAllTeamAllYear(matchesDataPerLine);
                if (matchesDataPerLine.split(",")[1].equals("2016")) {
                    matchesidOf2016.add(matchesDataPerLine.split(",")[0]);
                }
                if (matchesDataPerLine.split(",")[1].equals("2015")) {
                    matchesidof2015.add(matchesDataPerLine.split(",")[0]);
                }
            }
            while ((deliveriesDataPerLine  = in.readLine()) != null) {
                deliveryData.add(deliveriesDataPerLine );
            }
            extraRunsConceded(matchesidOf2016, deliveryData);
            economicaLBowlersof2015(matchesidof2015, deliveryData);
            runsMadeByBatsmenIn2016(matchesidOf2016, deliveryData);
            teamsWonTossAndMatchin2016(matchesData);
            playerOftheMatchin2010(matchesData);
            matchesWonInBangalorePerTeam(matchesData);
            totalSixesByMsDhoni(deliveryData);
            strikeRateOfViratKohli(deliveryData);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("1. matches played per year of all the years in IPL: \n" + totalMatchesPerYear);
        System.out.println("2. Number of matches won of all teams over all the years of IPL: \n" + matchesWonAllTeamAllYear);
        System.out.println("3. For the year 2016 the extra runs conceded per team: \n" + extraRunsin2016);
        System.out.println("5. Runs made by batsmen in 2016: \n" + RunsByBatsmenIn2016);
        System.out.println("6. Teams who won the toss and match in 2016: \n"+ teamsWonTossAndMatchin2016);
        System.out.println("7. Player of the match in year 2010: \n"+  playerOftheMatchin2010);
        System.out.println("8. Matches won in Bangalore per team: \n"+ matchesWonInBangalorePerTeam);
        System.out.println("9. Total sixes by Dhoni in all Season: \n"+ totalSixesByDhoni);

    }
}

