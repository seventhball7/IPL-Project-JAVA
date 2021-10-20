
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class MainClass {
    static HashMap<String,Integer> map=new HashMap<>();
    static HashMap<String, Integer> winMap = new HashMap<>();
    public static void totalMatchesPlayedPerYear(String line){
        String[] value=line.split(",");
        if(map.containsKey(value[1])){
            map.put(value[1],map.get(value[1])+1);
        }else{
            map.put(value[1],1);
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
    public static void main(String[] args) throws FileNotFoundException {
        String matchesPath="/home/sameer/IdeaProjects/Practice/src/com/company/matches.csv";
        String deliveriesPath="/home/sameer/IdeaProjects/Practice/src/com/company/deliveries.csv";
        String matchesPerLine="";
        String deliveriesPerLine="";
        try{
            BufferedReader br=new BufferedReader(new FileReader(matchesPath));
            BufferedReader in=new BufferedReader(new FileReader(deliveriesPath));
            br.readLine();
            in.readLine();
            ArrayList<String> arrList=new ArrayList<>();
            while((matchesPerLine=br.readLine())!=null){
                totalMatchesPlayedPerYear(matchesPerLine);
                matchesWonAllTeamAllYear(matchesPerLine);

            }

        } catch(FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
       System.out.println(map);
        System.out.println(winMap);
    }
}
