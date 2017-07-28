package videos.hoctienganh.cuong.objects;

public class MusicOnline {

    String duration;
    String linkImages;
    String linkMusic;
    String songName;

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getLinkImages() {
        return linkImages;
    }

    public void setLinkImages(String linkImages) {
        this.linkImages = linkImages;
    }

    public String getLinkMusic() {
        return linkMusic;
    }

    public void setLinkMusic(String linkMusic) {
        this.linkMusic = linkMusic;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getCaSiName() {
        return caSiName;
    }

    public void setCaSiName(String caSiName) {
        this.caSiName = caSiName;
    }

    String caSiName;

    public MusicOnline() {

    }

    public MusicOnline(String duration, String linkImages, String linkMusic, String songName, String caSiName) {
        this.duration = duration;
        this.caSiName = caSiName;
        this.linkImages = linkImages;
        this.linkMusic = linkMusic;
        this.songName = songName;
    }
}
