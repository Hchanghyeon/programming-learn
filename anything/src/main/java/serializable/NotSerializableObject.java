package serializable;

public class NotSerializableObject {

    private Long id;
    private String name;

    public NotSerializableObject(Long id, String name){
        this.id = id;
        this.name = name;
    }
}
