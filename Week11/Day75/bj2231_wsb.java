import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(getConstructor(Integer.parseInt(br.readLine())));
        br.close();
    }

    static int getConstructor(int num) {
        int len = (int) (Math.log(num) + 1);
        int minCandidate = num - 9 * len, maxCandidate = num - 1 * len;
        minCandidate = (minCandidate < 0 ? 1 : minCandidate);
        int constructor = 0;

        for (int i = minCandidate; i <= maxCandidate; i++) {
            int[] eachNumArr = Arrays.stream(String.valueOf(i).split("")).mapToInt(Integer::parseInt).toArray();
            if (sumNumArr(i, eachNumArr) == num) {
                constructor = i;
                break;
            }
        }

        return constructor;
    }

    static int sumNumArr(int initial, int[] numArr) {
        for (int n : numArr) initial += n;
        return initial;
    }
}