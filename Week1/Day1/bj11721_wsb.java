import java.util.Scanner;

//// 워.. 10글자씩 아예 한번에 출력해버리는 효율성 높은 알고리즘이랑 Math.min 활용 배워갑니다:22 감탄스러운 코딩이네요 저도 배워갑니다
class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        int strlen = str.length();
        int index = 0;

        while (index <= strlen){
            System.out.println(str.substring(index, Math.min(index += 10, strlen)));
        }
    }
}
