import java.util.Scanner;

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