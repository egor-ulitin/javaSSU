package Interface;

import com.fasterxml.jackson.core.JsonProcessingException;
import entity.AgentEntity;

import java.util.Date;
import java.util.List;

public class MananingAgentsMenu extends Menu {
    public MananingAgentsMenu() throws JsonProcessingException {
        start();
    }
    public void start() throws JsonProcessingException {
        boolean correctly = false;
        while (!correctly) {
            System.out.println("1 - Найти агента по ID\n2 - Найти агента по фамилии\n3 - Вывести всех агентов\n4 - Добавить агента\n" +
                    "5 - Удалить агента\n6-Вернуться в меню");
            int i = in.nextInt();
            switch (i) {
                case 1:
                    System.out.println("Введите ID");
                    int id = in.nextInt();
                    System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(agentService.findById(id)));
                    break;
                case 2:
                    System.out.println("Введите фамилию");
                    String surname = in.next();
                    System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(agentService.findByName(surname)));
                    break;
                case 3:
                    System.out.println("Агенты");
                    List<AgentEntity> agents =  agentService.findAll();
                    for(AgentEntity item: agents) {
                        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(item));
                    }
                    break;
                case 4:
                    System.out.println("Введите Фамилию");
                    String name = in.next();
                    System.out.println("Введите номер телефона");
                    String phoneNumber = in.next();
                    System.out.println("Введите дату рождения в формате yyyy-mm-dd");
                    String date = in.next();
                    String[]temp = date.split("-");
                    Date birthday = new Date(Integer.parseInt(temp[0]), Integer.parseInt(temp[1]),
                            Integer.parseInt(temp[2]));
                    if  (agentService.create(name, phoneNumber, birthday)) {
                        System.out.println("Пользователь успешно добавлен");
                    }
                    else {
                        System.out.println("Пользователь не был добавлен");
                    }
                    break;
                case 5:
                    try {
                        System.out.println("Введите ID-агента, которого хотите удалить");
                        int idForRemove = in.nextInt();
                        if (confirm()) {
                            agentService.delete(idForRemove);
                            System.out.println("Пользователь успешно удален");
                        }
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                case 6:
                    correctly = true;
                    break;
            }
        }
    }
}
