import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] albumInfo = br.readLine().split(" ");
        int A = Integer.parseInt(albumInfo[0]);
        int I = Integer.parseInt(albumInfo[1]);

        System.out.println((I - 1) * A + 1);
        br.close();
    }
}