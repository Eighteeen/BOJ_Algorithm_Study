import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int cntSeries = 1;
        int titleNum = 666;

        while(cntSeries < N){
            titleNum++;
            if(String.valueOf(titleNum).contains("666")) cntSeries++;
        }
        
        System.out.println(titleNum);
        br.close();
    }
}