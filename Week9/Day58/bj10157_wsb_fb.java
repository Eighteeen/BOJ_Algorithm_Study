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
        //// -> 뿌듯합니다 ㅎㅎㅎ 감사해요!
        Hall concertHall = new Hall(C, R);
        Seat orderSeat = concertHall.orderSeat(K);

        //// 편하게 바로 System.out 때려도 괜찮았을 것 같아요! 출력하는 양이 매우 적고 고정적이니까요
        //// -> 이것은 저의 랭킹 욕심...ㅋㅋㅋㅋㅋㅋ큐ㅠ 밑에 toString에서도 그냥 썼어요 ㅎㅎ,,,
        //// Seat에 toString을 만들면 편하고 가독성도 더 좋을 것 같아요!
        //// -> 너무 좋네요! 적용해봤어요
        System.out.print(orderSeat.toString());
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
        //// -> 오호 목표물 탐색! 사실 이 단어를 처음봤어요 직관적이라고 생각되어서 그대로 적용해봤습니다!
            //// -> (해당 메소드 안에 compare 변수는 비교라는 의미가 잘 통한다고 생각해서 그대로 두었습니다.)
        int passNum = seekSeat(order);
        if(passNum == 0) return locateSeat(order);

        return orderSeat(order - passNum);
    }

    //// locateSeat 어때요? (지금 네이밍도 좋은데 그냥 욕심 ㅎ)
    //// -> 위에서 피드백 주신 seekSeat를 적용하면 searchSeat와 의미가 비슷해져서 혼란스러울 것 같았는데,
        //// -> 이 부분도 피드백 주신 locate를 사용하면 의미가 더 명확해져 같이 적용하면 좋을 것 같아 그대로 적용해봤습니다!
    //// -> 멋진 네이밍 피드백 감사합니다 ^^
    private Seat locateSeat(int order){
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

    private int seekSeat(int order){
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(h).append(" ").append(v);
        return sb.toString();
    }
}