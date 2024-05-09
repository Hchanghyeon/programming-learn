package util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Logger;

final public class FileUtils {

    private static final Logger log = Logger.getLogger(FileUtils.class.getName());
    private static final int MAX_COUNT = 100000;
    private static final int STRING_COUNT = 9;

    public static void createFile(String fileName) {
        final File file = new File(fileName);

        try {
            if(!file.exists()){
                file.createNewFile();
            }

            final FileWriter fileWriter = new FileWriter(file);
            final BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            for(int i = 0; i < MAX_COUNT; i++){
                bufferedWriter.write(StringUtils.getRandomString(STRING_COUNT)); // 한 줄씩 만들어서 File에 Write하기
                log.info(i + "번째 줄 처리되었습니다.");
            }

            bufferedWriter.close();
        } catch (IOException e) {
            throw new RuntimeException("파일에 데이터 입력 중 오류 발생");
        }
    }
}
