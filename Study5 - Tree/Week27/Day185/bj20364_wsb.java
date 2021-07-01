import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.HashMap;

class Main {
    static Map<Integer, Integer> landMap = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String[] realEstateInfo = br.readLine().split(" ");
        int N = Integer.parseInt(realEstateInfo[0]);
        int Q = Integer.parseInt(realEstateInfo[1]);

        for (int i = 0; i < Q; i++) {
            int wantLandNum = Integer.parseInt(br.readLine());
            sb.append(getOccupiedLandNum(wantLandNum)).append("\n");
        }

        System.out.print(sb);
        br.close();
    }

    static int getOccupiedLandNum(int wantLandNum) {
        int checkNum = wantLandNum, occupiedNum = wantLandNum;
        boolean isOccupied = false;

        while (checkNum > 0) {
            if (landMap.containsKey(checkNum)) {
                isOccupied = true;
                occupiedNum = landMap.get(checkNum);
            }
            checkNum /= 2;
        }

        landMap.put(wantLandNum, occupiedNum);
        return (isOccupied ? occupiedNum : 0);
    }
}