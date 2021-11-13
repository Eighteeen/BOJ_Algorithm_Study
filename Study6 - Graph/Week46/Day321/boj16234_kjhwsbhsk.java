import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    static final int dLen = 4;
    static final int[] dx = {-1, 0, 1, 0}, dy = {0, 1, 0, -1};
    static int worldSize;
    static int[][] worldPeople;
    static int minMovePeople, maxMovePeople;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] worldInfo = br.readLine().split(" ");
        worldSize = Integer.parseInt(worldInfo[0]);
        minMovePeople = Integer.parseInt(worldInfo[1]);
        maxMovePeople = Integer.parseInt(worldInfo[2]);

        worldPeople = new int[worldSize][];
        for (int i = 0; i < worldSize; i++) {
            worldPeople[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        System.out.println(getDaysOfMove());
        br.close();
    }

    static int getDaysOfMove() {
        int day = 0;
        int totalCountries = worldSize * worldSize;

        while (true) {
            List<Union> unions = new ArrayList();
            visited = new boolean[worldSize][worldSize];

            for (int i = 0; i < worldSize; i++) {
                for (int j = 0; j < worldSize; j++) {
                    if (visited[i][j]) continue;
                    unions.add(getUnion(Coordinate.twoPointOf(i, j)));
                }
            }

            if (unions.size() == totalCountries) break;
            for (Union union : unions) {
                movePeople(union);
            }
            day++;
        }

        return day;
    }

    static void movePeople(Union union) {
        int peopleAverage = union.getPeopleNum() / union.getNumOfCountries();
        for (Coordinate country : union.getCountries()) {
            worldPeople[country.getX()][country.getY()] = peopleAverage;
        }
    }

    static Union getUnion(Coordinate startCountry) {
        int x = startCountry.getX(), y = startCountry.getY();
        Union union = new Union();
        if (visited[x][y]) return union;
        visited[x][y] = true;
        union.addCountry(startCountry, worldPeople[x][y]);

        for (int i = 0; i < dLen; i++) {
            int nx = x + dx[i], ny = y + dy[i];
            if (isOutOfSize(worldSize, nx) || isOutOfSize(worldSize, ny)) continue;

            if (isPossibleMove(worldPeople[x][y], worldPeople[nx][ny])) {
                union.addCountries(getUnion(Coordinate.twoPointOf(nx, ny)));
            }
        }

        return union;
    }

    static boolean isPossibleMove(int nowPeople, int nextPeople) {
        int peopleDifference = Math.abs(nowPeople - nextPeople);
        return (peopleDifference >= minMovePeople
            && peopleDifference <= maxMovePeople);
    }

    static boolean isOutOfSize(int size, int point) {
        return (point < 0 || point >= size);
    }
}

class Union {
    private int peopleNum = 0;
    private List<Coordinate> countries;

    public Union() {
        this.countries = new ArrayList<>();
    }

    public List<Coordinate> getCountries() { return countries; }
    public int getNumOfCountries() { return countries.size(); }
    public int getPeopleNum() { return peopleNum; }

    public void addCountry(Coordinate countryCoordinate, int peopleNum) {
        countries.add(countryCoordinate);
        this.peopleNum += peopleNum;
    }

    public void addCountries(Union union) {
        for (Coordinate country : union.getCountries()) {
            countries.add(country);
        }
        peopleNum += union.getPeopleNum();
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
}