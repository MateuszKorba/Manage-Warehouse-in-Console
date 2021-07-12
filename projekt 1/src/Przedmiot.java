public class Przedmiot implements Comparable<Przedmiot>{

    private String nazwa;
    private double szerokosc;
    private double wysokosc;
    private double dlugosc;
    private double wolnaPowierzchnia;
    private String nrVIN;

    public Przedmiot(String nazwa, double szerokosc, double wysokosc, double dlugosc, String nrVIN) {
        this.nazwa = nazwa;
        this.szerokosc = szerokosc;
        this.wysokosc = wysokosc;
        this.dlugosc = dlugosc;
        this.wolnaPowierzchnia = szerokosc*dlugosc*wysokosc;
        this.nrVIN = nrVIN;
    }

    public String getNazwa() {
        return nazwa;
    }

    public String getNrVIN() {
        return nrVIN;
    }

    public double getSzerokosc() {
        return szerokosc;
    }

    public double getWysokosc() {
        return wysokosc;
    }

    public double getDlugosc() {
        return dlugosc;
    }

    public double getWolnaPowierzchnia() {
        return wolnaPowierzchnia;
    }

    @Override
    public String toString() {
        return nazwa + wolnaPowierzchnia;
    }

    @Override
    public int compareTo(Przedmiot przedmiot) {
        if(wolnaPowierzchnia > przedmiot.wolnaPowierzchnia){
            return -1;
        }else if(wolnaPowierzchnia == przedmiot.wolnaPowierzchnia){
            return nazwa.compareToIgnoreCase(przedmiot.nazwa);
        }else{
            return 1;
        }
    }
}
