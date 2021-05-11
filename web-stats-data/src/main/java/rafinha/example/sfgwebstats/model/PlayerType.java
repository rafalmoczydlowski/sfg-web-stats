package rafinha.example.sfgwebstats.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "players_type")
public class PlayerType extends BaseEntity {

    @Column(name = "description")
    private String description;

    @ManyToMany(mappedBy = "playerTypeSet")
    private Set<Player> playerSet = new HashSet<>();
}
