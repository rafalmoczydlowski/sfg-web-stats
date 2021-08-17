package rafinha.example.sfgwebstats.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

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
@Entity
@Table(name = "clubs")
public class Club extends BaseEntity{

    @Builder
    public Club(Long id, String name, LocalDate yearOfEstablishment, Set<Player> playerSet, Coach coach, Set<Match> matchSet) {
        super(id);
        this.name = name;
        this.yearOfEstablishment = yearOfEstablishment;
        this.playerSet = playerSet;
        this.coach = coach;
        this.matchSet = matchSet;
    }

    @Column(name = "name")
    private String name;

    @Column(name = "year_of_establishment")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate yearOfEstablishment;

    @OneToMany(mappedBy = "club")
    private Set<Player> playerSet = new HashSet<>();

    @OneToOne(cascade=CascadeType.ALL)
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

    public Player getPlayer(String name) {
        return getPlayer( name, false);
    }

    public Player getPlayer(String name, boolean ignoreNew) {
        name = name.toLowerCase();
        for (Player player : playerSet) {
            if(!ignoreNew || !player.isNew()) {
                String compName = player.getFullName();
                compName = compName.toLowerCase();
                if(compName.equals(name))
                    return player;
            }
        }
        return null;
    }
}
