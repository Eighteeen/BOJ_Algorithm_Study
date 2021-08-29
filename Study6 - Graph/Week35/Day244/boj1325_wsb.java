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

            computerGraph.connectOneWayNodes(trustworthyComputer, currentComputer);
        }

        computerGraph.setNumOfConnectedNodeArr();
        int maxNumOfComputerCanHack = computerGraph.getMaxNumOfConnectedNodeArr();

        System.out.println(computerGraph.getAscNodeInfoOfMatchNumOfConnected(maxNumOfComputerCanHack));
        br.close();
    }
}

//// 트리에서 그래프로 오면서 separator 쓰시는데 이유가 있을까요?
//// separator 직역하면 구분자(구분기호)인데 그래프에서 알맞은 단어인지는 잘 모르겠습니다.
class Graph<T> {
    private int maxSize;
    private Node<T>[] nodeArr;
    private int[] numOfConnectedNodeArr;

    public Graph(int maxSize) {
        this.maxSize = maxSize;
        nodeArr = new Node[maxSize + 1];
        numOfConnectedNodeArr = new int[maxSize + 1];
    }

    public Node<T> findNode(int separator) {
        return nodeArr[separator];
    }

    public void connectOneWayNodes(int fromSeparator, int toSeparator) {
        Node<T> fromNode = getOrCreateNode(fromSeparator);
        Node<T> toNode = getOrCreateNode(toSeparator);

        fromNode.addAdjacentNode(toNode);
    }

    private Node<T> getOrCreateNode(int separator) {
        Node<T> node;
        if (nodeArr[separator] == null) {
            node = new Node<>(separator);
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

    public void setNumOfConnectedNodeArr() {
        for (Node<T> node : nodeArr) {
            if (node == null || numOfConnectedNodeArr[node.getSeparator()] != 0) continue;
            numOfConnectedNodeArr[node.getSeparator()] = getNumOfConnectedNode(node);
            resetAllVisit();
        }
    }

    private int getNumOfConnectedNode(Node<T> node) {
        if (node.isVisited()) return 0;
        node.visit();

        int cntNode = 1;
        for (Node<T> adjNode : node.getAdjacentNodeList()) {
            cntNode += getNumOfConnectedNode(adjNode);
        }
        return cntNode;
    }

    public int getMaxNumOfConnectedNodeArr() {
        return Arrays.stream(numOfConnectedNodeArr).max().getAsInt();
    }

    public String getAscNodeInfoOfMatchNumOfConnected(int numOfConnected) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= maxSize; i++) {
            if (numOfConnected == numOfConnectedNodeArr[i]) sb.append(i).append(" ");
        }
        return sb.toString();
    }
}

class Node<T> {
    private int separator;
    private T value;
    private boolean isVisited = false;
    private List<Node<T>> adjNodeList;

    public Node(int separator) {
        this.separator = separator;
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