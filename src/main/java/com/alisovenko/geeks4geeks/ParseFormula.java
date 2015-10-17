package com.alisovenko.geeks4geeks;

/**
 * http://www.geeksforgeeks.org/forums/topic/facebook-interview-3/
 *
 * BNF:
 * calculate := formula=formula
 * formula := (expr)+;
 * expr := [+/-][\d+/\d+x];
 * @author alisovenko 07.04.15
 */
public class ParseFormula {
    private static int idx = 0;

    public static double calculate(String s) {
        Tuple t = parseFormula(s);

        if (s.charAt(idx) != '=') {
            throw new IllegalArgumentException();
        }

        idx++;
        Tuple t2 = parseFormula(s);
        int devisor = (t.x - t2.x);

        return devisor == 0 ? 0 : (double)(t2.v - t.v) / devisor;
    }

    private static Tuple parseFormula(String s) {
        Tuple t = new Tuple();
        skipBlanks(s);

        while (idx < s.length() && s.charAt(idx) != '=') {
            t.add(parseExpression(s));
            skipBlanks(s);
        }
        return t;
    }

    private static Tuple parseExpression(String s) {
        char sign = parseSign(s);

        Tuple t = parseTuple(s);

        return sign == '-' ? t.negate() : t;
    }

    private static char parseSign(String s) {
        skipBlanks(s);
        char c = s.charAt(idx);
        if (idx < s.length() && (c == '-' || c == '+')) {
            idx++;
            return c;
        }
        return '+';
    }

    private static Tuple parseTuple(String s) {
        skipBlanks(s);
        int res = 0;
        Tuple t = new Tuple();
        while (idx < s.length() && s.charAt(idx) >= '0' && s.charAt(idx) <= '9') {
            res += res * 10 + s.charAt(idx) - '0';
            idx++;
        }
        if (idx < s.length() && s.charAt(idx) == 'x') {
            t.x = res;
            idx++;
        }
        else {
            t.v = res;
        }
        return t;
    }
    private static void skipBlanks(String s) {
        while (idx < s.length() && s.charAt(idx) == ' '){
            idx++;
        }
    }

    private static class Tuple {
        int x;
        int v;

        public Tuple add(Tuple t) {
            this.x += t.x;
            this.v += t.v;
            return this;
        }
        public Tuple negate() {
            this.x = (int)-((long)x);
            this.v = (int)-((long)v);
            return this;
        }

        @Override
        public String toString() {
            return x + "," + v;
        }
    }

    public static void main(String[] args) {
        System.out.println(calculate("5x + 4 = -3 + 2 + 2x"));
        System.out.println(calculate("5x-4x  -7x + 3-1 = 0"));
    }
}
