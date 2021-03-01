  import java.io.BufferedReader;
  import java.io.InputStreamReader;
  import java.util.StringTokenizer;

  class Main {
    static boolean[][] quadtreeBits;
    static String quadtree;
    static int idx;
    static int quadtreeLastIdx;

    public static void main(String[] args) throws Exception {
      StringBuilder sb = new StringBuilder();

      final int N = Input.nextInt();
      final String ENCRYPTED = Input.nextLine();

      sb.append("#define quadtree_width ").append(N).append('\n');
      sb.append("#define quadtree_height ").append(N).append('\n');
      sb.append("static char quadtree_bits[] = {\n");

      quadtreeBits = new boolean[N][N];
      idx = 0;
      quadtree = ENCRYPTED;
      quadtreeLastIdx = quadtree.length() - 1;
      makePixels(N, 0, 0);
      
      for (int i = 0; i < N; i++) {
        sb.append(convertBinaryToHex(quadtreeBits[i])).append('\n');
      }

      sb.append("};");

      System.out.print(sb);
    }

    static String convertBinaryToHex(boolean[] binary) {
      StringBuilder hex = new StringBuilder();

      for (int i = 0; i < binary.length; i += 8) {
        int decimal = 0;
        for (int j = i; j < i + 8; j++) {
          if (binary[j] == false) continue;
          decimal += (int) Math.pow(2, j % 8);
        }

        hex.append("0x");
        if (decimal < 16) hex.append('0');
        
        hex.append(Integer.toHexString(decimal)).append(',');
      }

      return hex.toString();
    }

    static void makePixels(int size, int row, int col) {
      for (int i = 0; i < 4; i++) {
        if (idx >= quadtreeLastIdx) return;

        char checkLetter = quadtree.charAt(idx++);
        int checkRow = row, checkCol = col;

        switch (i) {
          case 1:
            checkCol += size;
            break;
          case 2:
            checkRow += size;
            break;
          case 3:
            checkRow += size;
            checkCol += size;
            break;
        }

        if (checkLetter == 'Q') {
          makePixels(size / 2, checkRow, checkCol);
        }
        else if (checkLetter == 'B') {
          fillBlack(checkRow, checkCol, size);
        }
      }
    }

    static void fillBlack(int row, int col, int range){
      for (int i = row; i < row + range; i++) {
        for (int j = col; j < col + range; j++) {
          quadtreeBits[i][j] = true;
        }
      }
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