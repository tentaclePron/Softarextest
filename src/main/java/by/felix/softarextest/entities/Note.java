package by.felix.softarextest.entities;


import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
@Table(name = "NOTE")
public class Note implements Serializable {

    @Column(name = "TITLE", nullable = false)
    private String title;

    @Column(name = "CONTENT", nullable = false)
    private String content;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb", name = "FIELDS")
    private Field fields;

    @ManyToOne(targetEntity = User.class)
    @Column(name = "USERID", nullable = false)
    private long userId;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    public void setId(long id) {
        this.id = id;
    }

    @Id
    public long getId() {
        return id;
    }
}
