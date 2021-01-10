package Day14;

import java.util.Scanner;

public class bj10250_jhw {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int number =sc.nextInt();
		for(int i = 0 ; i < number;i++) {
			int ho = 1;
            int floor = 1;
            
			int H = sc.nextInt();
			int W = sc.nextInt();
            int N = sc.nextInt();
			String[]hotel = new String[H*W+1];
			
			for(int j = 1; j < a.length; j++) {
				if(ho < 10) {
                    hotel[j] = floor + "0" + ho;
                }else {
					hotel[j] = floor + "" + ho;
                }

                if(floor >= H) {
					floor = 1;
					ho += 1;
					if(ho > W) {
						ho = 1;
					}
				}else {
					floor++;
				}	
			}
			System.out.println(hotel[N]);
			
			
		}
		
	}

}
