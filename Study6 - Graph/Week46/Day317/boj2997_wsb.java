import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        List<Integer> nums = Arrays.stream(br.readLine().split(" ")).map(Integer::valueOf).sorted().collect(Collectors.toList());
        int frontDifference = nums.get(1) - nums.get(0), backDifference = nums.get(2) - nums.get(1);

        int forgotNum = 0;
        if (frontDifference == backDifference) forgotNum = nums.get(2) + frontDifference;
        else if (frontDifference < backDifference) forgotNum = nums.get(1) + frontDifference;
        else forgotNum = nums.get(0) + backDifference;

        System.out.println(forgotNum);
        br.close();
    }
}