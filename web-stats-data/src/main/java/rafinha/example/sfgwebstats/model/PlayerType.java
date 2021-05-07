package rafinha.example.sfgwebstats.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "players_type")
public class PlayerType extends BaseEntity {

    @Column(name = "description")
    private String description;

    @ManyToMany(mappedBy = "playerTypeSet")
    private Set<Player> playerSet = new HashSet<>();

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Player> getPlayerSet() {
        return playerSet;
    }

    public void setPlayerSet(Set<Player> playerSet) {
        this.playerSet = playerSet;
    }
}
