import java.util.*;

public class Program {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int k = sc.nextInt();

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        System.out.println(new Solution().kDistinctElements(arr, k));
        sc.close();
    }
}

class Solution {
    int kDistinctElements(int[] arr, int k) {
        return atMostK(arr, k) - atMostK(arr, k - 1);
    }

    int atMostK(int[] arr, int k) {
        int n = arr.length;
        int i = 0, j = 0;
        int count = 0;
        Map<Integer, Integer> map = new HashMap<>();
        while (j < n) {
            map.put(arr[j], map.getOrDefault(arr[j], 0) + 1);
            while (map.size() > k) {
                map.put(arr[i], map.get(arr[i]) - 1);
                if (map.get(arr[i]) == 0) {
                    map.remove(arr[i]);
                }
                i++;
            }
            count += j - i + 1;
            j++;
        }
        return count;
    }
}