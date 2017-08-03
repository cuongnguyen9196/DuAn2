package videos.hoctienganh.cuong.objects;

public class English {

    private String title, thumbnailUrl,vid_link;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getVid_link() {
        return vid_link;
    }

    public void setVid_link(String vid_link) {
        this.vid_link = vid_link;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    private double duration;

    public English() {

    }

    public English(String name, String thumbnailUrl, String video,double duration) {
        this.title = name;
        this.thumbnailUrl = thumbnailUrl;
        this.vid_link = video;
        this.duration = duration;
    }
}