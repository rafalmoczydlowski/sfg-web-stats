package rafinha.example.sfgwebstats.model;

import java.time.LocalDate;
import java.util.Set;

public class Match extends BaseEntity {

    private Set<Club> playingClubs;
    private String score;
    private LocalDate playDate;

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
}
