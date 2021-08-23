import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String[] numStrArr = br.readLine().split(" ");
        int A = Integer.parseInt(numStrArr[0]);
        int B = Integer.parseInt(numStrArr[1]);
        int C = Integer.parseInt(numStrArr[2]);

        int x = (A + B) % C;
        int y = (A * B) % C;

        sb.append(x).append("\n")
            .append(x).append("\n")
            .append(y).append("\n")
            .append(y).append("\n");
            
        System.out.print(sb);
        br.close();
    }
}