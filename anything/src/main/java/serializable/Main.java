package serializable;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        final Long id = 1L;
        final String name = "changhyeon";
        final String fileName1 = "noSerializable.txt";
        final String fileName2 = "serializable.txt";

        final NotSerializableObject notSerializableObject = new NotSerializableObject(id, name);
        final SerializableObject serializableObject = new SerializableObject(id, name, "test");

        // Serializable 구현이 되어있지 않기 때문에 직렬화 해서 저장 할 수 없음
        ObjectOutputStream out1 = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(fileName1)));
        out1.writeObject(notSerializableObject);
        out1.close();

        ObjectOutputStream out2 = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(fileName2)));
        out2.writeObject(serializableObject);
        out2.close();

        ObjectInputStream in1 = new ObjectInputStream(new BufferedInputStream(new FileInputStream(fileName1)));
        NotSerializableObject notSerializableObjectDeserialized = (NotSerializableObject) in1.readObject();

        ObjectInputStream in2 = new ObjectInputStream(new BufferedInputStream(new FileInputStream(fileName2)));
        SerializableObject serializableObjectDeserialized = (SerializableObject) in2.readObject();

        System.out.println(notSerializableObject.toString()); // 불가
        System.out.println(serializableObjectDeserialized.toString());
    }
}
