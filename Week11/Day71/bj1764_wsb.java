import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String[] unInfo = br.readLine().split(" ");
        int N = Integer.parseInt(unInfo[0]), M = Integer.parseInt(unInfo[1]);
        int peopleNum = N + M;
        String[] people = new String[peopleNum];
        for(int i = 0; i < peopleNum; i++) people[i] = br.readLine();

        List<String> unHeardUnSeedList = overlapList(people);
        sb.append(unHeardUnSeedList.size()).append("\n");
        unHeardUnSeedList.forEach(u -> sb.append(u).append("\n"));

        System.out.print(sb);
        br.close();
    }

    static List overlapList(String[] strArr){
        String[] sortedArr = strArr.clone();
        Arrays.sort(sortedArr);

        List<String> overlapList = new ArrayList<>();
        int len = sortedArr.length;
        String compare = sortedArr[0];
        for(int i = 1; i < len; i++){
            String current = sortedArr[i];
            if(compare.equals(current)) overlapList.add(compare);
            compare = current;
        }
        return overlapList;
    }
}