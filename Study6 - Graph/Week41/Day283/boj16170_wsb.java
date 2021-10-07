import java.text.SimpleDateFormat;
import java.util.TimeZone;

//// 깔끔
class Main {
    public static void main(String[] args) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy\nMM\ndd");
        format.setTimeZone(TimeZone.getTimeZone("UTC"));
        System.out.println(format.format(System.currentTimeMillis()));
    }
}