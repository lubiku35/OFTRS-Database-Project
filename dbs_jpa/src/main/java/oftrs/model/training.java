package oftrs.model;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.Objects;

@Entity
@IdClass(trainingPK.class)
@Table(name = "training", schema = "oftrs", catalog = "motoslub")
public class training {

    // --- ID ---
    @Id
    @Column(name = "trainingid", nullable = false, length = 10)
    private String trainingid;
    @Id
    @Column(name = "trainingcount", nullable = false, length = 10)
    private String trainingcount;

    // --- BASIC ---
    @Basic
    @Column(name = "trainerid", nullable = false)
    private int trainerid;
    @Basic
    @Column(name = "trainingname", nullable = false, length = 100)
    private String trainingname;
    @Basic
    @Column(name = "trainingdate", nullable = false)
    private Date trainingdate;
    @Basic
    @Column(name = "place", nullable = false, length = 120)
    private String place;
    @Basic
    @Column(name = "maxcapacity", nullable = false)
    private int maxcapacity;
    @Basic
    @Column(name = "duration", nullable = false)
    private int duration;

    // --- RELATIONSHIPS ---


    // ----- GETTERS -----
    public int getTrainerid() {
        return trainerid;
    }
    public String getTrainingid() {
        return trainingid;
    }
    public String getTrainingcount() {
        return trainingcount;
    }
    public String getTrainingname() {
        return trainingname;
    }
    public Date getTrainingdate() {
        return trainingdate;
    }
    public String getPlace() {
        return place;
    }
    public int getMaxcapacity() {
        return maxcapacity;
    }
    public int getDuration() {
        return duration;
    }


    // ----- SETTERS -----
    public void setTrainerid(int trainerid) {
        this.trainerid = trainerid;
    }
    public void setTrainingid(String trainingid) {this.trainingid = trainingid; }
    public void setTrainingcount(String trainingcount) {
        this.trainingcount = trainingcount;
    }
    public void setTrainingname(String trainingname) {
        this.trainingname = trainingname;
    }
    public void setTrainingdate(Date trainingdate) {
        this.trainingdate = trainingdate;
    }
    public void setPlace(String place) {
        this.place = place;
    }
    public void setMaxcapacity(int maxcapacity) {
        this.maxcapacity = maxcapacity;
    }
    public void setDuration(int duration) {
        this.duration = duration;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        training training = (oftrs.model.training) o;

        if (trainerid != training.trainerid) return false;
        if (maxcapacity != training.maxcapacity) return false;
        if (duration != training.duration) return false;
        if (!Objects.equals(trainingid, training.trainingid)) return false;
        if (!Objects.equals(trainingcount, training.trainingcount)) return false;
        if (!Objects.equals(trainingname, training.trainingname)) return false;
        if (!Objects.equals(trainingdate, training.trainingdate)) return false;
        if (!Objects.equals(place, training.place)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = trainerid;
        result = 31 * result + (trainingid != null ? trainingid.hashCode() : 0);
        result = 31 * result + (trainingcount != null ? trainingcount.hashCode() : 0);
        result = 31 * result + (trainingname != null ? trainingname.hashCode() : 0);
        result = 31 * result + (trainingdate != null ? trainingdate.hashCode() : 0);
        result = 31 * result + (place != null ? place.hashCode() : 0);
        result = 31 * result + maxcapacity;
        result = 31 * result + duration;
        return result;
    }
}
