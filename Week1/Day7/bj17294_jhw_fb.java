package Day7;
import java.util.Scanner;

public class bj17294_jhw_fb {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String st = sc.next();
		//// st는 split되기 위해서만 사용되는데, 따로 변수를 만들어 줄 필요 있었을까 싶습니다. : 22
		String []str = sc.next().split("");
        //// str보다 result 등의 의미 있는 이름을 사용했으면 좋았을 것 같습니다. 아래의 d와 a도 그렇고요 : 22
        ////-> 네 알겠습니다.우선 d라고 변수를 선언한것은 등차수열에서 공차를 d로 표현을해서 공차라는걸 알려줄려고 
        ////->d를 썻습니다.
		String result = null;
		int d = 0;
		boolean a = false;
		if(str.length == 1) {
			d = Integer.parseInt(str[0]);
			str1 ="◝(⑅•ᴗ•⑅)◜..°♡ 뀌요미!!";
		}else {
			d = Integer.parseInt(str[0])-Integer.parseInt(str[1]);
			for(int i = 1; i <str.length;i++) {
                ////i값과 다음값의 차이가 d와 같은지 비교해주면 좀 더 간단할거 같아요
                ////->그렇게도 해봤었는데 1112111이런식으로 입력하면 원하는 값이 안나와서 이런식으로 했습니다!
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


