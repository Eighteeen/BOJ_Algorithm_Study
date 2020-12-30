import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//// 읽기 좋으면서 효율적인 것 같아요 굳
class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            int nowNum = Integer.parseInt(st.nextToken());
            if(nowNum < X){
                sb.append(nowNum).append(" ");
            }
        }
        System.out.print(sb);
    }
}