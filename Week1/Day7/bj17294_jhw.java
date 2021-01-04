package Day7;
import java.util.Scanner;

public class bj17294_jhw {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String st = sc.next();
		//// st는 split되기 위해서만 사용되는데, 따로 변수를 만들어 줄 필요 있었을까 싶습니다.
		String []str = st.split("");
		//// str보다 result 등의 의미 있는 이름을 사용했으면 좋았을 것 같습니다. 아래의 d와 a도 그렇고요
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


