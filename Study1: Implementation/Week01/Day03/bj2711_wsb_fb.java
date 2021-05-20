import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        ////다른 변수들은 다 소문자로 하셨는데 이거는 한글자고 입력하는 코드라서 T 이렇게 표현하셨는지 궁금합니다.
        //// 문제에서 'T'라고 명명된거라서 적절히 지어진거 같다고 생각해요 전!
        //// -> 문제 상에서 T라고 쓰여 있어서 그대로 사용했습니다
        int T = Integer.parseInt(br.readLine());
        //// 오타를 의미하는 'typo'를 사용한 변수 작명 좋네요!
        //// 근데 Num보다는 Index가 좀 더 정확했을 것 같아요. 오타의 위치를 나타내는거니까요
        //// -> Num보다는 Index! 좋은 지적 감사해요
        int typoIndex;

        for(int i = 0; i < T; i++){
            st = new StringTokenizer(br.readLine());
            typoIndex = Integer.parseInt(st.nextToken());
            sb.append(new StringBuffer(st.nextToken()).delete(typoIndex - 1, typoIndex)).append("\n");
        }
        System.out.print(sb);
    }
}