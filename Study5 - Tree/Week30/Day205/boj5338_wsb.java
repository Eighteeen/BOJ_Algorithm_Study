import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        sb.append("       _.-;;-._").append("\n")
            .append("'-..-'|   ||   |").append("\n")
            .append("'-..-'|_.-;;-._|").append("\n")
            .append("'-..-'|   ||   |").append("\n")
            .append("'-..-'|_.-''-._|").append("\n");

        System.out.print(sb);
        br.close();
    }
}