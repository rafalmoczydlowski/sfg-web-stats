package rafinha.example.sfgwebstats.model;

import java.util.Set;

public class Player extends Person {

    private String position;
    private Club club;
    private int shirtNumber;
    private Set<PlayerType> playerTypeSet;

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
