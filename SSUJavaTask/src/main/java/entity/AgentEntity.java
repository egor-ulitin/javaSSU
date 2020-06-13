package entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "Agent")
public class AgentEntity {
    public String name;
    public String phoneNumber;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AgentID", nullable = false)
    private int id;

    public AgentEntity() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    public AgentEntity(String name, String phone_number, Date birthday) {
        this.name = name;
        this.phoneNumber = phone_number;
    }

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "agent")
    private Set<TicketEntity> tickets;

    public Set<TicketEntity> getTickets() {
        return tickets;
    }

    public int getId() {
        return id;
    }

    public AgentEntity(String name, String phoneNumber, Date birthday, int id, Set<TicketEntity> tickets) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.id = id;
        this.tickets = tickets;
    }
}
