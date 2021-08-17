package rafinha.example.sfgwebstats.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "players")
public class Player extends Person {

    @Builder
    public Player(Long id, String firstName, String lastName, int age, String position, Club club, int shirtNumber) {
        super(id, firstName, lastName, age);
        this.position = position;
        this.club = club;
        this.shirtNumber = shirtNumber;
    }

    @Column(name = "position")
    private String position;

    @ManyToOne
    @JoinColumn(name = "club_id")
    private Club club;

    @Column(name = "shirt_number")
    private int shirtNumber;

}
