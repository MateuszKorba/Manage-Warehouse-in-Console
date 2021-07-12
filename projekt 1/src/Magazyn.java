import java.util.TreeMap;

public class Magazyn {

    TreeMap <Pomieszczenie,Osoba> Magazyn;

    public Magazyn() {
        Magazyn = new TreeMap<>();
    }

    public TreeMap<Pomieszczenie,Osoba> getMagazyn(){
        return Magazyn;
    }

    public void dodajNajemce(Pomieszczenie pomieszczenie, Osoba osoba){
        Magazyn.put(pomieszczenie,osoba);
        if(pomieszczenie.getStatusPomieszczenia() == pomieszczenie.getStatusPomieszczenia().remont){
            pomieszczenie.setStatusPomieszczenia(pomieszczenie.getStatusPomieszczenia().remont);
        }
        else if(pomieszczenie.getStatusPomieszczenia() == pomieszczenie.getStatusPomieszczenia().wolny){
            pomieszczenie.setStatusPomieszczenia(pomieszczenie.getStatusPomieszczenia().zajety);
        }
    }
    public void dodajPomieszcenie(Pomieszczenie pomieszczenie){
        Magazyn.put(pomieszczenie, null);
        if(pomieszczenie.getStatusPomieszczenia() == pomieszczenie.getStatusPomieszczenia().remont){
            pomieszczenie.setStatusPomieszczenia(pomieszczenie.getStatusPomieszczenia());
        }else if(pomieszczenie.getStatusPomieszczenia() == pomieszczenie.getStatusPomieszczenia().wolny){
            pomieszczenie.setStatusPomieszczenia(pomieszczenie.getStatusPomieszczenia());
        }
    }

}
