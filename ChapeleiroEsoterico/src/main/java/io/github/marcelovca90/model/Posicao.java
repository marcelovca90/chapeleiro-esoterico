package io.github.marcelovca90.model;

import org.json.JSONObject;

public class Posicao
{
    private long id;
    private String nome;
    private String abreviacao;

    public long getId()
    {
        return id;
    }

    public String getNome()
    {
        return nome;
    }

    public String getAbreviacao()
    {
        return abreviacao;
    }

    public Posicao(int id)
    {
        this.id = id;
        switch (id)
        {
            case 1:
                this.nome = "Goleiro";
                this.abreviacao = "gol";
                break;
            case 2:
                this.nome = "Lateral";
                this.abreviacao = "lat";
                break;
            case 3:
                this.nome = "Zagueiro";
                this.abreviacao = "zag";
                break;
            case 4:
                this.nome = "Meia";
                this.abreviacao = "mei";
                break;
            case 5:
                this.nome = "Atacante";
                this.abreviacao = "ata";
                break;
            case 6:
                this.nome = "Técnico";
                this.abreviacao = "tec";
                break;
        }
    }

    public Posicao(JSONObject json)
    {
        this.id = json.getLong("id");
        this.nome = json.getString("nome");
        this.abreviacao = json.getString("abreviacao");
    }

    @Override
    public String toString()
    {
        return "Posicao [id=" + id + ", nome=" + nome + ", abreviacao=" + abreviacao + "]";
    }
}
