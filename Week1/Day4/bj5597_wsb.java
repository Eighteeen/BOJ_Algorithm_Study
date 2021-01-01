import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
        ////깔끔하게 잘 짠거 
class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        boolean submit[] = new boolean[30];
        int len = 30;
        String strN;

        while((strN = br.readLine()) != null){
            submit[Integer.parseInt(strN) - 1] = true;
        }
        for(int i = 0; i < len; i++){
            sb.append(!submit[i] ? i + 1 + " " : "");
        }
        System.out.print(sb);
    }
}
