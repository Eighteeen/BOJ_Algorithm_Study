import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] floorInfo = br.readLine().split(" ");
        int N = Integer.parseInt(floorInfo[0]);
        int M = Integer.parseInt(floorInfo[1]);

        char[][] decorationArr = new char[N][];
        Graph<Character> decorationGraph = new Graph<>(N * M);
        for (int row = 0; row < N; row++) {
            decorationArr[row] = br.readLine().toCharArray();

            int currentRow = M * row;
            char prevDecoration = '\u0000';
            for (int col = 0; col < M; col++) {
                int currentIdx = currentRow + col;
                decorationGraph.setNode(currentIdx, decorationArr[row][col]);
                if (prevDecoration == '-' && prevDecoration == decorationArr[row][col]) {
                    decorationGraph.connectNodes(currentIdx, currentIdx - 1);
                }
                prevDecoration = decorationArr[row][col];
            }
        }

        for (int col = 0; col < M; col++) {
            char prevDecoration = '\u0000';
            for (int row = 0; row < N; row++) {
                int currentIdx = col + M * row;
                if (prevDecoration == '|' && prevDecoration == decorationArr[row][col]) {
                    decorationGraph.connectNodes(currentIdx, currentIdx - M);
                }
                prevDecoration = decorationArr[row][col];
            }
        }

        System.out.println(decorationGraph.getNumOfComponent());
        br.close();
    }
}

class Graph<T> {
    private Node<T>[] nodeArr;

    public Graph(int maxSize) {
        nodeArr = new Node[maxSize + 1];
    }

    public Node<T> findNode(int separator) {
        return nodeArr[separator];
    }

    public void setNode(int separator, T value) {
        getOrCreateNode(separator, value);
    }

    public void connectNodes(int separatorA, int separatorB) {
        Node<T> nodeA = findNode(separatorA);
        Node<T> nodeB = findNode(separatorB);

        nodeA.addAdjacentNode(nodeB);
        nodeB.addAdjacentNode(nodeA);
    }

    private Node<T> getOrCreateNode(int separator, T value) {
        Node<T> node;
        if (nodeArr[separator] == null) {
            node = new Node<>(separator, value);
            nodeArr[separator] = node;
        }
        return nodeArr[separator];
    }

    private void resetAllVisit() {
        for (Node<T> node : nodeArr) {
            if (node == null) continue;
            node.resetVisit();
        }
    }

    public int getNumOfComponent() {
        int cnt = 0;
        for (Node<T> node : nodeArr) {
            if (node == null || node.isVisited()) continue;
            cnt++;
            DFS(node);
        }
        resetAllVisit();
        return cnt;
    }

    private void DFS(Node<T> node) {
        if (node.isVisited()) return;

        node.visit();
        for (Node<T> adjNode : node.getAdjacentNodeList()) {
            DFS(adjNode);
        }
    }
}

class Node<T> {
    private int separator;
    private T value;
    private boolean isVisited = false;
    private List<Node<T>> adjNodeList;

    public Node(int separator, T value) {
        this.separator = separator;
        this.value = value;
        adjNodeList = new ArrayList<>();
    }

    public int getSeparator() {
        return separator;
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

    public List<Node<T>> getAdjacentNodeList() {
        return adjNodeList;
    }

    public void addAdjacentNode(Node<T> node) {
        adjNodeList.add(node);
    }

    public void removeAdjacentNode(Node<T> node) {
        adjNodeList.remove(node);
    }
}