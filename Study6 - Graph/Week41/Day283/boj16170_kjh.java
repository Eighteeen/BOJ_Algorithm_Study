import java.time.LocalDate;

class Main {
  public static void main(String[] args) throws Exception {
    LocalDate now = LocalDate.now();

    System.out.println(now.getYear());
    System.out.println(now.getMonthValue());
    System.out.println(now.getDayOfMonth());
  }
}