import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Queue;
import java.util.LinkedList;
//// 조금은 복잡한거 같기도 해요.
//// 클래스 분류로 메인이 정말 깔끔해지고 의미를 알아보기 쉽네요 객체지향 활용 굿굿!!
//// 전체적으로 활용성이 좋은 프로그램을 구현하신 것 같습니다 굿굿굿
class Main {
  public static void main(String[] args) throws Exception {
    final int BLUE_REQUIRED_SECONDS = Input.nextInt();
    final int RED_REQUIRED_SECONDS = Input.nextInt();
    final int CUSTOMERS = Input.nextInt();

    Worker blueWorker = new Worker(BLUE_REQUIRED_SECONDS);
    Worker redWorker = new Worker(RED_REQUIRED_SECONDS);
    Shop shop = new Shop(blueWorker, redWorker);

    for (int i = 0; i < CUSTOMERS; i++) {
      int second = Input.nextInt();
      char color = Input.nextChar();
      int amount = Input.nextInt();
      shop.giveAnOrder(new Order(second, color, amount));
    }
    shop.processOrder();

    shop.printWorks();
  }
}

class Shop {
  private Queue<Order> orders = new LinkedList<Order>();
  private int giftNumber = 1;
  private Worker blueWorker;
  private Worker redWorker;

  public Shop(Worker blueWorker, Worker redWorker) {
    this.blueWorker = blueWorker;
    blueWorker.employedBy(this);
    this.redWorker = redWorker;
    redWorker.employedBy(this);
  }

  //// class Order의 사용이 매우 좋네요
  public void giveAnOrder(Order order) {
    orders.offer(order);
  }

  public void processOrder() {
    int currentSecond = 1;
    while (existsRemainingWorks()) {
      blueWorker.workFor1Second();
      redWorker.workFor1Second();

      boolean timeToWork = orders.size() > 0 && orders.peek().getSecond() == currentSecond;
      if (timeToWork) {
        Order order = orders.poll();
        if (order.getColor() == 'B') {
          blueWorker.allocateWorks(order.getAmount());
        } else {
          redWorker.allocateWorks(order.getAmount());
        }
      }

      currentSecond++;
    }
  }

  private boolean existsRemainingWorks() {
    return orders.size() > 0 || blueWorker.existsRemainingWorks() || redWorker.existsRemainingWorks();
  }

  public int numbering() {
    return giftNumber++;
  }
  
  public void printWorks() {
    blueWorker.printWorks();
    redWorker.printWorks();
  }
}

class Worker {
  private Queue<Integer> works = new LinkedList<>();
  private int requiredSeconds;
  private int secondsToComplete;
  private int amount;
  private Shop shop;

  public Worker(int requiredSeconds) {
    this.requiredSeconds = requiredSeconds;
    this.secondsToComplete = requiredSeconds;
  }

  public void employedBy(Shop shop) {
    this.shop = shop;
  }

  public void allocateWorks(int amount) {
    this.amount += amount;
  }

  public void workFor1Second() {
    if (amount == 0) return;

    if (requiredSeconds == 0) {
      for (int i = 0; i < amount; i++) {
        //// 이 부분보고 활용성 정말 좋다고 생각했습니다. 작은 부분이지만 객체지향 제대로 활용하시네요
        works.offer(shop.numbering());
      }
      amount = 0;
      return;
    }

    boolean isStartedNow = secondsToComplete == requiredSeconds;
    if (isStartedNow) {
      works.offer(shop.numbering());
    }

    secondsToComplete--;
    if (secondsToComplete == 0) {
      amount--;
      secondsToComplete = requiredSeconds;
    }
  }

  public boolean existsRemainingWorks() {
    return amount > 0;
  }

  public void printWorks() {
    System.out.println(works.size());

    StringBuilder sb = new StringBuilder();
    works.stream()
      .forEach(giftNumber -> sb.append(giftNumber).append(' '));
  
    System.out.println(sb);
  }
}

class Order {
  private int second;
  private char color;
  private int amount;
  
  public Order(int second, char color, int amount) {
    this.second = second;
    this.color = color;
    this.amount = amount;
  }
  
  public int getSecond() {
    return second;
  }

  public char getColor() {
    return color;
  }
  
  public int getAmount() {
    return amount;
  }

  @Override
  public String toString() {
    return "[" + second + ", " + color + ", " + amount + "]";
  }
}

class Input {
  private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  private static StringTokenizer tokenizer;

  public static String nextLine() {
    try {
      return br.readLine();
    } catch(Exception e) { }
    
    return null;
  }

  public static int nextInt() {
    String nextString = next();
    return Integer.parseInt(nextString);
  }

  public static double nextDouble() {
    String nextString = next();
    return Double.parseDouble(nextString);
  }


  public static long nextLong() {
    String nextString = next();
    return Long.parseLong(nextString);
  }

  public static char nextChar() {
    String nextString = next();
    return nextString.charAt(0);
  }
  
  public static String next() {
    makeTokensIfNeed();
    return tokenizer.nextToken();
  }

  private static void makeTokensIfNeed() {
    makeTokensIfNotReadedYet();
    makeTokensIfHasNoToken();
  }

  private static void makeTokensIfNotReadedYet() {
    if (tokenizer == null) {
      tokenizer = makeTokens();
    }
  }

  private static void makeTokensIfHasNoToken() {
    if (tokenizer.hasMoreTokens() == false) {
      tokenizer = makeTokens();
    }
  }
  
  private static StringTokenizer makeTokens() {
    return new StringTokenizer(nextLine(), " ");
  }
}