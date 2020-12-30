package Day3;



import java.util.Scanner;


public class bj2711_jhw {

    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);

        int num = sc.nextInt();

        for(int i = 0; i < num; i++) {
            int num2 = sc.nextInt();
            String str = sc.next();

            System.out.println(str.substring(0,num2-1)+str.substring(num2));
        }
        
        sc.close();
    }

}
