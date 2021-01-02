package Day5;

import java.util.Scanner;

public class bj10173_jhw {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			String str = sc.nextLine();
			
			if(str.contains("NEMO")||str.contains("NEMo")||str.contains("NEmO")||str.contains("NeMO")||str.contains("nEMO")
					||str.contains("neMO")||str.contains("nEmO")||str.contains("nEMo")||str.contains("nemO")||str.contains("neMo")
					||str.contains("NemO")||str.contains("NeMo")||str.contains("NEmo")||str.contains("Nemo")||str.contains("nemo")) {
				System.out.println("Found");
			}else {
				System.out.println("Missing");
			}
		
		}

	}

}
