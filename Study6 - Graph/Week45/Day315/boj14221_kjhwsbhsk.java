import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;

class Main {
  static Vertex[] vertices;
  static boolean[] visited;

  static final int HOUSE_TYPE = -1;
  static final int NONE_TYPE = 0;
  static final int STORE_TYPE = 1;
  static final int MAX_DISTANCE = 1000000000;
  
  static int[] distanceToNearestStore;

  public static void main(String[] args) throws Exception {
    final int VERTEX_AMOUNT = Input.nextInt();
    vertices = new Vertex[VERTEX_AMOUNT + 1];
    for (int i = 1; i <= VERTEX_AMOUNT; i++) {
      vertices[i] = new Vertex(i);
    } 
    
    final int EDGE_AMOUNT = Input.nextInt();
    for (int i = 0; i < EDGE_AMOUNT; i++) {
      int numA = Input.nextInt();
      int numB = Input.nextInt();
      
      int distance = Input.nextInt();
      vertices[numA].addEdge(new Edge(vertices[numB], distance));
      vertices[numB].addEdge(new Edge(vertices[numA], distance));
    }
    
    List<Integer> houses = new ArrayList<>();
    final int HOUSE_AMOUNT = Input.nextInt();
    final int STORE_AMOUNT = Input.nextInt();
    
    for (int i = 0; i < HOUSE_AMOUNT; i++) {
      int houseNum = Input.nextInt();
      vertices[houseNum].type = HOUSE_TYPE;
      houses.add(houseNum);
    }
    
    for (int i = 0; i < STORE_AMOUNT; i++) {
      int storeNum = Input.nextInt();
      vertices[storeNum].type = STORE_TYPE;
    }
    
    distanceToNearestStore = new int[VERTEX_AMOUNT + 1];

    int minDistance = MAX_DISTANCE;
    int nearestHouse = 0;
    for (int house : houses) {
      visited = new boolean[vertices.length];
      int distance = getDistanceToNearestStore(house);

      if (distance < minDistance) {
        minDistance = distance;
        nearestHouse = house;
      }
    }

    System.out.print(nearestHouse);
  }

  static int getDistanceToNearestStore(int vertexNum) {
    if (distanceToNearestStore[vertexNum] != 0) {
      return distanceToNearestStore[vertexNum];
    }

    Vertex vertex = vertices[vertexNum];
    visited[vertex.number] = true;

    int minDistance = MAX_DISTANCE;
    for (Edge edge : vertex.edges) {
      Vertex connectedVertex = edge.connectedVertex;
      if (visited[connectedVertex.number]) {
        continue;
      }
      if (connectedVertex.type == HOUSE_TYPE) {
        continue;
      }
      if (connectedVertex.type == STORE_TYPE) {
        minDistance = Math.min(minDistance, edge.distance);
        continue;
      }
      minDistance = Math.min(minDistance,
        edge.distance + getDistanceToNearestStore(connectedVertex.number)
      );
    }

    visited[vertex.number] = false;
    distanceToNearestStore[vertex.number] = minDistance;
    return minDistance;
  }
}

class Vertex {
  public int number;
  public int type;
  public List<Edge> edges;

  public Vertex(int number) {
    this.number = number;
    edges = new ArrayList<>();
  }

  public void addEdge(Edge edge) {
    edges.add(edge);
  }

  public void removeEdge(int targetData) {
    Edge targetEdge = null;
    for (int i = 0; i < edges.size(); i++) {
      Edge edge = edges.get(i);
      if (edge.connectedVertex.number == targetData) {
        targetEdge = edge;
        break;
      }
    }
    if (targetEdge == null) return;
    edges.remove(targetEdge);
  }
}

class Edge {
  public Vertex connectedVertex;
  public int distance;

  public Edge(Vertex connectedVertex, int distance) {
    this.connectedVertex = connectedVertex;
    this.distance = distance;
  }
}

class Input {
  private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  private static StringTokenizer tokenizer;

  public static String nextLine() {
    try {
      String line = br.readLine();
      if (line.equals("")) {
        return nextLine();
      }
      return line;
    } catch (Exception e) {
    }

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

  public static void skipLine() {
    nextLine();
  }

  public static void skipLine(int n) {
    for (int i = 0; i < n; i++) {
      nextLine();
    }
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
    if (!tokenizer.hasMoreTokens()) {
      tokenizer = makeTokens();
    }
  }

  private static StringTokenizer makeTokens() {
    StringTokenizer tokens = new StringTokenizer(nextLine(), " ");
    if (tokens.hasMoreTokens() == false) {
      makeTokens();
    }
    return tokens;
  }
}