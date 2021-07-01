import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] adjacentLengths = new int[N + 1];

        int maxAdjacentLenght = 0;
        while (--N > 0) {
            String[] nodeInfo = br.readLine().split(" ");
            int firstNum = Integer.parseInt(nodeInfo[0]);
            int secondNum = Integer.parseInt(nodeInfo[1]);

            maxAdjacentLenght = Math.max(maxAdjacentLenght,
                    Math.max(++adjacentLengths[firstNum],
                        ++adjacentLengths[secondNum])
                );
        }

        System.out.println(maxAdjacentLenght + 1);
        br.close();
    }
}