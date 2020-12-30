import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        int typoNum;

        for(int i = 0; i < T; i++){
            st = new StringTokenizer(br.readLine());
            typoNum = Integer.parseInt(st.nextToken());
            sb.append(new StringBuffer(st.nextToken()).delete(typoNum - 1, typoNum)).append("\n");
        }
        System.out.print(sb);
    }
}