import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        Deque<Balloon> balloons = new ArrayDeque<>();
        for(int i = 1; i <= N; i++){
            balloons.add(new Balloon(i, Integer.parseInt(st.nextToken())));
        }

        for(int i = 0; i < N; i++){
            sb.append(getBurstSequence(balloons)).append(" ");
        }

        System.out.print(sb);
        br.close();
    }

    static int getBurstSequence(Deque<Balloon> balloons){
        Balloon balloon = balloons.removeFirst();
        int num = balloon.burstBalloon();
        int size = balloons.size();

        if(!balloons.isEmpty()){
            if(num > 0){
                num %= size;
                if(num == 0) moveToLeft(balloons, 1);
                else moveToRight(balloons, num);
            }else{
                num = Math.abs(num) % size;
                moveToLeft(balloons, num);
            }
        }

        return balloon.sequence;
    }

    static void moveToRight(Deque objects, int num){
        for(int i = 1; i < num; i++){
            objects.addLast(objects.removeFirst());
        }
    }
    
    static void moveToLeft(Deque objects, int num){
        for(int i = 0; i < num; i++){
            objects.addFirst(objects.removeLast());
        }
    }
}

class Balloon{
    public int sequence;
    private int paperNum;

    public Balloon(int sequence, int paperNum){
        this.sequence = sequence;
        this.paperNum = paperNum;
    }

    public int burstBalloon(){
        return paperNum;
    }
}