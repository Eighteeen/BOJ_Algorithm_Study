import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String[] gridInfo = br.readLine().split(" ");
        int R = Integer.parseInt(gridInfo[0]);
        int C = Integer.parseInt(gridInfo[1]);
        int N = Integer.parseInt(gridInfo[2]);

        //// 와우.. 블록 하나하나에 초마다 상태가 변하게 하는? 말로 표현하기가 어렵네요
        //// 무튼 처음에는 이해하기 어려웠는데 이해하고 보니 정말 참신하게 푸신 것 같습니다 리스펙
        //// : 22 깔끔해요
        int[][] bombTimesArr = new int[R][C];
        final int WAIT_NORMAL_TIME = -2, NORMAL_TIME = -1, WAIT_BOMD_TIME = 2, BOMB_TIME = 3;

        for (int i = 0; i < R; i++) {
            char[] bombStates = br.readLine().toCharArray();
            for (int j = 0; j < C; j++) {
                if (bombStates[j] == '.') bombTimesArr[i][j] = WAIT_NORMAL_TIME; 
            }
        }

        int[] dx = {-1, 0, 1, 0}, dy = {0, 1, 0, -1};
        final int RIGHT_INDEX = 1, DOWN_INDEX = 2;

        for (int time = 1; time <= N; time++) {
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    if (++bombTimesArr[i][j] != BOMB_TIME) continue;

                    bombTimesArr[i][j] = NORMAL_TIME;
                    for (int k = 0; k < 4; k++) {
                        int x = i + dx[k];
                        int y = j + dy[k];
                        if (x < 0 || x >= R || y < 0 || y >= C) continue;
                        if (k == RIGHT_INDEX || k == DOWN_INDEX) {
                            if (bombTimesArr[x][y] == WAIT_BOMD_TIME) continue;
                            bombTimesArr[x][y] = WAIT_NORMAL_TIME;
                        } else {
                            bombTimesArr[x][y] = NORMAL_TIME;   
                        }
                    }
                }
            }
        }

        for (int[] bombTimes : bombTimesArr) {
            for (int bombTime : bombTimes) {
                sb.append(bombTime < 0 ? '.' : 'O');
            }
            sb.append("\n");
        }

        System.out.print(sb);
        br.close();
    }
}