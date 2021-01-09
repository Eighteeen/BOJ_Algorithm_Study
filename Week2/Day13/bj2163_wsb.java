import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String chocoInfo[] = br.readLine().split(" ");
        int N = Integer.parseInt(chocoInfo[0]);
        int M = Integer.parseInt(chocoInfo[1]);

        System.out.print(N * M - 1);
    }
}