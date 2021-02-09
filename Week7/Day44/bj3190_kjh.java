import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;

class Main {
  public static void main(String[] args) throws Exception {
    final int BOARD_SIZE = Input.nextInt();
    final int APPLE_AMOUNT = Input.nextInt();
    
    List<Tile> appleTiles = new ArrayList<Tile>();
    for (int i = 0; i < APPLE_AMOUNT; i++) {
      int row = Input.nextInt();
      int column = Input.nextInt();
      appleTiles.add(new Tile(row, column));
    }

    final int ROTATE_INFO_AMOUNT = Input.nextInt();
    Queue<RotateInfo> rotateInfos = new LinkedList<>();
    for (int i = 0; i < ROTATE_INFO_AMOUNT; i++) {
      int second = Input.nextInt();
      char rotateTo = Input.nextChar();
      rotateInfos.offer(new RotateInfo(second, rotateTo));
    }

    Board board = new Board(BOARD_SIZE, appleTiles);
    Board.Snake snake = board.new Snake();

    int second = 0;
    while (true) {
      second++;
      if (!snake.isAliveAfter1Second()) {
        break;
      }

      if (rotateInfos.size() > 0) {
        RotateInfo rotateInfo =                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               rotateInfos.peek();
        if (rotateInfo.getSecond() == second) {
          snake.rotateDirection(rotateInfo.getRotateTo());
          rotateInfos.poll();
        }
      }
    }

    System.out.print(second);
  }
}

class Board {
  private int boardSize;
  private List<Tile> appleTiles;

  public Board(int boardSize, List<Tile> appleTiles) {
    this.boardSize = boardSize;
    this.appleTiles = appleTiles;
  }

  class Snake {
    private List<Tile> snakeTiles;
    private Direction direction;

    public Snake() {
      snakeTiles = new ArrayList<Tile>();
      snakeTiles.add(new Tile(1, 1));
      direction = Direction.columnPlus;
    }

    public boolean isAliveAfter1Second() {
      Tile head = snakeTiles.get(snakeTiles.size() - 1);
      Tile whereToMove = head.move(direction);

      if (snakeTiles.contains(whereToMove)) {
        return false;
      }

      boolean outOfBoard = whereToMove.getRow() > boardSize || whereToMove.getColumn() > boardSize
        || whereToMove.getRow() < 1 || whereToMove.getColumn() < 1;
      if (outOfBoard) {
        return false;
      }

      if (appleTiles.contains(whereToMove)) {
        appleTiles.remove(whereToMove);
        snakeTiles.add(whereToMove);
        return true;
      }
      
      snakeTiles.remove(0);
      snakeTiles.add(whereToMove);
      return true;
    }

    public void rotateDirection(char rotateTo) {
      if (rotateTo == 'L') {
        direction = direction.L();
        return;
      }
      direction = direction.D();
    }
  }
}

enum Direction {
  columnPlus, rowPlus, columnMinus, rowMinus;

  public Direction L() {
    switch (this) {
      case columnPlus:
        return Direction.rowMinus;
      case rowPlus:
        return Direction.columnPlus;
      case columnMinus:
        return Direction.rowPlus;
      case rowMinus:
        return Direction.columnMinus;
    }

    return null;
  }
  public Direction D() {
    switch (this) {
      case columnPlus:
        return Direction.rowPlus;
      case rowPlus:
        return Direction.columnMinus;
      case columnMinus:
        return Direction.rowMinus;
      case rowMinus:
        return Direction.columnPlus;
    }

    return null;
  }
}

class Tile {
  private int row;
  private int column;

  public Tile(int row, int column) {
    this.row = row;
    this.column = column;
  }

  public Tile move(Direction direction) {
    switch (direction) {
      case columnPlus:
        return new Tile(row, column + 1);
      case rowPlus:
        return new Tile(row + 1, column);
      case columnMinus:
        return new Tile(row, column - 1);
      case rowMinus:
        return new Tile(row - 1, column);
    }

    return null;
  }

  public int getRow() {
    return row;
  }

  public int getColumn() {
    return column;
  }

  // 디버그용
  @Override
  public String toString() {
    return "{" + row + ", " + column + "}";
  }

  @Override
  public boolean equals(Object o) {
    return ((Tile)o).getRow() == row && ((Tile)o).getColumn() == column;
  }
}

class RotateInfo {
  private int second;
  private char rotateTo;

  public RotateInfo(int second, char rotateTo) {
    this.second = second;
    this.rotateTo = rotateTo;
  }

  public int getSecond() {
    return second;
  }

  public char getRotateTo() {
    return rotateTo;
  }
}

class Input {
  private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  private static StringTokenizer tokenizer;

  public static String nextLine() {
    try {
      return br.readLine();
    } catch(Exception e) { }
    
    return null;
  }

  public static int nextInt() {
    String nextString = next();
    return Integer.parseInt(nextString);
  }

  public static double nextDouble() {
    String nextString = next();
    return Double.parseDouble(nextString);
  }


  public static long nextLong() {
    String nextString = next();
    return Long.parseLong(nextString);
  }

  public static char nextChar() {
    String nextString = next();
    return nextString.charAt(0);
  }
  
  public static String next() {
    makeTokensIfNeed();
    return tokenizer.nextToken();
  }

  private static void makeTokensIfNeed() {
    makeTokensIfNotReadedYet();
    makeTokensIfHasNoToken();
  }

  private static void makeTokensIfNotReadedYet() {
    if (tokenizer == null) {
      tokenizer = makeTokens();
    }
  }

  private static void makeTokensIfHasNoToken() {
    if (tokenizer.hasMoreTokens() == false) {
      tokenizer = makeTokens();
    }
  }
  
  private static StringTokenizer makeTokens() {
    return new StringTokenizer(nextLine(), " ");
  }
}