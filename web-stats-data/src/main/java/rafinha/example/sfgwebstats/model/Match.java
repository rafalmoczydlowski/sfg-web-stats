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
@Table(name = "matches")
public class Match extends BaseEntity {

    @ManyToMany(mappedBy = "matchSet")
    private Set<Club> playingClubs = new HashSet<>();

    @Column(name = "score")
    private String score;

    @Column(name = "play_date")
    private LocalDate playDate;

    @ManyToOne
    @JoinColumn(name = "host_club_id")
    private Club hostClub;

    @ManyToOne
    @JoinColumn(name = "visitor_club_id")
    private Club visitorClub;

    public void setOpponents(Club... clubs) {
        this.playingClubs = Arrays.stream(clubs).collect(Collectors.toSet());
    }

    public String getOpponentsClubNames() {
        List<String> clubNamesList = playingClubs.stream().map(Club::getName).collect(Collectors.toList());
        return clubNamesList.toString().replaceAll("(^\\[|\\]$)", "");
    }

    public String getPlayingClubs() {
        return hostClub.getName() + " - " +  visitorClub.getName();
    }
}
