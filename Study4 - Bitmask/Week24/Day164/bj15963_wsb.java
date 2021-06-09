import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] batteries = br.readLine().split(" ");
        System.out.println(batteries[0].equals(batteries[1]) ? 1 : 0);

        br.close();
    }
}