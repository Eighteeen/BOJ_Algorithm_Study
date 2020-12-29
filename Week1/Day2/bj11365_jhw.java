package Day2;

import java.util.Scanner;

public class bj11365_jhw {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		String str;
	
		while(true) {
			
			str = sc.nextLine();
			
			for(int i =str.length()-1;i>=0;i--) {
				
				
			 if(str.length()>500) {
					
					return;
				}
				
			 if(str.equals("END")|| str.equals("end")) {
				 
				 return;
			 }else {
				 System.out.print(str.charAt(i));
				 continue;
			 }
		
			}
			System.out.println();

            sc.close();
        }
		
		
		

	}

}
