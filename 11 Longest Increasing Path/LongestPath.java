import java.util.*;

public class LongestPath {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int r = sc.nextInt();
        int c = sc.nextInt();

        int[][] mat = new int[r][c];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                mat[i][j] = sc.nextInt();
            }
        }

        System.out.println(new Solution().findLongestIncreasing(mat));

        sc.close();
    }
}

class Solution {
    int rows[] = { -1, 1, 0, 0 };
    int cols[] = { 0, 0, -1, 1 };

    int findLongestIncreasing(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int ans = 0;
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                ans = Math.max(ans, dfs(matrix, i, j, dp));
            }
        }
        return ans;
    }

    int dfs(int[][] matrix, int r, int c, int[][] dp) {
        if (dp[r][c] > 0)
            return dp[r][c];
        int m = matrix.length;
        int n = matrix[0].length;

        int curAns = 0;
        for (int i = 0; i < 4; i++) {
            int nr = r + rows[i];
            int nc = c + cols[i];

            if (nr >= 0 && nr < m && nc >= 0 && nc < n && matrix[nr][nc] > matrix[r][c]) {
                curAns = Math.max(curAns, dfs(matrix, nr, nc, dp));
            }
        }

        return dp[r][c] = curAns + 1;
    }
}