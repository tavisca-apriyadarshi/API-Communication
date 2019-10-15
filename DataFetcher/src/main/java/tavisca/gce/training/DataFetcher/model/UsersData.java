package tavisca.gce.training.DataFetcher.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class UsersData {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_generator")
    @SequenceGenerator(name="id_generator", sequenceName = "id_seq", allocationSize=1)
    @Column(name = "id", updatable = false, nullable = false, length = 64)
    private int id;
    @Column
    private Date created_Date;
    @Column(length = 64)
    private String name;
    @Column
    private Date birthDate;
    @Column(length = 128)
    private String password;
    @Column(length = 64)
    private String createdBy;
    @Column(length = 64)
    private String role;

    public Date getCreated_Date() {
        return created_Date;
    }

    public void setCreated_Date(Date created_Date) {
        this.created_Date = created_Date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
