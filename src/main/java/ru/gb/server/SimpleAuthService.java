package ru.gb.server;

public class SimpleAuthService implements AuthService {

    @Override
    public String getNickByLoginAndPassword(String login, String password) {
        return JdbcApp.getUserNickname(login, password);
    }
}
