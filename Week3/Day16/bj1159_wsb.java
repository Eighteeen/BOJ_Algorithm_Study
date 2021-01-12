import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        String firstChr;
        HashMap<String, Integer> firstChrMap = new HashMap<>();
        List<String> selectFirstChrList = new ArrayList<>();

        for(int i = 0; i < N; i++){
            firstChr = String.valueOf(br.readLine().charAt(0));
            if(firstChrMap.containsKey(firstChr)){
                firstChrMap.put(firstChr, firstChrMap.get(firstChr) + 1);
            }else{
                firstChrMap.put(firstChr, 1);
            }
        }

        for(String key : firstChrMap.keySet()){
            if(firstChrMap.get(key) >= 5) selectFirstChrList.add(key);
        }
        if(selectFirstChrList.size() == 0){
            System.out.print("PREDAJA");
            return;
        }
        Collections.sort(selectFirstChrList);

        for(String Chr : selectFirstChrList) sb.append(Chr);
        System.out.print(sb);
    }
}