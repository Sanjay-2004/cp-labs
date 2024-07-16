import java.util.*;

public class SumAtLeastK {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int k = sc.nextInt();

        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }

        System.out.println(new Solution().shortestSubarray(nums, k));
        sc.close();
    }
}

class Solution {
    public int shortestSubarray(int[] nums, int k) {
        int n = nums.length;
        Deque<long[]> deque = new LinkedList<>();
        long sum = 0;
        long ans = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            sum += nums[i];
            if (sum >= k) {
                ans = Math.min(ans, i + 1);
            }
            long[] cur = { Integer.MIN_VALUE, Integer.MIN_VALUE };
            while (!deque.isEmpty() && (sum - deque.peekFirst()[1] >= k)) {
                cur = deque.pollFirst();
            }
            if (cur[1] != Integer.MIN_VALUE) {
                ans = Math.min(ans, i - cur[0]);
            }

            while (!deque.isEmpty() && sum <= deque.peekLast()[1]) {
                deque.pollLast();
            }

            deque.add(new long[] { i, sum });
        }

        return ans == Integer.MAX_VALUE ? -1 : (int) ans;
    }
}
