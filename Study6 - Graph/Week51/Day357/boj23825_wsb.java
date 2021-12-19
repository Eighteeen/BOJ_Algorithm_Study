import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] blockInfo = br.readLine().split(" ");
        int blockS = Integer.parseInt(blockInfo[0]);
        int blockA = Integer.parseInt(blockInfo[1]);

        int result = Math.min(blockS, blockA) / 2;

        System.out.println(result);
        br.close();
    }
}