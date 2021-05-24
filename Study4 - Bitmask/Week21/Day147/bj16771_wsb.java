import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] firstBarnBucketUnits = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] secondBarnBucketUnits = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        
        System.out.println(numOfPossibleMilkResultAfterFriday(firstBarnBucketUnits, secondBarnBucketUnits));
        br.close();
    }

    static int numOfPossibleMilkResultAfterFriday(int[] firstBarnBucketUnits, int[] secondBarnBucketUnits) {
        Set<Integer> possibleResult = new HashSet<>();
        possibleResult.add(0);

        int numOfBucket = firstBarnBucketUnits.length;
        int range = (1 << numOfBucket);
        for (int firstBarnBuckets = 1; firstBarnBuckets < range; firstBarnBuckets++) {
            //// 비트마스킹을 사용했다는 점에서 의미있는 것 같습니다 굳
            int numOfFirstBucket = Integer.bitCount(firstBarnBuckets);
            if (numOfFirstBucket > 2) continue;

            int firstBarnMilk = 0;
            for (int bucket = 0; bucket < numOfBucket; bucket++) {
                if ((firstBarnBuckets & (1 << bucket)) == 0) continue;
                firstBarnMilk -= firstBarnBucketUnits[bucket];
            }

            for (int secondBarnBuckets = 1; secondBarnBuckets < range; secondBarnBuckets++) {
                if (numOfFirstBucket != Integer.bitCount(secondBarnBuckets)) continue;

                int firstBarnMilkCopy = firstBarnMilk;
                for (int bucket = 0; bucket < numOfBucket; bucket++) {
                    if ((secondBarnBuckets & (1 << bucket)) == 0) continue;
                    firstBarnMilkCopy += secondBarnBucketUnits[bucket];
                }

                possibleResult.add(firstBarnMilkCopy);
            }
        }

        return possibleResult.size();
    }
}