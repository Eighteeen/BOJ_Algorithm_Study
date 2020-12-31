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
        
        //// 군더더기 없는 깔-끔한 코드
        while(true){
            st = new StringTokenizer(br.readLine());
            num1 = Integer.parseInt(st.nextToken());
            num2 = Integer.parseInt(st.nextToken());
                //// 비교문 이렇게 짜는게 더 깔끔한거 같네ㅔ요 배워갑니다
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
