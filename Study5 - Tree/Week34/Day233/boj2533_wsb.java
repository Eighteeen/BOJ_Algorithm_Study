import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//// 깔끔해요
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        SnsTree snsTree = new SnsTree(N);
        while (--N > 0) {
            String[] edgeInfo = br.readLine().split(" ");
            int adaptorA = Integer.parseInt(edgeInfo[0]);
            int adaptorB = Integer.parseInt(edgeInfo[1]);

            snsTree.setRelationship(adaptorA, adaptorB);
        }

        System.out.println(snsTree.getMinEarlyAdaptor(1));
        br.close();
    }
}

class SnsTree extends Tree {
    private int[] minEarlyAdaptorIfEarlyAdaptor;
    private int[] minEarlyAdaptorIfNormalAdaptor;

    public SnsTree(int size) {
        super(size);
        minEarlyAdaptorIfEarlyAdaptor = new int[size + 1];
        minEarlyAdaptorIfNormalAdaptor = new int[size + 1];
    }

    public void setRelationship(int adaptorA, int adaptorB) {
        setNodes(adaptorA, adaptorB);
    }

    public int getMinEarlyAdaptor(int adaptorNum) {
        setMinEarlyAdaptorArrs(findNode(adaptorNum));
        return Math.min(minEarlyAdaptorIfEarlyAdaptor[adaptorNum], minEarlyAdaptorIfNormalAdaptor[adaptorNum]);
    }

    private void setMinEarlyAdaptorArrs(Node adaptor) {
        int adaptorNum = adaptor.getData();
        minEarlyAdaptorIfEarlyAdaptor[adaptorNum]++;

        for (Node connectAdaptor : adaptor.getAdjacentNodeList()) {
            connectAdaptor.removeAdjacentNode(adaptor);
            setMinEarlyAdaptorArrs(connectAdaptor);

            //// 아 요렇게 할수가있군요.... 배워갑니다
            int connectAdaptorNum = connectAdaptor.getData();
            minEarlyAdaptorIfEarlyAdaptor[adaptorNum] += Math.min(
                minEarlyAdaptorIfEarlyAdaptor[connectAdaptorNum],
                minEarlyAdaptorIfNormalAdaptor[connectAdaptorNum]);
            minEarlyAdaptorIfNormalAdaptor[adaptorNum] += minEarlyAdaptorIfEarlyAdaptor[connectAdaptorNum];
        }
    }
}

class Tree {
    private Node[] nodeArr;

    public Tree(int size) {
        nodeArr = new Node[size + 1];
    }

    public Node findNode(int data) {
        return nodeArr[data];
    }

    public void setNodes(int dataA, int datB) {
        Node nodeA = getNode(dataA);
        Node nodeB = getNode(datB);

        nodeA.setAdjacentNode(nodeB);
        nodeB.setAdjacentNode(nodeA);
    }

    private Node getNode(int data) {
        Node node;
        if (nodeArr[data] == null) {
            node = new Node(data);
            nodeArr[data] = node;
        }
        return nodeArr[data];
    }
}

class Node {
    private int data;
    private List<Node> adjNodeList;

    public Node(int data) {
        this.data = data;
        adjNodeList = new ArrayList<>();
    }

    public int getData() {
        return data;
    }

    public List<Node> getAdjacentNodeList() {
        return adjNodeList;
    }

    public void setAdjacentNode(Node node) {
        adjNodeList.add(node);
    }

    public void removeAdjacentNode(Node node) {
        adjNodeList.remove(node);
    }
}