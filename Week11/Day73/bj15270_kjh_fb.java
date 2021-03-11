// 문제 실패 - 모범답안
// 본인의 코드는 O(PAIR!)의 복잡도, 이 코드는 O(N*FRIEND)의 복잡도인 것 같음
// 재귀가 끝나는 기준을 '짝이 모두 맺어졌을 때'로 한 본인과 다르게
// '1~N까지 엮어봤을 때'를 기준으로 하여 훨~~씬 효율적인 코드

// 거의 최악의 시간 복잡도를 가진 코드를 짜놓고 왜 안되나 한 본인이 이상하게 보임 

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
        // 입력 왼쪽에 나오는 놈 친구들을 HashMap<Integer, List<Integer>>에 담음
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
        // 없는 놈을 탐색하고 있거나 이미 다 무대에 나갔으면 재귀호출 종료
        if (checkNum > N || friendsMap.isEmpty()) return cnt;

        int nextNum = checkNum + 1;
        // 이놈이 친구가 없거나 이미 짝이 있다면 다음 놈 탐색
        if (!friendsMap.containsKey(checkNum) || isPairedArr[checkNum]) return maxPairFriends(nextNum, cnt);

        // 이놈이 짝을 못 맺는다고 가정하고 최대 짝 수를 계산해 maxPair(최대 짝 수)에 일단 넣어둠
        int maxPair = maxPairFriends(nextNum, cnt);
        List<Integer> friendList = friendsMap.get(checkNum);
        for (int fNum : friendList) {
            // 이놈의 친구들이랑 엮어서 짝 몇명나오나 견적내서 많이 나오는쪽을 maxPair에 담음
            if (!isPairedArr[fNum]) {
                isPairedArr[checkNum] = isPairedArr[fNum] = true;
                maxPair = Math.max(maxPair, maxPairFriends(nextNum, cnt + 2));
                isPairedArr[checkNum] = isPairedArr[fNum] = false;
            }
        }

        return maxPair;
    }
}