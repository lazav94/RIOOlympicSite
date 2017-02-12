package hibernate;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "sport")
public class Sport implements Serializable {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id")
    Integer id;

    @Column(name = "name")
    @Size(max = 40)
    String name;
    
    @Column(name = "min")
    Integer min;
    
    @Column(name = "max")
    Integer max;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "sport")
    private Set<Discipline> disciplins = new HashSet<>();

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "sport")
    private Set<Sportsman> sportsmen = new HashSet<>();

    /* @OneToMany(fetch = FetchType.EAGER, mappedBy = "sport")
    private Set<Tournaments> tournaments = new HashSet<>();*/
    
    
    public Sport() {
    }

    public Sport(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Sport(Integer id, String name, Integer min, Integer max) {
        this.id = id;
        this.name = name;
        this.min = min;
        this.max = max;
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

    public Set<Discipline> getDisciplins() {
        return disciplins;
    }

    public void setDisciplins(Set<Discipline> disciplins) {
        this.disciplins = disciplins;
    }

    public Set<Sportsman> getSportsmen() {
        return sportsmen;
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
        if (!(object instanceof Sport)) {
            return false;
        }
        Sport other = (Sport) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hibernate.Sport[ id=" + id + " ]";
    }

}
