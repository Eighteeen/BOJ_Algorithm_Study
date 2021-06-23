import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

class Main {
    static Map<Character, Tree> treeMap;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        treeMap = new HashMap<>();
        
        for (int i = 0; i < N; i++) {
            String[] nodeInfo = br.readLine().split(" ");
            setTreeMap(nodeInfo[0].charAt(0), nodeInfo[1].charAt(0), nodeInfo[2].charAt(0));
        }

        Tree rootTree = treeMap.get(Character.valueOf('A'));
        sb.append(getPreorderTraversal(rootTree)).append("\n");
        sb.append(getInorderTraversal(rootTree)).append("\n");
        sb.append(getPostorderTraversal(rootTree)).append("\n");

        System.out.print(sb);
        br.close();
    }

    static void setTreeMap(char rootData, char leftData, char rightData) {
        Tree tree;
        if (treeMap.containsKey(rootData)) tree = treeMap.get(rootData);
        else tree = new Tree();

        tree.setRoot(rootData);
        treeMap.put(rootData, tree);

        Tree leftTree, rightTree;
        if (leftData != '.') {
            leftTree = new Tree();
            leftTree.setRoot(leftData);
            tree.setLeftNode(leftTree);
            treeMap.put(leftData, leftTree);
        }

        if (rightData != '.') {
            rightTree = new Tree();
            rightTree.setRoot(rightData);
            tree.setRightNode(rightTree);
            treeMap.put(rightData, rightTree);
        }
    }

    static StringBuilder getPreorderTraversal(Tree checkTree) {
        StringBuilder sb = new StringBuilder();
        if (checkTree == null) return sb;
        sb.append(checkTree.getRoot())
            .append(getPreorderTraversal(checkTree.getLeftNode()))
            .append(getPreorderTraversal(checkTree.getRightNode()));
        return sb;
    }

    static StringBuilder getInorderTraversal(Tree checkTree) {
        StringBuilder sb = new StringBuilder();
        if (checkTree == null) return sb;
        sb.append(getInorderTraversal(checkTree.getLeftNode()))
            .append(checkTree.getRoot())
            .append(getInorderTraversal(checkTree.getRightNode()));
        return sb;
    }

    static StringBuilder getPostorderTraversal(Tree checkTree) {
        StringBuilder sb = new StringBuilder();
        if (checkTree == null) return sb;
        sb.append(getPostorderTraversal(checkTree.getLeftNode()))
            .append(getPostorderTraversal(checkTree.getRightNode()))
            .append(checkTree.getRoot());
        return sb;
    }
}

class Tree {
    private char root = '\u0000';
    private Tree leftNode = null;
    private Tree rightNode = null;

    public char getRoot() {
        return root;
    }

    public Tree getLeftNode() {
        return leftNode;
    }

    public Tree getRightNode() {
        return rightNode;
    }

    public void setRoot(char root) {
        this.root = root;
    }

    public void setLeftNode(Tree leftNode) {
        this.leftNode = leftNode;
    }

    public void setRightNode(Tree rightNode) {
        this.rightNode = rightNode;
    }
}