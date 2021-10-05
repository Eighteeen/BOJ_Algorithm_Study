import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//// 깔끔
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());

        int inputNum;
        while ((inputNum = Integer.parseInt(br.readLine())) != 0) {
            boolean isMultiple = inputNum % n == 0;
            sb.append(inputNum)
                .append(" is");
            if (!isMultiple) sb.append(" NOT");
            sb.append(" a multiple of ")
                .append(n)
                .append(".\n");
        }
        
        System.out.print(sb);
        br.close();
    }
}