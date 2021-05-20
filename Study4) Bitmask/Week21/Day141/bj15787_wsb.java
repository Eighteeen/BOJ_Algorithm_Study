import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] conditionInfo = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int N = conditionInfo[0], M = conditionInfo[1];

        Train[] trains = new Train[N];
        for (int i = 0; i < N; i++) trains[i] = new Train(20);

        for (int i = 0; i < M; i++){
          int[] commandInfo = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
          int commandNum = commandInfo[0];
          int trainIdx = commandInfo[1] - 1;

          switch (commandNum) {
            case 1 :
              trains[trainIdx].getOn(commandInfo[2]);
              break;
            case 2 :
              trains[trainIdx].getOff(commandInfo[2]);
              break;
            case 3 :
              trains[trainIdx].moveAllBackward();
              break;
            case 4 :
              trains[trainIdx].moveAllForward();
              break;
          }
        }

        System.out.println(cntTrainsCanCrossGalaxy(trains));
        br.close();
    }

    //// [참고] Arrays.stream(trains).distinct().count()로도 가능함다
    static int cntTrainsCanCrossGalaxy(Train[] trains) {
      return new HashSet<>(Arrays.asList(trains)).size();
    }
}

//// 객체지향 활용 굳굳
class Train {
  private int seatSize;
  private int bitmaskRange;
  private int bitmaskSeat = 0;

  public Train(int seatSize) {
    this.seatSize = seatSize;
    bitmaskRange = 1 << seatSize;
  }

  public void getOn(int seatNum) {
    bitmaskSeat |= (1 << --seatNum);
  }

  public void getOff(int seatNum) {
    bitmaskSeat &= ~(1 << --seatNum);
  }

  public void moveAllBackward() {
    bitmaskSeat <<= 1;
    if (bitmaskSeat >= bitmaskRange) bitmaskSeat -= bitmaskRange;
  }

  public void moveAllForward() {
    bitmaskSeat >>= 1;
  }

  @Override
  public boolean equals(Object obj){
    if(this == obj) return true;
    if(obj == null) return false;
    if(getClass() != obj.getClass()) return false;

    Train other = (Train)obj;
    if(seatSize != other.seatSize) return false;
    if(bitmaskSeat != other.bitmaskSeat) return false;
    return true;
  }

  @Override
  public int hashCode() {
    return Objects.hash(seatSize, bitmaskSeat);
  }
}