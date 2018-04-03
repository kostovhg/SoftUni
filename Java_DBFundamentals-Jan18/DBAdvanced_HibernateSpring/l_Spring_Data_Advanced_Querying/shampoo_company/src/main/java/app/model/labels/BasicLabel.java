package app.model.labels;

import app.model.shampoos.BasicShampoo;

import javax.persistence.*;

@Entity
@Table(name = "labels")
public class BasicLabel implements Label {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Basic
    private String title;

    @Basic
    private String subtitle;

    @OneToOne(mappedBy = "label", targetEntity = BasicShampoo.class, cascade = CascadeType.ALL)
    private BasicShampoo basicShampoo;

    public BasicLabel(){};
    public BasicLabel(String title, String subtitle){
        this.title = title;
        this.subtitle = subtitle;
    }
    // Getters and setters

    @Override
    public long getId() {
        return 0;
    }

    @Override
    public void setId(long id) {

    }

    @Override
    public String getTitle() {
        return this.title;
    }

    @Override
    public void setTitle(String title) {

    }

    @Override
    public String getSubtitle() {
        return this.subtitle;
    }

    @Override
    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }
}
