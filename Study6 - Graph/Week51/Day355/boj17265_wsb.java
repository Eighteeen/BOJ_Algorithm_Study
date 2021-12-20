import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//// 깔끔 :2
class Main {
    static int mapSize;
    static char[][] map;
    static Coordinate destination;
    static int maxResult = Integer.MIN_VALUE;
    static int minResult = Integer.MAX_VALUE;

    static int[] dx = {1, 0};
    static int[] dy = {0, 1};
    static int dLen = 2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        mapSize = Integer.parseInt(br.readLine());
        map = new char[mapSize][mapSize];

        for (int i = 0; i < mapSize; i++) {
            String[] mapInfo = br.readLine().split(" ");
            map[i] = stringArrayToCharArray(mapInfo);
        }

        //// 두two 점Point 은 아닌거같아유
        destination = Coordinate.twoPointOf(mapSize - 1, mapSize - 1);
        setResultInMap(Coordinate.twoPointOf(0, 0));

        System.out.println(String.format("%d %d", maxResult, minResult));
        br.close();
    }

    static void setResultInMap(Coordinate start) {
        setResultInMap(start, null, null);
    }

    static void setResultInMap(Coordinate start, Integer operand, Character operator) {
        int x = start.getX(), y = start.getY();

        if (operand == null) {
            operand = charToInt(map[x][y]);
        } else if (operator == null) {
            operator = map[x][y];
        } else {
            operand = getCalcResult(operand, charToInt(map[x][y]), operator);
            operator = null;
        }

        if (start.equals(destination)) {
            setResult(operand);
        } else {
            for (int i = 0; i < dLen; i++) {
                Coordinate next = Coordinate.twoPointOf(x + dx[i], y + dy[i]);
                if (isOutOfSize(next.getX()) || isOutOfSize(next.getY())) continue;
                setResultInMap(next, operand, operator);
            }
        }
    }

    static void setResult(int compareNum) {
        maxResult = Math.max(maxResult, compareNum);
        minResult = Math.min(minResult, compareNum);
    }

    static boolean isOutOfSize(int point) {
        return (point < 0 || point >= mapSize);
    }

    static int charToInt(char numChar) {
        return numChar - '0';
    }

    static int getCalcResult(int frontOperand, int backOperand, char operator) {
        switch (operator) {
            case '+':
                return frontOperand + backOperand;
            case '-':
                return frontOperand - backOperand;
            case '*':
                return frontOperand * backOperand;
            default:
                return frontOperand;
        }
    }

    static char[] stringArrayToCharArray(String[] array) {
        int len = array.length;
        char[] result = new char[len];
        for (int i = 0; i < len; i++) {
            result[i] = array[i].charAt(0);
        }
        return result;
    }
}

class Coordinate {
    private int x, y;

    public static Coordinate twoPointOf(int x, int y) {
        return new Coordinate(x, y);
    }

    private Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() { return x; }
    public int getY() { return y; }

    @Override
    public boolean equals(Object obj){
        if(this == obj) return true;
        if(obj == null) return false;
        if(getClass() != obj.getClass()) return false;

        Coordinate other = (Coordinate) obj;
        if (x != other.x) return false;
        if (y != other.y) return false;
        return true;
    }
}