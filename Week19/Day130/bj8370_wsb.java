import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] seatInfoArr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int businessSeats = seatInfoArr[0] * seatInfoArr[1];
        int economicSeats = seatInfoArr[2] * seatInfoArr[3];
        System.out.println(businessSeats + economicSeats);
        
        br.close();
    }
}