import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//// 깔끔해요
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] computerInfo = br.readLine().split(" ");
        int N = Integer.parseInt(computerInfo[0]);
        int M = Integer.parseInt(computerInfo[1]);

        Graph computerGraph = new Graph<>(N);

        while (M-- > 0) {
            String[] trustComputerInfo = br.readLine().split(" ");
            int currentComputer = Integer.parseInt(trustComputerInfo[0]);
            int trustworthyComputer = Integer.parseInt(trustComputerInfo[1]);

            computerGraph.connectOneWayVertices(trustworthyComputer, currentComputer);
        }

        computerGraph.setNumOfConnectedVertexArr();
        int maxNumOfComputerCanHack = computerGraph.getMaxNumOfConnectedVertexArr();

        System.out.println(computerGraph.getAscVertexInfoOfMatchNumOfConnected(maxNumOfComputerCanHack));
        br.close();
    }
}

//// 트리에서 그래프로 오면서 separator 쓰시는데 이유가 있을까요?
//// separator 직역하면 구분자(구분기호)인데 그래프에서 알맞은 단어인지는 잘 모르겠습니다.
//// 보통 구분자는 split('\n')에서 "\n을 기준으로 나눈다(분리한다)"의 의미로 쓰이는 경우가 많아 separator 쓰셨을 때 저 의미로 느껴집니다.
//// 정점을 말하고 싶으신거라면 vertex로 변경하시는건 어떨까요?(Node클래스에서도, Graph 클래스에서도 조금 헷갈리네요)
//// -> separator이 문자열의 구분자로 많이 쓰이는 변수명인 것을 생각하지 못 했네요.
    //// Graph에서 유일한 값으로 index의 역할을 하는 말 그대로 구분할 수 있는 수를 두고 싶었는데, 표현이 어섹했습니다.
    //// node와 vertex 둘의 의미는 동일하고 표현만 다르지만, graph 상에서는 확실히 vertex라는 표현을 더 많이 쓰네요.
    //// vertex, idx(index)로 표현을 변경해서 피드백 반영해봤습니다. 적절한 피드백 감사합니다!
class Graph<T> {
    private int maxSize;
    private Vertex<T>[] vertexArr;
    private int[] numOfConnectedVertexArr;

    public Graph(int maxSize) {
        this.maxSize = maxSize;
        vertexArr = new Vertex[maxSize + 1];
        numOfConnectedVertexArr = new int[maxSize + 1];
    }

    public Vertex<T> findVertex(int vertexIdx) {
        return vertexArr[vertexIdx];
    }

    public void connectOneWayVertices(int fromVertexIdx, int toVertexIdx) {
        Vertex<T> fromVertex = getOrCreateVertex(fromVertexIdx);
        Vertex<T> toVertex = getOrCreateVertex(toVertexIdx);

        fromVertex.addAdjacentVertex(toVertex);
    }

    private Vertex<T> getOrCreateVertex(int vertexIdx) {
        Vertex<T> vertex;
        if (vertexArr[vertexIdx] == null) {
            vertex = new Vertex<>(vertexIdx);
            vertexArr[vertexIdx] = vertex;
        }
        return vertexArr[vertexIdx];
    }

    private void resetAllVisit() {
        for (Vertex<T> vertex : vertexArr) {
            if (vertex == null) continue;
            vertex.resetVisit();
        }
    }

    public void setNumOfConnectedVertexArr() {
        for (Vertex<T> vertex : vertexArr) {
            if (vertex == null || numOfConnectedVertexArr[vertex.getIndex()] != 0) continue;
            numOfConnectedVertexArr[vertex.getIndex()] = getNumOfConnectedVertex(vertex);
            resetAllVisit();
        }
    }

    private int getNumOfConnectedVertex(Vertex<T> vertex) {
        if (vertex.isVisited()) return 0;
        vertex.visit();

        int cntVertex = 1;
        for (Vertex<T> adjVertex : vertex.getAdjacentVertexList()) {
            cntVertex += getNumOfConnectedVertex(adjVertex);
        }
        return cntVertex;
    }

    public int getMaxNumOfConnectedVertexArr() {
        return Arrays.stream(numOfConnectedVertexArr).max().getAsInt();
    }

    public String getAscVertexInfoOfMatchNumOfConnected(int numOfConnected) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= maxSize; i++) {
            if (numOfConnected == numOfConnectedVertexArr[i]) sb.append(i).append(" ");
        }
        return sb.toString();
    }
}

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