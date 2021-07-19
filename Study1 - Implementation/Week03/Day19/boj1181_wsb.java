import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        String nowStr;
        int strLen;
        Set<String> strSet[] = new Set[51];

        //// 오.. 문자열의 길이 제약 조건을 이용해 시간 최적화하는 로직 좋네요
        //// TreeSet기능 처음보는데 검색정렬에 효율적인거라고 나오네요! 역시 자바기능을 잘 활용하십니당.
        for(int i = 0; i < N; i++){
            nowStr = br.readLine();
            strLen = nowStr.length();
            if(strSet[strLen] == null) strSet[strLen] = new TreeSet<>();
            strSet[strLen].add(nowStr);
        }

        for(int i = 1; i < 51; i++){
            if(strSet[i] != null) sb.append(strSet[i].stream().collect(Collectors.joining("\n")) + "\n");
        }
        System.out.print(sb);
    }
}
