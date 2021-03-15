import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] heightInfo = br.readLine().split(" ");
        int A = Integer.parseInt(heightInfo[0]), B = Integer.parseInt(heightInfo[1]), V = Integer.parseInt(heightInfo[2]);
        int heightExceptLastDay = V - A;
        int heightOfOneDay = A - B;
        
        int fewDays = heightExceptLastDay / heightOfOneDay;
        fewDays += (heightExceptLastDay % heightOfOneDay == 0 ? 1 : 2);

        System.out.println(fewDays);
        br.close();
    }
}