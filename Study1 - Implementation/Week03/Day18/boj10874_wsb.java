import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());

        //// 아..! 굳이 int[]로 바꿔줄 필요 없이 문자열로 비교해도 되구나.. 배워갑니다 :22 와우 이건 진짜 깔끔하게 풀 수 있는거였네요
        for(int i = 1; i <= N; i++){
            if(br.readLine().equals("1 2 3 4 5 1 2 3 4 5")) sb.append(i + "\n");
        }

        System.out.print(sb);
    }
}
