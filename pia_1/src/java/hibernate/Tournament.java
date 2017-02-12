package hibernate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "tournament")
public class Tournament implements Serializable {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id")
    Integer id;

    @ManyToOne
    @JoinColumn(name = "id_sport")
    private Sport sport;

    @ManyToOne
    @JoinColumn(name = "id_discipline")
    private Discipline discipline;

    @Column(name = "sex")
    @Size(max = 10)
    String sex;

    @Column(name = "begin")
    @Temporal(TemporalType.DATE)
    private Date beginDate;

    @Column(name = "end")
    @Temporal(TemporalType.DATE)
    private Date endDate;

    @Column(name = "location")
    @Size(max = 50)
    String location;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "tournament")
    private List<Team> teams = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "id_delegate")
    private User delegat;

    @Column(name = "valid")
    boolean valid;

    @OrderBy("kolo , groupa")
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "tournament")
    private List<Matchh> matches = new ArrayList<>();
    
    
    @OrderBy("qualification")
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "tournament")
    private List<Matchh8> matches8 = new ArrayList<>();

    public Tournament() {
    }

    public Tournament(Integer id, Sport sport, Discipline discipline, String sex, Date beginDate, Date endDate, String location, boolean valid) {
        this.id = id;
        this.sport = sport;
        this.discipline = discipline;
        this.sex = sex;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.location = location;
        this.valid = valid;
    }

    public Tournament(Integer id, Sport sport, Discipline discipline, String sex, Date beginDate, Date endDate, String location, User delegat, boolean valid) {
        this.id = id;
        this.sport = sport;
        this.discipline = discipline;
        this.sex = sex;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.location = location;
        this.delegat = delegat;
        this.valid = valid;
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

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    public User getDelegat() {
        return delegat;
    }

    public void setDelegat(User delegat) {
        this.delegat = delegat;
    }

    public boolean getValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public List<Matchh> getMatches() {
        return matches;
    }

    public void setMatches(List<Matchh> matches) {
        this.matches = matches;
    }

    public List<Matchh8> getMatches8() {
        return matches8;
    }

    public void setMatches8(List<Matchh8> matches8) {
        this.matches8 = matches8;
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
        if (!(object instanceof Tournament)) {
            return false;
        }
        Tournament other = (Tournament) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Tournament[ id=" + id + " ]";
    }

}
