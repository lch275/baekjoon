import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import java.io.*;

public class Solution11401 {
    static final int MOD = 1_000_000_007;
    static final int MAX = 4_000_000;
    static long[] factorial = new long[MAX + 1];

    public static void main(String[] args) throws IOException {
        // 빠른 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] nk = br.readLine().split(" ");
        int n = Integer.parseInt(nk[0]);
        int k = Integer.parseInt(nk[1]);

        precomputeFactorials();

        long numerator = factorial[n];
        long denominator = (factorial[k] * factorial[n - k]) % MOD;

        long result = (numerator * modInverse(denominator)) % MOD;
        System.out.println(result);
    }

    // 팩토리얼 사전 계산
    static void precomputeFactorials() {
        factorial[0] = 1;
        for (int i = 1; i <= MAX; i++) {
            factorial[i] = (factorial[i - 1] * i) % MOD;
        }
    }

    // 페르마의 소정리를 이용한 역원 계산
    static long modInverse(long a) {
        return modPow(a, MOD - 2);
    }

    // 거듭제곱: a^b % MOD
    static long modPow(long base, long exp) {
        long result = 1;
        base %= MOD;

        while (exp > 0) {
            if ((exp & 1) == 1) {
                result = (result * base) % MOD;
            }
            base = (base * base) % MOD;
            exp >>= 1;
        }

        return result;
    }
}
