import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int R = Integer.parseInt(br.readLine());
        double RSquared = Math.pow(R, 2);

        sb.append(String.format("%.6f", RSquared * Math.PI))
            .append("\n")
            .append(String.format("%.6f", RSquared * 2));

        System.out.println(sb);
        br.close();
    }
}