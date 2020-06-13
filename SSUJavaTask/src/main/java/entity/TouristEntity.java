package entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "Tourist")
public class TouristEntity {
    private String name;
    private String address;
    private String phoneNumber;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-dd-MM hh:mm:ss")
    @Temporal(TemporalType.DATE)
    private Date birthday;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TouristID", nullable = false)
    private int id;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "tourist")
    private Set<TicketEntity> tickets;



    public TouristEntity(String name, String address, String phone_number, Date birthday) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phone_number;
        this.birthday = birthday;
    }

    public TouristEntity(String name, String address, String phoneNumber, Date birthday, int id, Set<TicketEntity> tickets) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.birthday = birthday;
        this.id = id;
        this.tickets = tickets;
    }

    public TouristEntity() {

    }

    public Date getBirthday() {
        birthday.setYear(birthday.getYear() - 1900);
        return
                birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Set<TicketEntity> getTickets() {
        return tickets;
    }

    public void setTickets(Set<TicketEntity> tickets) {
        this.tickets = tickets;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
