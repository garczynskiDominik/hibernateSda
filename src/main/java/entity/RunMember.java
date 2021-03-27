package entity;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;

@Entity
@Table(name = "RUN_MEMBER")
public class RunMember {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_runmember")
    private Long id;
    private String name;
    @Column(name = "start_member")
    private Integer startNumber;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "run_id", insertable = false, updatable = false)
    private Run run;

    public Run getRun() {
        return run;
    }

    public void setRun(Run run) {
        this.run = run;
    }

    public RunMember(String name, Integer runId, Integer startNumber) {
        this.name = name;
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


    @Override
    public String toString() {
        return "RunMember{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", startNumber=" + startNumber +

                '}';
    }
}

