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
import javax.persistence.Table;
import javax.validation.constraints.Size;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "discipline")
public class Discipline implements Serializable {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id")
    Integer id;

    @ManyToOne
    @JoinColumn(name = "id_sport")
    private Sport sport;

    @Column(name = "name")
    @Size(max = 50)
    String name;

    @Column(name = "min")
    Integer min;

    @Column(name = "max")
    Integer max;

    @Column(name = "tim")
    boolean athletes;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "disciplins")
    Set<Sportsman> sportsmen = new HashSet<>();

    public Discipline() {
    }

    public Discipline(Integer id, Sport sport, String name, Integer min, Integer max, boolean athletes) {
        this.id = id;
        this.sport = sport;
        this.name = name;
        this.min = min;
        this.max = max;
        this.athletes = athletes;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Sport getSport() {
        return sport;
    }

    public void setSport(Sport sport) {
        this.sport = sport;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMin() {
        return min;
    }

    public void setMin(Integer min) {
        this.min = min;
    }

    public Integer getMax() {
        return max;
    }

    public void setMax(Integer max) {
        this.max = max;
    }

    public boolean isAthletes() {
        return athletes;
    }

    public void setAthletes(boolean athletes) {
        this.athletes = athletes;
    }

    public Set<Sportsman> getSportsmen() {
        return sportsmen;
    }

    public void setSportsmen(Set<Sportsman> sportsmen) {
        this.sportsmen = sportsmen;
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
        if (!(object instanceof Discipline)) {
            return false;
        }
        Discipline other = (Discipline) object;
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
