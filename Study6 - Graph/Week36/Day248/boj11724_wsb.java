import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//// 깔끔
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] graphInfo = br.readLine().split(" ");
        int N = Integer.parseInt(graphInfo[0]);
        int M = Integer.parseInt(graphInfo[1]);

        Graph graph = new Graph(N);
        while (M-- > 0) {
            String[] edgeInfo = br.readLine().split(" ");
            int u = Integer.parseInt(edgeInfo[0]);
            int v = Integer.parseInt(edgeInfo[1]);

            graph.connectVertices(u, v);
        }

        System.out.println(graph.getNumOfComponent());
        br.close();
    }
}

class Graph<T> {
    protected int maxSize;
    protected Vertex<T>[] vertexArr;

    public Graph(int maxSize) {
        this.maxSize = maxSize;
        vertexArr = new Vertex[maxSize + 1];
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
        //// null 인지를 확인하는 코드가 꽤 많이 보이는데 처음부터 초기화해두는건 어때요?
        //// 팁) Stream.generate가 개인적으로 유용했습니다
        //// -> 유용한 팁이지만 문제마다 null값을 체크해줘야 하는 경우가 생길 때도 있고,
            //// 필요할 때만 만들어도 된다는 생각이라서 현재를 유지하겠습니다. 팁 감사해요!
        if (vertexArr[vertexIdx] == null) {
            vertex = new Vertex<>(vertexIdx);
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

    public int getNumOfComponent() {
        int cnt = 0;
        for (Vertex<T>  vertex : vertexArr) {
            if (vertex != null && vertex.isVisited()) continue;
            cnt++;
            if (vertex == null) continue;
            DFS(vertex);
        }
        resetAllVisit();
        return cnt - 1;
    }

    private void DFS(Vertex<T> vertex) {
        if (vertex.isVisited()) return;

        vertex.visit();
        for (Vertex<T> adjVertex : vertex.getAdjacentVertexList()) {
            DFS(adjVertex);
        }
    }
}

//// 이제봤는데 제네릭 ㄷㄷ
class Vertex<T> {
    private int idx;
    private T value;
    private boolean isVisited = false;
    private List<Vertex<T>> adjVertexList;

    public Vertex(int idx) {
        this.idx = idx;
        adjVertexList = new ArrayList<>();
    }

    public int getIndex() {
        return idx;
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