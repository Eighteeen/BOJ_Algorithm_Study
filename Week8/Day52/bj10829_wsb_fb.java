import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long N = Long.parseLong(br.readLine());

        System.out.print(convertToBinary(N, 0));
        br.close();
    }

    //// type은 long이고 이름은 binary이길래 10101010 이런 숫자로 직접 저장을 하신건가? 했는데 아녔네요
    //// notConverted, converted 와 같은 작명은 어떨까요?
    //// -> 조언 주신 변수명도 좋지만 뭔가 저에겐 한번 더 해석을 해야하는 느낌이라서 checkNum으로 간단하게 바꿔봤어요!
    static StringBuilder convertToBinary(long num, long checkNum){
        //// 오호 저도 이렇게 하려다가 이러면 재귀호출마다 새 객체 만들게돼서 비효율적인거 아닐까? 했는데 랭킹 1위시네요 ㄷㄷ
        StringBuilder sb = new StringBuilder();
        if(checkNum == 0){
            if(num == 0) return sb;
            checkNum = rootPowerNCloseToNum(num, 2);
        }

        num -= checkNum;
        if(num >= 0) sb.append("1");
        else{
            num += checkNum;
            sb.append("0");
        }

        return sb.append(convertToBinary(num, checkNum / 2));
    }

    //// 함수의 이름이 어떤걸 표현하는건지 알기 어려웠어요 ㅠㅠ
    //// -> 바꿔봤어요!
    static long rootPowerNCloseToNum(long num, long root){
        long squared = 1;
        while(squared <= num) squared *= root;
        squared /= root;

        return squared;
    }
}