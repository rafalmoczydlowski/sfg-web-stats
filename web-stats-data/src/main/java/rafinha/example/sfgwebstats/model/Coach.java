package rafinha.example.sfgwebstats.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "coaches")
public class Coach extends Person {

    @Builder
    public Coach(Long id, String firstName, String lastName, int age, Club club) {
        super(id, firstName, lastName, age);
        this.club = club;
    }

    @OneToOne
    private Club club;
}
