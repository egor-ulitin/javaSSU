package entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "City")
public class CityEntity {
    private String name;
    private String country;
    private boolean isThereAnAirport;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CityID", nullable = false)
    private int id;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "citySights")
    Set<SightEntity> sights;

    public int getId() {
        return id;
    }

    public Set<SightEntity> getSights() {
        return sights;
    }

    public Set<HotelEntity> getHotels() {
        return hotels;
    }

    public CityEntity(String name, String country, boolean isThereAnAirport, int id, Set<SightEntity> sights, Set<HotelEntity> hotels) {
        this.name = name;
        this.country = country;
        this.isThereAnAirport = isThereAnAirport;
        this.id = id;
        this.sights = sights;
        this.hotels = hotels;
    }
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "cityHotels")
    Set<HotelEntity> hotels;
    public CityEntity() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public boolean isThereAnAirport() {
        return isThereAnAirport;
    }

    public void setThereAnAirport(boolean thereAnAirport) {
        isThereAnAirport = thereAnAirport;
    }
}
