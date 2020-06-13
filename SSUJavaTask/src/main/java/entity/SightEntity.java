package entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Sight")
public class SightEntity {
    private String name;
    private int price;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ShowplaceID", nullable = false)
    private int id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private RouteEntity sightsForRoute;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private CityEntity citySights;


    public SightEntity() {

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCitySights(CityEntity citySights) {
        this.citySights = citySights;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getId() {
        return id;
    }

    public CityEntity getCitySights() {
        return citySights;
    }

    public RouteEntity getSightsForRoute() {
        return sightsForRoute;
    }

    public void setSightsForRoute(RouteEntity sightsForRoute) {
        this.sightsForRoute = sightsForRoute;
    }

    public SightEntity(String name, int price, int id, Set<RouteEntity> routes, CityEntity citySights) {
        this.name = name;
        this.price = price;
        this.id = id;
        this.citySights = citySights;
    }
}
