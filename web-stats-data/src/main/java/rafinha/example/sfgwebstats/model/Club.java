package rafinha.example.sfgwebstats.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getYearOfEstablishment() {
        return yearOfEstablishment;
    }

    public void setYearOfEstablishment(LocalDate yearOfEstablishment) {
        this.yearOfEstablishment = yearOfEstablishment;
    }

    public Set<Player> getPlayerSet() {
        return playerSet;
    }

    public void setPlayerSet(Set<Player> playerSet) {
        this.playerSet = playerSet;
    }

    public void addPlayer(Player player) {
        playerSet.add(player);
        player.setClub(this);
    }

    public Coach getCoach() {
        return coach;
    }

    public void setCoach(Coach coach) {
        this.coach = coach;
    }

    public void setPlayers(Player... players) {
        this.playerSet = Arrays.stream(players).collect(Collectors.toSet());
    }

    public String getPlayersFullNames() {
        List<String> playersList = playerSet.stream().map(Player::getFullName).collect(Collectors.toList());
        return playersList.toString().replaceAll("(^\\[|\\]$)", "");
    }

    public Set<Match> getMatchSet() {
        return matchSet;
    }

    public void setMatchSet(Set<Match> matchSet) {
        this.matchSet = matchSet;
    }
}
