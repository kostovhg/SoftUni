package jsfapp.domain.entities;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "orders")
public class Order extends BaseEntity {

    private String orderName;
    private LocalDateTime orderDate;
    private User orderBy;

    public Order() {
    }

    @Column(name = "name")
    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    @Column(name = "date")
    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(
            name = "ordered_by",
            referencedColumnName = "id"
    )
    public User getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(User orderBy) {
        this.orderBy = orderBy;
    }
}
