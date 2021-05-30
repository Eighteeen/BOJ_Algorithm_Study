import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] KDAInfo = br.readLine().split("/");
        int K = Integer.parseInt(KDAInfo[0]), D = Integer.parseInt(KDAInfo[1]), A = Integer.parseInt(KDAInfo[2]);
        
        String result = "gosu";
        if (D == 0 || K + A < D) result = "hasu";

        System.out.println(result);
        br.close();
    }
}