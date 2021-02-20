import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.print(printFlagStar(Integer.parseInt(br.readLine()), null));

        br.close();
    }

    static StringBuilder printFlagStar(int n, List<StringBuilder> untilFlag){ // When calling this method, use null for List parameter 
        if(n < 0){
            StringBuilder resultFlag = new StringBuilder();
            untilFlag.forEach(sb -> resultFlag.append(sb).append("\n"));
            return resultFlag;
        }

        List<StringBuilder> currentFlag = new ArrayList<>();
        if(untilFlag == null) currentFlag.add(new StringBuilder("*"));
        else{
            int len = untilFlag.size();
            for(int i = 0; i < len; i++){
                StringBuilder line = new StringBuilder(untilFlag.get(i));
                currentFlag.add(line.append(" ".repeat(i)).append(untilFlag.get(i)));
            }
            currentFlag.addAll(untilFlag);
        }

        return printFlagStar(--n, currentFlag);
    }
}