import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        long[] courseSchedulesBitmask = new long[N];
        for (int i = 0; i < N; i++) {
            int[] courseInfo = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int k = courseInfo[0];
            courseSchedulesBitmask[i] = bitmaskOfNumArrAllAdd(Arrays.copyOfRange(courseInfo, 1, k + 1));
        }

        int M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            int[] studentInfo = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int p = studentInfo[0];

            long studentScheduleBitmask = bitmaskOfNumArrAllAdd(Arrays.copyOfRange(studentInfo, 1, p + 1));
            sb.append(numOfCourseCanBeRegistered(courseSchedulesBitmask, studentScheduleBitmask)).append("\n");
        }

        System.out.println(sb);
        br.close();
    }

    static int numOfCourseCanBeRegistered(long[] courseSchedulesBitmask, long studentScheduleBitmask) {
        int numOfCourse = 0;
        for (long courseSchedule : courseSchedulesBitmask) {
            if ((courseSchedule & studentScheduleBitmask) == courseSchedule) numOfCourse++;
        }
        return numOfCourse;
    }

    static long bitmaskOfNumArrAllAdd(int[] numArr) {
        long bitmask = 0;
        for (int num : numArr) {
          bitmask |= (1L << num);
        }
        return bitmask;
    }
}