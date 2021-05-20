import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

//// 알고리즘이 효율적으로 되게 잘 짜여진 것 같아요!
//// 연산결과를 저장하는게 꽤 시간단축을 이룬 것 같고
//// 밑이 아무리 커도 결국엔 2~8의 밑으로 변환된다는 점을 이용한점이 인상깊었습니다

//// 근데 개인적으로 좀 읽기 어려웠어요 ㅠㅠ
//// List는 왜 쓰는건지, 왜 size 8 배열을 만드는지, [base-2]로 접근하는 이유는 뭔지, 오버로딩된 함수(List<>, int)가 하는 역할이 원 함수와 조금 다르다는 사실을 알기까지 꽤 걸린 것 같습니다..
//// 여기에 대해서 변수명과 함수명으로 어떤 힌트가 있었으면 좋겠어요!
//// -> 친절하지 못 한 코드였던 것 같습니다. 피드백 반영했어요!
class Main {
    static final int BASE_SIZE_TO_STORE = 8;
    static final int MIN_BASE_TO_STORE = 2;
    static List<Integer>[] lastProcessListArr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        lastProcessListArr = new ArrayList[BASE_SIZE_TO_STORE];

        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            String[] testInfo = br.readLine().split(" ");
            sb.append(lastProcessComputer(Integer.parseInt(testInfo[0]), Integer.parseInt(testInfo[1])))
                .append("\n");
        }

        System.out.print(sb);
        br.close();
    }

    static int lastProcessComputer(int base, int exponent) {
        base %= 10;
        if (base == 1) return 1;
        if (base == 0) return 10;

        List<Integer> lastProcessList = lastProcessListArr[base - MIN_BASE_TO_STORE];
        if (lastProcessList != null) return getStoredLastProcess(lastProcessList, exponent);

        lastProcessList = new ArrayList<>();
        int lastProcess = base;
        do {
            lastProcessList.add(lastProcess);
            lastProcess = lastProcess * base % 10;
        } while (lastProcess != base);

        return getStoredLastProcess(lastProcessList, exponent);
    }

    //// getStoredLastProcess는 어때요
    //// -> public, private로 다른 클래스에서 사용했다면 그대로 유지했을 것 같은데 main 클래스내에서 사용해서 혼동될 수 있네요.
    //// -> 제시한 함수명 이름이 명확하게 전달되는 것 같아 그대로 반영했습니다!
    static int getStoredLastProcess(List<Integer> lastProcessList, int processLoop) {
        int loopSize = lastProcessList.size();
        processLoop %= loopSize;
        return lastProcessList.get((processLoop == 0 ? loopSize : processLoop) - 1);
    }
}