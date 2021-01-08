import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//// 저 이 문제 푸느라 퀵정렬, 병합정렬 별 삽질 다 하다가 1~10000만 들어온다는 힌트 보고 풀었는데..
//// 수가 1~10000로만 들어온다는걸 바로 캐치하셨네요 와우..
////깔끔하네요.
class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        int num[] = new int[10001];

        for(int i = 0; i < N; i++){
            num[Integer.parseInt(br.readLine())]++;
        }

        for(int i = 0; i < 10001; i++){
            sb.append((i + "\n").repeat(num[i]));
        }
        System.out.print(sb);
    }
}