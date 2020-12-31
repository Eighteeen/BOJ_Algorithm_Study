import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        int repeatNum = N / 2;
        //// 맨 뒤에 추가 공백이 붙어도 정답처리 된다는 걸 이용해서 굉장히 깔끔하게 짜여졌네요! 배워갑니다
        String str1 = (N % 2 == 0 ? "* ".repeat(repeatNum) : "* ".repeat(repeatNum + 1)) + "\n";
        String str2 = (" *".repeat(repeatNum)) + "\n";

        for(int i = 0; i < N; i++){
            sb.append(str1 + str2);
        }
        System.out.print(sb);
    }
}