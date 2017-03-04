package test.ezra.com.recyclertest;

/**
 * Created by Ezra on 2017/2/21.
 */

public class Fruit {

    private String name;
    private int imageId;

    public Fruit() {
    }

    public Fruit(String name, int imageId) {
        this.name = name;
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}