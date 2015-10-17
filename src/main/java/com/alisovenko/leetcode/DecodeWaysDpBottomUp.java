package com.alisovenko.leetcode;

/**
 * @author alisovenko 28.01.15
 */
public class DecodeWaysDpBottomUp {
    public int numDecodings(String s) {
        if (s.length() == 0) {
            return 0;
        }
        return numberOfWays(s);
    }

    private int numberOfWays(String s) {
        int[] cache = new int[s.length()];
        cache[0] = isDecodable(s.charAt(0)) ? 1 : 0;

        for (int i = 1; i < s.length(); i++) {
            if (isDecodable(s.charAt(i))) {
                cache[i] = cache[i - 1];
            }
            if (i > 0 && isDecodable(s.substring(i - 1, i + 1))) {
                int cached = i > 1 ? cache[i - 2] : 1;
                cache[i] += cached;
                if (cache[i] == 0) {
                    break;
                }
            }
        }
        return cache[s.length() - 1];
    }

    private boolean isDecodable(char c) {
        return '1' <= c && c <= '9';
    }
    private boolean isDecodable(String c) {
        return c.compareTo("10") >= 0 && c.compareTo("26") <= 0;
    }

    public static void main(String[] args) {
        System.out.println(new DecodeWaysDpBottomUp().numDecodings("101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010"));
        System.out.println(new DecodeWaysDpBottomUp().numDecodings("12"));
        System.out.println(new DecodeWaysDpBottomUp().numDecodings("110"));
        System.out.println(new DecodeWaysDpBottomUp().numDecodings("1234"));
    }
}
