package Interface;

import com.fasterxml.jackson.core.JsonProcessingException;
import entity.TicketEntity;

import java.util.Date;
import java.util.List;

public class MananingTicketsMenu extends Menu {
    public MananingTicketsMenu() throws JsonProcessingException {
        start();
    }

    private void start() throws JsonProcessingException {
        boolean correctly = false;
        while (!correctly) {
            System.out.println("1 - Найти билет по ID\n2 - Вывести все билеты\n3 - Добавить новый билет\n4 - Удалить билет" +
                    "5 - Вернуться назад");
            int i = in.nextInt();
            switch (i)
            {
                case 1:
                    System.out.println("Введите ID");
                    int id = in.nextInt();
                    try {


                        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(ticketService.findById(id)));
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                    break;
                case 2:
                    System.out.println("Агенты");
                    List<TicketEntity> tickets =  ticketService.findAll();
                    for(TicketEntity item: tickets) {
                        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(item));
                    }
                    break;
                case 3:
                    System.out.println("Введите ID агента");
                    int idAgent = in.nextInt();
                    System.out.println("Введите ID туриста");
                    int idTourist = in.nextInt();
                    System.out.println("Введите ID маршрута");
                    int idRoute = in.nextInt();
                    System.out.println("Введите цену");
                    int price = in.nextInt();
                    System.out.println("Введите дату начала путешествия в формате yyyy-mm-dd");
                    String date = in.next();
                    String[]temp = date.split("-");
                    Date startTravel = new Date(Integer.parseInt(temp[0]), Integer.parseInt(temp[1]),
                            Integer.parseInt(temp[2]));
                    System.out.println("Введите дату конца путешествия в формате yyyy-mm-dd");
                    date = in.next();
                    temp = date.split("-");
                    Date finishTravel = new Date(Integer.parseInt(temp[0]), Integer.parseInt(temp[1]),
                            Integer.parseInt(temp[2]));
                    if (ticketService.create(idAgent, idRoute, idTourist, price, startTravel, finishTravel)) {
                        System.out.println("Путевка успешно добавлена");
                    }
                    else {
                        System.out.println("Путевка не добавлена");
                    }
                    break;
                case 4:
                    System.out.println("Введите id путевки, которую хотите удалить");
                    int idForRemove = in.nextInt();
                    if (confirm()) {
                        ticketService.delete(idForRemove);
                        System.out.println("Пользователь успешно удален");
                    }
                    break;
                case 5:
                    correctly = true;
                    break;
            }
        }
    }
}
