package org.example.Bot.servis;

import lombok.SneakyThrows;
import org.example.Bot.db.Dburl;
import org.example.Bot.model.Kino;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class KinoService {

    @SneakyThrows
    public List<Kino> bitaKinoQaytarish(String text) {
        Connection connection = Dburl.getConnection();
        Statement statement = connection.createStatement();

        String users_find = "select * from kino where numer = '%s';";
        ResultSet resultSet = statement.executeQuery(String.format(users_find, text));
        List<Kino> list = new ArrayList<>();
        while (resultSet.next()) {
            Kino kino = new Kino();
            kino.setNumer(resultSet.getString("numer"));
            kino.setVedioLink(resultSet.getString("vediolink"));
            kino.setVedioName(resultSet.getString("vedeoname"));
            list.add(kino);
        }
        return list;
    }


    @SneakyThrows
    public List<Kino> kinolarListniQaytarish() {
        Connection connection = Dburl.getConnection();
        Statement statement = connection.createStatement();

        String users_find = "select * from kino;";
        ResultSet resultSet = statement.executeQuery(String.format(users_find));
        List<Kino> list = new ArrayList<>();
        while (resultSet.next()) {
            Kino kino = new Kino();
            kino.setNumer(resultSet.getString("numer"));
            kino.setVedioLink(resultSet.getString("vediolink"));
            kino.setVedioName(resultSet.getString("vedeoname"));
            list.add(kino);
        }
        return list;
    }

    @SneakyThrows
    public Boolean kinoKodiniTekshirish(String text) {
        Connection connection = Dburl.getConnection();
        Statement statement = connection.createStatement();
        String users_find = "select * from kino where numer = '%s';";
        ResultSet resultSet = statement.executeQuery(String.format(users_find, text));
        if (resultSet.next()) {
            return false;
        }
        return true;
    }

    @SneakyThrows
    public void kinoAdd(Kino kino) {
        Connection connection = Dburl.getConnection();
        PreparedStatement preparedStatement =
                connection.prepareStatement("insert into kino (numer,vediolink,vedeoname)values (?,?,?);");
        preparedStatement.setString(1, kino.getNumer());
        preparedStatement.setString(2, kino.getVedioLink());
        preparedStatement.setString(3, kino.getVedioName());
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    @SneakyThrows
    public void KinoDelete(String text) {
        Connection connection = Dburl.getConnection();
        PreparedStatement preparedStatement =
                connection.prepareStatement("delete from kino where numer = ?;");
        preparedStatement.setString(1,text);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }
}
