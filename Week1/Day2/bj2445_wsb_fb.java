import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());
        int m = 2 * n;

        for(int i = 1; i < m; i++){
            //// 이 부분은 가독성이 조금 떨어지는 것 같습니다 :22 저도 조금 이해하기 어려웠어요
            //// -> 좋은 지적 감사해요
            if(i <= n){
                sb.append("*".repeat(i));
                sb.append(" ".repeat(m - 2 * i));
                sb.append("*".repeat(i)).append("\n");
            }else{
                sb.append("*".repeat(m - i));
                sb.append(" ".repeat(2 * (i - n)));
                sb.append("*".repeat(m - i)).append("\n");
            }
        }
        System.out.print(sb);
    }
}
