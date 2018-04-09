package AutoMappingExerciseApplication.dto;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class GameDto {

    private String title;
    private String trailer; // YouTube Video Id
    private String thumbnailURL; // URL
    private BigDecimal size;
    private BigDecimal price;
    private String description;
    private Date releaseDate;

    public GameDto() {
    }

    public GameDto(String title, BigDecimal price, BigDecimal size, String trailer, String thumbnailURL, String description, Date releaseDate) {
        this.title = title;
        this.price = price;
        this.size = size;
        this.trailer = trailer;
        this.thumbnailURL = thumbnailURL;
        this.description = description;
        this.releaseDate = releaseDate;
    }

    public GameDto(Map<String, String> fieldValueMap) {
        Class thisClass = this.getClass();
        for (String key : fieldValueMap.keySet()) {
            try {
                Field currentField = thisClass.getDeclaredField(key);
                boolean accessible = currentField.isAccessible();
                Class<?> type = currentField.getType();
                currentField.setAccessible(true);
                Constructor constructor = type.getConstructor(String.class);
                currentField.set(this, constructor.newInstance(fieldValueMap.get(key)));
                currentField.setAccessible(accessible);
            } catch (NoSuchFieldException |
                    IllegalAccessException |
                    InvocationTargetException |
                    InstantiationException |
                    NoSuchMethodException e) {
                //e.printStackTrace();
            }
        }
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTrailer() {
        return this.trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    public String getImageThumbnail() {
        return this.thumbnailURL;
    }

    public void setImageThumbnail(String thumbnailURL) {
        this.thumbnailURL = thumbnailURL;
    }

    public BigDecimal getSize() {
        return this.size;
    }

    public void setSize(BigDecimal size) {
        this.size = size;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getReleaseDate() {
        return this.releaseDate;
    }

    public void setReleaseDate(String input) {
        if (input == null) {
            return;
        }
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(input);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.releaseDate = date;
    }

}
