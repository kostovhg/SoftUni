package entities.labels;

import contracts.Label;

import javax.persistence.*;

@Entity
@Table(name = "labels")
public class BasicLabel implements Label {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Basic
    private String title;

    @Basic
    private String subtitle;

//    @OneToOne(mappedBy = "label", targetEntity = BasicShampoo.class, cascade = CascadeType.ALL)
//    private BasicShampoo shampoo;

    public BasicLabel() {
    }

    public BasicLabel(String title, String subtitle) {
        this.title = title;
        this.subtitle = subtitle;
    }

    @Override
    public long getId() {
        return this.id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String getTitle() {
        return this.title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return this.subtitle;
    }

    @Override
    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

//    @Override
//    public BasicShampoo getBasicShampoo() {
//        return this.shampoo;
//    }
//
//    @Override
//    public void setBasicShampoo(BasicShampoo shampoo) {
//        this.shampoo = shampoo;
//    }
}
