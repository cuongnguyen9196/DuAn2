package videos.hoctienganh.cuong.objects;

public class YoutubeMV {

    String imgURL, tenClip, thoigian, linkYT;

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    public String getTenClip() {
        return tenClip;
    }

    public void setTenClip(String tenClip) {
        this.tenClip = tenClip;
    }

    public String getThoigian() {
        return thoigian;
    }

    public void setThoigian(String thoigian) {
        this.thoigian = thoigian;
    }

    public String getLinkYT() {
        return linkYT;
    }

    public void setLinkYT(String linkYT) {
        this.linkYT = linkYT;
    }

    public YoutubeMV() {}

    public YoutubeMV(String imgURL, String tenClip, String thoigian, String linkYT) {
        this.imgURL = imgURL;
        this.linkYT = linkYT;
        this.tenClip = tenClip;
        this.thoigian = thoigian;
    }


}
