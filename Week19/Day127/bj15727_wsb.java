import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int L = Integer.parseInt(br.readLine());
        int findTime = L / 5;
        if (L % 5 != 0) findTime++;

        System.out.println(findTime);
        br.close();
    }
}