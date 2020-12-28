import java.util.Scanner;

//// String.repeat 배워갑니다 👍
class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        //// 변수명을 한 글자로 지으니까 반복문에 사용되는 변수 같아요. lines 등으로 지어주면 더 가독성 좋을 것 같습니다.
        int m = 2 * n;
        for(int i = 1; i < m; i++){
            System.out.print("*".repeat(i <= n ? i : m - i));
            System.out.println();
        }
    }
}