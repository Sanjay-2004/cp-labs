import java.util.*;

public class Program {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        System.err.println(new Solution().isPalin(s));
        sc.close();
    }
}

class Solution {
    boolean isPalin(String s) {
        int mp = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            mp ^= (1 << (c - 'a'));
        }
        return Integer.bitCount(mp) <= 1;
    }
}