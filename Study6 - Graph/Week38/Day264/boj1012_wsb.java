import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//// 깔끔 : 22
class Main {
    static int[] dx = {-1, 0, 1, 0}, dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            String[] farmInfo = br.readLine().split(" ");
            int M = Integer.parseInt(farmInfo[0]);
            int N = Integer.parseInt(farmInfo[1]);
            int K = Integer.parseInt(farmInfo[2]);
            
            List<Coordinate> cabbageCoordinates = new ArrayList<>();

            while (K-- > 0) {
                String[] cabbageCoordinate = br.readLine().split(" ");
                int x = Integer.parseInt(cabbageCoordinate[0]);
                int y = Integer.parseInt(cabbageCoordinate[1]);

                cabbageCoordinates.add(Coordinate.twoPointOf(x, y));
            }

            sb.append(getCntNeedEarthworm(cabbageCoordinates)).append("\n");
        }

        System.out.print(sb);
        br.close();
    }

    static int getCntNeedEarthworm(List<Coordinate> cabbageCoordinates) {
        int cnt = 0;

        while (!cabbageCoordinates.isEmpty()) {
            Queue<Coordinate> coordinateQueue = new LinkedList<>();
            coordinateQueue.offer(cabbageCoordinates.remove(0));
            cnt++;
    
            while (!coordinateQueue.isEmpty()) {
                Coordinate coordinate = coordinateQueue.poll();
    
                int x = coordinate.getX(), y = coordinate.getY();
                for (int i = 0; i < 4; i++) {
                    int nx = x + dx[i], ny = y + dy[i];
                    Coordinate nCoordinate = Coordinate.twoPointOf(nx, ny);
                    if (cabbageCoordinates.remove(nCoordinate)) coordinateQueue.offer(nCoordinate);
                }
            }
        }

        return cnt;
    }
}

class Coordinate {
    private int x, y;

    //// 이욜..
    public static Coordinate twoPointOf(int x, int y) {
        return new Coordinate(x, y);
    }

    private Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() { return x; }
    public int getY() { return y; }

    //// equals는 어디에 쓰는건가요??
    //// -> cabbageCoordinates에서 객체를 확인할 때 쓰이고 있습니다! (remove)
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