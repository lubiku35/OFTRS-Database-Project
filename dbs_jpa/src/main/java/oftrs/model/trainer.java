package oftrs.model;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "trainer", schema = "oftrs", catalog = "motoslub")
public class trainer {

    // --- ID ---
    @Id
    @Column(name = "trainerid", nullable = false)
    private int trainerid;

    // --- BASIC ---
    @Basic
    @Column(name = "specification", nullable = false, length = 100)
    private String specification;
    @Basic
    @Column(name = "type", nullable = false, length = 100)
    private String type;
    @Basic
    @Column(name = "validitydate", nullable = false)
    private Date validitydate;

    // --- RELATIONSHIPS ---
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userid")
    private user user;

    // ----- GETTERS -----
    public int getTrainerid() { return trainerid; }
    public String getSpecification() { return specification; }
    public String getType() { return type; }
    public Date getValiditydate() {return validitydate; }
    public oftrs.model.user getUser() { return user; }


    // ----- SETTERS -----
    public void setTrainerid(int trainerid) { this.trainerid = trainerid; }
    public void setSpecification(String specification) { this.specification = specification; }
    public void setType(String type) { this.type = type; }
    public void setValiditydate(Date validitydate) { this.validitydate = validitydate; }
    public void setUser(oftrs.model.user user) { this.user = user; }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        trainer trainer = (oftrs.model.trainer) o;

        if (trainerid != trainer.trainerid) return false;
//        if (userid != trainer.userid) return false;
        if (!Objects.equals(specification, trainer.specification)) return false;
        if (!Objects.equals(type, trainer.type)) return false;
        if (!Objects.equals(validitydate, trainer.validitydate)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = trainerid;
//        result = 31 * result + userid;
        result = 31 * result + (specification != null ? specification.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (validitydate != null ? validitydate.hashCode() : 0);
        return result;
    }
}
