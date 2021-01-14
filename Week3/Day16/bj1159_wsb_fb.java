import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        //// 성의 첫 글자는 a~z 범위 안에 있으므로 HashMap이 아닌 int[26]로 첫 글자의 수를 구했다면 더 효율적이었을 것 같습니다. 이렇게 하면 정렬이 필요없거든요.
        //// -> 피드백 감사해요! 바꿔봤습니당
        //// HashMap사용은 처음봐요 한번 찾아봐야겠어요 다양한 기능이 많이 쓰였네요 ContainsKey기능 신기해요
        int firstLetterPlayers[] = new int[26];

        for(int i = 0; i < N; i++){
            firstLetterPlayers[br.readLine().charAt(0) - 'a']++;
        }
        for(int i = 0; i < firstLetterPlayers.length; i++){
            if(firstLetterPlayers[i] >= 5) sb.append((char)(i + 'a'));
        }

        System.out.print(sb.length() == 0 ? "PREDAJA" : sb);
    }
}
