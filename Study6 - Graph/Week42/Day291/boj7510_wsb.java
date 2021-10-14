import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        for (int i = 1; i <= n; i++) {
            String[] triangleInfo = br.readLine().split(" ");
            int a = Integer.parseInt(triangleInfo[0]);
            int b = Integer.parseInt(triangleInfo[1]);
            int c = Integer.parseInt(triangleInfo[2]);

            int longestLength = Math.max(a, Math.max(b, c));
            boolean isRightAngledTriangle = false;

            if (a == longestLength) {
                if (isRightAngledTriangle(longestLength, b, c)) isRightAngledTriangle = true;
            } else if (b == longestLength) {
                if (isRightAngledTriangle(longestLength, a, c)) isRightAngledTriangle = true;
            } else {
                if (isRightAngledTriangle(longestLength, a, b)) isRightAngledTriangle = true;
            }

            sb.append(String.format("Scenario #%d:", i))
                .append("\n")
                .append(isRightAngledTriangle ? "yes\n\n" : "no\n\n");
        }

        System.out.print(sb);
        br.close();
    }

    static boolean isRightAngledTriangle(int longestLength, int length1, int length2) {
        return getSquared(longestLength) == getSquared(length1) + getSquared(length2);
    }

    static int getSquared(int num) {
        return num * num;
    }
}