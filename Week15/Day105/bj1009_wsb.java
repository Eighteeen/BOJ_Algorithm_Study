import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

class Main {
    static List<Integer>[] lastProcessListArr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        lastProcessListArr = new ArrayList[8];

        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            String[] testInfo = br.readLine().split(" ");
            sb.append(lastProcessComputer(Integer.parseInt(testInfo[0]), Integer.parseInt(testInfo[1])))
                .append("\n");
        }

        System.out.print(sb);
        br.close();
    }

    static int lastProcessComputer(int base, int exponent) {
        base %= 10;
        if (base == 1) return 1;
        if (base == 0) return 10;

        List<Integer> lastProcessList = lastProcessListArr[base - 2];
        if (lastProcessList != null) return lastProcessComputer(lastProcessList, exponent);

        lastProcessList = new ArrayList<>();
        int lastProcess = base;
        do {
            lastProcessList.add(lastProcess);
            lastProcess = lastProcess * base % 10;
        } while (lastProcess != base);

        return lastProcessComputer(lastProcessList, exponent);
    }

    static int lastProcessComputer(List<Integer> lastProcessList, int processLoop) {
        int loopSize = lastProcessList.size();
        processLoop %= loopSize;
        return lastProcessList.get((processLoop == 0 ? loopSize : processLoop) - 1);
    }
}