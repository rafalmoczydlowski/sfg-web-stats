package rafinha.example.sfgwebstats.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "matches")
public class Match extends BaseEntity {

    @ManyToMany(mappedBy = "matchSet")
    private Set<Club> playingClubs = new HashSet<>();

    @Column(name = "score")
    private String score;

    @Column(name = "play_date")
    private LocalDate playDate;

    @ManyToOne
    @JoinColumn(name = "club_id")
    private Club club;

    public Set<Club> getPlayingClubs() {
        return playingClubs;
    }

    public void setPlayingClubs(Set<Club> playingClubs) {
        this.playingClubs = playingClubs;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public LocalDate getPlayDate() {
        return playDate;
    }

    public void setPlayDate(LocalDate playDate) {
        this.playDate = playDate;
    }

    public void setOpponents(Club... clubs) {
        this.playingClubs = Arrays.stream(clubs).collect(Collectors.toSet());
    }

    public String getOpponentsClubNames() {
        List<String> clubNamesList = playingClubs.stream().map(Club::getName).collect(Collectors.toList());
        return clubNamesList.toString().replaceAll("(^\\[|\\]$)", "");
    }

    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
    }
}
