package akshay.example.com.mvplistfetch.model.pojo;

/**
 * Created by akshay on 16/09/16.
 */
public class Items {


    private String image;
    private String description;
    private String title;

    public Items(String image, String description, String title) {
        this.image = image;
        this.description = description;
        this.title = title;
    }


    public String getImage() {
        return image;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }

}
