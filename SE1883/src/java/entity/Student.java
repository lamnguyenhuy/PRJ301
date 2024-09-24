
package entity;


/**
 *
 * @author lam
 */
public class Student {
    private  String name;
    private String id;
    private String dob;
    private String gender;

    public Student(String name, String id, String dob, String gender) {
        this.name = name;
        this.id = id;
        this.dob = dob;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
    
    
}
