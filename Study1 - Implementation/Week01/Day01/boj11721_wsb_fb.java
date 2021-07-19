import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String str = br.readLine();
        int strlen = str.length();
        int index = 0;

        while (index <= strlen){
            sb.append(str.substring(index, Math.min(index += 10, strlen))).append("\n");
        }
        System.out.print(sb);
    }
}