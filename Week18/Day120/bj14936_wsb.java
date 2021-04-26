import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] elevatorInfo = br.readLine().split(" ");
        int N = Integer.parseInt(elevatorInfo[0]), m = Integer.parseInt(elevatorInfo[1]);

        int buttonStateBit = 0;
        buttonStateBit |= (1 << 0);

        if (m == 0) {
            System.out.println(1);
            return;
        }
        if (N == 1) {
            System.out.println(2);
            return;
        } 
        if (N == 2) {
            if (m == 1) System.out.println(3);
            else System.out.println(4);
            return;
        }

        int[] prankTimeArr = {
            N,
            N / 2 + N % 2,
            N / 2,
            (N - 1) / 3 + 1
        };

        for (int i = 0; i < 4; i++) {
            if (prankTimeArr[i] <= m) buttonStateBit |= (1 << i + 1);
        }

        for (int i = 0; i < 3; i++) {
            if (m - prankTimeArr[i] < prankTimeArr[3] || (buttonStateBit & (1 << i)) == 0) continue;
            buttonStateBit |= (1 << i + 5);
        }

        System.out.println(Integer.bitCount(buttonStateBit));
        br.close();
    }
}