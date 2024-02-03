package oftrs.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "calendar", schema = "oftrs", catalog = "motoslub")
public class calendar {
    // --- ID ---
    @Id
    @Column(name = "calendarid", nullable = false, length = 100)
    private String calendarid;

    // --- BASIC ---
    @Basic
    @Column(name = "userid", nullable = true)
    private Integer userid;


    // ----- GETTERS -----
    public Integer getUserid() {
        return userid;
    }
    public String getCalendarid() {
        return calendarid;
    }


    // ----- SETTERS -----
    public void setUserid(Integer userid) {
        this.userid = userid;
    }
    public void setCalendarid(String calendarid) {
        this.calendarid = calendarid;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        calendar calendar = (oftrs.model.calendar) o;

        if (!Objects.equals(calendarid, calendar.calendarid)) return false;
        if (!Objects.equals(userid, calendar.userid)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = calendarid != null ? calendarid.hashCode() : 0;
        result = 31 * result + (userid != null ? userid.hashCode() : 0);
        return result;
    }
}
