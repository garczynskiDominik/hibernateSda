package entity;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.HashSet;
import java.util.Set;

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


    @ManyToOne
    @JoinColumn(name = "run_id")
    private Run run;

    @ManyToMany(mappedBy = "members")
    private Set<NfcTag> tags = new HashSet<>();

    public Set<NfcTag> getTags() {
        return tags;
    }

    public void setTags(Set<NfcTag> tags) {
        this.tags = tags;
    }

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

