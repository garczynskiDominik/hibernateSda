package entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "NFC_TAG")
public class NfcTag {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "serial_number")
    private String serialNumber;

    @ManyToMany
    @JoinTable(
            name = "NFC_TAG_RUN_MEMBER",
            joinColumns = {@JoinColumn(name = "id_nfc_tag")},
            inverseJoinColumns = {@JoinColumn(name = "id_run_member")}
    )
    private Set<RunMember> members = new HashSet<>();


    public Set<RunMember> getMembers() {
        return members;
    }

    public void setMembers(Set<RunMember> members) {
        this.members = members;
    }


    public NfcTag(String serialNumber) {

        this.serialNumber = serialNumber;
    }

    public NfcTag() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }


}
