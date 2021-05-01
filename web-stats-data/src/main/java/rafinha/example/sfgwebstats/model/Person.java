package rafinha.example.sfgwebstats.model;

import java.util.HashSet;
import java.util.Set;

public class Person extends BaseEntity{

    private String firstName;
    private String lastName;
    private int age;
    private Set<Match> matchSet = new HashSet<>();

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Set<Match> getMatchSet() {
        return matchSet;
    }

    public void setMatchSet(Set<Match> matchSet) {
        this.matchSet = matchSet;
    }
}
