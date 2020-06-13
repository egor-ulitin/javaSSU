package services;

import entity.LoginEntity;
import repository.LoginDAO;

import java.util.List;

public class LoginService {
    LoginDAO loginDAO = new LoginDAO();
    public LoginEntity create(String login, String password) {
        LoginEntity loginPassword = new LoginEntity();
        password = toHash(password);
        loginPassword.setLogin(login);
        loginPassword.setPassword(password);
        return (LoginEntity) loginDAO.create(loginPassword);
    }

    public List<LoginEntity> findAll() {
        return loginDAO.findAll();
    }
    public boolean logInAccount(String login, String password) {
        List<LoginEntity> loginsAndPasswords = findAll();
        for (LoginEntity item : loginsAndPasswords) {
            if (item.getLogin().contains(login)) {
                if (fromHash(password, item.getPassword())) {
                    return true;
                }
            }
        }
        return false;
    }
    public String toHash(String password)
    {
        String salt = BCrypt.gensalt();
        String pass = BCrypt.hashpw(password, salt);
        return pass;
    }

    public boolean fromHash(String password, String hash)
    {
        return BCrypt.checkpw(password, hash);
    }
}
