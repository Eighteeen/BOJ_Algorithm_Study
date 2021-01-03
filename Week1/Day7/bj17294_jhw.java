package Day7;
import java.util.Scanner;

public class bj17294_jhw {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String st = sc.next();
		String []str = st.split("");
		String str1 = null;
		int d = 0;
		boolean a = false;
		if(str.length == 1) {
			d = Integer.parseInt(str[0]);
			str1 ="◝(⑅•ᴗ•⑅)◜..°♡ 뀌요미!!";
		}else {
			d = Integer.parseInt(str[0])-Integer.parseInt(str[1]);
			for(int i = 1; i <str.length;i++) {
				a =Integer.parseInt(str[0]) - Integer.parseInt(str[i]) == d*i;
				if(a==false) {
					str1 = "흥칫뿡!! <(￣ ﹌ ￣)>";
					break;
				}else {
					str1 = "◝(⑅•ᴗ•⑅)◜..°♡ 뀌요미!!";
				}
			}
		}
		System.out.println(str1);
	}
}


