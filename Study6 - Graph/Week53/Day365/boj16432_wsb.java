import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//// 깔끔
class Main {
    static final int RICE_CAKE_TYPE_MAX = 9;

    static int marketPeriod;
    static List<Integer>[] riceCakeTypesByDate;
    static int[] riceCakeTypesForTiger;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        marketPeriod = Integer.parseInt(br.readLine());
        riceCakeTypesByDate = new ArrayList[marketPeriod + 1];
        riceCakeTypesForTiger = new int[marketPeriod + 1];
        visited = new boolean[marketPeriod + 1][RICE_CAKE_TYPE_MAX + 1];

        for (int i = 1; i <= marketPeriod; i++) {
            int[] riceCakeInfo = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int numOfRiceCakes = riceCakeInfo[0];

            riceCakeTypesByDate[i] = new ArrayList<>();
            for (int j = 1; j <= numOfRiceCakes; j++) {
                riceCakeTypesByDate[i].add(riceCakeInfo[j]);
            }
        }

        if (isSetUpRiceCakeTypesForTiger(1)) {
            for (int i = 1; i <= marketPeriod; i++) {
                sb.append(String.format("%d\n", riceCakeTypesForTiger[i]));
            }
        } else {
            System.out.println("-1");
            return;
        }

        System.out.println(sb);
        br.close();
    }

    //// 단어 어감상 date보다 day가 잘어울렸을 것 같아요
    static boolean isSetUpRiceCakeTypesForTiger(int date) {
        if (date > marketPeriod) return true;

        List<Integer> riceCakeTypes = riceCakeTypesByDate[date];
        for (Integer riceCakeType : riceCakeTypes) {
            if (riceCakeTypesForTiger[date - 1] == riceCakeType ||
                visited[date][riceCakeType]) continue;
            
            riceCakeTypesForTiger[date] = riceCakeType;
            visited[date][riceCakeType] = true;
            if (isSetUpRiceCakeTypesForTiger(date + 1)) return true;
        }

        return false;
    }
}