package hibernate;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "record")
public class Record implements Serializable {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id")
    private Integer id;

    @Column(name = "year")
    private Integer year;

    @Column(name = "record")
    @Size(max = 20)
    private String record;

    @OneToOne
    @JoinColumn(name = "id_discipline")
    private Discipline discipline;

    @OneToOne
    @JoinColumn(name = "id_sportsman")
    private Sportsman sportsman;

    @Column(name = "location")
    @Size(max = 20)
    private String location;

    public Record() {
    }

    public Record(Integer id, String record, Sportsman sportsman, Discipline discipline, String location) {
        this.id = id;
        this.record = record;
        this.sportsman = sportsman;
        this.discipline = discipline;

        this.location = location;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getRecord() {
        return record;
    }

    public void setRecord(String record) {
        this.record = record;
    }

    public Sportsman getSportsman() {
        return sportsman;
    }

    public void setSportsman(Sportsman sportsman) {
        this.sportsman = sportsman;
    }

    public Discipline getDiscipline() {
        return discipline;
    }

    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
    
    

}
