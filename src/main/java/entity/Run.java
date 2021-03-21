package entity;


import javax.persistence.*;

@Entity
@Table(name = "RUN")
public class Run {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
//    @Column(name = "MEMBERS_LIMIT")
    private Integer membersLimit;


    private Integer distance;

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public Run(long id, String name, Integer membersLimit, Integer distance) {
        this.id = id;
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
