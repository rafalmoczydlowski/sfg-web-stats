package rafinha.example.sfgwebstats.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "players")
public class Player extends Person {

    @Builder
    public Player(Long id, String firstName, String lastName, int age, String position, Club club, int shirtNumber, Set<PlayerType> playerTypeSet) {
        super(id, firstName, lastName, age);
        this.position = position;
        this.club = club;
        this.shirtNumber = shirtNumber;
        this.playerTypeSet = playerTypeSet;
    }

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
