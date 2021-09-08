import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//// 깔끔 :22
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] graphInfo = br.readLine().split(" ");
        int N = Integer.parseInt(graphInfo[0]);
        int M = Integer.parseInt(graphInfo[1]);

        String[] movementInfo = br.readLine().split(" ");
        int S = Integer.parseInt(movementInfo[0]);
        int E = Integer.parseInt(movementInfo[1]);

        Graph graph = Graph.maxSizeOf(N);
        for (int i = 1; i < N; i++) {
            graph.connectVertices(i, i + 1);
        }

        while (M-- > 0) {
            String[] teleportInfo = br.readLine().split(" ");
            int vertexIdxA = Integer.parseInt(teleportInfo[0]);
            int vertexIdxB = Integer.parseInt(teleportInfo[1]);

            graph.connectVertices(vertexIdxA, vertexIdxB);
        }

        System.out.println(graph.getMinMovement(S, E));
        br.close();
    }
}

class Graph<T> {
    protected int maxSize;
    protected Vertex<T>[] vertexArr;

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

    public void connectVertices(int vertexIdxA, int vertexIdxB) {
        Vertex<T> vertexA = getOrCreateVertex(vertexIdxA);
        Vertex<T> vertexB = getOrCreateVertex(vertexIdxB);

        vertexA.addAdjacentVertex(vertexB);
        vertexB.addAdjacentVertex(vertexA);
    }

    private Vertex<T> getOrCreateVertex(int vertexIdx) {
        Vertex<T> vertex;
        if (vertexArr[vertexIdx] == null) {
            vertex = Vertex.indexOf(vertexIdx);
            vertexArr[vertexIdx] = vertex;
        }
        return vertexArr[vertexIdx];
    }

    protected void resetAllVisit() {
        for (Vertex<T> vertex : vertexArr) {
            if (vertex == null) continue;
            vertex.resetVisit();
        }
    }

    //// 오 드디어 while문으로 하셨네용 , 혹시 minmMovement도 minMovement 오탄가요,,?
    public int getMinMovement(int fromVertexIdx, int toVertexIdx) {
        Queue<Vertex<T>> bfsVertexQueue = new LinkedList<>();
        int[] minmMovement = new int[maxSize + 1];

        Vertex<T> fromVertex = vertexArr[fromVertexIdx];
        bfsVertexQueue.offer(fromVertex);
        fromVertex.visit();
        
        while (!bfsVertexQueue.isEmpty()) {
            Vertex<T> vertex = bfsVertexQueue.poll();

            for (Vertex<T> adjVertex : vertex.getAdjacentVertexList()) {
                if (adjVertex.isVisited()) continue;
                adjVertex.visit();
                minmMovement[adjVertex.getIndex()] = minmMovement[vertex.getIndex()] + 1;
                bfsVertexQueue.offer(adjVertex);
            }
        }
        
        return minmMovement[toVertexIdx];
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