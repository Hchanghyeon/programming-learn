import util.FileUtils;

public class MakeFileApplication {

    private static final String FILE_PATH = "C:/Users\\changhyeon\\Desktop\\ojt\\netty\\data.txt";

    public static void main(String[] args) {
        FileUtils.createFile(FILE_PATH);
    }
}
