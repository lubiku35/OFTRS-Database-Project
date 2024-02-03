package oftrs.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "user", schema = "oftrs", catalog = "motoslub")
public class user {

    // --- ID ---
    @Id
    @Column(name = "userid", nullable = false)
    private int userid;

    // --- BASIC ---
    @Column(name = "firstname", nullable = false, length = 75)
    private String firstname;
    @Column(name = "lastname", nullable = false, length = 75)
    private String lastname;
    @Column(name = "email", nullable = true, length = 125)
    private String email;
    @Column(name = "phonenumber", nullable = true, length = 45)
    private String phonenumber;
    @Column(name = "nickname", nullable = false, length = 55)
    private String nickname;

    // --- RELATIONSHIPS ---




    // ----- GETTERS -----
    public int getUserid() {
        return userid;
    }
    public String getFirstname() {
        return firstname;
    }
    public String getLastname() {
        return lastname;
    }
    public String getEmail() {
        return email;
    }
    public String getPhonenumber() {
        return phonenumber;
    }
    public String getNickname() {
        return nickname;
    }


    // ----- SETTERS -----
    public void setUserid(int userid) {
        this.userid = userid;
    }
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        user user = (oftrs.model.user) o;

        if (userid != user.userid) return false;
        if (!Objects.equals(firstname, user.firstname)) return false;
        if (!Objects.equals(lastname, user.lastname)) return false;
        if (!Objects.equals(email, user.email)) return false;
        if (!Objects.equals(phonenumber, user.phonenumber)) return false;
        if (!Objects.equals(nickname, user.nickname)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userid;
        result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (phonenumber != null ? phonenumber.hashCode() : 0);
        result = 31 * result + (nickname != null ? nickname.hashCode() : 0);
        return result;
    }
}
