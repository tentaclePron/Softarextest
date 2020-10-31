package by.felix.softarextest.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "USER_ENTITY") //postgres cant have table "user".... soo yea
@DiscriminatorValue("USER_ENTITY")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class User implements Serializable {

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

}
