import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    static BarnFarm barnFarm;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] barnInfo = br.readLine().split(" ");
        int N = Integer.parseInt(barnInfo[0]);
        int M = Integer.parseInt(barnInfo[1]);

        barnFarm = BarnFarm.maxBarnOf(N);

        while (M-- > 0) {
            String[] edgeBarnInfo = br.readLine().split(" ");
            int A = Integer.parseInt(edgeBarnInfo[0]);
            int B = Integer.parseInt(edgeBarnInfo[1]);

            barnFarm.connectVertices(A, B);
        }

        System.out.println(getAnswer());
        br.close();
    }

    static String getAnswer() {
        StringBuilder sb = new StringBuilder();
        
        barnFarm.setMinDistances();
        int maxDistance = barnFarm.getFarthestDistanceFromSetUpBarn();
        
        sb.append(barnFarm.getMinBarnIdxThatMatch(maxDistance))
            .append(" ")
            .append(maxDistance)
            .append(" ")
            .append(barnFarm.getCntBarnThatMatch(maxDistance));

        return sb.toString();
    }
}

class BarnFarm extends Graph {
    private int fromBarnIdx = 1;

    public static BarnFarm maxBarnOf(int maxBarn) {
        return new BarnFarm(maxBarn);
    }

    private BarnFarm(int maxBarn) {
        super(maxBarn);
    }

    public void setFromBarn(int fromBarnIdx) {
        this.fromBarnIdx = fromBarnIdx;
    }

    public void setMinDistances() {
        setMinDistances(fromBarnIdx);
    }

    public int getFarthestDistanceFromSetUpBarn() {
        return Arrays.stream(distances).max().getAsInt();
    }

    public int getMinBarnIdxThatMatch(int matchDistance) {
        for (int i = 0; i <= maxSize; i++) {
            if (distances[i] == matchDistance) return i;
        }
        return -1;
    }

    public int getCntBarnThatMatch(int matchDistance) {
        int cnt = 0;
        for (int distance : distances) {
            if (distance == matchDistance) cnt++;
        }
        return cnt;
    }
}

class Graph<T> {
    protected int maxSize;
    protected Vertex<T>[] vertexArr;
    protected int[] distances;

    public static Graph maxSizeOf(int maxSize) {
        return new Graph(maxSize);
    }

    protected Graph(int maxSize) {
        this.maxSize = maxSize;
        vertexArr = new Vertex[maxSize + 1];
    }

    public void setVertex(int vertexIdx) {
        getOrCreateVertex(vertexIdx);
    }

    public void setVertex(int vertexIdx, T value) {
        getOrCreateVertex(vertexIdx).setValue(value);
    }

    public Vertex<T> findVertex(int vertexIdx) {
        return vertexArr[vertexIdx];
    }

    protected boolean isBeforeSetUp(Vertex<T> vertex) {
        return vertex == null;
    }

    public void connectVertices(int vertexIdxA, int vertexIdxB) {
        Vertex<T> vertexA = getOrCreateVertex(vertexIdxA);
        Vertex<T> vertexB = getOrCreateVertex(vertexIdxB);

        vertexA.addAdjacentVertex(vertexB);
        vertexB.addAdjacentVertex(vertexA);
    }

    private Vertex<T> getOrCreateVertex(int vertexIdx) {
        Vertex<T> vertex;
        if (isBeforeSetUp(vertexArr[vertexIdx])) {
            vertex = Vertex.indexOf(vertexIdx);
            vertexArr[vertexIdx] = vertex;
        }
        return vertexArr[vertexIdx];
    }

    protected void resetAllVisit() {
        for (Vertex<T> vertex : vertexArr) {
            if (isBeforeSetUp(vertex)) continue;
            vertex.resetVisit();
        }
    }

    public void setMinDistances(int fromVertexIdx) {
        Queue<Vertex<T>> bfsVertexQueue = new LinkedList<>();
        distances = new int[maxSize + 1];

        Vertex<T> fromVertex = vertexArr[fromVertexIdx];
        bfsVertexQueue.offer(fromVertex);
        fromVertex.visit();
        
        while (!bfsVertexQueue.isEmpty()) {
            Vertex<T> vertex = bfsVertexQueue.poll();

            for (Vertex<T> adjVertex : vertex.getAdjacentVertexList()) {
                if (adjVertex.isVisited()) continue;
                adjVertex.visit();
                distances[adjVertex.getIndex()] = distances[vertex.getIndex()] + 1;
                bfsVertexQueue.offer(adjVertex);
            }
        }
    }
}

class Vertex<T> {
    private int idx;
    private T value;
    private boolean isVisited = false;
    private List<Vertex<T>> adjVertexList;

    public static Vertex indexOf(int index) {
        return new Vertex(index);
    }

    private Vertex(int idx) {
        this.idx = idx;
        adjVertexList = new ArrayList<>();
    }

    public int getIndex() {
        return idx;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public boolean isVisited() {
        return isVisited;
    }

    public void visit() {
        isVisited = true;
    }

    public void resetVisit() {
        isVisited = false;
    }

    public int getNumOfAdjacentVertex() {
        return adjVertexList.size();
    }

    public List<Vertex<T>> getAdjacentVertexList() {
        return adjVertexList;
    }

    public void addAdjacentVertex(Vertex<T> vertex) {
        adjVertexList.add(vertex);
    }

    public void removeAdjacentVertex(Vertex<T> vertex) {
        adjVertexList.remove(vertex);
    }
}