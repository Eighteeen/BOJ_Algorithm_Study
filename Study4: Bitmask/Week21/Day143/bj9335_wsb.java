import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int TC = Integer.parseInt(br.readLine());
        for (int i = 0; i < TC; i++) {
          int n = Integer.parseInt(br.readLine());
          int[] friendsBitArr = new int[n];

          for (int j = 0; j < n; j++) {
            int[] friendsInfo = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int d = friendsInfo[0];
            friendsBitArr[i] = bitmaskOfNumArrAllAdd(Arrays.copyOfRange(friendsInfo, 1, d));
          }
        }

        System.out.println(sb);
        br.close();
    }

    static int minimumAds(int[] friendsArr) {
      int friendNum = friendsArr.length;
      return 0;
    }

    static int bitmaskOfNumArrAllAdd(int[] numArr) {
      int bitmask = 0;
      for (int num : numArr) {
          bitmask |= (1 << num);
      }
      return bitmask;
  }
}