package Interface;

import com.fasterxml.jackson.core.JsonProcessingException;

public class MainInterface extends Menu {
    public MainInterface() throws JsonProcessingException {
        start();
    }
    public void start() throws JsonProcessingException {
        boolean correctly = false;
        while (!correctly) {
            System.out.println("1 - Управление путевками\n2 - Управление туристами\n3 - Управление маршрутами\n4 - Управление агентами" +
                    "\n5 - Выйти");
            int i = in.nextInt();
            switch (i) {
                case 1:
                    MananingTicketsMenu mananingTickets = new MananingTicketsMenu();
                    break;
                case 2:
                    MananingTouristsMenu mananingTourists = new MananingTouristsMenu();
                    break;
                case 3:
                    MananingRoutesMenu mananingRoutes = new MananingRoutesMenu();
                    break;
                case 4:
                    MananingAgentsMenu mananingAgents = new MananingAgentsMenu();
                    break;
                case 5:
                    correctly = true;
                    break;
            }
        }
    }


}