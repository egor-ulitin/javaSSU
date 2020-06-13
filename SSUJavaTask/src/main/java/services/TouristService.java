package services;


import entity.TouristEntity;
import repository.TouristDAO;

import java.util.Date;
import java.util.List;

public class TouristService {
    private TouristDAO touristDAO;

    public TouristService() {
        this.touristDAO = new TouristDAO();
    }


    public boolean create(String name, String address, String phoneNumber, Date birthday) {
        try {
            if (name.length() < 20 && name.length() > 3) {
                if (phoneNumber.length() == 10) {
                    if (address.length() > 3 && address.length() < 50) {
                        TouristEntity tourist = new TouristEntity();
                        tourist.setName(name);
                        tourist.setAddress(address);
                        tourist.setPhoneNumber(phoneNumber);
                        tourist.setBirthday(birthday);
                        touristDAO.create(tourist);
                        return true;
                    }
                }
            }
            return false;
        }
        catch (Exception e)
        {
            return false;
        }
    }

    public void delete(int id) {
        touristDAO.delete(id);
    }

    public List<TouristEntity> findAll() {
        return touristDAO.findAll();
    }

    public TouristEntity findById(int id) {
        return touristDAO.findById(id);
    }
    public List<TouristEntity> findByName(String name){
        return touristDAO.findByName(name);
    }
}
