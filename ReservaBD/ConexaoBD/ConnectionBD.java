package ProjectoReservaVoos.ReservaBD.ConexaoBD;

import java.sql.*;

public class ConnectionBD {
    public static Connection getConexao() {
        String url = "jdbc:mysql://localhost:3306/reserva_voo";
        String user = "root";
        String password = "root";

        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void fechar(Connection conexao) {
        try {
            if (conexao != null) {
                conexao.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void fechar(Connection conexao, Statement statement) {
        ConnectionBD.fechar(conexao);
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void fechar(Connection conexao, Statement resultset, ResultSet resultSet) {
        ConnectionBD.fechar(conexao,resultset);
        try {
            if (resultset != null) {
                resultset.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
