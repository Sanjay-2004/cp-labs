import java.util.*;

public class ArticulationPoints {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        int c = sc.nextInt();
        while (c-- > 0) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            adj.get(a).add(b);
            adj.get(b).add(a);
        }
        System.out.println(new Articulation().findArticulation(n, adj).toString());
        sc.close();
    }
}

class Articulation {
    int timer = 0;

    private void dfs(int node, int parent, boolean[] vis, int[] tin, int[] low, List<List<Integer>> adj,
            boolean[] isAP) {
        vis[node] = true;
        tin[node] = low[node] = timer++;
        int children = 0;
        for (int it : adj.get(node)) {
            if (it == parent)
                continue;
            if (!vis[it]) {
                dfs(it, node, vis, tin, low, adj, isAP);
                low[node] = Math.min(low[node], low[it]);
                if (low[it] >= tin[node] && parent != -1) {
                    isAP[node] = true;
                }
                children++;
            } else {
                low[node] = Math.min(low[node], tin[it]);
            }
        }
        if (children > 0 && parent == -1) {
            isAP[node] = true;
        }
    }

    List<Integer> findArticulation(int n, List<List<Integer>> adj) {
        boolean[] vis = new boolean[n];
        int[] tin = new int[n];
        int[] low = new int[n];
        boolean[] isAP = new boolean[n];
        dfs(0, -1, vis, tin, low, adj, isAP);
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (isAP[i])
                ans.add(i);
        }
        return ans;
    }
}