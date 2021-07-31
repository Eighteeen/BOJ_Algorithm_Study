import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    static long[] minNumOfVisitedCarArr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int H = Integer.parseInt(br.readLine());
        minNumOfVisitedCarArr = new long[H + 1];
        minNumOfVisitedCarArr[0] = 1;

        System.out.println(minNumOfVisitedCar(H));
        br.close();
    }

    //// 깔끔해요 :22
    static long minNumOfVisitedCar(int treeHeight) {
        if (minNumOfVisitedCarArr[treeHeight] != 0) return minNumOfVisitedCarArr[treeHeight];

        for (int i = treeHeight - 2; i >= 0; i--) {
            minNumOfVisitedCarArr[treeHeight] += minNumOfVisitedCar(i);
        }

        minNumOfVisitedCarArr[treeHeight] *= 2;
        return ++minNumOfVisitedCarArr[treeHeight];
    }
}