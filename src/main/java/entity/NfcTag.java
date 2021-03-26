package entity;

import javax.persistence.*;

@Entity
public class NfcTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "serial_number")
    private int serialNumber;
    @Column(name = "run_member")
    private Long runMember;

    public NfcTag(int serialNumber, Long runMember) {
        this.serialNumber = serialNumber;
        this.runMember = runMember;
    }

    public NfcTag() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Long getRunMember() {
        return runMember;
    }

    public void setRunMember(Long runMember) {
        this.runMember = runMember;
    }
}
