import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//// ㄲㄲ
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] bessieCoordinateInfo = br.readLine().split(" ");
        Coordinate bessieCoordinate = Coordinate.twoPointOf(bessieCoordinateInfo[0], bessieCoordinateInfo[1]);
        String[] daisyCoordinateInfo = br.readLine().split(" ");
        Coordinate daisyCoordinate = Coordinate.twoPointOf(daisyCoordinateInfo[0], daisyCoordinateInfo[1]);
        String[] johnCoordinateInfo = br.readLine().split(" ");
        Coordinate johnCoordinate = Coordinate.twoPointOf(johnCoordinateInfo[0], johnCoordinateInfo[1]);

        int bessieDistanceX = Math.abs(johnCoordinate.getX() - bessieCoordinate.getX());
        int bessieDistanceY = Math.abs(johnCoordinate.getY() - bessieCoordinate.getY());
        int bessieDistance = bessieDistanceX + bessieDistanceY - Math.min(bessieDistanceX, bessieDistanceY);

        int daisyDistanceX = Math.abs(johnCoordinate.getX() - daisyCoordinate.getX());
        int daisyDistanceY = Math.abs(johnCoordinate.getY() - daisyCoordinate.getY());
        int daisyDistance = daisyDistanceX + daisyDistanceY;

        if (bessieDistance < daisyDistance) {
            System.out.println("bessie");
        } else if (bessieDistance > daisyDistance) {
            System.out.println("daisy");
        } else {
            System.out.println("tie");
        }

        br.close();
    }
}

class Coordinate {
    private int x, y;

    public static Coordinate twoPointOf(String x, String y) {
        return new Coordinate(Integer.parseInt(x), Integer.parseInt(y));
    }

    public static Coordinate twoPointOf(int x, int y) {
        return new Coordinate(x, y);
    }

    private Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() { return x; }
    public int getY() { return y; }
}