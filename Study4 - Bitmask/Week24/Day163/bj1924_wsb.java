import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

//// 깔꼼~
class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        final int year = 2007;

        //// 해당 날짜 date 구하는 방법이 독특하네요
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyM dd");
        Date date = dateFormat.parse(year + br.readLine());

        SimpleDateFormat dayOfWeekFormat = new SimpleDateFormat("EEE");
        System.out.println(dayOfWeekFormat.format(date).toUpperCase());

        br.close();
    }
}