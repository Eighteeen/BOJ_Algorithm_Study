import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.IntStream;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] boxInfo = br.readLine().split(" ");
        int M = Integer.parseInt(boxInfo[0]);
        int N = Integer.parseInt(boxInfo[1]);

        FruitBox fruitBox = FruitBox.sizeOf(N, M);
        int[][] tomatoFlags = new int[N][];

        int idx = 0;
        for (int i = 0; i < N; i++) {
            tomatoFlags[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int j = 0; j < M; j++) {
                idx++;
                if (tomatoFlags[i][j] == -1) continue;
                fruitBox.storeFruit(idx, tomatoFlags[i][j] == 1);
            }
        }

        System.out.println(fruitBox.getDateOfAllFruitsRipen());
        br.close();
    }
}

class FruitBox extends Graph<Boolean> {
    private int row;
    private int col;
    private int[] datesForRipen;
    protected Queue<Vertex<Boolean>> bfsFruitQueue;
    private int[] adjSpaceDifferentIdxs;

    public static FruitBox sizeOf(int row, int col) {
        return new FruitBox(row, col);
    }

    private FruitBox(int row, int col) {
        super(row * col);
        this.row = row;
        this.col = col;
        datesForRipen = new int[maxSize + 1];
        bfsFruitQueue = new LinkedList<>();
        adjSpaceDifferentIdxs = new int[] {1, col, -1, -col};
    }

    public void storeFruit(int spaceIdx, boolean isRipe) {
        setVertex(spaceIdx, isRipe);
        if (!isRipe) return;

        bfsFruitQueue.offer(vertexArr[spaceIdx]);
        datesForRipen[spaceIdx] = 1;
    }

    public int getDateOfAllFruitsRipen() {
        setMinDatesForRipen();

        for (Vertex<Boolean> fruit : vertexArr) {
            if (fruit == null) continue;
            if (!fruit.getValue()) return -1;
        }
        resetAllVisit();

        return IntStream.of(datesForRipen).max().getAsInt() - 1;
    }

    private void setMinDatesForRipen() {
        while (!bfsFruitQueue.isEmpty()) {
            Vertex<Boolean> fruit = bfsFruitQueue.poll();
            fruit.visit();
            fruit.setValue(true);
            int fruitIdx = fruit.getIndex();

            for (int adjSpaceDifferentIdx : adjSpaceDifferentIdxs) {
                if (!isInFruitBoxSpace(fruitIdx, adjSpaceDifferentIdx)) continue;
                int adjFruitIdx = fruitIdx + adjSpaceDifferentIdx;

                Vertex<Boolean> adjFruit = vertexArr[adjFruitIdx];
                if (adjFruit == null || adjFruit.isVisited() || adjFruit.getValue()) continue;

                bfsFruitQueue.offer(adjFruit);
                datesForRipen[adjFruitIdx] = datesForRipen[fruitIdx] + 1;
            }
        }
    }

    private boolean isInFruitBoxSpace(int spaceIdx, int adjSpaceDifferentIdx) {
        if (adjSpaceDifferentIdx == 1 && spaceIdx % col == 0) return false;
        if (adjSpaceDifferentIdx == -1 && spaceIdx % col == 1) return false;
        return spaceIdx + adjSpaceDifferentIdx > 0 && spaceIdx + adjSpaceDifferentIdx <= maxSize;
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