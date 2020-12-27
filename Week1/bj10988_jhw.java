
import java.util.Scanner;

public class bj10988_jhw {

	public static void main(String[] args) {
	
		Scanner sc = new Scanner(System.in);
		String str  = sc.next();
		

	    String sum="";
			
		for(int i = str.length()-1;i>=0;i--) {
        
            sum+=str.charAt(i);
		}
	
		if(str.equals(sum)) {
			System.out.println(1);
		}else {
			System.out.println(0);
        }
        
        sc.close();
	}

}
