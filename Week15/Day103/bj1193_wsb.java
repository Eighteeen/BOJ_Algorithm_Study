import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int X = Integer.parseInt(br.readLine());
        int molecule = 1, denominator = 1;
        int leftNth = 1, downNth = 1;

        while (X > leftNth && X > downNth) {
            if (denominator % 2 == 0) leftNth += denominator * 2;
            else leftNth++;
            denominator++;

            if (molecule % 2 == 1) downNth += molecule * 2;
            else downNth++;
            molecule++;
        }

        if (leftNth < downNth) {
            molecule = 1;
            for (int i = leftNth; i < X; i++) {
                molecule++;
                denominator--;
            }
        } else {
            denominator = 1;
            for (int i = downNth; i < X; i++) {
                denominator++;
                molecule--;
            }
        }

        System.out.printf("%d/%d\n", molecule, denominator);
        br.close();
    }
}