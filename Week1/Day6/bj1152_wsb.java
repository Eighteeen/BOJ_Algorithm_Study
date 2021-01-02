import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine().trim();
        int len = str.length();
        int cnt = 0;

        for(int i = 0; i < len; i++){
            if(str.charAt(i) == ' '){
                cnt++;
            }
        }
        System.out.print(str.equals("") ? 0 : ++cnt);
    }
}