import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Main {
    static int N;
    static Map<Integer, List<Integer>> friendsMap;
    static boolean[] isPairedArr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] classInfo = br.readLine().split(" ");
        N = Integer.parseInt(classInfo[0]);
        int M = Integer.parseInt(classInfo[1]);

        isPairedArr = new boolean[N + 1];
        friendsMap = new HashMap<>();
        for (int i = 0; i < M; i++) {
            String[] friendsInfo = br.readLine().split(" ");
            int student1 = Integer.parseInt(friendsInfo[0]), student2 = Integer.parseInt(friendsInfo[1]);

            if (!friendsMap.containsKey(student1)) friendsMap.put(student1, new ArrayList<>());
            friendsMap.get(student1).add(student2);
        }

        int cntPair = maxPairFriends(1, 0);
        System.out.println(cntPair < N ? cntPair + 1 : N);

        br.close();
    }

    static int maxPairFriends(int checkNum, int cnt) {
        if (checkNum > N || friendsMap.isEmpty()) return cnt;

        int nextNum = checkNum + 1;
        int maxPair = maxPairFriends(nextNum, cnt);
        if (!friendsMap.containsKey(checkNum) || isPairedArr[checkNum]) return maxPair;

        List<Integer> friendList = friendsMap.get(checkNum);
        for (int fNum : friendList) {
            if (!isPairedArr[fNum]) {
                isPairedArr[checkNum] = isPairedArr[fNum] = true;
                maxPair = Math.max(maxPair, maxPairFriends(nextNum, cnt + 2));
                isPairedArr[checkNum] = isPairedArr[fNum] = false;
            }
        }

        return maxPair;
    }
}