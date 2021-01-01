import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String str;

        while(!(str = br.readLine()).equals("EOI")){
            sb.append(str.toUpperCase().contains("NEMO") ? "Found" : "Missing").append("\n");
        }
        System.out.print(sb);
    }
}