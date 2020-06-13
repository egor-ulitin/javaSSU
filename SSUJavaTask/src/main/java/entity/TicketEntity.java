package entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "Ticket")
public class TicketEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TicketID", nullable = false)
    private int id;
    private int Price;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    private Date startTravelDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    private Date finishTravelDate;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private AgentEntity agent;

    @JsonIgnore
    @ManyToOne(fetch =  FetchType.LAZY)
    private TouristEntity tourist;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private RouteEntity route;

    public TicketEntity(int id, int price, Date startTravelDate, Date finishTravelDate, AgentEntity agent, TouristEntity tourist, RouteEntity route) {
        this.id = id;
        Price = price;
        this.startTravelDate = startTravelDate;
        this.finishTravelDate = finishTravelDate;
        this.agent = agent;
        this.tourist = tourist;
        this.route = route;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public AgentEntity getAgent() {
        return agent;
    }

    public void setAgent(AgentEntity agent) {
        this.agent = agent;
    }

    public TouristEntity getTourist() {
        return tourist;
    }

    public void setTourist(TouristEntity tourist) {
        this.tourist = tourist;
    }

    public RouteEntity getRoute() {
        return route;
    }

    public void setRoute(RouteEntity route) {
        this.route = route;
    }

    public TicketEntity(int price, Date start_travel_date, Date finish_travel_date) {
        Price = price;
        this.startTravelDate = start_travel_date;
        this.finishTravelDate = finish_travel_date;
    }
    public TicketEntity() {
    }
    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }

    public Date getStartTravelDate() {
        startTravelDate.setYear(startTravelDate.getYear() - 1900);

        return startTravelDate;
    }

    public void setStartTravelDate(Date startTravelDate) {
        this.startTravelDate = startTravelDate;
    }

    public Date getFinishTravelDate() {
        finishTravelDate.setYear(finishTravelDate.getYear() - 1900);
        return finishTravelDate;
    }

    public void setFinishTravelDate(Date finishTravelDate) {
        this.finishTravelDate = finishTravelDate;
    }
}
