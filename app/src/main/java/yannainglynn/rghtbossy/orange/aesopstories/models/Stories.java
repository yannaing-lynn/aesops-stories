package yannainglynn.rghtbossy.orange.aesopstories.models;

public class Stories {
    private int id;
    private byte[] image;
    private String title;
    private String firstBody;
    private String secondBody;
    private String thirdBody;

    public Stories() {
    }

    public Stories(int id, byte[] image, String title, String firstBody, String secondBody, String thirdBody) {
        this.id = id;
        this.image = image;
        this.title = title;
        this.firstBody = firstBody;
        this.secondBody = secondBody;
        this.thirdBody = thirdBody;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFirstBody() {
        return firstBody;
    }

    public void setFirstBody(String firstBody) {
        this.firstBody = firstBody;
    }

    public String getSecondBody() {
        return secondBody;
    }

    public void setSecondBody(String secondBody) {
        this.secondBody = secondBody;
    }

    public String getThirdBody() {
        return thirdBody;
    }

    public void setThirdBody(String thirdBody) {
        this.thirdBody = thirdBody;
    }
}
