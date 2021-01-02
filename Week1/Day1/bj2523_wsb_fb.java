import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        //// 변수명을 한 글자로 지으니까 반복문에 사용되는 변수 같아요. lines 등으로 지어주면 더 가독성 좋을 것 같습니다. : 22와동시에 반성하고 갑니다..
        //// -> 짧은 별 찍기 문제라서 입력값 n을 사용했어요. 하지만 그래서 놓칠 수 있는 부분을 짚어주어 감사합니당
        int len = 2 * N;

        for(int i = 1; i < len; i++){
            sb.append("*".repeat(i <= N ? i : len - i)).append("\n");
        }
        System.out.print(sb);
    }
}