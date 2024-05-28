package ascii;

public class AsciiTest {

    public static void main(String[] args) {

        char zero = '0';
        char one = '1';
        char two = '2';
        System.out.println((int)zero); // Ascii 48
        System.out.println((int)one); // Ascii 49
        System.out.println((int)two); // Ascii 50

        System.out.println(zero - '0'); // int 0
        System.out.println(one - '0'); // int 1
        System.out.println(two - '0'); // int 2
    }
}
