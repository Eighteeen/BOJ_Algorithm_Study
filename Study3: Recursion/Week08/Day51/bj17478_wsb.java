import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//// 깔꼼쓰합니다!
class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        
        System.out.print(whatIsRecursion(N, 0, sb));
        br.close();
    }

    static StringBuilder whatIsRecursion(int n, int cnt, StringBuilder sb){
        if(n < 0) return new StringBuilder();
        if(cnt == 0) sb.append("어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다.\n");
        //// 변수명이 원어민스럽군요
        String underscore = "____".repeat(cnt);

        sb.append(underscore).append("\"재귀함수가 뭔가요?\"\n");

        if(n == 0){
            sb.append(underscore).append("\"재귀함수는 자기 자신을 호출하는 함수라네\"\n");
        }else{
            sb.append(underscore).append("\"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.\n")
                .append(underscore).append("마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.\n")
                .append(underscore).append("그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어.\"\n");
        }

        whatIsRecursion(--n, ++cnt, sb);
        sb.append(underscore).append("라고 답변하였지.\n");
        
        return sb;
    }
}