import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Stack;

class Main {
  public static void main(String[] args) throws Exception {
    Tasks tasks = new Tasks();

    final int MINUTES = Input.nextInt();
    int totalScore = 0;

    for (int i = 0; i < MINUTES; i++) {
      boolean notGivenTask = Input.nextInt() == 0;
      if (notGivenTask) {
        totalScore += tasks.getScoreAfterAMinute();
        continue;
      }

      Task newTask = new Task(Input.nextInt(), Input.nextInt());
      tasks.add(newTask);
      totalScore += tasks.getScoreAfterAMinute();
    }

    System.out.print(totalScore);
  }
}

class Tasks {
  Stack<Task> tasks = new Stack<>();

  public void add(Task newTask) {
    tasks.push(newTask);
  }

  public int getScoreAfterAMinute() {
    if (tasks.empty()) return 0;

    Task currentTask = tasks.peek();
    currentTask.performForAMinute();

    if (currentTask.isComplete()) {
      tasks.pop();
      return currentTask.getScore();
    }
    return 0;
  }
}

class Task {
  private final int score;
  private int requiredTime;

  public Task(int score, int requiredTime) {
    this.score = score;
    this.requiredTime = requiredTime;
  }
  
  public void performForAMinute() {
    requiredTime--;
  }
  
  public boolean isComplete() {
    return requiredTime <= 0;
  }

  public int getScore() {
    return score;
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

  public static long nextLong() {
    String nextString = next();
    return Long.parseLong(nextString);
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