import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int X = Integer.parseInt(br.readLine());
        int numerator = 1, denominator = 1;
        int leftNth = 1, downNth = 1;

        while (X > leftNth && X > downNth) {
            if (denominator % 2 == 0) leftNth += denominator * 2;
            else leftNth++;
            denominator++;

            if (numerator % 2 == 1) downNth += numerator * 2;
            else downNth++;
            numerator++;
        }

        if (leftNth < downNth) {
            numerator = 1;
            for (int i = leftNth; i < X; i++) {
                numerator++;
                denominator--;
            }
        } else {
            denominator = 1;
            for (int i = downNth; i < X; i++) {
                denominator++;
                numerator--;
            }
        }

        System.out.printf("%d/%d\n", numerator, denominator);
        br.close();
    }
}