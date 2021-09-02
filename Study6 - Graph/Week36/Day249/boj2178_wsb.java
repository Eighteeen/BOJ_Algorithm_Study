import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] mazeInfo = br.readLine().split(" ");
        int N = Integer.parseInt(mazeInfo[0]);
        int M = Integer.parseInt(mazeInfo[1]);

        Maze maze = new Maze(N * M - 1);
        char[][] mazeSections = new char[N][];

        int idx = 0;
        for (int i = 0; i < N; i++) {
            mazeSections[i] = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                maze.setSection(idx, mazeSections[i][j] == '1');
                
                if (i > 0) maze.connectSections(idx, idx - M);
                if (j < (M - 1)) maze.connectSections(idx, idx + 1);
                if (i < (N - 1)) maze.connectSections(idx, idx + M);
                if (j > 0) maze.connectSections(idx, idx - 1);

                idx++;
            }
        }

        System.out.println(maze.getMinMovement(0, idx - 1));
        br.close();
    }
}

class Maze extends Graph<Boolean> {
    private Queue<Vertex<Boolean>> bfsSectionQueue;
    private int[] minmMovement;

    public Maze(int maxSection) {
        super(maxSection);
    }

    public void setSection(int sectionIdx, boolean isMoveable) {
        setVertex(sectionIdx, isMoveable);
    }

    public void connectSections(int sectionIdxA, int sectionIdxB) {
        connectVertices(sectionIdxA, sectionIdxB);
    }

    public int getMinMovement(int fromSectionIdx, int toSectionIdx) {
        bfsSectionQueue = new LinkedList<>();
        minmMovement = new int[maxSize + 1];

        Vertex<Boolean> fromSection = vertexArr[fromSectionIdx];
        fromSection.visit();
        minmMovement[fromSectionIdx] = 1;
        setMinMovementValueOfAllSection(fromSection);
        resetAllVisit();
        
        return minmMovement[toSectionIdx];
    }

    private void setMinMovementValueOfAllSection(Vertex<Boolean> section) {
        for (Vertex<Boolean> adjSection : section.getAdjacentVertexList()) {
            if (adjSection.isVisited() || !adjSection.getValue()) continue;

            minmMovement[adjSection.getIndex()] = minmMovement[section.getIndex()] + 1;
            bfsSectionQueue.offer(adjSection);
            adjSection.visit();
        }

        if (bfsSectionQueue.isEmpty()) return;
        setMinMovementValueOfAllSection(bfsSectionQueue.poll());
    }
}

class Graph<T> {
    protected int maxSize;
    protected Vertex<T>[] vertexArr;

    public Graph(int maxSize) {
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

    public Vertex(int idx, T value) {
        this(idx);
        this.value = value;
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