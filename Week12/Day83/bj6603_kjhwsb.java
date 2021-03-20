import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

class Main {
    static List<Lotto> lottoList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String[] input;
        while ((input = br.readLine().split(" "))[0].charAt(0) != '0') {
            int k = Integer.parseInt(input[0]);
            List<Integer> numList = new ArrayList<>();
            for (int i = 1; i <= k; i++) {
                numList.add(Integer.parseInt(input[i]));
            }

            lottoList = new ArrayList<>();
            selectedPossibleBalls(new Lotto(), numList);
            lottoList.forEach(l -> sb.append(l.toString()).append("\n"));
            sb.append("\n");
        }

        System.out.print(sb);
        br.close();
    }

    static void selectedPossibleBalls(Lotto lotto, List<Integer> balls) {
        if (lotto.isFull()) {
            lottoList.add(lotto);
            return;
        }

        List<Integer> copyBalls = new ArrayList<>(balls);
        for (Integer ball : balls) {
            Lotto currentLotto = new Lotto(lotto);
            currentLotto.addBall(ball);
            copyBalls.remove(ball);
            selectedPossibleBalls(currentLotto, copyBalls);
        }
    }
}

class Lotto {
    private final int MAX_SELECT = 6;
    private int[] balls = new int[MAX_SELECT];
    private int pointer = 0;

    public Lotto() {}

    public Lotto(Lotto lotto) {
        this.balls = lotto.getBalls().clone();
        this.pointer = lotto.getPointer();
    }

    public int[] getBalls() {
        return balls;
    }

    public int getPointer() {
        return pointer;
    }

    public void addBall(int ball) {
        balls[pointer++] = ball;
    }

    public boolean isFull() {
        return pointer == MAX_SELECT;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int b : balls) {
            sb.append(b).append(" ");
        }
        return sb.toString();
    }
}