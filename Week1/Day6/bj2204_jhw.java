package Day6;

import java.util.Scanner;

public class bj2204_jhw {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String firstWord;
        String compareWord;

        while(true) {
        int n = sc.nextInt();
        if(n == 0) {
            return;
        }

        firstWord = sc.next();	
        for(int i = 1; i < n; i++) {
            compareWord = sc.next();
            if(compareWord.compareToIgnoreCase(firstWord) < 0) {
                firstWord= compareWord;
            }
        }
        System.out.println(firstWord);
        }
    }
}
