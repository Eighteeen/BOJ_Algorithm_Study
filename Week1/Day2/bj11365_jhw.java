package Day2;

import java.util.Scanner;

public class bj11365_jhw {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		String st;
	
		while(true) {
			
			st = sc.nextLine();
			
			for(int i =st.length()-1;i>=0;i--) {
				
				
			 if(st.length()>500) {
					
					return;
				}
				
			 if(st.equals("END")|| st.equals("end")) {
				 
				 return;
			 }else {
				 System.out.print(st.charAt(i));
				 continue;
			 }
		
			}
			System.out.println();

            sc.close();
        }
		
		
		

	}

}
