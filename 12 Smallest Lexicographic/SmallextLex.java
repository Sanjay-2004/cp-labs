
import java.util.*;

public class SmallextLex {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String a = sc.next();
        String b = sc.next();

        DisjointSet dsu = new DisjointSet(26);
        dsu.addStrings(a, b);

        String baseStr = sc.next();
        System.out.println(dsu.findBase(baseStr));

        sc.close();
    }
}

class DisjointSet {
    int n;
    int[] parent, size;

    DisjointSet(int n) {
        this.n = n + 1;
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
        size = new int[n];
    }

    void addStrings(String a, String b) {
        for (int i = 0; i < a.length(); i++) {
            int u = a.charAt(i) - 'a';
            int v = b.charAt(i) - 'a';
            unionBySize(u, v);
        }
    }

    String findBase(String baseStr) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < baseStr.length(); i++) {
            int u = baseStr.charAt(i) - 'a';
            str.append((char) (findParent(u) + 'a'));
        }
        return str.toString();
    }

    int findParent(int u) {
        if (parent[u] == u)
            return u;
        return parent[u] = findParent(parent[u]);
    }

    void unionBySize(int u, int v) {
        int p1 = findParent(u);
        int p2 = findParent(v);

        if (p1 != p2) {
            parent[Math.max(p1, p2)] = Math.min(p1, p2);
        }
    }
}