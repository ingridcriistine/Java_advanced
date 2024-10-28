package com.desktopapp.model;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
@Table(name = "tb<Mensagens>")
public class Mensagem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    private String titulo;

    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    private String origem;

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public void setOrigem(User origem) {
        this.origem = origem.getEmail();
    }

    private String destino;

    public String getDestino() {
        return destino;
    }
    public void setDestino(User destino) {
        this.destino = destino.getEmail();
    }

    private String data;
    
    public String getData() {
        return data;
    }
    public void setData(String data) {
        this.data = data;
    }

    private String texto;
    
    public String getTexto() {
        return texto;
    }
    public void setTexto(String texto) {
        this.texto = texto;
    }

}