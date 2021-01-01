import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
        ////깔끔하게 잘 짠거 같아요
class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        //// 크 boolean 배열로 푸는거 괜찮네요.
        boolean submit[] = new boolean[30];
        int len = 30;
        String strN;

        //// 입력은 28줄로 정해져있어서 while문을 사용하지 않아도 됐을 것 같아요!
        while((strN = br.readLine()) != null){
            submit[Integer.parseInt(strN) - 1] = true;
        }
        for(int i = 0; i < len; i++){
            sb.append(!submit[i] ? i + 1 + " " : "");
        }
        System.out.print(sb);
    }
}
