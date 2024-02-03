package oftrs.model;


import java.io.Serializable;

public class trainingPK implements Serializable {
    private String trainingid;
    private String trainingcount;

    // ----- GETTERS -----
    public String getTrainingid() { return trainingid; }
    public String getTrainingcount() { return trainingcount; }

    // ----- SETTERS -----
    public void setTrainingid(String trainingid) { this.trainingid = trainingid; }
    public void setTrainingcount(String trainingcount)  { this.trainingcount = trainingcount; }
}
