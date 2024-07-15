import java.util.*;

public class Program {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String text = sc.next();
        sc.nextLine();
        String[] words = sc.nextLine().split(" ");
        int[][] ans = new Trie().indexPairs(text, words);
        for (int[] t : ans) {
            System.out.println(Arrays.toString(t));
        }

        sc.close();
    }
}

class Trie {
    class TrieNode {
        TrieNode[] children;
        boolean isEnd;

        TrieNode() {
            children = new TrieNode[26];
            isEnd = false;
        }
    }

    private TrieNode root;

    Trie() {
        root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode temp = root;

        for (char ch : word.toCharArray()) {
            int idx = ch - 'a';

            if (temp.children[idx] == null) {
                temp.children[idx] = new TrieNode();
            }

            temp = temp.children[idx];
        }
        temp.isEnd = true;
    }

    public List<int[]> search(String text, int start) {
        List<int[]> ans = new ArrayList<>();
        TrieNode node = root;

        for (int i = start; i < text.length(); i++) {
            int idx = text.charAt(i) - 'a';
            if (node.children[idx] == null)
                break;
            node = node.children[idx];
            if (node.isEnd) {
                ans.add(new int[] { start, i });
            }
        }
        return ans;
    }

    public int[][] indexPairs(String text, String[] words) {
        for (String word : words) {
            insert(word);
        }

        List<int[]> res = new ArrayList<>();

        for (int i = 0; i < text.length(); i++) {
            res.addAll(search(text, i));
        }

        res.sort(new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                if (a[0] == b[0])
                    return Integer.compare(a[1], b[1]);
                return Integer.compare(a[0], b[0]);
            }
        });

        int[][] ans = new int[res.size()][2];
        for (int i = 0; i < res.size(); i++) {
            ans[i] = res.get(i);
        }

        return ans;
    }
}