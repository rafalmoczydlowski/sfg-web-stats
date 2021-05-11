package rafinha.example.sfgwebstats.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "coaches")
public class Coach extends Person {

    @OneToOne
    private Club club;
}
