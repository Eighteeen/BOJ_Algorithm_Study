import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//// 깔끔
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        final char houseFlag = '1';

        int N = Integer.parseInt(br.readLine());
        char[][] mapSections = new char[N][];
        for (int i = 0; i < N; i++) mapSections[i] = br.readLine().toCharArray();

        Village village = Village.maxHouseOf(N * N - 1);

        final int[] dx = {-1, 0, 1, 0}, dy = {0, 1, 0, -1}, dIdx = {-N, 1, N , -1};
        int idx = -1;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                idx++;
                if (mapSections[i][j] != houseFlag) continue;
                village.setVertex(idx);

                //// 왼쪽과 위쪽만 체크해서 양방향 연결해도 갠춘해요
                for (int k = 0; k < 4; k++) {
                    int nx = i + dx[k], ny = j + dy[k];
                    if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
                    if (mapSections[nx][ny] == houseFlag) village.connectHouses(idx, idx + dIdx[k]);
                }
            }
        }

        sb.append(village.getNumOfComlex()).append("\n");
        village.getAscNumListOfHouseInComlex().stream().forEach(num -> sb.append(num).append("\n"));

        System.out.print(sb);
        br.close();
    }
}

class Village extends Graph<Object> {
    List<Integer> numsOfHouseInComlex;

    public static Village maxHouseOf(int maxHouse) {
        return new Village(maxHouse);
    }

    private Village(int maxHouse) {
        super(maxHouse);
    }

    public void connectHouses(int houseIdxA, int houseIdxB) {
        connectVertices(houseIdxA, houseIdxB);
    }

    public int getNumOfComlex() {
        numsOfHouseInComlex = new ArrayList<>();

        int cnt = 0;
        for (Vertex<Object> house : vertexArr) {
            if (house == null || house.isVisited()) continue;
            cnt++;
            numsOfHouseInComlex.add(getNumOfHouseInComlex(house));
        }

        resetAllVisit();
        return cnt;
    }

    public List<Integer> getAscNumListOfHouseInComlex() {
        List<Integer> list = new ArrayList<>(numsOfHouseInComlex);
        Collections.sort(list);
        return list;
    }

    //// 요것도 오타?
    private int getNumOfHouseInComlex(Vertex<Object> house) {
        if (house.isVisited()) return 0;
        house.visit();

        int cnt = 1;
        for (Vertex<Object> adjHouse : house.getAdjacentVertexList()) {
            cnt += getNumOfHouseInComlex(adjHouse);
        }
        return cnt;
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