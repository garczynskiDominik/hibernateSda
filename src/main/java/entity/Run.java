package entity;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
//@Table(name = "RUN")
public class Run {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Column(name = "id_run")
    private Long id;
    private String name;
    @Column(name = "MEMBERS_LIMIT")
    private Integer membersLimit;


    @OneToMany(fetch = FetchType.EAGER, mappedBy = "run")
    private Set<RunMember> runMembers = new HashSet<>();

    public Set<RunMember> getRunMembers() {
        return runMembers;
    }

    public void setRunMembers(Set<RunMember> runMembers) {
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
        return
                "id: " + id +
                        ", name: " + name +
                        ", members limit: " + membersLimit +
                        ", distance: " + distance;
    }
}
