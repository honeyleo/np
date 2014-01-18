package com.w.np.utils;
public class Vigenere {
    private String table;
    private int seedA = 1103515245;
    private int seedB = 12345;
    
    public Vigenere(String table, int seed) {
        this.table = chaos(table, seed, table.length());
    }
    public Vigenere(String table) {
        this(table, 11);
    }
    public Vigenere() {
        this(11);
    }
    public Vigenere(int seed) {
        this("ABCDEFGHIJKLMNOPQRSTUVWXYZ", seed);
    }
    
    private char dict(int i, boolean reverse) {
        int s = table.length(), index = reverse ? s - i : i;
        return table.charAt(index);
    }
    private int dict(char c,  boolean reverse) {
        int s = table.length(), index = table.indexOf(c);
        return reverse ? s - index : index;
    }
    private int seed(int seed) {
        long temp = seed;
        return (int)((temp * seedA + seedB) & 0x7fffffffL);
    }

    public String chaos(String data, int seed, int cnt) {
        StringBuffer buf = new StringBuffer(data);
        char tmp; int a, b, r = data.length();
        for (int i = 0; i < cnt; i += 1) {
            seed = seed(seed); a = seed % r;
            seed = seed(seed); b = seed % r;
            tmp = buf.charAt(a);
            buf.setCharAt(a, buf.charAt(b));
            buf.setCharAt(b, tmp);
        }
        return buf.toString();
    }
    public String crypto(boolean reverse,
                         String key, String text) {
        String ret = null;
        StringBuilder buf = new StringBuilder();
        int m, k, s = table.length(), 
                  e = text.length(), 
                  ke = key.length();

        for(int i = 0; i < e; i += 1) {
            m = dict(text.charAt(i), reverse);
            k = dict(key.charAt(i % ke), false);
            if (m < 0 || k < 0) break;
            m = m + k + i;
            buf.append(dict(m % s, reverse));
        }
        if (buf.length() == e)
            ret = buf.toString();
        return ret;
    }
    public String encode(String key, String text) {
        return crypto(false, key, text);
        
    }
    public String decode(String key, String text) {
        return crypto(true , key, text);
    }
    public static void main(String[] args) {
        Vigenere vigenere = new Vigenere();
        String data = vigenere.encode("BELLASO", "APPLE");
        System.out.println(data);
        String content = vigenere.decode("BELLASO", data);
        System.out.println(content);
    }
}