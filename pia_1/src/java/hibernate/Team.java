package hibernate;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "team")
public class Team implements Serializable {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id")
    Integer id;

    @ManyToOne
    @JoinColumn(name = "id_tournament")
    private Tournament tournament;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    @ManyToOne
    @JoinColumn(name = "id_sport")
    private Sport sport;

    @ManyToOne
    @JoinColumn(name = "id_discipline")
    private Discipline discipline;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "teams")
    Set<Sportsman> sportsmen = new HashSet<>();
    
    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "teams8")
    Set<Matchh8> matchh8 = new HashSet<>();
    
    

    @Column(name = "sex")
    @Size(max = 10)
    String sex;
    
    
    @Column(name = "applied")
    boolean applied;

    public Team() {
    }

    public Team(Integer id, User user, Sport sport, Discipline discipline, String sex, boolean applied) {
        this.id = id;
        this.user = user;
        this.sport = sport;
        this.discipline = discipline;
        this.sex = sex;
        this.applied = applied;
    }

    public Team(Integer id, Tournament tournament, User user, Sport sport, Discipline discipline, String sex, boolean applied) {
        this.id = id;
        this.tournament = tournament;
        this.user = user;
        this.sport = sport;
        this.discipline = discipline;
        this.sex = sex;
        this.applied = applied;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Tournament getTournament() {
        return tournament;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }

    public Set<Sportsman> getSportsmen() {
        return sportsmen;
    }

    public void setSportsmen(Set<Sportsman> sportsmen) {
        this.sportsmen = sportsmen;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User nationalLeader) {
        this.user = user;
    }

    public Sport getSport() {
        return sport;
    }

    public void setSport(Sport sport) {
        this.sport = sport;
    }

    public Discipline getDiscipline() {
        return discipline;
    }

    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public boolean isApplied() {
        return applied;
    }

    public void setApplied(boolean applied) {
        this.applied = applied;
    }

    public Set<Matchh8> getMatchh8() {
        return matchh8;
    }

    public void setMatchh8(Set<Matchh8> matchh8) {
        this.matchh8 = matchh8;
    }
    
    
    
     @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Team)) {
            return false;
        }
        Team other = (Team) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
       return "hibernate.Team[ id=" + id + " ]";
    }
    

}
