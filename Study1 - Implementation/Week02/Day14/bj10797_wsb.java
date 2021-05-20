import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
        ////깔끔해요 따보오옹! :22 굳:33
class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int day = Integer.parseInt(br.readLine());
        String carInfo[] = br.readLine().split(" ");
        int cnt = 0;

        for(int i = 0; i < 5; i++){
            if(Integer.parseInt(carInfo[i]) == day) cnt++; 
        }

        System.out.print(cnt);
    }
}