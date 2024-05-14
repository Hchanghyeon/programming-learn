package serializable;

import java.io.Serializable;

public class SerializableObject implements Serializable {
    private Long id;
    private String name;
    private String test;

    public SerializableObject(Long id, String name, String test){
        this.id = id;
        this.name = name;
        this.test = test;
    }

    @Override
    public String toString() {
        return "SerializableObject{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", test='" + test + '\'' +
                '}';
    }
}
