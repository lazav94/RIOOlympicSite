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
@Table(name = "nation")
public class Nation implements Serializable {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    @Size(max = 40)
    private String name;

    @Column(name = "flag")
    @Size(max = 40)
    private String flag;

    @Column(name = "athletes")
    private Integer athletes;

    @Column(name = "gold")
    private Integer gold;

    @Column(name = "silver")
    private Integer silver;

    @Column(name = "bronze")
    private Integer bronze;

    @Column(name = "total")
    private Integer total;

    @Column(name = "rang")
    private Integer rang;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "nation")
    private Set<Sportsman> sportsmen = new HashSet<>();

    public Nation() {
    }

    public Nation(Integer id, String name, String flag, Integer athletes, Integer gold, Integer silver, Integer bronze, Integer total, Integer rang) {
        this.id = id;
        this.name = name;
        this.flag = flag;
        this.athletes = athletes;
        this.gold = gold;
        this.silver = silver;
        this.bronze = bronze;
        this.total = total;
        this.rang = rang;
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

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public Integer getAthletes() {
        return athletes;
    }

    public void setAthletes(Integer athletes) {
        this.athletes = athletes;
    }

    public Integer getGold() {
        return gold;
    }

    public void setGold(Integer gold) {
        this.gold = gold;
    }

    public Integer getSilver() {
        return silver;
    }

    public void setSilver(Integer silver) {
        this.silver = silver;
    }

    public Integer getBronze() {
        return bronze;
    }

    public void setBronze(Integer bronze) {
        this.bronze = bronze;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getRang() {
        return rang;
    }

    public void setRang(Integer rang) {
        this.rang = rang;
    }

    /*public Set<Sportsman> getSportsmen() {
        return sportsmen;
    }

    public void setSportsmen(Set<Sportsman> sportsmen) {
        this.sportsmen = sportsmen;
    }*/

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Nation)) {
            return false;
        }
        Nation other = (Nation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hibernate.Nation[ id=" + id + " ]";
    }

}
