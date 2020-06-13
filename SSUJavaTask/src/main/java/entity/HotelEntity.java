package entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "Hotel")
public class HotelEntity {
    private String name;
    private int stars;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "HotelID", nullable = false)
    private int id;


    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private CityEntity cityHotels;

    public RouteEntity getHotelsForRoute() {
        return hotelsForRoute;
    }

    public void setHotelsForRoute(RouteEntity hotelsForRoute) {
        this.hotelsForRoute = hotelsForRoute;
    }

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private RouteEntity hotelsForRoute;

    public void setId(int id) {
        this.id = id;
    }

    public HotelEntity() {

    }

    public CityEntity getCityHotels() {
        return cityHotels;
    }

    public void setCityHotels(CityEntity cityHotels) {
        this.cityHotels = cityHotels;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public HotelEntity(String name, int stars, int id, CityEntity cityHotels) {
        this.name = name;
        this.stars = stars;
        this.id = id;
        this.cityHotels = cityHotels;
    }
}
