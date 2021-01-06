package Day9;
import java.util.Scanner;

public class bj17293_jhw {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		int beerNum = sc.nextInt();
		
		for(int i = beerNum ; i  >= 0; i--) {
			//// 백준에서 예외처리는 필요 없습니다! : 22
			if(beerNum > 99 || beerNum < 1) {
				return;
			}
			if( i == 0)  {
				//// 함수로 적절히 나눠주었다면 직관적으로 이해할 수 있었을 것 같습니다. : 22
				if(beerNum == 1) {
					sb.append('\n')
					   .append("No more bottles of beer on the wall, no more bottles of beer.")
					   .append('\n')
					   .append("Go to the store and buy some more, ")
					   .append(beerNum)
					   .append(" bottle of beer on the wall.");
				}
				else {
					sb.append('\n')
					  .append("No more bottles of beer on the wall, no more bottles of beer.")
					  .append('\n')
					  .append("Go to the store and buy some more, ")
					  .append(beerNum)
					  .append(" bottles of beer on the wall.");
				}
				
				
			}
			else if( i == 1) {
				sb
				  .append(i)
				  .append(" bottle of beer on the wall, ")
				  .append(i)
				  .append(" bottle of beer.")
				  .append('\n')
				  .append("Take one down and pass it around, no more bottles of beer on the wall.\n");
			}else if( i == 2) {
				sb
				  .append(i)
				  .append(" bottles of beer on the wall, ")
				  .append(i)
				  .append(" bottles of beer.")
				  .append('\n')
				  .append("Take one down and pass it around, ")
				  .append(i-1)
				  .append(" bottle of beer on the wall.\n\n");
			}else {
				sb
				.append(i)
				  .append(" bottles of beer on the wall, ")
				  .append(i)
				  .append(" bottles of beer.")
				  .append('\n')
				  .append("Take one down and pass it around, ")
				  .append(i-1)
				  .append(" bottles of beer on the wall.\n\n");
			}
		
			
		}
		System.out.println(sb);
		
	
		

	}

}
