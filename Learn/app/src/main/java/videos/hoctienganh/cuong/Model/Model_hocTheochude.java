package videos.hoctienganh.cuong.Model;

import java.io.Serializable;

/**
 * Created by Vaio on 2017-08-11.
 */

public class Model_hocTheochude implements Serializable {
        String idChude,CauHoi,nameQuest;

    public String getIdChude() {
        return idChude;
    }

    public void setIdChude(String idChude) {
        this.idChude = idChude;
    }

    public String getCauHoi() {
        return CauHoi;
    }

    public void setCauHoi(String cauHoi) {
        CauHoi = cauHoi;
    }

    public String getNameQuest() {
        return nameQuest;
    }

    public void setNameQuest(String nameQuest) {
        this.nameQuest = nameQuest;
    }

    public Model_hocTheochude(String idChude, String cauHoi, String nameQuest) {
        this.idChude = idChude;
        CauHoi = cauHoi;
        this.nameQuest = nameQuest;
    }
}
