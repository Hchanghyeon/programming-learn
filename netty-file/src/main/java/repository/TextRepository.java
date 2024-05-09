package repository;

import util.StringUtils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.concurrent.BlockingQueue;
import java.util.logging.Logger;

public class TextRepository {

    private static final String URL = "jdbc:mariadb://192.168.2.91:3306/text";
    private static final String USER = "root";
    private static final String PASSWORD = "1410";
    private static final Logger log = Logger.getLogger(TextRepository.class.getName());
    private static final String SQL = "INSERT INTO text (uuid1,uuid2,uuid3,uuid4,uuid5,uuid6,uuid7,uuid8,uuid9) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static int COUNT = 1;
    private Connection connection;
    private BlockingQueue<String> queue;

    public TextRepository() throws SQLException {
        connection = DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public void save(final String text) throws IOException, SQLException {
        if(StringUtils.isBlankOrNull(text)){
            throw new RuntimeException("문자가 없거나, Null 입니다.");
        }

        String[] strings = StringUtils.parseString(text);
        PreparedStatement statement = connection.prepareStatement(SQL);

        for(int i = 0; i < strings.length; i++){
            statement.setString((i % 9) + 1, strings[i]);
            if((i + 1) % 9 == 0 || i == strings.length - 1){
                statement.addBatch();
                if((i + 1) % 9 == 0){
                    statement.executeBatch();
                    statement.clearBatch();
                }
            }
        }
        statement.executeBatch(); // 남은 배치가 있다면 실행
        statement.close();
    }

    public void closeConnection() throws SQLException {
        if(connection != null && !connection.isClosed()){
            connection.close();
        }
    }
}
