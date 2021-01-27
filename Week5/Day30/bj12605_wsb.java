import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;
import java.util.StringTokenizer;

//// 깔-끔 : 22굿
class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        Stack strStack = new Stack<>();

        for(int i = 1; i <= N; i++){
            st = new StringTokenizer(br.readLine());
            while(st.hasMoreTokens()){
                strStack.push(st.nextToken());
            }

            bw.write("Case #" + i + ": ");
            while(!strStack.empty()){
                bw.write(strStack.pop() + " ");
            }
            bw.write("\n");
        }

        bw.flush();
        br.close();
        bw.close();
    }
}
