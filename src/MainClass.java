


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainClass {
    static HashMap<String, Integer> map = new HashMap<>();
    static HashMap<String, Integer> winMap = new HashMap<>();
    static Map<String,Integer> runs=new HashMap<>();

    //static HashMap<String,HashMap<String,Integer>> map2=new HashMap<String,HashMap<String,Integer>>();
    public static void totalMatchesPlayedPerYear(String line) {
        String[] value = line.split(",");
        if (map.containsKey(value[1])) {
            map.put(value[1], map.get(value[1]) + 1);
        } else {
            map.put(value[1], 1);
        }
    }

    public static void matchesWonAllTeamAllYear(String value) {
        String[] newValue = value.split(",");
        if (newValue[10] != "") {
            if (winMap.containsKey(newValue[10])) {
                winMap.put(newValue[10], winMap.get(newValue[10]) + 1);
            } else {
                winMap.put(newValue[10], 1);
            }
        }
    }

    public static void extraRunsConceded(ArrayList<String> list, ArrayList<String> deliveryData) {

        String[]  listId=new String[list.size()];
        list.toArray(listId);

        for(int i = 0;i < deliveryData.size(); i++){
            String[] arr = deliveryData.get(i).split(",");
            //    int totalRuns=Integer.parseInt(arr[16]);
            if(list.contains(arr[0])){
                if(runs.containsKey(arr[3])){
                    runs.put(arr[3],runs.get(arr[3])+Integer.parseInt(arr[16]));
                }else{
                    runs.put(arr[3],Integer.parseInt(arr[16]));
                }
            }

        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        String matchesPath = "/home/sameer/IdeaProjects/Practice/src/com/company/matches.csv";
        String deliveriesPath = "/home/sameer/IdeaProjects/Practice/src/com/company/deliveries.csv";
        String matchesPerLine = "";
        String deliveriesPerLine = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(matchesPath));
            BufferedReader in = new BufferedReader(new FileReader(deliveriesPath));
            br.readLine();
            in.readLine();
            ArrayList<String> matchesid = new ArrayList<>();
            ArrayList<String> deliveryData=new ArrayList<>();
            while ((matchesPerLine = br.readLine()) != null) {
                totalMatchesPlayedPerYear(matchesPerLine);
                matchesWonAllTeamAllYear(matchesPerLine);
                if (matchesPerLine.split(",")[1].equals("2016")) {
                    matchesid.add(matchesPerLine.split(",")[0]);
                }

            }
            while ((deliveriesPerLine = in.readLine()) != null) {
                deliveryData.add(deliveriesPerLine);
            }
            extraRunsConceded(matchesid,deliveryData);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(map);
        System.out.println(winMap);
        System.out.println(runs);
    }
}

