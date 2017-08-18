package videos.hoctienganh.cuong.Model;


import java.io.Serializable;

public class Model_Lesson  implements Serializable {
    String id,tenbai ;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTenbai() {
        return tenbai;
    }

    public void setTenbai(String tenbai) {
        this.tenbai = tenbai;
    }

    public Model_Lesson(String id, String tenbai) {
        this.id = id;
        this.tenbai = tenbai;
    }
}

