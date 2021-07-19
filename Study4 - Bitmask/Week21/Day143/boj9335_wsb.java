import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

//// 깔꼼~
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
            friendsBitArr[j] = bitmaskOfNumArrAllAdd(Arrays.copyOfRange(friendsInfo, 1, d + 1)) | (1 << (j + 1));
          }

          sb.append(minimumAds(friendsBitArr)).append("\n");
        }

        System.out.println(sb);
        br.close();
    }

    static int minimumAds(int[] friendsArr) {
      int friendNum = friendsArr.length;
      int minimumAds = friendNum;

      int range = (1 << friendNum);
      for (int i = 1; i < range; i++) {
        int currentSeen = 0, needAds = 0;
        for (int j = 0; j < friendNum; j++) {
          if ((i & (1 << j)) == 0) continue;
          currentSeen |= friendsArr[j];
          needAds++;
        }
        if (Integer.bitCount(currentSeen) != friendNum) continue;
        if (needAds == 1) return 1; //// 최적화 굳굳
        minimumAds = Math.min(minimumAds, needAds);
      }

      return minimumAds;
    }

    static int bitmaskOfNumArrAllAdd(int[] numArr) {
      int bitmask = 0;
      for (int num : numArr) {
        bitmask |= (1 << num);
      }
      return bitmask;
  }
}