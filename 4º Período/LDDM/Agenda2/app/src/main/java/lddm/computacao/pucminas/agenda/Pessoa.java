package lddm.computacao.pucminas.agenda;

import android.support.annotation.NonNull;

/**
 * Created by gabri on 23/09/2017.
 */

public class Pessoa implements Comparable <Pessoa>, Cloneable {
    private String nome;
    private String telefone;
    private String email;

    public Pessoa(String nome, String telefone, String email){
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public Pessoa clone() throws CloneNotSupportedException {
        super.clone();
        return new Pessoa(nome, telefone, email);
    }

    @Override
    public int compareTo(@NonNull Pessoa pessoa) {
        return this.nome.compareTo(pessoa.getNome());
    }
}
