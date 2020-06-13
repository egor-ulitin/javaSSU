package entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Route")
public class RouteEntity {
    private String name;
    private String departureAirport;
    private String destinationAirport;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RouteID", nullable = false)
    private int id;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "route")
    private Set<TicketEntity> tickets;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "hotelsForRoute")
    Set<HotelEntity> hotels;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "sightsForRoute")
    Set<SightEntity> sights;


    public void setId(int id) {
        this.id = id;
    }

    public void setTickets(Set<TicketEntity> tickets) {
        this.tickets = tickets;
    }

    public void setSights(Set<SightEntity> sights) {
        this.sights = sights;
    }

    public Set<HotelEntity> getHotels() {
        return hotels;
    }

    public void setHotels(Set<HotelEntity> hotels) {
        this.hotels = hotels;
    }

    public RouteEntity() {

    }

    public int getId() {
        return id;
    }

    public Set<TicketEntity> getTickets() {
        return tickets;
    }

    public Set<SightEntity> getSights() {
        return sights;
    }

    public RouteEntity(String name, String departureAirport, String destinationAirport, int id, Set<TicketEntity> tickets, Set<SightEntity> sights) {
        this.name = name;
        this.departureAirport = departureAirport;
        this.destinationAirport = destinationAirport;
        this.id = id;
        this.tickets = tickets;
        this.sights = sights;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartureAirport() {
        return departureAirport;
    }

    public void setDepartureAirport(String departureAirport) {
        this.departureAirport = departureAirport;
    }

    public String getDestinationAirport() {
        return destinationAirport;
    }

    public void setDestinationAirport(String destinationAirport) {
        this.destinationAirport = destinationAirport;
    }

    public RouteEntity(String name, String departureAirport, String destinationAirport) {
        this.name = name;
        this.departureAirport = departureAirport;
        this.destinationAirport = destinationAirport;
    }
}
