import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int sugarKg = Integer.parseInt(br.readLine());
        int bagDCnt = 0;

        if(divisionFive(sugarKg)) return;

        if(sugarKg / 5 >= 2){
            bagDCnt = sugarKg / 5;
            bagDCnt -= 2;
            sugarKg -= bagDCnt * 5;
        }

        if(divisionThree(sugarKg, bagDCnt)) return;
        
        sugarKg -= 5;
        bagDCnt++;
        
        if(divisionThree(sugarKg, bagDCnt)) return;
        else if(sugarKg == 8) System.out.print(bagDCnt += 2);
        else System.out.print(-1);
    }

    static boolean divisionFive(int num){
        if(num % 5 == 0){
            System.out.print(num / 5);
            return true;
        }
        return false;
    }

    static boolean divisionThree(int num, int cnt){
        if(num % 3 == 0){
            System.out.print(cnt += num / 3);
            return true;
        }
        return false;
    }
}