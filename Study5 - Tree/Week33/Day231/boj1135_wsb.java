import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//// 깔끔
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] parentArr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        NewsTree newsTree = new NewsTree(N);
        newsTree.setRoot(0);
        for (int i = 1; i < N; i++) {
            newsTree.setNodes(parentArr[i], i);
        }

        System.out.println(newsTree.getMinTimeForAllHearNews());
        br.close();
    }
}

class NewsTree extends NodeMap {
    public NewsTree(int size) {
        super(size);
    }

    public int getMinTimeForAllHearNews() {
        return getTimeForAllHearNews(getRoot());
    }

    public int getTimeForAllHearNews(Node parent) {
        List<Node> childList = parent.getChildList();
        for (Node child : childList) {
            child.setValue(getTimeForAllHearNews(child));
        }
        parent.sortDesChildListByValue();

        int time = 0, maxTime = 0;
        for (Node child : childList) {
            maxTime = Math.max(maxTime, ++time + child.getValue());
        }

        return maxTime;
    }
}

class NodeMap {
    private Node[] nodeArr;
    private Node root;

    public NodeMap(int size) {
        nodeArr = new Node[size];
    }

    public void setRoot(int rootData) {
        root = getNode(rootData);
    }

    public Node getRoot() {
        return root;
    }

    public Node findNode(int data) {
        return nodeArr[data];
    }

    public void setNodes(int parentData, int childData) {
        Node parent = getNode(parentData);
        Node child = getNode(childData);

        parent.addChild(child);
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
    private int value;
    private List<Node> childList;
    private int maxChildDepth = 0;

    public Node(int data) {
        this.data = data;
        childList = new ArrayList<>();
    }

    public int getData() {
        return data;
    }

    public int getValue() {
        return value;
    }

    public List<Node> getChildList() {
        return childList;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getNumOfChildren() {
        return childList.size();
    }

    public int getMaxChildDepth() {
        return maxChildDepth;
    }

    public void setMaxChildDepth(int childDepth) {
        maxChildDepth = Math.max(maxChildDepth, childDepth);
    }

    public void addChild(Node node) {
        childList.add(node);
    }

    public void sortDesChildListByValue() {
        Collections.sort(childList, Comparator.comparing(Node::getValue).reversed());
    }
}