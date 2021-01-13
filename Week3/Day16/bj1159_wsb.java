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
        //// 성의 첫 글자는 a~z 범위 안에 있으므로 HashMap이 아닌 int[26]로 첫 글자의 수를 구했다면 더 효율적이었을 것 같습니다. 이렇게 하면 정렬이 필요없거든요.
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