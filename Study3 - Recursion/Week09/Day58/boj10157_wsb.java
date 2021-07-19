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

        //// 객체지향적으로 정말 잘 작성하신 것 같습니다. 리스펙! (영화 기생충 톤)
        Hall concertHall = new Hall(C, R);
        Seat orderSeat = concertHall.orderSeat(K);

        //// 편하게 바로 System.out 때려도 괜찮았을 것 같아요! 출력하는 양이 매우 적고 고정적이니까요
        StringBuilder sb = new StringBuilder();
        //// Seat에 toString을 만들면 편하고 가독성도 더 좋을 것 같아요!
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

    //// 함수 분리도 너무 잘해두신 것 같아요!
    //// public private 구분도 굳
    public Seat orderSeat(int order){
        if(orderSeat == null) orderSeat = new Seat(maxSeat);

        //// '좌석을 비교한다? 무엇과 무엇을 비교하는거지? 비교해서 뭘 하려는거지?' 싶었는데
        //// seekSeat라는 네이밍은 어때요? 
        int compare = compareSeat(order);
        if(compare == 0) return searchSeat(order); //// locateSeat 어때요? (지금 네이밍도 좋은데 그냥 욕심 ㅎ)

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