package services;

import entity.SightEntity;
import repository.SightDAO;

import java.util.List;

public class SightService {
    private SightDAO sightDAO;

    public SightService() {
        this.sightDAO = new SightDAO();
    }


    public boolean create(String name, int idCity, int price, int idRoute) {
        try {
            if (name.length() < 20 && name.length() > 3) {
                SightEntity sight = new SightEntity();
                sight.setName(name);
                sight.setPrice(price);
                CityService cityService = new CityService();
                RouteService routeService = new RouteService();
                sight.setCitySights(cityService.findById(idCity));
                sight.setSightsForRoute(routeService.findById(idRoute));
                sightDAO.create(sight);
                return true;
            }
            return  false;
        }
        catch (Exception e)
        {
            return false;
        }
    }

    public void delete(int id) {
        sightDAO.delete(id);
    }

    public List<SightEntity> findAll() {
        return sightDAO.findAll();
    }

    public SightEntity findById(int id) {
        return sightDAO.findById(id);
    }

}
