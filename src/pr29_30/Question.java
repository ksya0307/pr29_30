package pr29_30;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Question {
    public int id;
    public String text;
    public int rightanswer;
    public int idtest;

    public Map<Integer,String> getQuestion(int idtest) throws ClassNotFoundException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        String hostname = "localhost";
        String user = "ksyaVova";
        String pass = "sys";
        String sid = "orcl";
        Map<Integer,String> questions = new HashMap<Integer,String>();
        Connection con = null;
        Statement st = null;
        String url = "jdbc:oracle:thin:@" + hostname + ":1521:" + sid;

        try {
            String sql = "select id, text from questions where idtest=" + idtest;
            System.out.println("Подключаемся к БД");
            con = DriverManager.getConnection(url, user, pass);
            System.out.println("Успешно");
            st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            System.out.print("\n");

            if (rs != null) {

                while (rs.next()) {

                    questions.put(rs.getInt("id"),rs.getString("text"));

                }
                return questions;


            } else System.out.println("Ошибочка!");


        } catch (SQLException var33) {
            System.out.println(var33.toString() + "ворао");

        } finally {
            if (con != null) {
                System.out.println("Закрытие подключения");

                try {
                    con.close();
                    return questions;
                } catch (SQLException e) {
                    System.out.println(e.toString() + "sdf");
                }
            }
        }
        return null;
    }

}

