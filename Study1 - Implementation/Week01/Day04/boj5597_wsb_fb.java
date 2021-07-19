import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        boolean submit[] = new boolean[30];
        int len = 30;
        String strN;

        //// 입력은 28줄로 정해져있어서 while문을 사용하지 않아도 됐을 것 같아요!
        //// -> 문제상에서는 28명 입력이지만 전체학생과 제출학생 수가 수시로 달라질 수 있는 프로그램이라고 생각했을 때의 활용성을 고려하여 작성했습니다!
        //// -> 28명 입력은 보다 쉽게 구현할 수 있다고 생각하여 유지하겠습니다. 다른 의견의 피드백 감사해요
        while((strN = br.readLine()) != null){
            submit[Integer.parseInt(strN) - 1] = true;
        }
        for(int i = 0; i < len; i++){
            sb.append(!submit[i] ? i + 1 + " " : "");
        }
        System.out.print(sb);
    }
}