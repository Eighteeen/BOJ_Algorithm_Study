import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String str;

        while(!(str = br.readLine()).equals("END")){
            sb.append(new StringBuffer(str).reverse().toString()).append("\n");
        }
        System.out.print(sb);
    }
}