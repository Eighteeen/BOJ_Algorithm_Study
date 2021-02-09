import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Main{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        List<Point> appleList = getItemPointList();
        Queue<Integer> secondQueue = new LinkedList<>();
        Queue<Character> directionQueue= new LinkedList<>();
        getChangeSkill(secondQueue, directionQueue);

        Snake snake = new Snake(N);
        int currentSecond = 0;
        while(true){
            currentSecond++;
            if(!snake.moveHead()) break;

            int appleIdx = appleList.indexOf(snake.headPoint());
            if(appleIdx > -1) appleList.remove(appleIdx);
            else snake.moveTail();

            if(!secondQueue.isEmpty() && secondQueue.peek() == currentSecond){
                snake.changeDirection(directionQueue.poll());
                secondQueue.poll();
            }
        }

        System.out.print(currentSecond);
        br.close();
    }
    
    static List getItemPointList() throws IOException {
        int K = Integer.parseInt(br.readLine());
        List<Point> itemList = new ArrayList<>();
        for(int i = 0; i < K; i++){
            String[] itemInfo = br.readLine().split(" ");
            itemList.add(new Point(Integer.parseInt(itemInfo[0]) - 1, Integer.parseInt(itemInfo[1]) - 1));
        }
        return itemList;
    }

    static void getChangeSkill(Queue<Integer> timeQueue, Queue<Character> skillQueue) throws IOException {
        int L = Integer.parseInt(br.readLine());
        for(int i = 0; i < L; i++){
            String[] changeSkillInfo = br.readLine().split(" ");
            timeQueue.offer(Integer.valueOf(changeSkillInfo[0]));
            skillQueue.offer(changeSkillInfo[1].charAt(0));
        }
    }
}

enum Direction{
    RIGHT, DOWN, LEFT, UP;
    static int len = values().length;
}

class Snake{
    private boolean[][] isBody;
    private Direction direction = Direction.RIGHT;
    private Deque<Point> bodyDeque = new ArrayDeque<>();

    public Snake(int boardSize){
        isBody = new boolean[boardSize][boardSize];
        isBody[0][0] = true;
        bodyDeque.addFirst(new Point(0, 0));
    }

    public Point headPoint(){
        return bodyDeque.peekFirst();
    }

    public void changeDirection(Character ch){
        int len = Direction.len;
        int idx = direction.ordinal() + len;
        if(ch == 'D') direction = Direction.values()[++idx % len];
        else direction = Direction.values()[--idx % len];
    }

    public boolean moveHead(){
        int x = headPoint().x;
        int y = headPoint().y;

        switch(direction){
            case RIGHT:
                y++;
                break;
            case DOWN:
                x++;
                break;
            case LEFT:
                y--;
                break;
            case UP:
                x--;
                break;
        }
        if(checkGameOver(x, y)) return false;

        isBody[x][y] = true;
        bodyDeque.addFirst(new Point(x, y));
        return true;
    }

    public void moveTail(){
        Point tailPoint = bodyDeque.removeLast();
        isBody[tailPoint.x][tailPoint.y] = false;
    }

    private boolean checkGameOver(int x, int y){
        if(outBoard(x)) return true;
        if(outBoard(y)) return true;
        return isBody[x][y];
    }

    private boolean outBoard(int n){
        int boardSize = isBody.length;
        if(n < 0 || n >= boardSize) return true;
        return false;
    }
}

class Point{
    public int x = 0;
    public int y = 0;

    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void setPoint(int x, int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj) return true;
        if(obj == null) return false;
        if(getClass() != obj.getClass()) return false;

        Point other = (Point)obj;
        if(x != other.x) return false;
        if(y != other.y) return false;
        return true;
    }
}