package io.github.marcelovca90.model;

import org.json.JSONObject;

public class Atleta
{
    private long id;
    private String apelido;
    private Posicao posicao;
    private Status status;
    private int pontos;
    private int preco;
    private int variacao;
    private int media;
    private int jogos;
    private int tendencia;

    public long getId()
    {
        return id;
    }

    public String getApelido()
    {
        return apelido;
    }

    public Posicao getPosicao()
    {
        return posicao;
    }

    public Status getStatus()
    {
        return status;
    }

    public int getPreco()
    {
        return preco;
    }

    public int getPontos()
    {
        return pontos;
    }

    public int getVariacao()
    {
        return variacao;
    }

    public int getMedia()
    {
        return media;
    }

    public int getJogos()
    {
        return jogos;
    }

    public int getTendencia()
    {
        return tendencia;
    }

    public Atleta(JSONObject json)
    {
        this.id = json.getLong("atleta_id");
        this.apelido = json.getString("apelido");
        this.posicao = new Posicao(json.getInt("posicao_id"));
        this.status = new Status(json.getInt("status_id"));
        this.preco = (int) (100 * json.getDouble("preco_num"));
        this.pontos = (int) (100 * json.getDouble("pontos_num"));
        this.variacao = (int) (100 * json.getDouble("variacao_num"));
        this.media = (int) (100 * json.getDouble("media_num"));
        this.jogos = json.getInt("jogos_num");
        this.tendencia = (pontos > media ? media + Math.abs(variacao) : media - Math.abs(variacao));
    }

    @Override
    public String toString()
    {
        return "Atleta [id=" + id + ", apelido=" + apelido + ", posicao=" + posicao + ", status=" + status + ", preco=" + preco + ", pontos=" + pontos + ", variacao=" + variacao + ", media=" + media + ", jogos=" + jogos + ", tendencia="
                + tendencia + "]";
    }

    public String toShortString()
    {
        return "Atleta [apelido=" + apelido + ", posicao=" + posicao.getNome() + ", preco=" + preco + ", pontos=" + pontos + ", tendencia=" + tendencia + "]";
    }
}
