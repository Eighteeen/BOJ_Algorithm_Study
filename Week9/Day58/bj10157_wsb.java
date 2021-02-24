import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] hallInfo = br.readLine().split(" ");
        int C = Integer.parseInt(hallInfo[0]);
        int R = Integer.parseInt(hallInfo[1]);
        int K = Integer.parseInt(br.readLine());

        if(K > C * R){
            System.out.print(0);
            return;
        }

        Hall concertHall = new Hall(C, R);
        Seat orderSeat = concertHall.orderSeat(K);

        StringBuilder sb = new StringBuilder();
        sb.append(orderSeat.h).append(" ").append(orderSeat.v);
        System.out.print(sb);
        
        br.close();
    }
}

enum Direction{
    UP, RIGHT, DOWN, LEFT;
}

class Hall{
    private Direction direction = Direction.UP;
    private Seat maxSeat;
    private Seat orderSeat;

    public Hall(int maxHorizontal, int maxVertical){
        maxSeat = new Seat(maxHorizontal, maxVertical);
    }

    public Seat orderSeat(int order){
        if(orderSeat == null) orderSeat = new Seat(maxSeat);

        int compare = compareSeat(order);
        if(compare == 0) return searchSeat(order);

        return orderSeat(order - compare);
    }

    private Seat searchSeat(int order){
        Seat resultSeat = new Seat(0, 0);
        int horizontalGap = maxSeat.h - orderSeat.h;
        int verticalGap = maxSeat.v - orderSeat.v;

        switch(direction){
            case UP:
                resultSeat.h = horizontalGap / 2 + 1;
                resultSeat.v = verticalGap / 2 + order;
                break;
            case RIGHT:
                resultSeat.h = horizontalGap / 2 + order + 1;
                resultSeat.v = maxSeat.v - verticalGap / 2;
                break;
            case DOWN:
                resultSeat.h = maxSeat.h - horizontalGap / 2;
                resultSeat.v = maxSeat.v - verticalGap / 2 - order;
                break;
            case LEFT:
                resultSeat.h = maxSeat.h - horizontalGap / 2 - order + 1;
                resultSeat.v = verticalGap / 2 + 1;
                break;
        }

        orderSeat = null;
        return resultSeat;
    }

    private int compareSeat(int order){
        int horizontal = orderSeat.h, vertical = orderSeat.v;
        if((direction == Direction.UP || direction == Direction.DOWN) && vertical >= order) return 0;
        if((direction == Direction.RIGHT || direction == Direction.LEFT) && horizontal >= order) return 0;
        
        int compare = vertical;
        if(compare < order && order < compare + horizontal){
            changeSeat(1, 0, Direction.RIGHT);
            return compare;
        }
        
        compare += horizontal - 1;
        if(compare < order && order < compare + vertical){
            changeSeat(1, 1, Direction.DOWN);
            return compare;
        }
        
        compare += vertical - 1;
        if(compare < order && order < compare + horizontal - 1){
            changeSeat(2, 1, Direction.LEFT);
            return compare;
        }

        compare += horizontal - 2;
        changeSeat(2, 2, Direction.UP);
        return compare;
    }

    private void changeSeat(int minusH, int minusV, Direction applyD){
        orderSeat.h -= minusH;
        orderSeat.v -= minusV;
        direction = applyD;
    }
}

class Seat{
    public int h = 0;
    public int v = 0;

    public Seat(int h, int v){
        this.h = h;
        this.v = v;
    }

    public Seat(Seat seat){
        this.h = seat.h;
        this.v = seat.v;
    }
}