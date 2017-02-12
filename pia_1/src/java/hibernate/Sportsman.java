package hibernate;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "sportsman")
public class Sportsman implements Serializable {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    @Size(max = 50)
    private String name;

    @ManyToOne
    @JoinColumn(name = "id_sport")
    private Sport sport;

    @ManyToOne
    @JoinColumn(name = "id_nation")
    private Nation nation;

    @Column(name = "sex")
    @Size(max = 10)
    private String sex;

    @Column(name = "medal")
    private Boolean medal;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "sportsman_discipline",
            joinColumns = {@JoinColumn(name = "id_sportsman")},
            inverseJoinColumns = { @JoinColumn(name = "id_discipline")}
    )
    Set<Discipline> disciplins = new HashSet<>();
    
    
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "sportsman_team",
            joinColumns = {@JoinColumn(name = "id_sportsman")},
            inverseJoinColumns = { @JoinColumn(name = "id_team")}
    )
    Set<Team> teams = new HashSet<>();

    public Sportsman() {
    }

    public Sportsman(Integer id, String name, Sport sport, Nation nation, String sex, Boolean medal) {
        this.id = id;
        this.name = name;
        this.sport = sport;
        this.nation = nation;
        this.sex = sex;
        this.medal = medal;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSex() {
        return sex;
    }

    public Sport getSport() {
        return sport;
    }

    public void setSport(Sport sport) {
        this.sport = sport;
    }

    public Nation getNation() {
        return nation;
    }

    public void setNation(Nation nation) {
        this.nation = nation;
    }

    public Boolean getMedal() {
        return medal;
    }

    public void setMedal(Boolean medal) {
        this.medal = medal;
    }

    public Set<Discipline> getDisciplins() {
        return disciplins;
    }

    public void setDisciplins(Set<Discipline> disciplins) {
        this.disciplins = disciplins;
    }

    public Set<Team> getTeams() {
        return teams;
    }

    public void setTeams(Set<Team> teams) {
        this.teams = teams;
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
        if (!(object instanceof Sportsman)) {
            return false;
        }
        Sportsman other = (Sportsman) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return name;
    }

}
