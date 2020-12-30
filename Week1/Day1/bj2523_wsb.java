import java.util.Scanner;

//// String.repeat 배워갑니다 👍:22별출력이 for없이 가능하다니,,,!
class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        //// 변수명을 한 글자로 지으니까 반복문에 사용되는 변수 같아요. lines 등으로 지어주면 더 가독성 좋을 것 같습니다. : 22와동시에 반성하고 갑니다..
        //// -> 짧은 별 찍기 문제라서 입력값 n을 사용했어요. 하지만 그래서 놓칠 수 있는 부분을 짚어주어 감사합니당
        int m = 2 * n;
        for(int i = 1; i < m; i++){
            System.out.print("*".repeat(i <= n ? i : m - i));
            System.out.println();
        }
    }
}
