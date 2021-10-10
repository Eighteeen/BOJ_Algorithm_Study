import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//// 깔끔해요
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String[] tvInfo = br.readLine().split(" ");
        int D = Integer.parseInt(tvInfo[0]);
        int H = Integer.parseInt(tvInfo[1]);
        int W = Integer.parseInt(tvInfo[2]);

        double standardLength = Math.sqrt((double) D * D / (H * H + W * W));
        int height = (int) Math.floor(H * standardLength);
        int width = (int) Math.floor(W * standardLength);

        sb.append(height).append(" ").append(width);
        System.out.println(sb);
        br.close();
    }
}