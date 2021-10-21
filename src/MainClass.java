
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class MainClass {
    static HashMap<String, Integer> map = new HashMap<>();
    static HashMap<String, Integer> winMap = new HashMap<>();
    static Map<String,Integer> runs=new HashMap<>();
    static Map<String,Float> economyBowlers=new HashMap<>();
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
    static void economicaLBowlersof2015(List<String > list, List<String>deliveryData){
        Map<String,Integer> bowlers=new HashMap<>();
        Map<String,Integer> bowl=new HashMap<>();
        Map<String,Integer> wideNo=new HashMap<>();
        for(int i = 0;i < deliveryData.size(); i++) {
            String[] arr = deliveryData.get(i).split(",");
            if(list.contains(arr[0])) {
                if (bowlers.containsKey(arr[8])){
                    bowlers.put(arr[8], bowlers.get(arr[8]) + Integer.parseInt(arr[17]));
                    bowl.put(arr[8],bowl.get(arr[8])+1);
                }
                else{
                    bowlers.put(arr[8], Integer.parseInt(arr[17]));
                    bowl.put(arr[8],1);
                }
                economyBowlers.put(arr[8],(bowlers.get(arr[8])*6f)/bowl.get(arr[8]));
            }
        }
        Set<Map.Entry<String,Float>> entrySet=economyBowlers.entrySet();
        List<Map.Entry<String,Float>> list1=new ArrayList<>(entrySet);
        Collections.sort(list1, new Comparator<Map.Entry<String, Float>>() {
            @Override
            public int compare(Map.Entry<String, Float> o1, Map.Entry<String, Float> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
        });
        HashMap<String,Float> eBowler=new HashMap<>();
        int c=0;
        list1.forEach(s->{
            eBowler.put(s.getKey(),s.getValue());
        });
//         System.out.println(eBowler);
        System.out.println(list1);

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
            ArrayList<String> matchesidof2015 = new ArrayList<>();
            ArrayList<String> deliveryData=new ArrayList<>();
            while ((matchesPerLine = br.readLine()) != null) {
                totalMatchesPlayedPerYear(matchesPerLine);
                matchesWonAllTeamAllYear(matchesPerLine);
                if (matchesPerLine.split(",")[1].equals("2016")) {
                    matchesid.add(matchesPerLine.split(",")[0]);
                }
                if (matchesPerLine.split(",")[1].equals("2015")) {
                    matchesidof2015.add(matchesPerLine.split(",")[0]);
                }

            }
            while ((deliveriesPerLine = in.readLine()) != null) {
                deliveryData.add(deliveriesPerLine);
            }
            extraRunsConceded(matchesid,deliveryData);
            economicaLBowlersof2015(matchesidof2015,deliveryData);
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

