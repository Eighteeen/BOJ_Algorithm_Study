import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

//// 깔꼼합니다!
//// compareTo를 구현하면 객체 배열 정렬할때 기준이 돼주는군요
class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        Point[] points = new Point[N];
        for(int i = 0; i < N; i++){
            String[] pointInfo = br.readLine().split(" ");
            points[i] = new Point(Integer.parseInt(pointInfo[0]), Integer.parseInt(pointInfo[1]));
        }

        Arrays.sort(points);
        Arrays.stream(points).forEach(p -> sb.append(p.toString()).append("\n"));

        System.out.print(sb);
        br.close();
    }
}

class Point implements Comparable<Point>{
    public int x;
    public int y;

    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public int compareTo(Point p){
        if(this.y > p.y) return 1;
        else if(this.y == p.y){
            if(this.x > p.x) return 1;
            else if(this.x == p.x) return 0;
        }
        return -1;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(x).append(" ").append(y);
        return sb.toString();
    }
}