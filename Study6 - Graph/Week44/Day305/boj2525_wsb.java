import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//// ㄲㄲ
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] timeInfo = br.readLine().split(" ");
        int startHour = Integer.parseInt(timeInfo[0]);
        int startMinute = Integer.parseInt(timeInfo[1]);
        int cookMinute = Integer.parseInt(br.readLine());

        int endMinute = startMinute + cookMinute;
        int endHour = startHour + endMinute / 60;
        endHour %= 24;
        endMinute %= 60;

        System.out.println(String.format("%d %d", endHour, endMinute));
        br.close();
    }
}