import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] costnfo = br.readLine().split(" ");
        long A = Long.parseLong(costnfo[0]), B = Long.parseLong(costnfo[1]), C= Long.parseLong(costnfo[2]);

        System.out.println(getBreakEvenPoint(A, B, C));
        br.close();
    }

    static int getBreakEvenPoint (long fixedCost, long variableCost, long sellingCost) {
        if (variableCost >= sellingCost) return -1;
        return (int) (fixedCost / (sellingCost - variableCost)) + 1;
    }
}