package entity;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
//@Table(name = "RUN")
public class Run {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_run")
    private Long id;
    private String name;
    @Column(name = "MEMBERS_LIMIT")
    private Integer membersLimit;
    @OneToMany(fetch = FetchType.EAGER,mappedBy = "run")
    private List<RunMember> runMembers = new ArrayList<RunMember>();

    public List<RunMember> getRunMembers() {
        return runMembers;
    }

    public void setRunMembers(List<RunMember> runMembers) {
        this.runMembers = runMembers;
    }

    private Integer distance;

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public Run(String name, Integer membersLimit, Integer distance) {

        this.name = name;
        this.membersLimit = membersLimit;
        this.distance = distance;
    }

    public Run() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMembersLimit() {
        return membersLimit;
    }

    public void setMembersLimit(Integer membersLimit) {
        this.membersLimit = membersLimit;
    }

    @Override
    public String toString() {
        return "Run{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", membersLimit=" + membersLimit +
                ", distance=" + distance +
                '}';
    }
}
