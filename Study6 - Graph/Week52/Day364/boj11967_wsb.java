import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//// 깔끔해용~
class Main {
    static int barnSize;
    static boolean[][] roomsLightOn;
    static Map<Coordinate, List<Coordinate>> buttonsInRoom;

    static final int dLen = 4;
    static final int[] dx = {-1, 0, 1, 0}, dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] barnInfo = br.readLine().split(" ");
        barnSize = Integer.parseInt(barnInfo[0]);
        int M = Integer.parseInt(barnInfo[1]);

        buttonsInRoom = new HashMap<>();

        for (int i = 0; i < M; i++) {
            String[] roomButtonInfo = br.readLine().split(" ");
            int roomX = Integer.parseInt(roomButtonInfo[0]);
            int roomY = Integer.parseInt(roomButtonInfo[1]);
            int buttonX = Integer.parseInt(roomButtonInfo[2]);
            int buttonY = Integer.parseInt(roomButtonInfo[3]);

            Coordinate room = Coordinate.twoPointOf(roomX, roomY);
            Coordinate button = Coordinate.twoPointOf(buttonX, buttonY);

            if (!buttonsInRoom.containsKey(room)) {
                buttonsInRoom.put(room, new ArrayList<>());
            }
            buttonsInRoom.get(room).add(button);
        }

        System.out.println(getMaxOfTurnOnLightRooms(Coordinate.twoPointOf(1, 1)));
        br.close();
    }

    static int getMaxOfTurnOnLightRooms(Coordinate from) {
        turnOnLightAllPossibleRooms(from);
        return getNumOfLightOnRooms();
    }

    static int getNumOfLightOnRooms() {
        int cnt = 0;
        for (int i = 1; i <= barnSize; i++) {
            for (int j = 1; j <= barnSize; j++) {
                if (roomsLightOn[i][j]) cnt++;
            }
        }
        return cnt;
    }

    static void turnOnLightAllPossibleRooms(Coordinate from) {
        roomsLightOn = new boolean[barnSize + 1][barnSize + 1];
        int[][] visited = new int[barnSize + 1][barnSize + 1];;
        final int VISIT_LATER = 1, VISITED = 2;

        //// Set활용 good~
        Queue<Coordinate> bfsQueue = new LinkedList<>();
        Set<Coordinate> yetVisitedRooms = new HashSet<>();

        int fromX = from.getX(), fromY = from.getY();
        roomsLightOn[fromX][fromY] = true;
        bfsQueue.offer(from);

        while (!bfsQueue.isEmpty()) {
            Coordinate current = bfsQueue.poll();
            int x = current.getX(), y = current.getY();
            visited[x][y] = VISITED;

            for (int i = 0; i < dLen; i++) {
                int nx = x + dx[i], ny = y + dy[i];
                if (isOutOfSize(nx, ny, barnSize) || visited[nx][ny] >= VISIT_LATER) continue;
                
                visited[nx][ny] = VISIT_LATER;
                yetVisitedRooms.add(Coordinate.twoPointOf(nx, ny));
            }

            lightOnWithButtonsHas(current);

            Set<Coordinate> mustVisitRooms = new HashSet<>();
            for (Coordinate room : yetVisitedRooms) {
                if (isLightOnRoom(room)) {
                    mustVisitRooms.add(room);
                }
            }
            
            for (Coordinate room : mustVisitRooms) {
                bfsQueue.offer(room);
                yetVisitedRooms.remove(room);
            }
        }
    }

    static boolean isLightOnRoom(Coordinate room) {
        return roomsLightOn[room.getX()][room.getY()];
    }

    static void lightOnWithButtonsHas(Coordinate room) {
        if (!buttonsInRoom.containsKey(room)) return;

        for (Coordinate button : buttonsInRoom.get(room)) {
            roomsLightOn[button.getX()][button.getY()] = true;
        }
    }

    static boolean isOutOfSize(int x, int y, int size) {
        return isOutOfSize(x, barnSize) || isOutOfSize(y, barnSize);
    }

    static boolean isOutOfSize(int point, int size) {
        return (point < 0 || point > size);
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

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}