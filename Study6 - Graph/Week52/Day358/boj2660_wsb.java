import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    static final int MAX_SCORE = 250;

    static int memberSize;
    static int[][] memberScores;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        memberSize = Integer.parseInt(br.readLine());
        memberScores = new int[memberSize + 1][memberSize + 1];
        int[] memberFinalScores = new int[memberSize + 1];
        initMemberScores();

        while (true) {
            String[] friendsMemberInfo = br.readLine().split(" ");
            int member1 = Integer.parseInt(friendsMemberInfo[0]);
            int member2 = Integer.parseInt(friendsMemberInfo[1]);
            
            if (member1 == -1) break;
            setMemberFriendsScores(member1, member2);
        }

        setMemberScores();
        memberFinalScores[0] = MAX_SCORE;
        for (int i = 1; i <= memberSize; i++) {
            memberFinalScores[i] = Arrays.stream(memberScores[i]).max().getAsInt();
        }

        int candidateScore = Arrays.stream(memberFinalScores).min().getAsInt();
        int numOfCandidates = (int) Arrays.stream(memberFinalScores).filter(item -> item == candidateScore).count();
        sb.append(String.format("%d %d\n", candidateScore, numOfCandidates));
        for (int i = 1; i <= memberSize; i++) {
            if (candidateScore == memberFinalScores[i]) {
                sb.append(String.format("%d ", i));
            }
        }

        System.out.println(sb);
        br.close();
    }

    //// 이 함수가 하는 일이 뭔지 감도 안 잡혀요..
    static void setMemberScores() {
        for (int k = 1; k <= memberSize; k++) {
            for (int i = 1; i <= memberSize; i++) {
                for (int j = 1; j <= memberSize; j++) {
                    int throughKScore = memberScores[i][k] + memberScores[k][j];
                    if (throughKScore < memberScores[i][j]) {
                        memberScores[i][j] = throughKScore;
                    }
                }
            }
        }
    }

    //// 설정set하는건 알겠는데 '어떻게 설정하는지'가 설명된다면 좋을거같아요 setOne
    static void setMemberFriendsScores(int member1, int member2) {
        memberScores[member1][member2] = memberScores[member2][member1] = 1;
    }

    static void initMemberScores() {
        for (int i = 1; i <= memberSize; i++) {
            Arrays.fill(memberScores[i], MAX_SCORE);
            memberScores[i][0] = memberScores[i][i] = 0;
        }
    }
}