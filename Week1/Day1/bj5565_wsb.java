import java.util.Scanner;

//// 흠 잡을 데 없이 깔끔한 것 같습니다.:22 코딩 장인이신가요??
class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int price = sc.nextInt();

        for(int i = 0; i < 9; i++){
            price -= sc.nextInt();
        }
        
        System.out.println(price);
    }
}
