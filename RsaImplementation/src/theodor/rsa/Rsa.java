package theodor.rsa;

public class Rsa {
    private final long pk, sk, n;

    public Rsa() {
        long p = 5;
        long q = 7;

        n = p*q;

        long r = (p-1)*(q-1);
        pk = genPk(r);

        sk = genSk(r);
    }

    public long encode(long msg, long pk) {
        return (long)(Math.pow(msg, pk) % n);
    }

    public long decode(long code) {
        return (long)(Math.pow(code, sk) % n);
    }

    public long getPk() {
        return this.pk;
    }

    private long genPk(long r) {
        long res = 2;

        while (greatestCommonDivisor(res, r) != 1) {
            res += 1;
        }

        return res;
    }

    private long greatestCommonDivisor(long a, long b) {
        long temp;
        while (true) {
            temp = a%b;
            if (temp == 0) {
                return b;
            }
            a = b;
            b = temp;
        }
    }

    public long genSk(long r) {
        long res = 0;

        while ( (res*pk) % r != 1 ) {
            res += 1;
        }

        return res;
    }
}
