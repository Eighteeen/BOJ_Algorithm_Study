package 알고르즘;
import java.util.Scanner;

public class bj17293_jhw {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		int beerNum = sc.nextInt();
		
		for(int i = beerNum ; i  >= 0; i--) {
            //// 백준에서 예외처리는 필요 없습니다! : 22
            ////->넵 지우겠습니다
		
			if( i == 0)  {
                //// 함수로 적절히 나눠주었다면 직관적으로 이해할 수 있었을 것 같습니다. : 22
                ////->수정했습니다.
				if(beerNum == 1) {
					sb.append(bottle(beerNum));
				}
				else {
					sb.append(bottle(beerNum));
				}
			}
			else if( i == 1) {
				sb.append(bottles(i));
				
			}else if( i == 2) {
				sb.append(bottles(i));
				  
			}else {
				sb.append(bottles(i));
			}
		}
		System.out.println(sb);
    }
    private static String bottles(int num){
    	String str;
    	 if(num == 1) {
    		str = num + " bottle of beer on the wall, "
    		         + num +  " bottle of beer.\nTake one down and pass it around, no more bottles of beer on the wall.\n";
    		         
    	}
    	else if(num == 2) {
    		str = num + " bottles of beer on the wall, "
    		         + num +  " bottles of beer.\nTake one down and pass it around, "
    		          +  (num-1) +  " bottle of beer on the wall.\n\n";
    		        
    	}
    	else {
         str = num + " bottles of beer on the wall, "
         + num +  " bottles of beer.\nTake one down and pass it around, "
          +  (num-1) +  " bottles of beer on the wall.\n\n";
        
    	}
    	return str;
    }
    
    private static String bottle(int num) {
    	String str;
    	if(num == 1) {
    	str = "\nNo more bottles of beer on the wall, no more bottles of beer.\nGo to the store and buy some more, "
    					+num+" bottle of beer on the wall.";
    	}else {
    		str = "\nNo more bottles of beer on the wall, no more bottles of beer.\nGo to the store and buy some more, "
					+num+" bottles of beer on the wall.";
    	}
    	return str;
    }

}
