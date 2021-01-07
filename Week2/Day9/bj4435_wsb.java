import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main{
    ////enum을 이렇게 활용할 수도 있군요 배워갑니다
    //// enum 사용과 enum에 대한 sum 메소드까지.. 자바 기능을 정말 잘 이해하고 활용하시는 것 같아요. 저도 이렇게 바꿔야겠습니다. 배워가요
    enum Gandalf{
        HOBBIT(1), HUMAN(2), ELF(3), DWARVE(3), EAGLE(4), WIZARD(10);
        private final int value;
        Gandalf(int value) { this.value = value; }
        static int len = values().length;
        static int sumArmy(int Army[]) {
            int sum = 0;
            for (int i = 0; i < len; i++) {
                sum += values()[i].value * Army[i];
            }
            return sum;
        }
    }
    enum Sauron{
        OAK(1), HUMAN(2), WARG(2), GOBLIM(2), URUKHAI(3), TROLL(5), WIZARD(10);
        private final int value;
        Sauron(int value) { this.value = value; }
        static int len = values().length;
        static int sumArmy(int Army[]){
            int sum = 0;
            for(int i = 0; i < len; i++){
                sum += values()[i].value * Army[i];
            }
            return sum;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine()) + 1;
        int GandalfArmy[], SauronArmy[];
        int GandalfSum, SauronSum;

        for(int i = 1; i < T; i++){
            ////이 부분은 입력값들을 split으로 분리하고 키와 value값을 받아와 배열로 만드는건가요??
            GandalfArmy = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            SauronArmy = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            GandalfSum = Gandalf.sumArmy(GandalfArmy);
            SauronSum = Sauron.sumArmy(SauronArmy);

            if(GandalfSum > SauronSum){
                sb.append("Battle ").append(i)
                    .append(": Good triumphs over Evil\n");
            }else if(GandalfSum < SauronSum){
                sb.append("Battle ").append(i)
                    .append(": Evil eradicates all trace of Good\n");
            }else{
                sb.append("Battle ").append(i)
                    .append(": No victor on this battle field\n");
            }
        }
        System.out.print(sb);
    }
}
