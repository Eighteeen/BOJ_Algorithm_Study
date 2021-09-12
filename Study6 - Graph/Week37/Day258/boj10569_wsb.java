import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            String[] polyhedronInfo = br.readLine().split(" ");
            int V = Integer.parseInt(polyhedronInfo[0]);
            int E = Integer.parseInt(polyhedronInfo[1]);

            sb.append(2 - (V - E)).append("\n");
        }

        System.out.print(sb);
        br.close();
    }
}
//// ㄲㄲ