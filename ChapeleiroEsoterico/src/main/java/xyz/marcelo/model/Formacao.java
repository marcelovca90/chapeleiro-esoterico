package xyz.marcelo.model;

public enum Formacao
{
    F_3_4_3(1, 1, 0, 3, 4, 3),
    F_3_5_2(1, 1, 0, 3, 5, 2),
    F_4_3_3(1, 1, 2, 2, 3, 3),
    F_4_4_2(1, 1, 2, 2, 4, 2),
    F_4_5_1(1, 1, 2, 2, 5, 1),
    F_5_3_2(1, 1, 2, 3, 3, 2),
    F_5_4_1(1, 1, 2, 3, 4, 1);

    private final int tec;
    private final int gol;
    private final int lat;
    private final int zag;
    private final int mei;
    private final int ata;

    public int getTec()
    {
        return tec;
    }

    public int getGol()
    {
        return gol;
    }

    public int getLat()
    {
        return lat;
    }

    public int getZag()
    {
        return zag;
    }

    public int getMei()
    {
        return mei;
    }

    public int getAta()
    {
        return ata;
    }

    private Formacao(final int tec, final int gol, final int lat, final int zag, final int mei, final int ata)
    {
        this.tec = tec;
        this.gol = gol;
        this.lat = lat;
        this.zag = zag;
        this.mei = mei;
        this.ata = ata;
    }
}
