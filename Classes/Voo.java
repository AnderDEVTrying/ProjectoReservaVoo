package ProjectoReservaVoos.Classes;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Voo implements Serializable {



    public enum Origem { // Enumeração da Origem dos voos
        Maputo
    } 


    public enum Destino { // Enumeração do Destino dos voos
        Tete, Nampula, Beira
    }

    private static int numeroAtual = 1;
    private static char letraAtual = 'A';
    private String numero_de_voo;
    private Origem origem;
    private Destino destino;
    private LocalDate data_da_Reserva;
    private LocalDateTime data_partida;
    private LocalDateTime data_de_chegada;

    public Voo(LocalDate data_da_Reserva, LocalDateTime data_de_chegada, LocalDateTime data_de_partida, Destino destino) {
        this.numero_de_voo = proximoID();
        this.origem = Origem.Maputo;
        this.destino = destino;
        this.data_da_Reserva = data_da_Reserva;
        this.data_de_chegada = data_de_chegada;
        this.data_partida = data_de_partida;
    }


    //Metodo Para incrementar o numero ou id do voo
    public static String proximoID() {
        String id;
        if (numeroAtual < 1000 && letraAtual < 26) {
            id = letraAtual + String.format("%03d", numeroAtual);
            numeroAtual++;
        } else {
            letraAtual++;
            numeroAtual = 1;
            id = letraAtual + String.format("%03d", numeroAtual);
        }
        return id;
    }

    public static int getNumeroAtual() {
        return numeroAtual;
    }

    public static void setNumeroAtual(int numeroAtual) {
        Voo.numeroAtual = numeroAtual;
    }

    public static char getLetraAtual() {
        return letraAtual;
    }

    public static void setLetraAtual(char letraAtual) {
        Voo.letraAtual = letraAtual;
    }

    public String getNumero_de_voo() {
        return numero_de_voo;
    }

    public void setNumero_de_voo(String numero_de_voo) {
        this.numero_de_voo = numero_de_voo;
    }

    public Origem getOrigem() {
        return origem;
    }

    public void setOrigem(Origem origem) {
        this.origem = origem;
    }

    public Destino getDestino() {
        return destino;
    }

    public void setDestino(Destino destino) {
        this.destino = destino;
    }

    public void setData_da_Reserva(LocalDate data_da_Reserva) {
        this.data_da_Reserva = data_da_Reserva;
    }

    public LocalDateTime getData_partida() {
        return data_partida;
    }

    public void setData_partida(LocalDateTime data_partida) {
        this.data_partida = data_partida;
    }

    public LocalDateTime getData_de_chegada() {
        return data_de_chegada;
    }


    public LocalDate getData_da_Reserva() {
        return data_da_Reserva;
    }
}
