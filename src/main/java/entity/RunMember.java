package entity;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;

@Entity
@Table(name = "RUN_MEMBER")
public class RunMember {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
//    @Column(name = "START_NUMBER")
    private Integer startNumber;
//    @Column(name = "RUN_ID")
    private Integer runId;


    public RunMember(Long id, String name, Integer startNumber, Integer runId) {
        this.id = id;
        this.name = name;
        this.startNumber = startNumber;
        this.runId = runId;
    }

    public RunMember() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStartNumber() {
        return startNumber;
    }

    public void setStartNumber(Integer startNumber) {
        this.startNumber = startNumber;
    }

    public Integer getRunId() {
        return runId;
    }

    public void setRunId(Integer runId) {
        this.runId = runId;
    }

    @Override
    public String toString() {
        return "RunMember{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", startNumber=" + startNumber +
                ", runId=" + runId +
                '}';
    }
}

