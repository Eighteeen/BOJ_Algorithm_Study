import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    static List<Set<Integer>> friendsList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        final char friendFlag = 'Y';

        int N = Integer.parseInt(br.readLine());
        friendsList = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            Set<Integer> friends = new HashSet<>();
            char[] friendFlags = br.readLine().toCharArray();
            for (int j = 0; j < N; j++) {
                if (friendFlags[j] == friendFlag) friends.add(j);
            }
            friendsList.add(friends);
        }

        System.out.println(getMaxNumOfKnowPeople());
        br.close();
    }

    static int getMaxNumOfKnowPeople() {
        int maxNum = 0;

        for (Set<Integer> friends : friendsList) {
            Set<Integer> knowPeople = new HashSet<>();
            knowPeople.addAll(friends);
            for (int friend : friends) {
                knowPeople.addAll(friendsList.get(friend));
            }

            int knowPeopleNum = knowPeople.size() - 1;
            if (maxNum < knowPeopleNum) maxNum = knowPeopleNum;
        }

        return maxNum;
    }
}