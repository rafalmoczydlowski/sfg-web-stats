package rafinha.example.sfgwebstats.model;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Club extends BaseEntity{

    private String name;
    private LocalDate yearOfEstablishment;
    private Set<Player> playerSet;
    private Coach coach;

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

    public void setPlayers(Player... players) {
        this.playerSet = Arrays.stream(players).collect(Collectors.toSet());
    }

    public Coach getCoach() {
        return coach;
    }

    public void setCoach(Coach coach) {
        this.coach = coach;
    }

    public String getPlayersFullNames() {
        List<String> playersList = playerSet.stream().map(Player::getFullName).collect(Collectors.toList());
        return playersList.toString().replaceAll("(^\\[|\\]$)", "");
    }
}
