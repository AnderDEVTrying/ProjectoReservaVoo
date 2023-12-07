package ProjectoReservaVoos.Executador;

import ProjectoReservaVoos.Classes.Reserva;
import ProjectoReservaVoos.Ficheiro.Ficheiros;
import ProjectoReservaVoos.ReservaBD.Classe.Reserva_vooBD;

import java.util.List;
import java.util.Scanner;

public class GerirReservas {
    public static void main(String[] args) {
        Scanner ler = new Scanner(System.in);
        GerirReservas gerirReservas = new GerirReservas();
        Reserva reserva = new Reserva();
        while (true) {
            Ficheiros.sincronizarBD();
            System.out.println("--------MENU--------");
            System.out.println("1. Efetuar Reserva");
            System.out.println("2. Imprimir Reservas");
            System.out.println("3. Procurar Reserva");
            System.out.println("4. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = ler.nextInt();
            ler.nextLine();

            switch (opcao) {
                case 1 -> {
                    //Efectuar a Reserva
                    reserva.efectuar_Reserva();
                    //Salvar no Ficheiro e BD
                    System.out.println("Deseja Salvar a Reserva?S/N");
                    String resposta = ler.next();
                    if (resposta.equalsIgnoreCase("S")) {
                        Ficheiros.salvarReservas();
                        Reserva_vooBD.salvarBD(reserva);
                        Ficheiros.sincronizarBD();
                        System.out.println("Reservas salvas com sucesso.");
                    } else if (resposta.equalsIgnoreCase("N")) {
                        System.out.println("Cancelando...");
                    } else {
                        System.out.println("Opçao Invalida");
                    }
                }
                case 2 -> {
                    //Retirar do ficheiro e fazer a impressao dos dados
                    Ficheiros.abrirReservas();
                    gerirReservas.imprimirReservas();
                }
                //Procurar e/ou eliminar o passageiro
                case 3 -> reserva.procurarReservas();
                case 4 -> {
                    System.out.println("Saindo...");
                    return;
                }
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    public void imprimirReservas() {
        List<Reserva> reservas = Reserva.getReservas();
        //  lista de reservas carregada do arquivo e pode imprimir as reservas:
        for (Reserva r : reservas) {
            System.out.println("-----------------");
            System.out.println("Voo Numero: " + r.getVoo().getNumero_de_voo());
            System.out.println("Passageiro: " + r.getPassageiro().getNome());
            System.out.println("B.I: " + r.getPassageiro().getB_i());
            System.out.println("Contacto: " + r.getPassageiro().getContacto());
            System.out.println("Nacionalidade: " + r.getPassageiro().getNacionalidade());
            System.out.println("Origem do Voo: " + r.getVoo().getOrigem());
            System.out.println("Data Da Reserva: " + r.getVoo().getData_da_Reserva());
            System.out.println("Data de Partida: " + r.getVoo().getData_partida());
            System.out.println("Data de Chegada: " + r.getVoo().getData_de_chegada());
            System.out.println("Destino: " + r.getVoo().getDestino());
            System.out.println("-----------------");

        }
    }
}
