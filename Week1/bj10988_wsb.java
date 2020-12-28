import java.util.Scanner;

class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        String strCompare;

        strCompare = new StringBuffer(str).reverse().toString();
        System.out.print(str.equals(strCompare) ? 1 : 0);
    }
}