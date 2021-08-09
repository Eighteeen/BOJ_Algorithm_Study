import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Trie trie = new Trie();
        int N = Integer.parseInt(br.readLine());
        while (N-- > 0) {
            String[] foodInfo = br.readLine().split(" ");
            int K = Integer.parseInt(foodInfo[0]);
            trie.connectWord(Arrays.copyOfRange(foodInfo, 1, K + 1));
        }

        System.out.print(trie.toString());
        br.close();
    }
}

class Trie {
    private Node root = new Node("");

    public void connectWord(String[] wordArr) {
        Node parent = root;
        for (String word : wordArr) {
            parent = getNodeAfterConnectWord(parent, word);
        }
    }

    private Node getNodeAfterConnectWord(Node parent, String word) {
        boolean isAlreadyChild = false;
        Node node = new Node(word);

        for (Node child : parent.getChildNodeList()) {
            if (!child.getData().equals(word)) continue;

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