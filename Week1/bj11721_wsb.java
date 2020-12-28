import java.util.Scanner;

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