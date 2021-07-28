import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//// 깔끔해요
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String[] companyPraiseInfo = br.readLine().split(" ");
        int numOfEmployee = Integer.parseInt(companyPraiseInfo[0]);
        int m = Integer.parseInt(companyPraiseInfo[1]);

        //// 0을 추가하는 생각 멋져요
        String directSuperiorInfo = "0 " + br.readLine();
        int[] directSuperiorArr = Arrays.stream(directSuperiorInfo.split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] praiseArr = new int[numOfEmployee + 1];

        while (m-- > 0) {
            String[] employeePraiseInfo = br.readLine().split(" ");
            int empolyeeToPraise = Integer.parseInt(employeePraiseInfo[0]);
            int praiseNum = Integer.parseInt(employeePraiseInfo[1]);

            praiseArr[empolyeeToPraise] += praiseNum;
        }

        praiseDown(numOfEmployee, directSuperiorArr, praiseArr);
        for (int i = 1; i <= numOfEmployee; i++) {
            sb.append(praiseArr[i]).append(" ");
        }
        
        System.out.println(sb);
        br.close();
    }

    //// 아~ 어차피 서열순으로 입력이 들어오니까 그냥 전부 부분합 때려버리면 되군요. 배워가요
    static void praiseDown(int numOfEmployee, int[] directSuperiorArr, int[] praiseArr) {
        for (int i = 2; i <= numOfEmployee; i++) {
            int directSuperior = directSuperiorArr[i];
            praiseArr[i] += praiseArr[directSuperior];
        }
    }
}