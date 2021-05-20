import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

//// 까알꼼~
class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        WsbSet set = new WsbSet();
        int M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            String[] operationInfo = br.readLine().split(" ");
            int num = 0;
            if (operationInfo.length == 2) num = Integer.parseInt(operationInfo[1]);
            switch (operationInfo[0]) {
                case "add":
                    set.add(num);
                    break;
                case "remove":
                    set.remove(num);
                    break;
                case "check":
                    sb.append(set.check(num) ? "1" : "0").append("\n");
                    break;
                case "toggle":
                    set.toggle(num);
                    break;
                case "all":
                    set.all();
                    break;
                case "empty":
                    set.empty();
                    break;
            }
        }

        System.out.print(sb);
        br.close();
    }
}

class WsbSet {
    final int MAX_SIZE = 20;
    boolean[] isExistNum = new boolean[MAX_SIZE + 1];

    public void add(int num) {
        isExistNum[num] = true;
    }

    public void remove(int num) {
        isExistNum[num] = false;
    }

    public boolean check(int num) {
        return isExistNum[num];
    }

    public void toggle(int num) {
        isExistNum[num] = !isExistNum[num];
    }

    public void all() {
        Arrays.fill(isExistNum, true);
    }

    public void empty() {
        Arrays.fill(isExistNum, false);
    }
}