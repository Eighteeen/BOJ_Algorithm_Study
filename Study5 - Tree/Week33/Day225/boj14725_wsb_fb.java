import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//// 깔끔합니다! : 22
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Trie trie = new Trie();
        int N = Integer.parseInt(br.readLine());
        while (N-- > 0) {
            String[] foodInfo = br.readLine().split(" ");
            int K = Integer.parseInt(foodInfo[0]);
            trie.connectNodeArr(Arrays.copyOfRange(foodInfo, 1, K + 1));
        }

        System.out.print(trie.toString());
        br.close();
    }
}

class Trie {
    private Node root = new Node("");

    //// 클래스명의 작명은 구체적인 구현(Trie)을 표현하는데 함수명의 작명은 추상적(Word)이라 위화감이 드는 것 같습니다
    //// -> 작명에서도 추상화 범위를 생각해볼 수 있던 피드백이었습니다. node로 변경해봤습니다!
    public void connectNodeArr(String[] nodeDataArr) {
        Node parent = root;
        for (String data : nodeDataArr) {
            parent = getNodeAfterConnectNode(parent, data);
        }
    }

    private Node getNodeAfterConnectNode(Node parent, String data) {
        boolean isAlreadyChild = false;
        Node node = new Node(data);

        for (Node child : parent.getChildNodeList()) {
            if (!child.getData().equals(data)) continue;

            isAlreadyChild = true;
            node = child;
            break;
        }

        if (!isAlreadyChild) parent.addChild(node);

        return node;
    }

    @Override
    public String toString() {
        return toStringBuilder(root, 0).toString();
    }

    private StringBuilder toStringBuilder(Node parent, int depth) {
        StringBuilder sb = new StringBuilder();

        StringBuilder depthDivision = new StringBuilder("--".repeat(depth));
        for (Node child : parent.getChildNodeList()) {
            sb.append(depthDivision)
                .append(child.getData())
                .append("\n")
                .append(toStringBuilder(child, depth + 1));
        }

        return sb;
    }
}

class Node implements Comparable<Node> {
    private String data;
    private List<Node> childNodeList;

    public Node(String data) {
        this.data = data;
        childNodeList = new ArrayList<>();
    }

    public String getData() {
        return data;
    }

    public List<Node> getChildNodeList() {
        return childNodeList;
    }

    public void addChild(Node node) {
        childNodeList.add(node);
        Collections.sort(childNodeList);
    }

    @Override
    public int compareTo(Node n){
        return this.data.compareTo(n.data);
    }
}