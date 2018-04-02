package soft_uni.user_system.models.entities.albumEntity;

import soft_uni.user_system.enums.Color;
import soft_uni.user_system.models.entities.User;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "albums")
public class Album {

    private static final Color DEFAULT_BG_COLOR = Color.WHITE;

    private Long albumId;
    private User owner;
    private String albumName;
    private int backgroundColor;
    private boolean isPublic;
    private Set<Picture> pictures;

    public Album() {
        this.pictures = new HashSet<>();
        this.backgroundColor = DEFAULT_BG_COLOR.idOf();
        this.isPublic = false;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "album_id", columnDefinition = "INT(11) UNSIGNED")
    public Long getAlbumId() {
        return this.albumId;
    }

    public void setAlbumId(Long albumId) {
        this.albumId = albumId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", nullable = false)
    public User getOwner() {
        return this.owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    @Column(name = "album_name", nullable = false)
    public String getAlbumName() {
        return this.albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    @Column(name = "color", nullable = false)
    private int getBackgroundColor() {
        return this.backgroundColor;
    }

    public void setBackgroundColor(int backgroundColorId) {
        Color color = Color.values()[backgroundColorId];
        if (color == null)
            throw new IllegalArgumentException("No such color.");
        this.backgroundColor = color.idOf();
    }

    @Column(name = "is_public", nullable = false)
    public boolean isPublic() {
        return this.isPublic;
    }

    public void setPublic(boolean aPublic) {
        this.isPublic = aPublic;
    }

    @ManyToMany
    @JoinTable(name = "albums_pictures",
            joinColumns =
            @JoinColumn(name = "album_id", referencedColumnName = "album_id"),
            inverseJoinColumns =
            @JoinColumn(name = "picture_id", referencedColumnName = "picture_id"))
    public Set<Picture> getPictures() {
        return this.pictures;
    }

    public void setPictures(Set<Picture> pictures) {
        this.pictures = pictures;
    }
}
