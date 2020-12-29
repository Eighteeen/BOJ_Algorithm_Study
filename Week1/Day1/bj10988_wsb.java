import java.util.Scanner;

//// 효율성에선 조금 아쉽지만 가독성이 굉장히 좋고 깔끔한 것 같아요
class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        String strCompare;

        strCompare = new StringBuffer(str).reverse().toString();
        System.out.print(str.equals(strCompare) ? 1 : 0);
    }
}