import java.time.LocalDate;

public class Main {

    public static void main(String[] args) throws TooManyThingsException {

        Osoba osoba1 = new Osoba("jan123","Jan","Kowalski","12345678912","ul Czerniakowska 34", LocalDate.of(1999,05,06), LocalDate.of(2012,06,07));
        Osoba osoba2 = new Osoba("marek532","Marek","Andrzejewski","46060543936","ul Księcia Bolesława 12", LocalDate.of(1969,02,21), LocalDate.of(1985,03,15));
        Osoba osoba3 = new Osoba("kuba567","Kuba","Owczarek","56081135456","ul Moszczenicka 56", LocalDate.of(1972,01,11), LocalDate.of(1990,01,12));
        Osoba osoba4 = new Osoba("piotr347","Piotr","Zych","49031783335","ul Zielonej Gęsi 74", LocalDate.of(1977,10,07), LocalDate.of(2004,02,03));
        Osoba osoba5 = new Osoba("zuzia890","Zuzanna"," Kania","73082416625","ul Górnośląska 45", LocalDate.of(1983,01,14),LocalDate.of(2004,10,20));

        Pomieszczenie pomieszczenie1 = new Pomieszczenie(1,1,1,StatusPomieszczenia.wolny);
        Pomieszczenie pomieszczenie2 = new Pomieszczenie(5,5,5,StatusPomieszczenia.wolny);
        Pomieszczenie pomieszczenie3 = new Pomieszczenie(6,6,6,StatusPomieszczenia.wolny);
        Pomieszczenie pomieszczenie4 = new Pomieszczenie(7,7,7,StatusPomieszczenia.wolny);
        Pomieszczenie pomieszczenie5 = new Pomieszczenie(5,5,5,StatusPomieszczenia.wolny);
        Pomieszczenie pomieszczenie6 = new Pomieszczenie(6,6,6,StatusPomieszczenia.wolny);
        Pomieszczenie pomieszczenie7 = new Pomieszczenie(6,6,6,StatusPomieszczenia.wolny);
        Pomieszczenie pomieszczenie8 = new Pomieszczenie(6,6,6,StatusPomieszczenia.remont);

        Interfejs interfejs = new Interfejs();

        //pomieszczenie1.dodajSamochod(new Samochod("Mercedes",TypSilnika.benzyna,"75674637367",2,2,2),pomieszczenie1);
        //pomieszczenie1.dodajSamochod(new Samochod("citroen",TypSilnika.benzyna,"4636363",1,1,1),pomieszczenie1);
        //pomieszczenie1.dodajSamochod(new Samochod("bmw",TypSilnika.benzyna,"756746373671",1,1,1),pomieszczenie1);

        //pomieszczenie1.zawartoscPomieszczenia();
        //pomieszczenie2.zawartoscPomieszczenia();

        interfejs.dodajOsobe(osoba1);
        interfejs.dodajOsobe(osoba2);
        interfejs.dodajOsobe(osoba3);
        interfejs.dodajOsobe(osoba4);
        interfejs.dodajOsobe(osoba5);

        interfejs.getMagazyn().dodajNajemce(pomieszczenie1,osoba1);
        interfejs.getMagazyn().dodajNajemce(pomieszczenie2,osoba1);
        interfejs.getMagazyn().dodajNajemce(pomieszczenie3,osoba2);
        interfejs.getMagazyn().dodajNajemce(pomieszczenie4,osoba3);
        interfejs.getMagazyn().dodajNajemce(pomieszczenie5,osoba4);
        interfejs.getMagazyn().dodajPomieszcenie(pomieszczenie6);
        interfejs.getMagazyn().dodajPomieszcenie(pomieszczenie7);
        interfejs.getMagazyn().dodajPomieszcenie(pomieszczenie8);

        interfejs.start();

    }

}
