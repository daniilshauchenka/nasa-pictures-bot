package entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NasaObject {
    private String copyright;
    private String creationDate;
    private String explanation;
    private String pictureUrlHQ;
    private String mediaType;
    private String serviceVersion;
    private String title;
    private String pictureUrl;

    public NasaObject(
            @JsonProperty("copyright") String copyright,
            @JsonProperty("date") String creationDate,
            @JsonProperty("explanation") String explanation,
            @JsonProperty("hdurl") String pictureUrlHQ,
            @JsonProperty("media_type") String mediaType,
            @JsonProperty("service_version") String serviceVersion,
            @JsonProperty("title") String title,
            @JsonProperty("url") String pictureUrl) {
        this.copyright = copyright;
        this.creationDate = creationDate;
        this.explanation = explanation;
        this.pictureUrlHQ = pictureUrlHQ;
        this.mediaType = mediaType;
        this.serviceVersion = serviceVersion;
        this.title = title;
        this.pictureUrl = pictureUrl;
    }

    @Override
    public String toString() {
        return "NasaObject{" +
                "copyright='" + copyright + '\'' +
                ", date='" + creationDate + '\'' +
                ", explanation='" + explanation + '\'' +
                ", hdurl='" + pictureUrlHQ + '\'' +
                ", mediaType='" + mediaType + '\'' +
                ", serviceVersion='" + serviceVersion + '\'' +
                ", title='" + title + '\'' +
                ", url='" + pictureUrl + '\'' +
                '}';
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public String getPictureUrlHQ() {
        return pictureUrlHQ;
    }

    public void setPictureUrlHQ(String pictureUrlHQ) {
        this.pictureUrlHQ = pictureUrlHQ;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public String getServiceVersion() {
        return serviceVersion;
    }

    public void setServiceVersion(String serviceVersion) {
        this.serviceVersion = serviceVersion;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }
}
