package rafinha.example.sfgwebstats.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "clubs")
public class Club extends BaseEntity{

    @Column(name = "name")
    private String name;

    @Column(name = "year_of_establishment")
    private LocalDate yearOfEstablishment;

    @OneToMany(mappedBy = "club")
    private Set<Player> playerSet = new HashSet<>();

    @OneToOne
    private Coach coach;

    @ManyToMany
    @JoinTable(name = "match_set",
            joinColumns = @JoinColumn(name = "club_id"),
            inverseJoinColumns = @JoinColumn(name = "match_id"))
    private Set<Match> matchSet = new HashSet<>();

    public void addPlayer(Player player) {
        playerSet.add(player);
        player.setClub(this);
    }

    public void setPlayers(Player... players) {
        this.playerSet = Arrays.stream(players).collect(Collectors.toSet());
    }

    public String getPlayersFullNames() {
        List<String> playersList = playerSet.stream().map(Player::getFullName).collect(Collectors.toList());
        return playersList.toString().replaceAll("(^\\[|\\]$)", "");
    }
}
