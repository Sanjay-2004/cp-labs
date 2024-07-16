import java.util.*;

public class PalindromePerm {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        System.err.println(new Palindrome().isPalin(s));
        sc.close();
    }
}

class Palindrome {
    boolean isPalin(String s) {
        int mp = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            mp ^= (1 << (c - 'a'));
        }
        return Integer.bitCount(mp) <= 1;
    }
}