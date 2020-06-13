package services;

import entity.HotelEntity;
import repository.HotelDAO;

import java.util.List;

public class HotelService {
    private HotelDAO hotelDAO;

    public HotelService() {
        this.hotelDAO = new HotelDAO();
    }

    public boolean create(String name, int idCity, int idRoute) {
        try {
            if (name.length() < 20 && name.length() > 3) {
                HotelEntity hotel = new HotelEntity();
                RouteService routeService = new RouteService();
                hotel.setHotelsForRoute(routeService.findById(idRoute));
                hotel.setName(name);
                CityService cityService = new CityService();
                hotel.setCityHotels(cityService.findById(idCity));
                hotelDAO.create(hotel);
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
        hotelDAO.delete(id);
    }

    public List<HotelEntity> findAll() {
        return hotelDAO.findAll();
    }

    public HotelEntity findById(int id) {
        return hotelDAO.findById(id);
    }

    public List<HotelEntity> findByName(String name) {
        return hotelDAO.findByName(name);
    }
}
