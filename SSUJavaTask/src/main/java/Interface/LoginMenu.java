package Interface;

public class LoginMenu extends Menu {
    public LoginMenu()
    {
        System.out.println("Добрый день\n Вас приветствует туристическое агенство 'Едем отсюда'");
        start();
    }

    public void start() {
        boolean correctly = false;
        while (!correctly)
        {
            System.out.println("Выберите действие:\n 1 - Зарегистрироваться \n 2 - Войти\n 3 - Закрыть приложение");
            int number = in.nextInt();
            switch (number) {
                case 1:
                    signup();
                case 2:
                    login();
                case 3:
                    correctly = true;
                    break;
            }
        }
    }

    private void login() {

        try {
            boolean isCorrectly = false;
            while (!isCorrectly) {
                System.out.println("Введите логин");
                String log = in.next();
                System.out.println("Введите пароль");
                String password = in.next();
                boolean check = loginService.logInAccount(log, password);
                if (check) {
                    System.out.println("Вход выполнен");
                    isCorrectly = true;
                    MainInterface mainInterface = new MainInterface();
                } else {
                    System.out.println("Вход не выполнен");
                    System.out.println("1 - повторно ввести пароль");
                    System.out.println("2 - перейти в главное меню");
                    int i = in.nextInt();
                    if(i == 2)
                    {
                        break;
                    }
                }
            }
        }
        catch(Exception e) {
            LoginMenu loginInterface = new LoginMenu();
        }
    }

    private void signup() {
        System.out.println("Придумайте логин");
        String log = in.next();
        System.out.println("Придумайте пароль");
        String password = in.next();
        System.out.println("Подтвердите пароль");
        String passwordDublicate = in.next();
        loginService.create(log, password);
    }
}
