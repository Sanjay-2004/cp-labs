import java.util.*;

public class Program {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = sc.nextInt();

        BinaryTree root = new BinaryTree(arr);
        int a = sc.nextInt();
        int b = sc.nextInt();

        System.out.println(root.leastCommonAncestor(a, b));

        sc.close();
    }
}

class BinaryTree {
    class BinaryTreeNode {
        int val;
        BinaryTreeNode left, right;

        BinaryTreeNode(int n) {
            val = n;
            left = null;
            right = null;
        }
    }

    BinaryTreeNode root;

    BinaryTree(int[] arr) {

        root = new BinaryTreeNode(arr[0]);
        Queue<BinaryTreeNode> queue = new LinkedList<>();
        queue.offer(root);

        int idx = 1;
        while (idx < arr.length && !queue.isEmpty()) {
            BinaryTreeNode temp = queue.poll();
            if (arr[idx] != -1) {
                temp.left = new BinaryTreeNode(arr[idx]);
                queue.offer(temp.left);
            }
            idx++;
            if (idx < arr.length && arr[idx] != -1) {
                temp.right = new BinaryTreeNode(arr[idx]);
                queue.offer(temp.right);
            }
            idx++;
        }

    }

    int leastCommonAncestor(int l, int r) {
        BinaryTreeNode ans = binaryLCA(root, l, r);
        return ans == null ? -1 : ans.val;
    }

    BinaryTreeNode binaryLCA(BinaryTreeNode root, int l, int r) {
        if (root == null || root.val == l || root.val == r)
            return root;

        BinaryTreeNode left = binaryLCA(root.left, l, r);
        BinaryTreeNode right = binaryLCA(root.right, l, r);

        if (left == null)
            return right;
        if (right == null)
            return left;

        return root;
    }
}