import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());

        for(int i = 1; i <= N; i++){
            if(br.readLine().equals("1 2 3 4 5 1 2 3 4 5")) sb.append(i + "\n");
        }

        System.out.print(sb);
    }
}