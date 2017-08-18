package videos.hoctienganh.cuong.Model;

import java.io.Serializable;

/**
 * Created by Vaio on 2017-08-13.
 */

public class Model_cauTL implements Serializable {
     String textTL;

    public String getTextTL() {
        return textTL;
    }

    public void setTextTL(String textTL) {
        this.textTL = textTL;
    }

    public Model_cauTL(String textTL) {
        this.textTL = textTL;
    }
}
