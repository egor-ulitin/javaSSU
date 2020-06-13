package Interface;

import com.fasterxml.jackson.databind.ObjectMapper;
import services.*;

import java.util.Scanner;

public class Menu {
    static Scanner in = new Scanner(System.in);
    AgentService agentService = new AgentService();
    LoginService loginService = new LoginService();
    TouristService touristService = new TouristService();
    static ObjectMapper mapper = new ObjectMapper();
    HotelService hotelService = new HotelService();
    SightService sightService = new SightService();
    CityService cityService = new CityService();
    RouteService routeService = new RouteService();
    TicketService ticketService = new TicketService();
    public static boolean confirm()
    {
        System.out.println("1 - Подтвердить действие\n2 - Отмена");
        int i = in.nextInt();
        if (i == 1)
            return true;
        else
            return false;
    }
//    public static void dateToJson(Date date){
//        DateFormat dateFormat = new SimpleDateFormat(date);
//        mapper.setDateFormat(date);
//    }

}
