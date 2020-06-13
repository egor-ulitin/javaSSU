package Interface;

import com.fasterxml.jackson.core.JsonProcessingException;
import entity.TouristEntity;

import java.util.Date;
import java.util.List;

public class MananingTouristsMenu extends Menu {
    public MananingTouristsMenu() throws JsonProcessingException {
        start();
    }
    public void start() throws JsonProcessingException {
        boolean correctly = false;
        while (!correctly) {
            System.out.println("1 - Найти туриста по ID\n2 - Найти туриста по фамилии\n3 - Вывести всех туристов" +
                    "\n4 - Добавить туриста\n5 - Удалить туриста\n6 - Вернуться в главное меню ");
            int i = in.nextInt();
            switch(i)
            {
                case 1:
                    System.out.println("Введите ID");
                    int id = in.nextInt();
                    System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(touristService.findById(id)));
                    break;
                case 2:
                    System.out.println("Введите фамилию");
                    String surname = in.next();
                    System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(touristService.findByName(surname)));
                    break;
                case 3:
                    System.out.println("Туристы");
                    List<TouristEntity> tourists =  touristService.findAll();
                    for(TouristEntity item: tourists) {
                        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(item));
                    }
                    break;
                case 4:
                    System.out.println("Введите фамилию туриста");
                    String name = in.next();
                    System.out.println("Введите номер телефона");
                    String phone = in.next();
                    System.out.println("Введите адрес туриста");
                    String address = in.next();
                    System.out.println("Введите дату рождения в формате yyyy-mm-dd");
                    String date = in.next();
                    String[]temp = date.split("-");
                    Date birthday = new Date(Integer.parseInt(temp[0]), Integer.parseInt(temp[1]),
                            Integer.parseInt(temp[2]));
                    if  (touristService.create(name, address,phone,birthday)) {
                        System.out.println("Пользователь успешно добавлен");
                    }
                    else {
                        System.out.println("Пользователь не был добавлен");
                    }
                    break;
                case 5:
                    System.out.println("Введите ID-туриста, которого хотите удалить");
                    int idForRemove = in.nextInt();
                    if (confirm()) {
                        touristService.delete(idForRemove);
                        System.out.println("Пользователь успешно удален");
                    }
                    break;
                case 6:
                    correctly = true;
                    break;
            }
        }
    }
}
