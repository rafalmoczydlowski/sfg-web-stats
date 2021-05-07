package rafinha.example.sfgwebstats.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "players")
public class Player extends Person {

    @Column(name = "position")
    private String position;

    @ManyToOne
    private Club club;

    @Column(name = "shirt_number")
    private int shirtNumber;

    @ManyToMany
    @JoinTable(name = "player_type_set",
            joinColumns = @JoinColumn(name = "player_id"),
            inverseJoinColumns = @JoinColumn(name = "player_type_id"))
    private Set<PlayerType> playerTypeSet = new HashSet<>();

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
    }

    public int getShirtNumber() {
        return shirtNumber;
    }

    public void setShirtNumber(int shirtNumber) {
        this.shirtNumber = shirtNumber;
    }

    public Set<PlayerType> getPlayerTypeSet() {
        return playerTypeSet;
    }

    public void setPlayerTypeSet(Set<PlayerType> playerTypeSet) {
        this.playerTypeSet = playerTypeSet;
    }
}
