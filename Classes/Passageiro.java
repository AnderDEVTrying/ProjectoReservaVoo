package ProjectoReservaVoos.Classes;

import java.io.Serializable;

public class Passageiro implements Serializable {
    private String nome;
    private String b_i;
    private int contacto;
    private String nacionalidade;

    public Passageiro(String nome, String b_i, int contacto, String nacionalidade) {
        this.nome = nome;
        this.b_i = b_i;
        this.contacto = contacto;
        this.nacionalidade = nacionalidade;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getB_i() {
        return b_i;
    }

    public void setB_i(String b_i) {
        this.b_i = b_i;
    }

    public int getContacto() {
        return contacto;
    }

}
