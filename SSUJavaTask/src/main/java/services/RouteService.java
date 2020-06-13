package services;

import entity.RouteEntity;
import repository.RouteDAO;

import java.util.List;

public class RouteService {
    private RouteDAO routeDAO;

    public RouteService() {
        this.routeDAO = new RouteDAO();
    }


    public boolean create(String name, String airportDeparture, String airoportDestination) {
        try {
            RouteEntity route = new RouteEntity();
            route.setName(name);
            route.setDepartureAirport(airportDeparture);
            route.setDestinationAirport(airoportDestination);
            routeDAO.create(route);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    public void delete(int id) {
        routeDAO.delete(id);
    }

    public List<RouteEntity> findAll() {
        return routeDAO.findAll();
    }
    public RouteEntity findById(int id) {
        return routeDAO.findById(id);
    }

}
