package entity;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;

@Entity
@Table(name = "RUN_MEMBER")
public class RunMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_runmember")
    private Long id;
    private String name;
    @Column(name = "start_member")
    private Integer startNumber;
    @Column(name = "run_id",nullable = false)
    private Integer runId;
    @ManyToOne(fetch = FetchType.EAGER,optional = false)
    @JoinColumn(name = "run_id",insertable=false, updatable=false)
    private Run run;


    public RunMember(String name, Integer runId, Integer startNumber) {
        this.name = name;
        this.runId = runId;
        this.startNumber = startNumber;

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

