import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//// 노드를 연결해서 그래프로 구현한 것 대단하네여 :22 대단합니다
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String[] pastureInfo = br.readLine().split(" ");
        int R = Integer.parseInt(pastureInfo[0]);
        int C = Integer.parseInt(pastureInfo[1]);

        PastureStateGraph pastureGraph = new PastureStateGraph(R * C);
        char[][] pastureStateArr = new char[R][];
        int idx = 0;
        for (int i = 0; i < R; i++) {
            pastureStateArr[i] = br.readLine().toCharArray();
            for (int j = 0; j < C; j++) {
                if (pastureStateArr[i][j] == '.') pastureStateArr[i][j] = 'D';
                pastureGraph.setNode(idx, pastureStateArr[i][j]);

                if (i > 0) pastureGraph.connectNodes(idx, idx - C);
                if (j < (C - 1)) pastureGraph.connectNodes(idx, idx + 1);
                if (i < (R - 1)) pastureGraph.connectNodes(idx, idx + C);
                if (j > 0) pastureGraph.connectNodes(idx, idx - 1);

                idx++;
            }
        }

        if (pastureGraph.isSafeToSheep()) {
            sb.append(1).append("\n");
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    sb.append(pastureStateArr[i][j]);
                }
                sb.append("\n");
            }
        } else {
            sb.append(0);
        }

        System.out.println(sb);
        br.close();
    }
}

class PastureStateGraph extends Graph<Character> {
    private Character sheep = 'S';
    private Character wolf = 'W';

    public PastureStateGraph(int maxSize) {
        super(maxSize);
    }

    public boolean isSafeToSheep() {
        for (Node<Character> node : getNodeArr()) {
            if (node == null) continue;
            if (node.getValue() == wolf && isNodeAdjacentToState(node, sheep)) return false;
        }
        return true;
    }

    private boolean isNodeAdjacentToState(Node<Character> node, Character state) {
        for (Node<Character> adjNode : node.getAdjacentNodeList()) {
            if (adjNode.getValue() == state) return true;
        }
        return false;
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

    public Node<T>[] getNodeArr() {
        return nodeArr;
    }

    public void setNode(int separator, T value) {
        getNodeAfterSetting(separator, value);
    }

    public void connectNodes(int separatorA, int separatorB) {
        Node<T> nodeA = getNodeAfterSetting(separatorA);
        Node<T> nodeB = getNodeAfterSetting(separatorB);

        nodeA.addAdjacentNode(nodeB);
        nodeB.addAdjacentNode(nodeA);
    }

    private Node<T> getNodeAfterSetting(int separator) {
        Node<T> node;
        if (nodeArr[separator] == null) {
            node = new Node<>(separator);
            nodeArr[separator] = node;
        }
        return nodeArr[separator];
    }

    private Node<T> getNodeAfterSetting(int separator, T value) {
        Node<T> node;
        if (nodeArr[separator] == null) {
            node = new Node<>(separator, value);
            nodeArr[separator] = node;
        } else {
            nodeArr[separator].setValue(value);
        }
        return nodeArr[separator];
    }
}

class Node<T> {
    private int separator;
    private T value;
    private List<Node<T>> adjNodeList;

    public Node(int separator) {
        this.separator = separator;
        adjNodeList = new ArrayList<>();
    }

    public Node(int separator, T value) {
        this(separator);
        this.value = value;
    }

    public int getSeparator() {
        return separator;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
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