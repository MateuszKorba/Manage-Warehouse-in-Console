public class Samochod extends Przedmiot{

    private TypSilnika typSilnika;
    private double wolnaPowierzchnia;

    public Samochod(String nazwa, TypSilnika typSilnika, String nrVIN, double szerokosc, double wysokosc, double dlugosc) {
        super(nazwa, szerokosc, wysokosc, dlugosc, nrVIN);
        this.typSilnika = typSilnika;
        this.wolnaPowierzchnia = szerokosc*dlugosc*wysokosc;
    }

    public TypSilnika getTypSilnika() {
        return typSilnika;
    }

    public double getWolnaPowierzchnia() {
        return wolnaPowierzchnia;
    }

    public void setTypSilnika(TypSilnika typSilnika) {
        this.typSilnika = typSilnika;
    }

    public void setWolnaPowierzchnia(double wolnaPowierzchnia) {
        this.wolnaPowierzchnia = wolnaPowierzchnia;
    }

    @Override
    public String toString() {
        return "  marka: " + getNazwa() + ", numerVIN: "+getNrVIN()+ ", "+getTypSilnika()+ ", zajmowna powierzchnia: " + wolnaPowierzchnia+ " metrow szesciennych";
    }
}
