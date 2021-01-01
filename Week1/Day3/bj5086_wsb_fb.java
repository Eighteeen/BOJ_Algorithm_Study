import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int num1, num2;
        
        while(true){
            st = new StringTokenizer(br.readLine());
            num1 = Integer.parseInt(st.nextToken());
            num2 = Integer.parseInt(st.nextToken());

            if(num1 == 0 && num2 == 0){
                break;
            }else if(num2 % num1 == 0){
                sb.append("factor\n");
            }else if(num1 % num2 == 0){
                sb.append("multiple\n");
            }else{
                sb.append("neither\n");
            }
        }
        System.out.print(sb);
    }
}
