import java.util.Scanner;

class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = 2 * n;
        for(int i = 1; i < m; i++){
            System.out.print("*".repeat(i <= n ? i : m - i));
            System.out.println();
        }
    }
}