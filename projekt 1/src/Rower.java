public class Rower extends Przedmiot  {

    private int liczbaPrzerzutek;
    private StanPrzedmiotu stanPrzdmiotu;
    private double wolnaPowierzchnia;

    public Rower(String nazwa, String nrVIN, double szerokosc, double wysokosc, double dlugosc, int liczbaPrzerzutek, StanPrzedmiotu stanPrzdmiotu) {
        super(nazwa, szerokosc, wysokosc, dlugosc, nrVIN);
        this.liczbaPrzerzutek = liczbaPrzerzutek;
        this.stanPrzdmiotu = stanPrzdmiotu;
        if(stanPrzdmiotu == StanPrzedmiotu.ROZLOZONY){
            this.wolnaPowierzchnia = szerokosc*dlugosc*wysokosc;
        }else if(stanPrzdmiotu == StanPrzedmiotu.ZLOZONY){
            this.wolnaPowierzchnia = (szerokosc*dlugosc*wysokosc)/2;
        }
    }

    public void setLiczbaPrzerzutek(int liczbaPrzerzutek) {
        this.liczbaPrzerzutek = liczbaPrzerzutek;
    }

    public void setStanPrzdmiotu(StanPrzedmiotu stanPrzdmiotu) {
        this.stanPrzdmiotu = stanPrzdmiotu;
    }

    public void setWolnaPowierzchnia(double wolnaPowierzchnia) {
        this.wolnaPowierzchnia = wolnaPowierzchnia;
    }

    public int getLiczbaPrzerzutek() {
        return liczbaPrzerzutek;
    }

    public double getWolnaPowierzchnia() {
        return wolnaPowierzchnia;
    }

    public StanPrzedmiotu getStanPrzdmiotu() {
        return stanPrzdmiotu;
    }

    @Override
    public String toString() {
        return "  nazwa: " + getNazwa() + ", numer seryjny: "+getNrVIN()+ ", liczba przeutek: " +getLiczbaPrzerzutek()+ ", " + getStanPrzdmiotu() + ", zajmowna powierzchnia: " + wolnaPowierzchnia+ " metrow szesciennych";
    }
}
