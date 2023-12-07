package ProjectoReservaVoos.ReservaBD.Classe;

import ProjectoReservaVoos.Classes.Passageiro;
import ProjectoReservaVoos.Classes.Reserva;
import ProjectoReservaVoos.Classes.Voo;
import ProjectoReservaVoos.Ficheiro.Ficheiros;
import ProjectoReservaVoos.ReservaBD.ConexaoBD.ConnectionBD;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Reserva_vooBD {
    public static void salvarBD(Reserva reserva) {
        // Para inserir  as reservas na base de dados
        Connection conexao = ConnectionBD.getConexao();

        try {

            Statement statement = conexao.createStatement();
            String dados = "INSERT INTO `reserva_voo`.`reservas` (`nome_passageiro`, `bi_passageiro`, `contacto_passageiro`, `nacionalidade_passageiro`, `num_voo`, `origem`, `destino`, `data_reserva`, `data_partida`, `data_chegada`) " +
                    "VALUES ('" + reserva.getPassageiro().getNome() + "', '" + reserva.getPassageiro().getB_i() + "', '" + reserva.getPassageiro().getContacto() + "', '" + reserva.getPassageiro().getNacionalidade() + "', '" +
                    reserva.getVoo().getNumero_de_voo() + "', '" + reserva.getVoo().getOrigem() + "', '" + reserva.getVoo().getDestino() + "', '" +
                    reserva.getVoo().getData_da_Reserva() + "', '" + reserva.getVoo().getData_partida() + "', '" + reserva.getVoo().getData_de_chegada() + "')";

            statement.executeUpdate(dados);
            System.out.println("Reserva Inserida na Base De Dados com Sucesso!!!");

            ConnectionBD.fechar(conexao, statement);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void apagarBD(Reserva reserva) {
        Connection conexao = ConnectionBD.getConexao();

        try {
            Statement statement = conexao.createStatement();
            String dados = "DELETE FROM reservas WHERE nome_passageiro = '" + reserva.getPassageiro().getNome() + "' AND bi_passageiro = '" + reserva.getPassageiro().getB_i() + "'";
            statement.executeUpdate(dados);
            System.out.println("Reserva removida da Base De Dados com Sucesso!!!");

            ConnectionBD.fechar(conexao, statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Reserva> obterReservasBD() {
        List<Reserva> reservas_BD = new ArrayList<>();
        Connection conexao = ConnectionBD.getConexao();
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = conexao.createStatement();
            String dados = "SELECT * FROM reserva_voo.reservas;";
            resultSet = statement.executeQuery(dados);
            while (resultSet.next()) {
                String nome = resultSet.getString(2);
                String bi = resultSet.getString(3);
                int contacto = resultSet.getInt(4);
                String nacionalidade = resultSet.getString(5);
                Passageiro passageiro = new Passageiro(nome, bi, contacto, nacionalidade);
                String origem = resultSet.getString(6);
                Voo.Destino destino = Voo.Destino.valueOf(resultSet.getString("destino"));
                LocalDate data_reserva = resultSet.getDate("data_reserva").toLocalDate();
                LocalDateTime data_partida = resultSet.getTimestamp("data_partida").toLocalDateTime();
                LocalDateTime data_chegada = resultSet.getTimestamp("data_chegada").toLocalDateTime();
                Voo voo = new Voo(data_reserva, data_chegada, data_partida, destino);
                Reserva reserva = new Reserva(passageiro, voo);
                reservas_BD.add(reserva);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionBD.fechar(conexao, statement, resultSet);
        }
        return reservas_BD;
    }
}
