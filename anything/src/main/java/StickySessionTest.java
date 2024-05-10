/*
 * 똑같은 문자열은 항상 동일한 HashCode를 갖게 됨
 */

public class StickySessionTest {
    public static void main(String[] args) {
        String text = "123.123.123.123";
        int index = 0;
        int size = 3;

        String result = text+":"+index;

        System.out.println(result.hashCode());
        System.out.println(Math.abs(result.hashCode() % size));
    }
}
