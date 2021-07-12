import java.util.ArrayList;
import java.util.List;

public class Pomieszczenie implements Comparable<Pomieszczenie>{

    private int id;
    private static int idNowegoPomieszczenia;
    private double szerokosc;
    private double wysokosc;
    private double dlugosc;
    private List<Przedmiot> listaPrzedmiotow = new ArrayList<>();
    private double wolnaPowierzchnia;
    private StatusPomieszczenia statusPomieszczenia;

    public Pomieszczenie(double szerokosc, double wysokosc, double dlugosc, StatusPomieszczenia statusPomieszczenia) {
        idNowegoPomieszczenia++;
        this.id = idNowegoPomieszczenia;
        this.szerokosc = szerokosc;
        this.wysokosc = wysokosc;
        this.dlugosc = dlugosc;
        this.wolnaPowierzchnia = szerokosc*dlugosc*wysokosc;
        this.statusPomieszczenia = statusPomieszczenia;
    }

    public int getId() {
        return id;
    }

    public double getWolnaPowierzchnia() {
        return wolnaPowierzchnia;
    }

    public void wyjatekTooManyThings(Pomieszczenie pomieszczenie, Przedmiot przedmiot) throws TooManyThingsException {
        if (pomieszczenie.getWolnaPowierzchnia() - przedmiot.getWolnaPowierzchnia() <= 0){
            throw new TooManyThingsException();
        }
    }

    public void setWolnaPowierzchnia(double wolnaPowierzchnia) {
        this.wolnaPowierzchnia = wolnaPowierzchnia;
    }

    public double wypiszPowierzchnie(){
        return szerokosc*wysokosc*dlugosc;
    }

    public List <Przedmiot> getListaPrzedmiotow() {
        return listaPrzedmiotow;
    }

    public StatusPomieszczenia getStatusPomieszczenia() {
        return statusPomieszczenia;
    }

    public void setStatusPomieszczenia(StatusPomieszczenia statusPomieszczenia) {
        this.statusPomieszczenia = statusPomieszczenia;
    }

    @Override
    public int compareTo(Pomieszczenie pomieszczenie) {
        return (this.id-pomieszczenie.getId());
    }

    @Override
    public String toString() {
        return "pomieszczenie o nr " +id+ " o powierzchni " +(int)(szerokosc*wysokosc*dlugosc)+ " metrow szesciennych";
    }

    public double dodajSamochod(Samochod samochod,Pomieszczenie pomieszczenie) throws TooManyThingsException {
        if (getStatusPomieszczenia() != getStatusPomieszczenia().remont) {
            if (pomieszczenie.wolnaPowierzchnia - samochod.getWolnaPowierzchnia() <= 0) {
                throw new TooManyThingsException();
            } else {
                pomieszczenie.wolnaPowierzchnia = pomieszczenie.wolnaPowierzchnia - samochod.getWolnaPowierzchnia();
                listaPrzedmiotow.add(samochod);
                //System.out.println("Po dodaniu powierzchnia twojgo pomieszczenia wynosi " + wolnaPowierzchnia);
            }
        }else{
            System.out.println("Pomieszczenie w remoncie");
        }
        return 0;
    }

    public double dodajRower(Rower rower,Pomieszczenie pomieszczenie) throws TooManyThingsException {
        if (getStatusPomieszczenia() != getStatusPomieszczenia().remont) {
            if (pomieszczenie.wolnaPowierzchnia - rower.getWolnaPowierzchnia() <= 0) {
                throw new TooManyThingsException();
            } else {
                pomieszczenie.wolnaPowierzchnia = pomieszczenie.wolnaPowierzchnia - rower.getWolnaPowierzchnia();
                listaPrzedmiotow.add(rower);
                //System.out.println("Po dodaniu powierzchnia twojgo pomieszczenia wynosi " + wolnaPowierzchnia);
            }
        }else{
            System.out.println("Pomieszczenie w remoncie");
        }
        return 0;
    }

    public double dodajMotocykl(Motocykl motocykl,Pomieszczenie pomieszczenie) throws TooManyThingsException {
        if (getStatusPomieszczenia() != getStatusPomieszczenia().remont) {
            if (pomieszczenie.wolnaPowierzchnia - motocykl.getWolnaPowierzchnia() <= 0) {
                throw new TooManyThingsException();
            } else {
                pomieszczenie.wolnaPowierzchnia = pomieszczenie.wolnaPowierzchnia - motocykl.getWolnaPowierzchnia();
                listaPrzedmiotow.add(motocykl);
                //System.out.println("Po dodaniu powierzchnia twojgo pomieszczenia wynosi " + wolnaPowierzchnia);
            }
        }else{
            System.out.println("Pomieszczenie w remoncie");
        }
        return 0;
    }

    public void zawartoscPomieszczenia(){
        boolean zmienna = false;
        for(Przedmiot przedmiot:listaPrzedmiotow){
            zmienna = true;
            System.out.println("W pomieszczeniu " +getId()+ " znajduje sie:" +przedmiot);
        }
        if (zmienna == false){
            System.out.println("Brak przedmiotow w tym pomieszczeniu " + getId());
        }
    }

}
