package soft_uni.user_system.models.entities;

import soft_uni.user_system.models.entities.albumEntity.Album;
import soft_uni.user_system.models.entities.albumEntity.Picture;
import soft_uni.user_system.models.entities.townEntity.Town;

import javax.persistence.*;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    /**
     * Primary Key (number in range [1, pow(2,31)-1])
     */
    private Long id;

    /**
     * Text with length between 4 and 30 symbols. Required.
     */
    //@NotNull
    private String username;

    /**
     * Required field. Text with length between 6 and 50 symbols. Should contain at least:
     * 1 lowercase letter
     * 1 uppercase letter
     * 1 digit
     * 1 special symbol (!, @, #, $, %, ^, &, *, (, ), _, +, <, >, ?)
     */
    //@NotNull
    private String password;

    /**
     * Required field. Text that is considered to be in format <user>@<host> where:
     * <user> is a sequence of letters and digits,
     * where '.', '-' and '_' can appear between them (they cannot appear at the beginning or at the end of the sequence).
     * <host> is a sequence of at least two words,
     * separated by dots '.' (dots cannot appear at the beginning or at the end of the sequence)
     */
    private String email;

    /**
     * Image file (.jpeg or .png) with size maximum of 1MB
     */
    private byte[] profilePicture;

    /**
     * Date and time of user registration
     */
    private Date registeredOn;

    /**
     * Date and time of the last time the user logged in
     */
    private Date lastTimeLoggedIn;

    /**
     * number in range [1, 120]
     */
    private int age;

    /**
     * Shows whether the user is deleted or not
     */
    private boolean isDeleted;

    private String firstName;

    private String lastName;

    private Town bornTown;

    private Town currentlyLiving;

    private Set<User> friends;

    private Set<User> usersFriend;

    private Set<Album> albums;

    public User() {
        this.friends = new HashSet<>();
        this.usersFriend = new HashSet<>();
        this.albums = new HashSet<>();
        this.isDeleted = false;
    }

    public User(String username, String password, String email, Date reg_date) {
        this.setUsername(username);
        this.setPassword(password);
        this.setEmail(email);
        this.setRegisteredOn(reg_date);
        this.setLastTimeLoggedIn(reg_date); // default last login date
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", columnDefinition = "INT(11) UNSIGNED")
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "username", length = 30, nullable = false, unique = true)
    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        if (username.length() < 4 || username.length() > 30) {
            throw new IllegalArgumentException("Length of username should be between 4 and 30 symbols");
        }

        this.username = username;
    }

    @Column(name = "password", nullable = false, length = 50)
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        if (password.length() < 6 || password.length() > 50) {
            throw new IllegalArgumentException(
                    "Length of password should be between 6 and 50 symbols");
        }
        if (!password.matches(".*[a-z].*")) {
            throw new IllegalArgumentException(
                    "The password should contain at least one letter (a-z)");
        }

        if (!password.matches(".*[A-Z].*")) {
            throw new IllegalArgumentException(
                    "The password should contain at least one capital letter (A-Z)");
        }
        if (!password.matches(".*\\d.*")) {
            throw new IllegalArgumentException(
                    "The password should contain at least one digit (0-9)");
        }
        if (!password.matches(".*[!@#$%^&*()_+<>?].*")) {
            throw new IllegalArgumentException(
                    "The password should contain at least one special symbol (!, @, #, $, %, ^, &, *, (, ), _, +, <, >, ?)");
        }
        this.password = password;
    }

    @Column(nullable = false, unique = true)
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        //if (!email.matches("^(?<user>(?:[a-zA-Z0-9]+[_.-]*)+[a-zA-Z0-9]+)@(?<host>(\\\\w+\\\\.)+\\\\w{2,4})$"))
            //throw new IllegalArgumentException("Illegal email format.");
        this.email = email;
    }

    /**
     * For now this picture will be separate copy from the pictures from the albums
     */
    //@OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    //@JoinColumn(name = "profile_picture")
    public byte[] getProfilePicture() {
        return this.profilePicture;
    }

    // this has become too complex...
    public void setProfilePicture(String photoFilePath) {
        Album newAlbum = new Album();
        newAlbum.setAlbumName("unnamed album " + this.getAlbums().size() + 1);
        newAlbum.setOwner(this);
        newAlbum.setPublic(false);
        Picture newPicture = new Picture();
        newPicture.setPath(photoFilePath);
        newPicture.setTitle("unnamed picture");
        newPicture.setCaption("add some caption to the picture");
        newPicture.getAlbums().add(newAlbum);
        this.setProfilePictureFromAlbum(newAlbum.getAlbumName(), newPicture.getTitle());
    }

    public void setProfilePictureFromAlbum(String album, String title) {
        for (Album ownedAlbum : this.getAlbums()) {
            if (ownedAlbum.getAlbumName().equals(album)) {
                for (Picture picture : ownedAlbum.getPictures()) {
                    if (picture.getTitle().equals(title)) {
                        this.profilePicture = picture.getImage();
                    }
                }
            }
        }
    }

    @Column(name = "registered_on")
    public Date getRegisteredOn() {
        return this.registeredOn;
    }

    public void setRegisteredOn(Date registeredOn) {
        this.registeredOn = registeredOn;
    }

    @Column(name = "last_time_logged_in")
    public Date getLastTimeLoggedIn() {
        return this.lastTimeLoggedIn;
    }

    public void setLastTimeLoggedIn(Date lastTimeLoggedIn) {
        this.lastTimeLoggedIn = lastTimeLoggedIn;
    }

    @Column(name = "age", length = 3)
    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        //if (1 > age || age > 120) throw new IllegalArgumentException("Age should be in range 1 to 120.");
        this.age = age;
    }

    @Column(name = "is_deleted", nullable = false)
    public boolean isDeleted() {
        return this.isDeleted;
    }

    public void setDeleted(boolean deleted) {
        this.isDeleted = deleted;
    }

    @Column(name = "last_name")
    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "first_name")
    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "born_town_id")
    public Town getBornTown() {
        return this.bornTown;
    }

    public void setBornTown(Town bornTown) {
        this.bornTown = bornTown;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "currently_living_town_id")
    public Town getCurrentlyLiving() {
        return this.currentlyLiving;
    }

    public void setCurrentlyLiving(Town currentlyLiving) {
        this.currentlyLiving = currentlyLiving;
    }

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "users_friends",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "friend_id")})
    public Set<User> getFriends() {
        return this.friends;
    }

    public void setFriends(Set<User> friends) {
        this.friends = friends;
    }

    @ManyToMany(mappedBy = "friends")
    public Set<User> getUsersFriend() {
        return this.usersFriend;
    }

    public void setUsersFriend(Set<User> usersFriend) {
        this.usersFriend = usersFriend;
    }

    @OneToMany(mappedBy = "owner")
    public Set<Album> getAlbums() {
        return this.albums;
    }

    public void setAlbums(Set<Album> albums) {
        this.albums = albums;
    }

    @Transient
    public String getFullName() {
        // because in problem description it is not clear does these two fields are nullable or not
        if (this.firstName == null || this.lastName == null) {
            return "there is no first and/or last name.";
        }
        return String.format("%s %s", this.firstName, this.lastName);
    }
}