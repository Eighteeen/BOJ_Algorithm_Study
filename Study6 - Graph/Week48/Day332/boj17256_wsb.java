import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String[] aInfo = br.readLine().split(" ");
        int ax = Integer.parseInt(aInfo[0]);
        int ay = Integer.parseInt(aInfo[1]);
        int az = Integer.parseInt(aInfo[2]);

        String[] cInfo = br.readLine().split(" ");
        int cx = Integer.parseInt(cInfo[0]);
        int cy = Integer.parseInt(cInfo[1]);
        int cz = Integer.parseInt(cInfo[2]);

        System.out.printf("%d %d %d\n", cx - az, cy / ay, cz - ax);
        br.close();
    }
}