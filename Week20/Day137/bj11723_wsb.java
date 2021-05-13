import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        final int MAX_SIZE = 20;
        Set set = new Set(MAX_SIZE);
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

class Set {
    private int size;
    private int bitmask = 0;

    public Set(int size) {
        this.size = size;
    }

    public void add(int num) {
        bitmask |= (1 << num);
    }

    public void remove(int num) {
        bitmask &= ~(1 << num);
    }

    public boolean check(int num) {
        return (bitmask & (1 << num)) != 0;
    }

    public void toggle(int num) {
        bitmask ^= (1 << num);
    }

    public void all() {
        bitmask = (int) Math.pow(2, size) * 2 - 1;
    }

    public void empty() {
        bitmask = 0;
    }
}