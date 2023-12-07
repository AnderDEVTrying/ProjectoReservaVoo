package ProjectoReservaVoos.Classes;

import ProjectoReservaVoos.Ficheiro.Ficheiros;
import ProjectoReservaVoos.ReservaBD.Classe.Reserva_vooBD;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

import static ProjectoReservaVoos.Ficheiro.Ficheiros.abrirReservas;

public class Reserva implements Serializable {
    private Passageiro passageiro;
    private Voo voo;
    public static List<Reserva> reservas = new LinkedList<>();

    public Reserva() {
    }

    public Reserva(Passageiro passageiro, Voo voo) {
        this.passageiro = passageiro;
        this.voo = voo;
    }

    static {
        System.out.println("--------BEM VINDO A LAM (MAPUTO)--------");
    }

    public void efectuar_Reserva() {
        try {
            Scanner ler = new Scanner(System.in);
            //Inserir Dados do Passageiro
            System.out.println("Digite os dados do Passageiro");
            System.out.println("-----------------");
            System.out.println("Nome: ");
            String nome = ler.nextLine();
            System.out.println("B.I: ");
            String bi = ler.nextLine();
            System.out.println("Contacto: ");
            int contacto = ler.nextInt();
            ler.nextLine();
            System.out.println("Nacionalidade: ");
            String nacionalidade = ler.nextLine();
            passageiro = new Passageiro(nome, bi, contacto, nacionalidade);
            System.out.println("--------------------------");

            //Inserir Dados do Voo
            Voo.Destino destino = selecionar_Destino();
            LocalDate dataReserva = LocalDate.now();

            // Inserir a data de partida para o dia seguinte automaticamente
            LocalDate dataPartida = LocalDate.now().plusDays(1);
            LocalTime horaPartida = LocalTime.of(8, 0);
            LocalDateTime dataPartidaGeral = LocalDateTime.of(dataPartida, horaPartida);

            // Inserir a data de chegada para 1 dia depois da data de partida
            LocalDateTime dataChegada = dataPartidaGeral.plusDays(1);
            voo = new Voo(dataReserva, dataChegada, dataPartidaGeral, destino);
            Reserva reserva = new Reserva(passageiro, voo);
            reservas.add(reserva);
            reserva.voo.setNumero_de_voo(Voo.proximoID());
        } catch (InputMismatchException e) {
            System.err.println("Erro de Digitacao..." + e.getMessage());
        }
    }


    //Metodo para selecionar o destino
    public Voo.Destino selecionar_Destino() {
        int opcao;
        while (true) {
            Scanner ler = new Scanner(System.in);
            System.out.println("Selecione o Destino: \n" +
                    "1.[Nampula] \n" +
                    "2.[Beira] \n" +
                    "3.[Tete]");
            opcao = ler.nextInt();
            Voo.Destino destino = null; //Armazena o destino selecionado
            switch (opcao) {
                case 1 -> {
                    destino = Voo.Destino.Nampula;
                    System.out.println("Destino Selecionado: " + destino);
                    return destino;
                }
                case 2 -> {
                    destino = Voo.Destino.Beira;
                    System.out.println("Destino Selecionado: " + destino);
                    return destino;
                }
                case 3 -> {
                    destino = Voo.Destino.Tete;
                    System.out.println("Destino Selecionado: " + destino);
                    return destino;
                }
                default -> System.out.println("Opção não encontrada,tente novamente!");
            }
        }

    }

    //Metodo para Procurar passageiros
    public void procurarReservas() {
        Scanner ler = new Scanner(System.in);
        abrirReservas();
        System.out.println("Digite o B.I do Passageiro: ");
        String passageiroID = ler.nextLine();

        List<Reserva> reservasParaRemover = new ArrayList<>();
        boolean encontrado = false;

        for (Reserva procura : reservas) {
            if (passageiroID.equalsIgnoreCase(procura.getPassageiro().getB_i())) {
                encontrado = true;
                System.out.println("-----------------");
                System.out.println("Voo Numero: " + procura.getVoo().getNumero_de_voo());
                System.out.println("Passageiro: " + procura.getPassageiro().getNome());
                System.out.println("B.I: " + procura.getPassageiro().getB_i());
                System.out.println("Contacto: " + procura.getPassageiro().getContacto());
                System.out.println("Nacionalidade: " + procura.getPassageiro().getNacionalidade());
                System.out.println("Origem do Voo: " + procura.getVoo().getOrigem());
                System.out.println("Data Da Reserva: " + procura.getVoo().getData_da_Reserva());
                System.out.println("Data de Partida: " + procura.getVoo().getData_partida());
                System.out.println("Data de Chegada: " + procura.getVoo().getData_de_chegada());
                System.out.println("Destino: " + procura.getVoo().getDestino());
                System.out.println("-----------------");

                System.out.println("Deseja Remover o Passageiro? S/N");
                String resposta = ler.nextLine();
                if (resposta.equalsIgnoreCase("S")) {
                    reservasParaRemover.add(procura);
                    for (Reserva reservaParaRemover : reservasParaRemover) {
                        reservas.remove(reservaParaRemover);
                        Reserva_vooBD.apagarBD(reservaParaRemover);

                    }
                    Ficheiros.sincronizarBD();
                } else if (resposta.equalsIgnoreCase("N")) {
                    System.out.println("Saindo...");
                }
            }

        }

    }


    public Passageiro getPassageiro() {
        return passageiro;
    }

    public void setPassageiro(Passageiro passageiro) {
        this.passageiro = passageiro;
    }

    public Voo getVoo() {
        return voo;
    }

    public void setVoo(Voo voo) {
        this.voo = voo;
    }

    public static List<Reserva> getReservas() {
        return reservas;
    }

    public static void setReservas(List<Reserva> reservas) {
        Reserva.reservas = reservas;
    }
}
