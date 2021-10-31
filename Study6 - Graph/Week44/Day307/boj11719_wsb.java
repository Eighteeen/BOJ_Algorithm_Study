import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//// 깔끔
class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        String input;
        while ((input = br.readLine()) != null) {
            sb.append(input).append("\n");
        }

        System.out.print(sb);
        br.close();
    }
}