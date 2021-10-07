import java.time.LocalDate;

//// 깔끔 : 22
class Main {
  public static void main(String[] args) throws Exception {
    LocalDate now = LocalDate.now();

    System.out.println(now.getYear());
    System.out.println(now.getMonthValue());
    System.out.println(now.getDayOfMonth());
  }
}