package theodor.rsa;

public class Main {

    public static void main(String[] args) {
	    Rsa rsa = new Rsa();

        long msg = 42;
        System.out.println(rsa.encode( msg, rsa.getPk()) );
        System.out.println(rsa.decode( rsa.encode( msg, rsa.getPk()) ) );
    }
}
