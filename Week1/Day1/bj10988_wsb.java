import java.util.Scanner;

//// 효율성에선 조금 아쉽지만 가독성이 굉장히 좋고 깔끔한 것 같아요
//// -> 문자열 길이를 둘로 나누어서 검증하는 방법도 해봤는데 reverse 메소드 활용이 효율성이 더 좋아서 채택하였습니다!
class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        String strCompare;
        ////코드가 굉장히 짧네요 대단해요
        strCompare = new StringBuffer(str).reverse().toString();
        System.out.print(str.equals(strCompare) ? 1 : 0);
    }
}
