import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

////깔끔 :22
class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        char numChr[] = br.readLine().toCharArray();
        
        int sum = 0;
        for(char c : numChr) sum += (c - '0');
        
        System.out.println(sum);
        br.close();
    }
}