import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    static final int PUZZLE_LINE_SIZE = 3;
    static final int PUZZLE_SIZE = PUZZLE_LINE_SIZE * PUZZLE_LINE_SIZE;
    static final String BLANK_BLOCK = "0";
    static final String PUZZLE_SOLVED_STATE = "123456780";

    static final int dLen = 4;
    static final int[] dx = {-3, 1, 3, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder puzzleState = new StringBuilder();
        for (int i = 0; i < PUZZLE_LINE_SIZE; i++) {
            String[] puzzleLine = br.readLine().split(" ");
            for (int j = 0; j < PUZZLE_LINE_SIZE; j++) {
                puzzleState.append(puzzleLine[j]);
            }
        }

        System.out.println(getMinMovementToSolve(puzzleState.toString()));
        br.close();
    }

    static int getMinMovementToSolve(String puzzleState) {
        Queue<String> bfsQueue = new LinkedList<>();
        Map<String, Integer> movements = new HashMap<>();
        
        bfsQueue.offer(puzzleState);
        movements.put(puzzleState, 0);

        while (!bfsQueue.isEmpty()) {
            String currentState = bfsQueue.poll();
            if (currentState.equals(PUZZLE_SOLVED_STATE)) {
                return movements.get(PUZZLE_SOLVED_STATE);
            }

            int blankIdx = getBlankIndex(currentState);
            int nextMovement = movements.get(currentState) + 1;

            for (int i = 0; i < dLen; i++) {
                int nextIdx = blankIdx + dx[i];
                if (!isPossibleMovement(blankIdx, nextIdx)) continue;

                String nextState = getNextPuzzleState(currentState, blankIdx, nextIdx);
                if (movements.containsKey(nextState)) continue;
                movements.put(nextState, nextMovement);
                bfsQueue.offer(nextState);
            }
        }

        return -1;
    }

    static String getNextPuzzleState(String puzzleState, int currentIdx, int nextIdx) {
        StringBuilder nextState = new StringBuilder(puzzleState);
        String nextBlock = getBlock(puzzleState, nextIdx);
        nextState.replace(currentIdx, currentIdx + 1, nextBlock);
        nextState.replace(nextIdx, nextIdx + 1, BLANK_BLOCK);
        return nextState.toString();
    }

    static String getBlock(String puzzleState, int idx) {
        return String.valueOf(puzzleState.charAt(idx));
    }

    static int getBlankIndex(String puzzleState) {
        return puzzleState.indexOf(BLANK_BLOCK);
    }

    static boolean isPossibleMovement(int idx, int nextIdx) {
        if (isOutOfSize(nextIdx, PUZZLE_SIZE)) return false;
        idx %= PUZZLE_LINE_SIZE;
        nextIdx %= PUZZLE_LINE_SIZE;

        if (idx == 0 && nextIdx == 2) return false;
        if (idx == 2 && nextIdx == 0) return false;
        return true;
    }

    static boolean isOutOfSize(int point, int size) {
        return (point < 0 || point >= size);
    }
}