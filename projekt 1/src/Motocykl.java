public class Motocykl extends Przedmiot{

    Homologacja homologacja;
    double wolnaPowierzchnia;

    public Motocykl(String nazwa, String nrVIN, double szerokosc, double wysokosc, double dlugosc, Homologacja homologacja) {
        super(nazwa, szerokosc, wysokosc, dlugosc, nrVIN);
        this.homologacja = homologacja;
        this.wolnaPowierzchnia = szerokosc*dlugosc*wysokosc;
    }


    public Homologacja getHomologacja() {
        return homologacja;
    }

    public void setHomologacja(Homologacja homologacja) {
        this.homologacja = homologacja;
    }

    public void setWolnaPowierzchnia(double wolnaPowierzchnia) {
        this.wolnaPowierzchnia = wolnaPowierzchnia;
    }

    public double getWolnaPowierzchnia() {
        return wolnaPowierzchnia;
    }

    @Override
    public String toString() {
        return "  marka: " + getNazwa() + ", numerVIN " +getNrVIN()+", homologacja: " +getHomologacja()+ ", zajmowna powierzchnia: " + wolnaPowierzchnia+ " metrow szesciennych";
    }
}
