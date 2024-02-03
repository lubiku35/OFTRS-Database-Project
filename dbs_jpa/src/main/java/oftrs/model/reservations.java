package oftrs.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "reservations", schema = "oftrs", catalog = "motoslub")
public class reservations {

    // --- ID ---
    @Id
    @Column(name = "reservationid", nullable = false, length = 25)
    private String reservationid;

    // --- BASIC ---
    @Basic
    @Column(name = "userid", nullable = false)
    private int userid;
    @Basic
    @Column(name = "trainingid", nullable = false, length = 10)
    private String trainingid;


    // ----- GETTERS -----
    public String getReservationid() {
        return reservationid;
    }
    public int getUserid() {
        return userid;
    }
    public String getTrainingid() {
        return trainingid;
    }


    // ----- SETTERS -----
    public void setReservationid(String reservationid) {
        this.reservationid = reservationid;
    }
    public void setUserid(int userid) {
        this.userid = userid;
    }
    public void setTrainingid(String trainingid) {
        this.trainingid = trainingid;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        reservations that = (reservations) o;

        if (userid != that.userid) return false;
        if (!Objects.equals(reservationid, that.reservationid)) return false;
        if (!Objects.equals(trainingid, that.trainingid)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = reservationid != null ? reservationid.hashCode() : 0;
        result = 31 * result + userid;
        result = 31 * result + (trainingid != null ? trainingid.hashCode() : 0);
        return result;
    }
}
