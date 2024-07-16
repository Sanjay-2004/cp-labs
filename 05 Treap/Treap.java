import java.util.*;

class Treap {
    class Pair<U, V> {
        U left;
        V right;

        Pair(U _left, V _right) {
            left = _left;
            right = _right;
        }
    }

    class TreapNode {
        Double key;
        Double priority;
        long count;
        TreapNode left, right;

        // Constructor with key and priority
        TreapNode(Double key) {
            this.key = key;
            this.priority = Math.random(); // Assign a random priority
            this.left = this.right = null;
            this.count = 1L;
        }

        // Default constructor
        TreapNode() {
            this(null);
        }
    }

    // Return the number of nodes in the subtree rooted at `node`
    long getCount(TreapNode node) {
        if (node == null)
            return 0;
        return node.count;
    }

    // Update the count of the nodes in the subtree rooted at `node`
    void updateCount(TreapNode node) {
        if (node != null)
            node.count = 1 + getCount(node.left) + getCount(node.right);
    }

    // Perform a right rotation
    TreapNode rightRotate(TreapNode y) {
        TreapNode x = y.left;
        TreapNode T2 = x.right;
        x.right = y;
        y.left = T2;
        updateCount(y);
        updateCount(x);
        return x;
    }

    // Perform a left rotation
    TreapNode leftRotate(TreapNode x) {
        TreapNode y = x.right;
        TreapNode T2 = y.left;
        y.left = x;
        x.right = T2;
        updateCount(x);
        updateCount(y);
        return y;
    }

    // Split the treap into two treaps based on the given key
    TreapNode[] splitTreap(TreapNode node, double key) {
        TreapNode[] result;
        if (node == null) {
            result = new TreapNode[] { null, null };
        } else if (node.key < key) {
            result = splitTreap(node.right, key);
            node.right = result[0];
            result = new TreapNode[] { node, result[1] };
        } else {
            result = splitTreap(node.left, key);
            node.left = result[1];
            result = new TreapNode[] { result[0], node };
        }
        updateCount(node);
        return result;
    }

    // Merge two treaps
    TreapNode mergeTreaps(TreapNode left, TreapNode right) {
        TreapNode result = null;
        if (left == null || right == null) {
            result = (left != null) ? left : right;
        } else if (left.priority > right.priority) {
            left.right = mergeTreaps(left.right, right);
            result = left;
        } else {
            right.left = mergeTreaps(left, right.left);
            result = right;
        }
        updateCount(result);
        return result;
    }

    // Insert a node into the treap
    TreapNode insertNode(TreapNode root, TreapNode node) {
        if (root == null) {
            return node;
        }
        if (root.priority < node.priority) {
            TreapNode[] splitResult = splitTreap(root, node.key);
            node.left = splitResult[0];
            node.right = splitResult[1];
            return node;
        }
        if (root.key > node.key) {
            root.left = insertNode(root.left, node);
            if (root.left.priority > root.priority) {
                root = rightRotate(root);
            }
        } else {
            root.right = insertNode(root.right, node);
            if (root.right.priority > root.priority) {
                root = leftRotate(root);
            }
        }
        updateCount(root);
        return root;
    }

    // Search for the number of nodes with keys less than or equal to the given key
    Pair<TreapNode, Long> searchNoGreaterThan(TreapNode root, double key) {
        TreapNode[] splitResult = splitTreap(root, key);
        long count = getCount(splitResult[0]);
        return new Pair<>(mergeTreaps(splitResult[0], splitResult[1]), count);
    }

    // Calculate the number of reverse pairs in the array
    public int countReversePairs(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        int length = nums.length;

        TreapNode root = new TreapNode((double) nums[length - 1]);
        int result = 0;

        for (int i = length - 2; i >= 0; i--) {
            Pair<TreapNode, Long> searchResult = searchNoGreaterThan(root, (nums[i] + 0.0) / 2);
            result += searchResult.right;
            root = searchResult.left;
            TreapNode newNode = new TreapNode((double) nums[i]);
            root = insertNode(root, newNode);
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = sc.nextInt();
        System.out.println(new Treap().countReversePairs(arr));
        sc.close();
    }
}
