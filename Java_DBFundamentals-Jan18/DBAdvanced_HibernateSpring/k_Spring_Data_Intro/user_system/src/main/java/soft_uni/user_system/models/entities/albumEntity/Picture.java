package soft_uni.user_system.models.entities.albumEntity;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.persistence.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@Entity
@Table(name = "pictures")
public class Picture {

    private Long pictureId;
    private String title;
    private String caption;
    private String path;
    private Set<Album> albums;
    private byte[] image;

    public Picture() {
        this.albums = new HashSet<>();
    }

    public Picture(String filePath) throws IOException {
        this();
        this.setPath(filePath);
        this.setImage(filePath);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "picture_id", columnDefinition = "INT(11) UNSIGNED")
    public Long getPictureId() {
        return this.pictureId;
    }

    public void setPictureId(Long pictureId) {
        this.pictureId = pictureId;
    }

    @Column(name = "title", nullable = false)
    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "description")
    public String getCaption() {
        return this.caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    @Column(name = "path", nullable = false)
    public String getPath() {
        return this.path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @ManyToMany(mappedBy = "pictures")
    public Set<Album> getAlbums() {
        return this.albums;
    }

    public void setAlbums(Set<Album> albums) {
        this.albums = albums;
    }

    @Column(name = "image", length = 1000000, nullable = false)
    public byte[] getImage() {
        return this.image;
    }


    public void setImage(String path) throws IOException {
        byte[] photoBytes = readBytesFromFile(path);
        this.image = image;
    }

    // taken from http://www.codejava.net/frameworks/hibernate/hibernate-binary-data-and-blob-mapping-example
    private static byte[] readBytesFromFile(String filePath) throws IOException, IllegalArgumentException {
        File inputFile = new File(filePath);
        if (inputFile.getTotalSpace() > 1000000)
            throw new IllegalArgumentException("File size can not be more than 1MB.");
        String strExtension = filePath.substring(filePath.lastIndexOf("."));
        String imageExtension = determineTheFormatOfImage(inputFile);
        if (!imageExtension.matches("^jpeg|png$")) {
            throw new IllegalArgumentException("File should be .jpeg or .png format!");
        }

        if (!strExtension.equals(imageExtension)) {
            System.out.println("File format is correct but file extension in filename is not.");
        }

        FileInputStream inputStream = new FileInputStream(inputFile);

        byte[] fileBytes = new byte[(int) inputFile.length()];
        inputStream.read(fileBytes);
        inputStream.close();

        return fileBytes;
    }

    // taken from https://examples.javacodegeeks.com/desktop-java/imageio/determine-format-of-an-image/

    private static final String determineTheFormatOfImage(File file) {

        // create an image input stream from the specified file
        ImageInputStream iis = null;
        try {
            iis = ImageIO.createImageInputStream(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // get all currently registered readers that recognize the image format
        Iterator<ImageReader> iter = ImageIO.getImageReaders(iis);
        if (!iter.hasNext()) throw new RuntimeException("No readers found!");

        // get the first reader
        ImageReader reader = iter.next();
        try {
            return reader.getFormatName();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // close stream
            try {
                iis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
