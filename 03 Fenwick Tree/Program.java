import java.util.*;

public class Program {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        int s1 = sc.nextInt();
        int s2 = sc.nextInt();

        System.out.println(new Solution(arr).findRangeSum(s1, s2));

        sc.close();
    }
}

class Solution {
    int N;
    int[] fenwick, nums;

    Solution(int[] arr) {
        N = arr.length + 1;
        fenwick = new int[N];
        nums = arr;
        for (int i = 0; i < arr.length; i++)
            init(i + 1, arr[i]);
    }

    int findRangeSum(int l, int r) {
        return sum(r + 1) - sum(l);
    }

    void init(int idx, int val) {
        while (idx < N) {
            fenwick[idx] += val;
            idx += (idx & (-idx));
        }
    }

    void update(int i, int s) {
        int diff = s - nums[i];
        nums[i] = s;
        i++;
        while (i < N) {
            fenwick[i] += diff;
            i += (i & (-i));
        }
    }

    int sum(int i) {
        int ans = 0;
        while (i > 0) {
            ans += fenwick[i];
            i -= (i & (-i));
        }
        return ans;
    }
}