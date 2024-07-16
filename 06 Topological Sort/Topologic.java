import java.util.*;

public class Topologic {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        TopologicalSort tp = new TopologicalSort(n);
        int c = sc.nextInt();
        while (c-- > 0) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            tp.addEdge(a, b);
        }
        tp.topologicalSort();
        sc.close();
    }
}

class TopologicalSort {
    int n;
    ArrayList<ArrayList<Integer>> adj;

    TopologicalSort(int n) {
        this.n = n;
        adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
    }

    void addEdge(int u, int v) {
        adj.get(u).add(v);
    }

    void topologicalSort() {
        int[] inDiff = new int[n];

        for (int i = 0; i < n; i++) {
            for (int j : adj.get(i)) {
                inDiff[j]++;
            }
        }

        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (inDiff[i] == 0) {
                q.offer(i);
            }
        }
        List<Integer> ans = new ArrayList<>();

        while (!q.isEmpty()) {
            int top = q.poll();
            ans.add(top);

            for (int i : adj.get(top)) {
                inDiff[i]--;
                if (inDiff[i] == 0)
                    q.offer(i);
            }
        }

        System.err.println("TopoSort: " + ans.toString());
    }
}