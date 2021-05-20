import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//// 전체적으로 코드가 굉장히 깔끔한 것 같아요
class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());
        int m = 2 * n;

        for(int i = 1; i < m; i++){
            //// 이 부분은 가독성이 조금 떨어지는 것 같습니다 :22 저도 조금 이해하기 어려웠어요
            sb.append("*".repeat(i <= n ? i : m - i));
            sb.append(" ".repeat(i <= n ? m - 2 * i : 2 * (i - n)));
            sb.append("*".repeat(i <= n ? i : m - i)).append("\n");
        }
        System.out.print(sb);
    }
}
