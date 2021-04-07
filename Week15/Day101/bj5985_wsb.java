import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.IntStream;

class Main {
    static int totalDistance;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int cowLen = (int) Math.pow(2, N);
        int[] cowNumbers = new int[cowLen];
        for (int i = 0; i < cowLen; i++) {
            cowNumbers[i] = Integer.parseInt(br.readLine());
        }

        cowNumbers = skewedSortArr(cowNumbers);
        sb.append(totalDistance).append('\n');
        Arrays.stream(cowNumbers).forEach(c -> sb.append(c).append('\n'));

        System.out.print(sb);
        br.close();
    }

    static int[] skewedSortArr(int[] arr) {
        int len = arr.length;
        if (len == 1) return arr;

        int halfLen = len / 2;
        int[] firstArr = skewedSortArr(Arrays.copyOfRange(arr, 0, halfLen));
        int[] secondArr = skewedSortArr(Arrays.copyOfRange(arr, halfLen, len));

        if (firstArr[0] < secondArr[0]) return mergeArr(firstArr, secondArr);
        totalDistance += len * halfLen;
        return mergeArr(secondArr, firstArr);
    }

    static int[] mergeArr(int[] firstArr, int[] secondArr) {
        return IntStream.concat(IntStream.of(firstArr), IntStream.of(secondArr)).toArray();
    }
}