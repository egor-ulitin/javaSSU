package Interface;

import com.fasterxml.jackson.core.JsonProcessingException;
import entity.CityEntity;
import entity.HotelEntity;
import entity.SightEntity;

import java.util.List;

public class MananingRoutesMenu extends Menu {

    public MananingRoutesMenu() throws JsonProcessingException {
        start();
    }
    public void start() throws JsonProcessingException {
        boolean correctly = false;
        while (!correctly) {
            System.out.println("1 - Управление отелями\n2 - Управление достопримечательностями\n3 - Управление городами\n4 - Создать маршрут\n5 - Вернуться назад");
            int i = in.nextInt();
            switch (i) {
                case 1:
                    hotelMenu();
                    break;
                case 2:
                    sightMenu();
                    break;
                case 3:
                    cityMenu();
                    break;
                case 4:
                    System.out.println("Введите название маршурта");
                    String name = in.next();
                    System.out.println("Введите название аэропорта вылета");
                    String departureAirport = in.next();
                    System.out.println("Введите название аэропорта прилета");
                    String destinationAirport = in.next();
                    if(routeService.create(name, departureAirport, destinationAirport)) {
                        System.out.println("Маршрут добавлен");
                    }
                    else {
                        System.out.println("Маршрут не добавлен");
                    }
                    break;
                case 5:
                    correctly = true;
                    break;
            }
        }
    }

    private void cityMenu() throws JsonProcessingException {
        boolean correctly = false;
        while (!correctly) {
            System.out.println("1 - Добавить город в маршрут\n2 - Создать город\n3 - Удалить город\n4- Найти город по ID\n" +
                    "5- Найти город по названию\n6 - Вывести все города\n7 - Вернуться назад");
            int i = in.nextInt();
            switch (i)
            {
                case 1:
                    break;
                case 2:
                    System.out.println("Введите название города");
                    String name = in.next();
                    System.out.println("Введите название страны");
                    String country = in.next();
                    if (cityService.create(name, country)) {
                        System.out.println("Отель успешно добавлен");
                    }
                    else {
                        System.out.println("Отель не добавлен");
                    }
                    break;
                case 3:
                    System.out.println("Введите ID-города, которого хотите удалить");
                    int idForRemove = in.nextInt();
                    if (confirm()) {
                        cityService.delete(idForRemove);
                        System.out.println("Город успешно удален");
                    }
                    break;
                case 4:
                    System.out.println("Введите ID");
                    int idCity = in.nextInt();
                    System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(cityService.findById(idCity).getName()));
                    break;
                case 5:
                    System.out.println("Введите название страны");
                    String cityName = in.next();
                    System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(cityService.findByName(cityName)));
                    break;
                case 6:
                    System.out.println("Города");
                    List<CityEntity> cities =  cityService.findAll();
                    for(CityEntity item: cities) {
                        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(item));
                    }
                    break;
                case 7:
                    correctly = true;
                    break;
            }
        }
    }

    private void hotelMenu() throws JsonProcessingException {
        boolean correctly = false;
        while (!correctly) {
            System.out.println("\n1 - Создать отель\n2 - Удалить отель\n3- Найти отель по ID\n" +
                    "4 - Найти отель по названию\n5 - Вывести все отели\n6 - Вернуться назад");
            int i = in.nextInt();
            switch (i)
            {
                case 1:
                    System.out.println("Введите название отеля");
                    String name = in.next();
                    System.out.println("Введите ID города, в котором находится отель");
                    int idCity = in.nextInt();
                    System.out.println("Введите ID маршрута");
                    int idRoute = in.nextInt();
                    if (hotelService.create(name, idCity, idRoute)){
                        System.out.println("Отель успешно добавлен");
                    }
                    else {
                        System.out.println("Отель не добавлен");
                    }
                    break;
                case 2:
                    System.out.println("Введите ID-отеля, который хотите удалить");
                    int idForRemove = in.nextInt();
                    if (confirm()) {
                        hotelService.delete(idForRemove);
                        System.out.println("Отель успешно удален");
                    }
                    break;
                case 3:
                    System.out.println("Введите ID");
                    int idHotel = in.nextInt();
                    System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(hotelService.findById(idHotel).getName()));
                    break;
                case 4:
                    try {
                        System.out.println("Введите название отеля");
                        String nameHotel = in.next();
                        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(hotelService.findByName(nameHotel)));
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                    break;
                case 5:
                    System.out.println("Агенты");
                    List<HotelEntity> hotels =  hotelService.findAll();
                    for(HotelEntity item: hotels) {
                        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(item));
                    }
                    break;
                case 6:
                    correctly = true;
                    break;
            }
        }
    }
    private void sightMenu() throws JsonProcessingException {
        boolean correctly = false;
        while (!correctly) {
            System.out.println("1 - Добавить достопримечательность в маршрут\n2 - Создать достопримечательность" +
                    "\n3 - Удалить достопримечательность\n4- Найти достопримечательность по ID\n" +
                    "5 - Вывести все достопримечательности\n6 - Вернуться назад");
            int i = in.nextInt();
            switch (i)
            {
                case 1:
                    System.out.println("Введите ID -");
                    break;
                case 2:
                    System.out.println("Введите название достопримечательности");
                    String name = in.next();
                    System.out.println("Введите ID города, в котором находится достопримечательность");
                    int idCity = in.nextInt();
                    System.out.println("Введите ID маршрута");
                    int idRoute = in.nextInt();
                    System.out.println("Введите стоимость посещения достопримечательности");
                    int price = in.nextInt();

                    if (sightService.create(name, idCity, price, idRoute)){
                        System.out.println("Достопримечательность успешно добавлена");
                    }
                    else {
                        System.out.println("Достопримечательность не добавлена");
                    }
                    break;
                case 3:
                    System.out.println("Введите ID достопримечательности, которого хотите удалить");
                    int idForRemove = in.nextInt();
                    if (confirm()) {
                        sightService.delete(idForRemove);
                        System.out.println("Достопримечательность успешно удалена");
                    }
                    break;
                case 4:
                    System.out.println("Введите ID");
                    int idSight = in.nextInt();
                    System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(sightService.findById(idSight).getName()));
                    break;
                case 5:
                    System.out.println("Достопримечательности");
                    List<SightEntity> sights =  sightService.findAll();
                    for(SightEntity item: sights) {
                        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(item));
                    }
                    break;
                case 6:
                    correctly = true;
                    break;
            }
        }
    }
}
