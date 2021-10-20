
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class MainClass {
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
//                totalMatchesPlayedPerYear(matchesPerLine);
//                matchesWonAllTeamAllYear(matchesPerLine);
//                if(matchesPerLine.split(",")[1].equals("2016")) {
//                    arrList.add(matchesPerLine.split(",")[0]);
//                }
                System.out.println(matchesPerLine);
            }
            System.out.println(arrList);
        } catch(FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        System.out.println(map);
//        System.out.println(winMap);
    }
}
