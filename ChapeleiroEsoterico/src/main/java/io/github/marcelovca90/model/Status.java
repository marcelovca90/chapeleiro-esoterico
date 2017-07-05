package io.github.marcelovca90.model;

import org.json.JSONObject;

public class Status
{
    private long id;
    private String nome;

    public long getId()
    {
        return id;
    }

    public String getNome()
    {
        return nome;
    }

    public Status(int id)
    {
        this.id = id;
        switch (id)
        {
            case 2:
                this.nome = "Dúvida";
                break;
            case 3:
                this.nome = "Suspenso";
                break;
            case 5:
                this.nome = "Contundido";
                break;
            case 6:
                this.nome = "Nulo";
                break;
            case 7:
                this.nome = "Provável";
                break;
        }
    }

    public Status(JSONObject json)
    {
        this.id = json.getLong("id");
        this.nome = json.getString("nome");
    }

    @Override
    public String toString()
    {
        return "Status [id=" + id + ", nome=" + nome + "]";
    }
}
