package metube.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;

import static metube.utils.Constants.*;

@Entity(name = DB_TABLE_TUBES)
public class Tube extends BaseEntity {


    private String name;
    private String description;
    private String youTubeLink;
    private String uploader;

    public Tube() {
    }

    @Column(name = TUBE_EF_NAME)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = TUBE_EF_DESCRIPTION)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = TUBE_TF_YOU_TUBE_LINK)
    public String getYouTubeLink() {
        return youTubeLink;
    }

    public void setYouTubeLink(String youTubeLink) {
        this.youTubeLink = youTubeLink;
    }

    @Column(name = TUBE_EF_UPLOADER)
    public String getUploader() {
        return uploader;
    }

    public void setUploader(String uploader) {
        this.uploader = uploader;
    }
}
