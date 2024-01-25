package org.example.Bot.servis;

import lombok.SneakyThrows;
import org.example.Bot.db.Dburl;
import org.example.Bot.model.UserStatus;
import org.example.Bot.model.Users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class UserServis {
    @SneakyThrows
    public Users findByChatId(String chatId) {

        Connection connection = Dburl.getConnection();
        Statement statement = connection.createStatement();

        String users_find = "select * from users s where s.chatid = '%s';";
        ResultSet resultSet = statement.executeQuery(String.format(users_find, chatId));
        if (resultSet.next()) {
            Users users = new Users();
            users.setChatId(resultSet.getString("chatid"));
            users.setName(resultSet.getString("name"));
            users.setUserName(resultSet.getString("username"));
            users.setPhoneNumer(resultSet.getString("phonenomer"));
            users.setUserStatus(UserStatus.valueOf(resultSet.getString("userstatus")));
            return users;
        } else {
            return null;
        }
    }

    @SneakyThrows
    public void addUsers(Users users1) {
        Connection connection = Dburl.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("insert into users(chatid,name,phonenomer,userstatus,username) values (?,?,?,?,?)");
        preparedStatement.setString(1, users1.getChatId());
        preparedStatement.setString(2, users1.getName());
        preparedStatement.setString(3, users1.getPhoneNumer());
        preparedStatement.setString(4, users1.getUserStatus().toString());
        preparedStatement.setString(5, users1.getUserName());
        preparedStatement.execute();
        preparedStatement.close();
    }

    @SneakyThrows
    public void statusUptate(String chatId, UserStatus regestred) {
        Connection connection = Dburl.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("update users set userstatus = ? where chatid = ?;");
        preparedStatement.setString(2, chatId);
        preparedStatement.setString(1, regestred.toString());
        preparedStatement.executeUpdate();
        preparedStatement.close();

    }
}
