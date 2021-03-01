package Day3;



import java.util.Scanner;

public class bj2711_jhw_fb {

    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);

        int num1 = sc.nextInt();

        for(int i = 0; i < num1; i++) {
            int num2 = sc.nextInt();
            //// num1이 없고 num2부터 있으니까 어색해요. num1과 num2로 변수명을 지정하거나 더 의미있는 변수명으로 지정하면 좋을 것 같아요 :22 :33 num1없는게 조금 어색하긴 하네요
            ////->수정했습니다
            String str = sc.next();

            System.out.println(str.substring(0,num2-1) + str.substring(num2));
            //// 메소드들이 겹쳐져 있는 코드여서 + 연산에 공백을 띄워주면 가독성이 더 좋을 것 같아요 :22 :33 동의합니다
            ////->수정 했습니다!
        }
        
        sc.close();
    }

}
