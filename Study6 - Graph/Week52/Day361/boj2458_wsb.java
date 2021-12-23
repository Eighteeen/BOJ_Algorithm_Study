import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    static boolean[][] isKnowWhoTallerState;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] studentsInfo = br.readLine().split(" ");
        int N = Integer.parseInt(studentsInfo[0]);
        int M = Integer.parseInt(studentsInfo[1]);
        
        int size = N + 1;
        initIsKnowWhoTallerState(size);

        for (int i = 0; i < M; i++) {
            String[] orderInfo = br.readLine().split(" ");
            int smallStudent = Integer.parseInt(orderInfo[0]);
            int tallStudent = Integer.parseInt(orderInfo[1]);

            setStudentKnowWhoTaller(smallStudent, tallStudent);
        }

        int allKnowStateCount = 0;
        setIsKnowWhoTallerStateByFloydWarshall(size);
        for (int i = 1; i < size; i++) {
            int knowCount = 0;
            for (int j = 1; j < size; j++) {
                boolean isKnowOrder = isKnowWhoTallerState[i][j] || isKnowWhoTallerState[j][i];
                if (isKnowOrder) knowCount++;
            }
            if (knowCount == N) allKnowStateCount++;
        }

        System.out.println(allKnowStateCount);
        br.close();
    }

    static void initIsKnowWhoTallerState(int size) {
        isKnowWhoTallerState = new boolean[size][size];
        for (int i = 0; i < size; i++) {
            isKnowWhoTallerState[i][i] = true;
        }
    }

    static void setStudentKnowWhoTaller(int smallStudent, int tallStudent) {
        isKnowWhoTallerState[smallStudent][tallStudent] = true;
    }

    static void setIsKnowWhoTallerStateByFloydWarshall(int size) {
        for (int k = 1; k < size; k++) {
            for (int i = 1; i < size; i++) {
                if (!isKnowWhoTallerState[i][k]) continue;
                for (int j = 1; j < size; j++) {
                    if (isKnowWhoTallerState[i][j]) continue;
                    boolean isKnowSocreThroughK = isKnowWhoTallerState[i][k] && isKnowWhoTallerState[k][j];
                    if (isKnowSocreThroughK) {
                        isKnowWhoTallerState[i][j] = true;
                    }
                }
            }
        }
    }
}