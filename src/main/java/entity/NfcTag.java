package entity;

import javax.persistence.*;

@Entity
public class NfcTag {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "serial_number")
    private String serialNumber;


    public NfcTag( String serialNumber) {

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
