package ProjectoReservaVoos.Ficheiro;

import ProjectoReservaVoos.Classes.Reserva;
import ProjectoReservaVoos.ReservaBD.Classe.Reserva_vooBD;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

import static ProjectoReservaVoos.Classes.Reserva.reservas;

public class Ficheiros {
    private static final String FICHEIRO = "reservas.txt"; //Nome do Ficheiro

    //Metodo para salvar as reservas no ficheiro de objectos
    public static void salvarReservas() {
        try (ObjectOutputStream salvar = new ObjectOutputStream(new FileOutputStream(FICHEIRO))) {
            salvar.writeObject(reservas);
        } catch (IOException e) {
            System.err.println("Erro ao salvar as reservas: " + e.getMessage());
        }
    }

    //Metodo para acessar os dados dentro do ficheiro
    public static void abrirReservas() {
        try (ObjectInputStream abrir = new ObjectInputStream(new FileInputStream(FICHEIRO))) {
            List<Reserva> reservasLidas = (List<Reserva>) abrir.readObject();
            reservas.clear();
            reservas.addAll(reservasLidas);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao carregar as reservas: " + e.getMessage());
        }
    }

    public static void sincronizarBD() {
        List<Reserva> reservasBD = Reserva_vooBD.obterReservasBD();

        // Atualiza a lista de reservas com os dados da base de dados
        reservas.clear();
        reservas.addAll(reservasBD);

        // Salve a lista atualizada no arquivo
        salvarReservas();
    }
}

