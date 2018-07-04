package models;


public class BusinessRole {
    private int businessRoleID;
    private String businessRoleName;

    //Constructors
    public BusinessRole() {
    }

    public BusinessRole(int businessRoleID, String businessRoleName) {
        this.businessRoleID = businessRoleID;
        this.businessRoleName = businessRoleName;
    }

    //Getter&Setter
    public int getBusinessRoleID() {
        return businessRoleID;
    }

    public void setBusinessRoleID(int businessRoleID) {
        this.businessRoleID = businessRoleID;
    }

    public String getBusinessRoleName() {
        return businessRoleName;
    }

    public void setBusinessRoleName(String businessRoleName) {
        this.businessRoleName = businessRoleName;
    }
}