import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        Graph peopleGraph = Graph.maxSizeOf(n);

        String[] peopleInfo = br.readLine().split(" ");
        int personNum1 = Integer.parseInt(peopleInfo[0]);
        int personNum2 = Integer.parseInt(peopleInfo[1]);

        int m = Integer.parseInt(br.readLine());
        while (m-- > 0) {
            String[] relationshipInfo = br.readLine().split(" ");
            int parentNum = Integer.parseInt(relationshipInfo[0]);
            int childNum = Integer.parseInt(relationshipInfo[1]);

            peopleGraph.connectVertices(parentNum, childNum);
        }

        int degreeOfKinship = peopleGraph.getMinMovement(personNum1, personNum2);
        System.out.println(degreeOfKinship == 0 ? -1 : degreeOfKinship);
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

    public int getMinMovement(int fromVertexIdx, int toVertexIdx) {
        Queue<Vertex<T>> bfsVertexQueue = new LinkedList<>();
        int[] minMovement = new int[maxSize + 1];

        Vertex<T> fromVertex = vertexArr[fromVertexIdx];
        bfsVertexQueue.offer(fromVertex);
        fromVertex.visit();
        
        while (!bfsVertexQueue.isEmpty()) {
            Vertex<T> vertex = bfsVertexQueue.poll();

            for (Vertex<T> adjVertex : vertex.getAdjacentVertexList()) {
                if (adjVertex.isVisited()) continue;
                adjVertex.visit();
                minMovement[adjVertex.getIndex()] = minMovement[vertex.getIndex()] + 1;
                bfsVertexQueue.offer(adjVertex);
            }
        }
        
        return minMovement[toVertexIdx];
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