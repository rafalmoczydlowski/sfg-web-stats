package rafinha.example.sfgwebstats.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "players")
public class Player extends Person {

    @Column(name = "position")
    private String position;

    @ManyToOne
    @JoinColumn(name = "club_id")
    private Club club;

    @Column(name = "shirt_number")
    private int shirtNumber;

    @ManyToMany
    @JoinTable(name = "player_type_set",
            joinColumns = @JoinColumn(name = "player_id"),
            inverseJoinColumns = @JoinColumn(name = "player_type_id"))
    private Set<PlayerType> playerTypeSet = new HashSet<>();
}
