package project.persistence.entities;

/**
 * Created by Þórunn on 11/2/2016.
 */
public class Group {

    private String grpId;
    //private members: User[]; - þarf að tengja við user klasa
    private String grpName;

    public String getGrpId(){return grpId;};
    public void setGrpId(String grpId){this.grpId = grpId;};

    public String getGrpName(){return grpName;};
    public void setGrpName(String grpName){this.grpName = grpName;};

    //public void addMember(User){};
    //public void removeMember(User){};
    public void changeName(String newname){};
    public void delete(){};





}
