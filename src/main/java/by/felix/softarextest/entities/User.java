package by.felix.softarextest.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
@Table(name = "USER")
@DiscriminatorValue("USER")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class User implements Serializable {

    @Column(nullable = false, name = "USERNAME", unique = true)
    private String username;

    @Column(nullable = false, name = "PASSWORD")
    private String password;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }
}
