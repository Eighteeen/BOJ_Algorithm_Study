import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int line = Integer.parseInt(br.readLine());
        int cntStrike, nowScore;

        for(int i = 0; i < line; i++){
            cntStrike = nowScore = 0;
            for(char ch : br.readLine().toCharArray()){
                if(ch == 'O') cntStrike++;
                else{
                    nowScore += sumOneToNum(cntStrike);
                    cntStrike = 0;
                }
            }
            nowScore += sumOneToNum(cntStrike);
            bw.write(nowScore + "\n");
        }

        bw.flush();
        br.close();
        bw.close();
    }

    static int sumOneToNum(int num){
        return num * (num + 1) / 2;
    }
}