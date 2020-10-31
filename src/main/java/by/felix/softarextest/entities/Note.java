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
@Table
public class Note implements Serializable {

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private Fields fields;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = User.class, optional = false)
    @JoinColumn(name = "USERID")
    private User userId;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;


}
