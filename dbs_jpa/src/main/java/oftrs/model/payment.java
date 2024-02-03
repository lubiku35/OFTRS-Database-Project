package oftrs.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "payment", schema = "oftrs", catalog = "motoslub")
public class payment {

    // --- ID ---
    @Id
    @Column(name = "paymentid", nullable = false, length = 100)
    private String paymentid;

    // --- BASIC ---
    @Basic
    @Column(name = "userid", nullable = true)
    private Integer userid;
    @Basic
    @Column(name = "trainingid", nullable = true, length = 10)
    private String trainingid;
    @Basic
    @Column(name = "paymentamount", nullable = false, precision = 2)
    private BigDecimal paymentamount;
    @Basic
    @Column(name = "paymentdate", nullable = false)
    private Date paymentdate;
    @Basic
    @Column(name = "paymenttype", nullable = false, length = 50)
    private String paymenttype;


    // ----- GETTERS -----
    public String getPaymentid() {
        return paymentid;
    }
    public Integer getUserid() {
        return userid;
    }
    public String getTrainingid() {
        return trainingid;
    }
    public BigDecimal getPaymentamount() {
        return paymentamount;
    }
    public Date getPaymentdate() {
        return paymentdate;
    }
    public String getPaymenttype() {
        return paymenttype;
    }


    // ----- SETTERS -----
    public void setUserid(Integer userid) {
        this.userid = userid;
    }
    public void setPaymentid(String paymentid) {
        this.paymentid = paymentid;
    }
    public void setTrainingid(String trainingid) {
        this.trainingid = trainingid;
    }
    public void setPaymentamount(BigDecimal paymentamount) { this.paymentamount = paymentamount; }
    public void setPaymentdate(Date paymentdate) {
        this.paymentdate = paymentdate;
    }
    public void setPaymenttype(String paymenttype) {
        this.paymenttype = paymenttype;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        payment payment = (oftrs.model.payment) o;

        if (!Objects.equals(paymentid, payment.paymentid)) return false;
        if (!Objects.equals(userid, payment.userid)) return false;
        if (!Objects.equals(trainingid, payment.trainingid)) return false;
        if (!Objects.equals(paymentamount, payment.paymentamount)) return false;
        if (!Objects.equals(paymentdate, payment.paymentdate)) return false;
        if (!Objects.equals(paymenttype, payment.paymenttype)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = paymentid != null ? paymentid.hashCode() : 0;
        result = 31 * result + (userid != null ? userid.hashCode() : 0);
        result = 31 * result + (trainingid != null ? trainingid.hashCode() : 0);
        result = 31 * result + (paymentamount != null ? paymentamount.hashCode() : 0);
        result = 31 * result + (paymentdate != null ? paymentdate.hashCode() : 0);
        result = 31 * result + (paymenttype != null ? paymenttype.hashCode() : 0);
        return result;
    }
}
