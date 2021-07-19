import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//// 전체적으로 코드가 깔끔해서 좋았습니다
//// Math Math Math가 난무해서 제 머리로는 어떤 원리로 돌아가는지는 이해할 수 없었읍니다..
//// -> 로직에만 집중하다가 작명 센스를 잃어버렸네요..ㅠㅠ

//// -> 로직은 현재 원하는 n번째까지 code를 몇번 반복하는지 구해, 해당 반복시에 문자가 회전된 위치를 구하고
//// -> 회전된 위치와 원하는 위치가 같다면 바로 전 위치가 같은 문자일테니 그 전 위치를 구하고,
//// -> 위치가 같지 않다면 왼쪽과 오른쪽의 문자열이 같으니 원하는 위치에서 회전된 위치를 빼서 그 위치를 구합니다.
//// -> 반복해서 기본 code에서 구할 수 있는 위치라면 그 위치를 반환합니다.

//// -> 기본적인 로직의 흐름은 kjh과 비슷한데, 최소 몇번 반복하는지를 먼저 구해서 그 부분이 다른 것 같네요.
class Main {
    static float baseLen;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
        String[] input = br.readLine().split(" ");
        String baseCode = input[0];
        baseLen = baseCode.length();
        long wantNth = Long.parseLong(input[1]);

        System.out.println(baseCode.charAt(nthLetterInCode(wantNth) - 1));
        br.close();
    }

    static int nthLetterInCode(long nthLetter) {
        if (nthLetter <= baseLen) return (int) nthLetter;
    
        long nthLastRotated = nthLastRotated(nthLetter);
        if (nthLetter == nthLastRotated) return nthLetterInCode(nthLetter - 1);
        return nthLetterInCode(nthLetter - nthLastRotated);
    }

    //// 1조번째의 글자(nthLetter)를 1조번 돌린 것?(nthRotated)
    //// 어떤걸 의도하는 내용인지 알기 어려워요 ㅠㅠ
    //// -> 마지막으로 회전된 문자가 몇번째에 있는지 알아보기 위한 함수입니다. 함수 이름을 너무 간결하게 지은 것 같네요.
    //// -> last를 더하니 의미가 명확해져 nthLastRotated로 변경하고, nthLetter보다 nthWonder이 매개변수 이름으로 더 자연스러운 것 같아 바꿔봤습니다.
    static long nthLastRotated(long nthWonder) {
        return (long) (Math.pow(2, howManyRepeat(nthWonder) - 1) * baseLen) + 1; //// 누구보다 수학을 사랑하시는 승빈씨..
    }

    //// nthLetter를 어떻게 반복(repetition)하는거고 왜 int로 반환되는지 이름만으로 알기 어려운 것 같아요
    //// -> nthLetter까지 문자열을 늘릴려면 몇번 반복해야 하는지 알아내는 함수입니다.
    //// -> 너무 단순하게 이름을 지은 것 같네요 좀 더 명확하게 howManyRepeat로 바꾸고, 매개변수 이름또한 변경해봤어요.
    static int howManyRepeat(long nthWonder) {
        return (int) Math.ceil(Math.log(nthWonder / baseLen) / Math.log(2));
    }
}