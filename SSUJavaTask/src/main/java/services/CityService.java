package services;

import entity.CityEntity;
import repository.CityDAO;

import java.util.List;

public class CityService {
    private CityDAO cityDAO;

    public CityService() {
        this.cityDAO = new CityDAO();
    }

    public boolean create(String name, String country) {
        try {
            if (name.length() > 2) {
                CityEntity city = new CityEntity();
                city.setName(name);
                city.setCountry(country);
                cityDAO.create(city);
                return true;
            }
            return false;
        }
        catch(Exception e) {
            return false;
        }
    }

    public void delete(int id) {
        cityDAO.delete(id);
    }

    public List<CityEntity> findAll() {
        return cityDAO.findAll();
    }

    public CityEntity findById(int id) {
        return cityDAO.findById(id);
    }

    public List<CityEntity> findByName(String name) {
        return cityDAO.findByName(name);
    }

}
