package hibernate;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OrderBy;
import javax.persistence.OrderColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "matchh")
public class Matchh implements Serializable {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id")
    Integer id;

    @ManyToOne
    @JoinColumn(name = "id_op1")
    private Team op1;

    @ManyToOne
    @JoinColumn(name = "id_op2")
    private Team op2;

    @ManyToOne
    @JoinColumn(name = "winner")
    private Team winner;

    @Column(name = "result")
    @Size(max = 20)
    String result;

    @Column(name = "kolo")
    Integer kolo;

    @ManyToOne
    @JoinColumn(name = "id_tournament")
    private Tournament tournament;

    @Column(name = "time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date time;

    @Column(name = "location")
    @Size(max = 20)
    String location;

    @Column(name = "groupa")
    Integer groupa;

    public Matchh() {
    }

    public Matchh(Integer id, Team op1, Team op2, Team winner, String result, Integer kolo, Tournament tournament, Date time, String location, Integer groupa) {
        this.id = id;
        this.op1 = op1;
        this.op2 = op2;
        this.winner = winner;
        this.result = result;
        this.kolo = kolo;
        this.tournament = tournament;
        this.time = time;
        this.location = location;
        this.groupa = groupa;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Team getOp1() {
        return op1;
    }

    public void setOp1(Team op1) {
        this.op1 = op1;
    }

    public Team getOp2() {
        return op2;
    }

    public void setOp2(Team op2) {
        this.op2 = op2;
    }

    public Team getWinner() {
        return winner;
    }

    public void setWinner(Team winner) {
        this.winner = winner;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Integer getKolo() {
        return kolo;
    }

    public void setKolo(Integer kolo) {
        this.kolo = kolo;
    }

    public Tournament getTournament() {
        return tournament;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }

    public Date getTime() {
       
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getLocation() {

        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getGroupa() {
        return groupa;
    }

    public void setGroupa(Integer groupa) {
        this.groupa = groupa;
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
        if (!(object instanceof Matchh)) {
            return false;
        }
        Matchh other = (Matchh) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hibernate.Matchh[ id=" + id + " ]";
    }

}
