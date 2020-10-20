package pl.krysinski.beers.demo.model;

public class ShortBeersInfo {
   private Integer id;
   private String name;
   private String firstBrewed;
   private String description;
   private String ibu;
   private String imageUrl;

    public ShortBeersInfo(Integer id, String name, String firstBrewed, String description, String ibu, String imageUrl) {
        this.id = id;
        this.name = name;
        this.firstBrewed = firstBrewed;
        this.description = description;
        this.ibu = ibu;
        this.imageUrl = imageUrl;
    }

    public ShortBeersInfo() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstBrewed() {
        return firstBrewed;
    }

    public void setFirstBrewed(String firstBrewed) {
        this.firstBrewed = firstBrewed;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIbu() {
        return ibu;
    }

    public void setIbu(String ibu) {
        this.ibu = ibu;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "ShortBeersInfo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", firstBrewed='" + firstBrewed + '\'' +
                ", description='" + description + '\'' +
                ", ibu=" + ibu +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
