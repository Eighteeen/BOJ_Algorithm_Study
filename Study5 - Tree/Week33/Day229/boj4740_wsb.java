import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String input;
        while (!(input = br.readLine()).equals("***")) {
            sb.append(new StringBuilder(input).reverse()).append("\n");
        }

        System.out.print(sb);
        br.close();
    }
}