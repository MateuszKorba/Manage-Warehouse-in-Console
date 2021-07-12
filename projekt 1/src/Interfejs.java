import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Interfejs {

    private static final String RED = "\u001B[31m"; // biblioteka do koloru (ustwianie koloru)
    public static final String BLUE = "\033[0;34m"; // niebieski
    public static final String RESET = "\u001B[0m"; // przywrocenie koloru


    Scanner in = new Scanner(System.in);
    private List<Osoba> listaOsob = new ArrayList<>();
    private String odp;
    private String odp2;
    private String odp3;
    private Magazyn magazyn = new Magazyn();

    public Interfejs(){}

    public void dodajOsobe(Osoba osoba){
        listaOsob.add(osoba);
    }

    public Magazyn getMagazyn() {
        return magazyn;
    }

    public void start() {
        System.out.println("Witamy w magazynie");
        System.out.println("Jezeli chcesz wyjsc wpisz ESC i nacisnij 'Enter'");
        System.out.println("Podaj swoj login:");
        odp = in.nextLine();

        if("ESC".compareTo(odp.toUpperCase()) == 0){
            System.out.println();
            System.out.println(BLUE+"Dziekujemy i zapraszamy ponownie");
            System.exit(0);
        }else {
            try {
                osoba();
            } catch (TooManyThingsException e) {}
        }
    }

    public void osoba() throws TooManyThingsException {
        boolean zm = false;
        for (Osoba o : listaOsob) {
            if (odp.compareTo(o.getLogin()) == 0) {
                zm = true;
                menu(o);
            }
        }
        if(zm == false) {
            System.out.println();
            System.out.println(RED + "Nie ma takiego uzytkownika" + RESET);
            System.out.println("Sproboj ponownie a jezeli chcesz wyjsc wpisz ESC i nacisnij 'Enter'");
            odp=in.nextLine();
            if("ESC".compareTo(odp.toUpperCase()) == 0){
                System.out.println();
                System.out.println(BLUE+"Dziekujemy i zapraszamy ponownie");
                System.exit(0);
            }else{
                osoba();
            }
        }
        if("ESC".compareTo(odp.toUpperCase()) == 0){
            System.out.println();
            System.out.println(BLUE+"Dziekujemy i zapraszamy ponownie");
            System.exit(0);
        }
    }


    public void menu(Osoba o) throws TooManyThingsException {
        System.out.println("Witaj " +o.getImie()+ " !");
        System.out.println("Wybierz operacje którą chcesz wykonac:");
        System.out.println("   - jeżeli chcesz wypisac swoje dane łącznie z wynajętymi pomieszczeniami      "+BLUE+"wpisz 1 i nacisnij 'Enter'"+ RESET);
        System.out.println("   - jeżeli wyświetlenic zawartośc twojego danego pomieszczenia                 "+BLUE+"wpisz 2 i nacisnij 'Enter'"+ RESET);
        System.out.println("   - jeżeli chcesz włożyć nowy pojazd/przedmiot                                 "+BLUE+"wpisz 3 i nacisnij 'Enter'"+ RESET);
        System.out.println("   - jeżeli chcesz wyjąc pojazd/przedmiot                                       "+BLUE+"wpisz 4 i nacisnij 'Enter'"+ RESET);
        System.out.println("   - jeżeli chcesz wyświetlic wolne pomieszczenia                               "+BLUE+"wpisz 5 i nacisnij 'Enter'"+ RESET);
        System.out.println("   - jeżeli chcesz wynajać nowe pomieszczenie                                   "+BLUE+"wpisz 6 i nacisnij 'Enter'"+ RESET);
        System.out.println("Jezeli chcesz wyjsc wpisz ESC i nacisnij 'Enter'");
        System.out.println("Jezeli chcesz zapisac stan magazynu do pliku wpisz ZAPISZ i nacisnij 'Enter'");
        odp = in.nextLine();

        switch(odp){
            case "1":
                opcja1(o);
                break;
            case "2":
                opcja2(o);
                break;
            case "3":
                opcja3(o);
                break;
            case "4":
                opcja4(o);
                break;
            case "5":
                opcja5(o);
                break;
            case "6":
                opcja6(o);
                break;
            case "esc":
                System.out.println();
                System.out.println(BLUE+"Dziekujemy i zapraszamy ponownie"+RESET);
                System.exit(0);
                break;
            case "ESC":
                System.out.println();
                System.out.println(BLUE+"Dziekujemy i zapraszamy ponownie"+RESET);
                System.exit(0);
                break;
            case "zapisz":
                System.out.println();
                System.out.println(BLUE+"Zapisano stan magazynu"+RESET);
                System.out.println();
                zapiszDoPliku();
                menu(o);
                break;
            case "ZAPISZ":
                System.out.println();
                System.out.println(BLUE+"Zapisano stan magazynu"+RESET);
                System.out.println();
                zapiszDoPliku();
                menu(o);
                break;
            default:
                System.out.println();
                System.out.println(RED +"Zła komenda"+ RESET);
                System.out.println("Sprawdz jeszcze raz jaka operacje chcesz wykonac i wpisz ponownie");
                menu(o);
        }
    }
//-------------------------------------------------------------------------------------------------------------------------------------------
    public void opcja1(Osoba o) throws TooManyThingsException {
        boolean zm = false;
        System.out.println("Twoje dane: ");
        System.out.println("    imie: "+o.getImie()+", nazwiko: "+o.getNazwisko()+", pesel: "+o.getPesel()+", data urodzenia: "+o.getDataUrodzenia()+", adres zamieszkania:"+o.getAdresZamieszkania());
        System.out.println("Wynajmujesz: ");
        for (Pomieszczenie pomieszczenie : magazyn.getMagazyn().keySet()) {
            if (magazyn.getMagazyn().get(pomieszczenie) == o) {
                zm = true;
                System.out.println("   - pomieszczenie o numerze : " + pomieszczenie.getId());
            }
        }
        if(zm == false){
            System.out.println(RED +"    Nie wynajmujesz zadnych pomieszczen"+ RESET);
            System.out.println("Jezeli chcesz wrocic do glownego menu wpisz BACK i nacisnij 'Enter'");
            System.out.println("Jezeli chcesz wyjsc wpisz ESC i nacisnij 'Enter'");
            odp = in.nextLine();
            if("BACK".compareTo(odp.toUpperCase()) == 0 || "BACK".compareTo(odp.toLowerCase()) == 0){
                menu(o);
            }else if("ESC".compareTo(odp.toUpperCase()) == 0 || "ESC".compareTo(odp.toLowerCase()) == 0){
                System.out.println();
                System.out.println(BLUE+"Dziekujemy i zapraszamy ponownie");
                System.exit(0);
            }
        }
        System.out.println("Jezeli chcesz wrocic do glownego menu wpisz BACK i nacisnij 'Enter'");
        System.out.println("Jezeli chcesz wyjsc wpisz ESC i nacisnij 'Enter'");
        odp = in.nextLine();
        if("BACK".compareTo(odp.toUpperCase()) == 0 || "BACK".compareTo(odp.toLowerCase()) == 0){
            menu(o);
        }else if("ESC".compareTo(odp.toUpperCase()) == 0 || "ESC".compareTo(odp.toLowerCase()) == 0){
            System.out.println();
            System.out.println(BLUE+"Dziekujemy i zapraszamy ponownie");
            System.exit(0);
        }
    }

//-------------------------------------------------------------------------------------------------------------------------------------------

    public void opcja2(Osoba o) throws TooManyThingsException {
        boolean zm = false;
        System.out.println("Wybierz jedno z twoich wynajetych pomieszczen aby wyswietlic zawartosc: ");

        for(Pomieszczenie pomieszczenie : magazyn.getMagazyn().keySet()) {
            if (magazyn.getMagazyn().get(pomieszczenie) == o) {
                zm = true;
                System.out.println("   - pomieszczenie o numerze " + pomieszczenie.getId()+ " wpisz " + pomieszczenie.getId()+" i nacisnij 'Enter'");
            }
        }

        if(zm == false){
            System.out.println(RED +"    Nie wynajmujesz żadnch pomieszczen"+ RESET);
            System.out.println("Jezeli chcesz wrocic do glownego menu wpisz BACK i nacisnij 'Enter'");
            System.out.println("Jezeli chcesz wyjsc wpisz ESC i nacisnij 'Enter'");
            odp = in.nextLine();
            if("BACK".compareTo(odp.toUpperCase()) == 0 || "BACK".compareTo(odp.toLowerCase()) == 0){
                menu(o);
            }else if("ESC".compareTo(odp.toUpperCase()) == 0 || "ESC".compareTo(odp.toLowerCase()) == 0){
                System.out.println();
                System.out.println(BLUE+"Dziekujemy i zapraszamy ponownie");
                System.exit(0);
            }
        }

        System.out.println("Jezeli chcesz wrocic do glownego menu wpisz BACK i nacisnij 'Enter'");
        System.out.println("Jezeli chcesz wyjsc wpisz ESC i nacisnij 'Enter'");
        odp = in.nextLine();
        if("BACK".compareTo(odp.toUpperCase()) == 0 || "BACK".compareTo(odp.toLowerCase()) == 0){
            menu(o);
        }else if("ESC".compareTo(odp.toUpperCase()) == 0 || "ESC".compareTo(odp.toLowerCase()) == 0){
            System.out.println();
            System.out.println(BLUE+"Dziekujemy i zapraszamy ponownie");
            System.exit(0);
        }

        for (Pomieszczenie pomieszczenie : magazyn.getMagazyn().keySet()) {
            if (pomieszczenie.getId() == Integer.parseInt(odp)) {
                if (pomieszczenie.getListaPrzedmiotow().isEmpty()){
                    System.out.println(RED +"    Brak przedmiotów w tym pomieszczeniu"+ RESET);
                }else {
                    System.out.println("Przedmioty w pomieszceniu:");
                    Collections.sort(pomieszczenie.getListaPrzedmiotow());
                    for (Przedmiot p : pomieszczenie.getListaPrzedmiotow()) {
                        System.out.println("   - nazwa: "+p.getNazwa() +", nr: "+ p.getNrVIN());
                    }
                }
            }
        }
        System.out.println("Jezeli chcesz wrocic do wyboru pomieszczenia wpisz BACK i nacisnij 'Enter'");
        System.out.println("Jezeli chcesz wyjsc wpisz ESC i nacisnij 'Enter'");
        odp = in.nextLine();
        if("BACK".compareTo(odp.toUpperCase()) == 0 || "BACK".compareTo(odp.toLowerCase()) == 0){
           opcja2(o);
        }else if("ESC".compareTo(odp.toUpperCase()) == 0 || "ESC".compareTo(odp.toLowerCase()) == 0){
            System.out.println();
            System.out.println(BLUE+"Dziekujemy i zapraszamy ponownie");
            System.exit(0);
        }
    }

//-------------------------------------------------------------------------------------------------------------------------------------------

    public void opcja3(Osoba o) throws TooManyThingsException {
        boolean zm = false;
        System.out.println("Wybierz jedno z twoich wynajetych pomieszczen do ktorego chcesz dodac przedmiot:");

        for (Pomieszczenie pomieszczenie : magazyn.getMagazyn().keySet()) {
            if (magazyn.getMagazyn().get(pomieszczenie) == o) {
                zm = true;
                System.out.println("   - pomieszczenie o numerze " + pomieszczenie.getId() + " wpisz " + pomieszczenie.getId() + " i nacisnij 'Enter'");
            }
        }

        if (zm == false) {
            System.out.println(RED + "    Nie wynajmujesz żadnego pomieszczenia" + RESET);
            System.out.println("Jezeli chcesz wrocic do glownego menu wpisz BACK i nacisnij 'Enter'");
            System.out.println("Jezeli chcesz wyjsc wpisz ESC i nacisnij 'Enter'");
            odp = in.nextLine();
            if ("BACK".compareTo(odp.toUpperCase()) == 0 || "BACK".compareTo(odp.toLowerCase()) == 0) {
                menu(o);
            } else if ("ESC".compareTo(odp.toUpperCase()) == 0 || "ESC".compareTo(odp.toLowerCase()) == 0) {
                System.out.println();
                System.out.println(BLUE+"Dziekujemy i zapraszamy ponownie");
                System.exit(0);
            }
        }

        System.out.println("Jezeli chcesz wrocic do glownego menu wpisz BACK i nacisnij 'Enter'");
        System.out.println("Jezeli chcesz wyjsc wpisz ESC i nacisnij 'Enter'");
        odp2 = in.nextLine();
        if ("BACK".compareTo(odp2.toUpperCase()) == 0 || "BACK".compareTo(odp2.toLowerCase()) == 0) {
            menu(o);
        } else if ("ESC".compareTo(odp2.toUpperCase()) == 0 || "ESC".compareTo(odp2.toLowerCase()) == 0) {
            System.out.println();
            System.out.println(BLUE+"Dziekujemy i zapraszamy ponownie");
            System.exit(0);
        }

        for (Pomieszczenie pomieszczenie : magazyn.getMagazyn().keySet()) {
            if (pomieszczenie.getId() == Integer.parseInt(odp2)) {
                if (pomieszczenie.getStatusPomieszczenia() != pomieszczenie.getStatusPomieszczenia().remont) {
                    System.out.println("Co chcesz włozyć do swojego pomieszczenia:");
                    System.out.println("   - jezeli samochod wpisz SAMOCHOD i wcisnij 'Enter'");
                    System.out.println("   - jezeli rower wpisz ROWER i wcisnij 'Enter'");
                    System.out.println("   - jezeli motocykl wpisz MOTOCYKL i wcisnij 'Enter'");
                    System.out.println("   - jezeli inny przedmiot wpisz PRZEDMIOT i wcisnij 'Enter'");
                    System.out.println("Jezeli chcesz wrocic do glownego menu wpisz BACK i nacisnij 'Enter'");
                    System.out.println("Jezeli chcesz wyjsc wpisz ESC i nacisnij 'Enter'");
                    odp = in.nextLine();
                    if ("SAMOCHOD".compareTo(odp.toUpperCase()) == 0 || "SAMOCHOD".compareTo(odp.toLowerCase()) == 0) {
                        dodajSamochod(pomieszczenie, o);
                    }
                    if ("ROWER".compareTo(odp.toUpperCase()) == 0 || "ROWER".compareTo(odp.toLowerCase()) == 0) {
                        dodajRower(pomieszczenie, o);
                    }
                    if ("MOTOCYKL".compareTo(odp.toUpperCase()) == 0 || "MOTOCYKL".compareTo(odp.toLowerCase()) == 0) {
                        dodajMotocykl(pomieszczenie, o);
                    }
                    if ("PRZEDMIOT".compareTo(odp.toUpperCase()) == 0 || "PRZEDMIOT".compareTo(odp.toLowerCase()) == 0) {
                        dodajPrzedmiot(pomieszczenie, o);
                    }
                    if ("BACK".compareTo(odp.toUpperCase()) == 0 || "BACK".compareTo(odp.toLowerCase()) == 0) {
                        opcja3(o);
                    } else if ("ESC".compareTo(odp.toUpperCase()) == 0 || "ESC".compareTo(odp.toLowerCase()) == 0) {
                        System.out.println();
                        System.out.println(BLUE+"Dziekujemy i zapraszamy ponownie");
                        System.exit(0);
                    }
                } else {
                    System.out.println(RED + "    Pomieszczenie w remoncie" + RESET);
                    System.out.println("Jezeli chcesz wrocic do glownego menu wpisz BACK i nacisnij 'Enter'");
                    System.out.println("Jezeli chcesz wyjsc napisz ESC i nacisnij 'Enter'");
                    odp = in.nextLine();
                    if ("BACK".compareTo(odp.toUpperCase()) == 0 || "BACK".compareTo(odp.toLowerCase()) == 0) {
                        opcja3(o);
                    } else if ("ESC".compareTo(odp.toUpperCase()) == 0 || "ESC".compareTo(odp.toLowerCase()) == 0) {
                        System.out.println();
                        System.out.println(BLUE+"Dziekujemy i zapraszamy ponownie");
                        System.exit(0);
                    }
                }
            }
        }
    }



//-------------------------------------------------------------------------------------------------------------------------------------------

    public void opcja4(Osoba o) throws TooManyThingsException {
        boolean zm = false;
        System.out.println("Wybierz pomieszczenie z ktorego chcesz wyjac przedmiot:");
        for(Pomieszczenie pomieszczenie : magazyn.getMagazyn().keySet()) {
            if (magazyn.getMagazyn().get(pomieszczenie) == o) {
                zm = true;
                System.out.println("  - pomieszczenie o numerze " + pomieszczenie.getId() + " wpisz " + pomieszczenie.getId() + " i nacisnij 'Enter'");
            }
        }

        if(zm == false){
            System.out.println(RED +"    Nie wynajmujesz żadnego pomieszczenia"+ RESET);
            System.out.println("Jezeli chcesz wrocic do glownego menu wpisz BACK i nacisnij 'Enter'");
            System.out.println("Jezeli chcesz wyjsc napisz ESC i nacisnij 'Enter'");
            odp = in.nextLine();
            if("BACK".compareTo(odp.toUpperCase()) == 0 || "BACK".compareTo(odp.toLowerCase()) == 0){
                menu(o);
            }else if("ESC".compareTo(odp.toUpperCase()) == 0 || "ESC".compareTo(odp.toLowerCase()) == 0){
                System.out.println();
                System.out.println(BLUE+"Dziekujemy i zapraszamy ponownie");
                System.exit(0);
            }
        }

        System.out.println("Jezeli chcesz wrocic do glownego menu wpisz BACK i nacisnij 'Enter'");
        System.out.println("Jezeli chcesz wyjsc napisz ESC i nacisnij 'Enter'");
        odp = in.nextLine();
        if("BACK".compareTo(odp.toUpperCase()) == 0 || "BACK".compareTo(odp.toLowerCase()) == 0){
            menu(o);
        }else if("ESC".compareTo(odp.toUpperCase()) == 0 || "ESC".compareTo(odp.toLowerCase()) == 0){
            System.out.println();
            System.out.println(BLUE+"Dziekujemy i zapraszamy ponownie");
            System.exit(0);
        }

        for (Pomieszczenie pomieszczenie : magazyn.getMagazyn().keySet()) {
            if (pomieszczenie.getId() == Integer.parseInt(odp)) {
                if (pomieszczenie.getListaPrzedmiotow().isEmpty()){
                    System.out.println(RED +"    Brak przedmiotów w tym pomieszczeniu"+ RESET);
                    System.out.println("Jezeli chcesz wrocic do glownego menu wpisz BACK i nacisnij 'Enter'");
                    System.out.println("Jezeli chcesz wyjsc napisz ESC i nacisnij 'Enter'");
                    odp = in.nextLine();
                    if ("BACK".compareTo(odp.toUpperCase()) == 0 || "BACK".compareTo(odp.toLowerCase()) == 0) {
                        opcja4(o);
                    } else if ("ESC".compareTo(odp.toUpperCase()) == 0 || "ESC".compareTo(odp.toLowerCase()) == 0) {
                        System.out.println();
                        System.out.println(BLUE+"Dziekujemy i zapraszamy ponownie");
                        System.exit(0);
                    }
                }else {
                    System.out.println("Co chcesz wyjac ze swojego pomieszczenia:");
                    System.out.println("   - samochod wpisz SAMOCHOD i wcisnij 'Enter'");
                    System.out.println("   - rower wpisz ROWER i wcisnij 'Enter'");
                    System.out.println("   - motocykl wpisz MOTOCYKL i wcisnij 'Enter'");
                    System.out.println("   - inny przedmiot wpisz Przedmiot i wcisnij 'Enter'");
                    System.out.println("Jezeli chcesz wrocic do glownego menu wpisz BACK i nacisnij 'Enter'");
                    System.out.println("Jezeli chcesz wyjsc wpisz ESC i nacisnij 'Enter'");
                    odp = in.nextLine();
                    if ("SAMOCHOD".compareTo(odp.toUpperCase()) == 0 || "SAMOCHOD".compareTo(odp.toLowerCase()) == 0) {
                        usunSamochod(pomieszczenie, o);
                    }
                    if ("ROWER".compareTo(odp.toUpperCase()) == 0 || "ROWER".compareTo(odp.toLowerCase()) == 0) {
                        usunRower(pomieszczenie, o);
                    }
                    if ("MOTOCYKL".compareTo(odp.toUpperCase()) == 0 || "MOTOCYKL".compareTo(odp.toLowerCase()) == 0) {
                        usunMotocykl(pomieszczenie, o);
                    }
                    if("PRZEDMIOT".compareTo(odp.toUpperCase()) == 0 || "PRZEDMIOT".compareTo(odp.toLowerCase()) == 0){
                        usunPrzedmiot(pomieszczenie,o);
                    }
                    if ("BACK".compareTo(odp.toUpperCase()) == 0 || "BACK".compareTo(odp.toLowerCase()) == 0) {
                        opcja4(o);
                    } else if ("ESC".compareTo(odp.toUpperCase()) == 0 || "ESC".compareTo(odp.toLowerCase()) == 0) {
                        System.out.println();
                        System.out.println(BLUE+"Dziekujemy i zapraszamy ponownie");
                        System.exit(0);
                    }
                }
            }
        }
    }

//-------------------------------------------------------------------------------------------------------------------------------------------

    public void opcja5(Osoba o) throws TooManyThingsException {
        System.out.println("Dostepne pomieszczenia: ");
        boolean zm = false;
        for (Pomieszczenie pomieszczenie : magazyn.getMagazyn().keySet()){
            if (pomieszczenie.getStatusPomieszczenia() == pomieszczenie.getStatusPomieszczenia().wolny){
                System.out.println("  - pomieszczenie o numerze " + pomieszczenie.getId()+ " i powierzchni " + (int)pomieszczenie.getWolnaPowierzchnia()+ " metrow szesciennych");
                zm = true;
            }
        }
        if (zm == false) {
            System.out.println(RED +"    Brak wolnych pomieszczeń"+ RESET);
        }
        System.out.println("Jezeli chcesz wrocic do glownego menu wpisz BACK i nacisnij 'Enter'");
        System.out.println("Jezeli chcesz wyjsc wpisz ESC i nacisnij 'Enter'");
        odp = in.nextLine();
        if("BACK".compareTo(odp.toUpperCase()) == 0 || "BACK".compareTo(odp.toLowerCase()) == 0){
            menu(o);
        }else if("ESC".compareTo(odp.toUpperCase()) == 0 || "ESC".compareTo(odp.toLowerCase()) == 0) {
            System.out.println();
            System.out.println(BLUE+"Dziekujemy i zapraszamy ponownie");
            System.exit(0);
        }
    }

//-------------------------------------------------------------------------------------------------------------------------------------------

    public void opcja6(Osoba o) throws TooManyThingsException {
        System.out.println("Wybierz pomieszczenie ktore chcesz zarezrwowac: ");
        boolean zm = false;
        for (Pomieszczenie pomieszczenie:magazyn.getMagazyn().keySet()){
            if (pomieszczenie.getStatusPomieszczenia() == pomieszczenie.getStatusPomieszczenia().wolny){
                System.out.println("  - pomieszczenie o numerze " + pomieszczenie.getId() +" wpisz " + pomieszczenie.getId() + " i nacisnij 'Enter'");
                zm = true;
            }
        }
        if (zm == false) {
            System.out.println(RED +"    Brak wolnych pomieszczeń"+ RESET);
            System.out.println("Jezeli chcesz wrocic do glownego menu wpisz BACK i nacisnij 'Enter'");
            System.out.println("Jezeli chcesz wyjsc wpisz ESC i nacisnij 'Enter'");
            odp = in.nextLine();
            if ("BACK".compareTo(odp.toUpperCase()) == 0 || "BACK".compareTo(odp.toLowerCase()) == 0){
                menu(o);
            }else if ("ESC".compareTo(odp.toUpperCase()) == 0 || "ESC".compareTo(odp.toLowerCase()) == 0) {
                System.out.println();
                System.out.println(BLUE+"Dziekujemy i zapraszamy ponownie");
                System.exit(0);
            }
        }
        System.out.println("Jezeli chcesz wrocic do glownego menu wpisz BACK i nacisnij 'Enter'");
        System.out.println("Jezeli chcesz wyjsc wpisz ESC i nacisnij 'Enter'");
        odp3 = in.nextLine();
        if ("BACK".compareTo(odp3.toUpperCase()) == 0 || "BACK".compareTo(odp3.toLowerCase()) == 0){
            menu(o);
        }else if ("ESC".compareTo(odp3.toUpperCase()) == 0 || "ESC".compareTo(odp3.toLowerCase()) == 0) {
            System.out.println();
            System.out.println(BLUE+"Dziekujemy i zapraszamy ponownie");
            System.exit(0);
        }
        else if (Integer.parseInt(odp3) > 0 && Integer.parseInt(odp3) <= magazyn.getMagazyn().size()) {
            for (Pomieszczenie pomieszczenie : magazyn.getMagazyn().keySet()) {
                if (Integer.parseInt(odp3) == pomieszczenie.getId()) {
                    magazyn.dodajNajemce(pomieszczenie, o);
                    System.out.println(BLUE+"    Pomieszczenie nalezy do ciebie"+ RESET);
                    System.out.println("Jezeli chcesz wrocic do glownego menu wpisz BACK i nacisnij 'Enter'");
                    System.out.println("Jezeli chcesz wyjsc wpisz ESC i nacisnij 'Enter'");
                    odp3 = in.nextLine();
                    if ("BACK".compareTo(odp3.toUpperCase()) == 0 || "BACK".compareTo(odp3.toLowerCase()) == 0){
                        menu(o);
                    }else if ("ESC".compareTo(odp3.toUpperCase()) == 0 || "ESC".compareTo(odp3.toLowerCase()) == 0) {
                        System.out.println();
                        System.out.println(BLUE+"Dziekujemy i zapraszamy ponownie");
                        System.exit(0);
                    }
                }
            }
        }
    }

//-------------------------------------------------------------------------------------------------------------------------------------------

    public void dodajSamochod(Pomieszczenie pomieszczenie, Osoba osoba) throws TooManyThingsException {
        System.out.print("marka: ");
        String nazwa = in.next();
        System.out.println("podaj typ silnika: ");
        System.out.println("    - gaz");
        System.out.println("    - benzyna");
        System.out.println("    - hybryda");
        System.out.println("    - diesel");
        TypSilnika typsilnika = TypSilnika.valueOf(in.next().toLowerCase());
        System.out.print("numerVIN: ");
        String nrVIN = in.next();
        System.out.print("szerokosc: ");
        double szerokosc = in.nextDouble();
        System.out.print("wysokosc: ");
        double wysokosc = in.nextDouble();
        System.out.print("dlugosc: ");
        double dlugosc = in.nextDouble();

        Samochod samochod = new Samochod(nazwa,typsilnika,nrVIN,szerokosc,wysokosc,dlugosc);

        if (pomieszczenie.getWolnaPowierzchnia() - samochod.getWolnaPowierzchnia() <= 0) {
            try{
                pomieszczenie.wyjatekTooManyThings(pomieszczenie,samochod);
            }catch (TooManyThingsException e){}
                odp = in.nextLine();
                if("BACK".compareTo(odp.toUpperCase()) == 0 || "BACK".compareTo(odp.toLowerCase()) == 0){
                    opcja3(osoba);
                }else if("ESC".compareTo(odp.toUpperCase()) == 0 || "ESC".compareTo(odp.toLowerCase()) == 0) {
                    System.out.println();
                    System.out.println(BLUE+"Dziekujemy i zapraszamy ponownie");
                    System.exit(0);
                }
        }else {
            pomieszczenie.setWolnaPowierzchnia(pomieszczenie.getWolnaPowierzchnia() - samochod.getWolnaPowierzchnia());
            pomieszczenie.getListaPrzedmiotow().add(samochod);
            System.out.println(BLUE+"    Dodano samochod o nazwie " +nazwa+ RESET);
            System.out.println(BLUE+"    Po dodaniu powierzchnia twojgo pomieszczenia wynosi " + (int)pomieszczenie.getWolnaPowierzchnia()+ " metrow szesciennych"+ RESET);
            System.out.println("Jezeli chcesz wrocic do glownego menu wpisz BACK i nacisnij 'Enter'");
            System.out.println("Jezeli chcesz wyjsc napisz ESC i nacisnij 'Enter'");
            odp = in.nextLine();
            if("BACK".compareTo(odp.toUpperCase()) == 0 || "BACK".compareTo(odp.toLowerCase()) == 0){
                opcja3(osoba);
            }else if("ESC".compareTo(odp.toUpperCase()) == 0 || "ESC".compareTo(odp.toLowerCase()) == 0) {
                System.out.println();
                System.out.println(BLUE+"Dziekujemy i zapraszamy ponownie");
                System.exit(0);
            }
        }

        odp = in.nextLine();
        if("BACK".compareTo(odp.toUpperCase()) == 0 || "BACK".compareTo(odp.toLowerCase()) == 0){
            opcja3(osoba);
        }else if("ESC".compareTo(odp.toUpperCase()) == 0 || "ESC".compareTo(odp.toLowerCase()) == 0){
            System.out.println();
            System.out.println(BLUE+"Dziekujemy i zapraszamy ponownie");
            System.exit(0);
        }
    }

//-------------------------------------------------------------------------------------------------------------------------------------------

    public void usunSamochod(Pomieszczenie pomieszczenie,Osoba osoba) throws TooManyThingsException {
        System.out.print("Podaj numerVIN samochodu: ");
        odp = in.nextLine();
        for (Przedmiot p : pomieszczenie.getListaPrzedmiotow()) {
            if(odp.equals(p.getNrVIN())){
                pomieszczenie.getListaPrzedmiotow().remove(p);
                pomieszczenie.setWolnaPowierzchnia(pomieszczenie.getWolnaPowierzchnia() + p.getWolnaPowierzchnia());
                System.out.println(BLUE+"    Po usunieciu powierzchnia twojgo pomieszczenia wynosi " + (int)pomieszczenie.getWolnaPowierzchnia()+ " metrow szesciennych"+ RESET);
                System.out.println("Jezeli chcesz wrocic do glownego menu wpisz BACK i nacisnij 'Enter'");
                System.out.println("Jezeli chcesz wyjsc wpisz ESC i nacisnij 'Enter'");
                odp = in.nextLine();
                if("BACK".compareTo(odp.toUpperCase()) == 0 || "BACK".compareTo(odp.toLowerCase()) == 0){
                    opcja4(osoba);
                }else if("ESC".compareTo(odp.toUpperCase()) == 0 || "ESC".compareTo(odp.toLowerCase()) == 0){
                    System.out.println();
                    System.out.println(BLUE+"Dziekujemy i zapraszamy ponownie");
                    System.exit(0);
                }
            }else{
                System.out.println(RED +"    Brak takiego samochodu"+ RESET);
            }
        }
        System.out.println("Jezeli chcesz sprobowac jeszcze raz wpisz AGAIN i nacisnij 'Enter'");
        System.out.println("Jezeli chcesz wrocic do glownego menu wpisz BACK i nacisnij 'Enter'");
        System.out.println("Jezeli chcesz wyjsc wpisz ESC i nacisnij 'Enter'");
        odp = in.nextLine();
        if("BACK".compareTo(odp.toUpperCase()) == 0 || "BACK".compareTo(odp.toLowerCase()) == 0){
            menu(osoba);
        }else if("ESC".compareTo(odp.toUpperCase()) == 0 || "ESC".compareTo(odp.toLowerCase()) == 0){
            System.out.println();
            System.out.println(BLUE+"Dziekujemy i zapraszamy ponownie");
            System.exit(0);
        }else if("AGAIN".compareTo(odp.toUpperCase()) == 0 || "AGAIN".compareTo(odp.toLowerCase()) == 0){
            usunSamochod(pomieszczenie,osoba);
        }
    }

//-------------------------------------------------------------------------------------------------------------------------------------------

    public void dodajRower(Pomieszczenie pomieszczenie,Osoba osoba) throws TooManyThingsException {
        System.out.print("nazwa: ");
        String nazwa = in.next();
        System.out.print("numer seryjny: ");
        String nrSeryjny = in.next();
        System.out.print("szerokosc: ");
        double szerokosc = in.nextDouble();
        System.out.print("wysokosc: ");
        double wysokosc = in.nextDouble();
        System.out.print("dlugosc: ");
        double dlugosc = in.nextDouble();
        System.out.print("liczba przerzutek: ");
        int liczbaPrzerzutek = in.nextInt();
        System.out.println("- ROZLOZONY");
        System.out.println("- ZLOZONY");
        StanPrzedmiotu stanPrzedmiotu = StanPrzedmiotu.valueOf(in.next().toUpperCase());

        Rower rower = new Rower(nazwa,nrSeryjny,szerokosc,wysokosc,dlugosc,liczbaPrzerzutek,stanPrzedmiotu);

        if (pomieszczenie.getWolnaPowierzchnia() - rower.getWolnaPowierzchnia() <= 0) {
            try{
                pomieszczenie.wyjatekTooManyThings(pomieszczenie,rower);
            }catch (TooManyThingsException e){}

            System.out.println("Jezeli chcesz wrocic do glownego menu wpisz BACK i nacisnij 'Enter'");
            System.out.println("Jezeli chcesz wyjsc wpisz ESC i nacisnij 'Enter'");
            odp = in.nextLine();
            if("BACK".compareTo(odp.toUpperCase()) == 0 || "BACK".compareTo(odp.toLowerCase()) == 0){
                opcja3(osoba);
            }else if("ESC".compareTo(odp.toUpperCase()) == 0 || "ESC".compareTo(odp.toLowerCase()) == 0) {
                System.out.println();
                System.out.println(BLUE+"Dziekujemy i zapraszamy ponownie");
                System.exit(0);
            }
        }else {
            pomieszczenie.setWolnaPowierzchnia(pomieszczenie.getWolnaPowierzchnia() - rower.getWolnaPowierzchnia());
            pomieszczenie.getListaPrzedmiotow().add(rower);
            System.out.println(BLUE+"    Dodano rower o nazwie " +nazwa+ RESET);
            System.out.println(BLUE+"    Po dodaniu powierzchnia twojgo pomieszczenia wynosi " + (int)pomieszczenie.getWolnaPowierzchnia()+ " metrow szesciennych"+ RESET);
            System.out.println("Jezeli chcesz wrocic do glownego menu wpisz BACK i nacisnij 'Enter'");
            System.out.println("Jezeli chcesz wyjsc wpisz ESC i nacisnij 'Enter'");
            odp = in.nextLine();
            if("BACK".compareTo(odp.toUpperCase()) == 0 || "BACK".compareTo(odp.toLowerCase()) == 0){
                opcja3(osoba);
            }else if("ESC".compareTo(odp.toUpperCase()) == 0 || "ESC".compareTo(odp.toLowerCase()) == 0) {
                System.out.println();
                System.out.println(BLUE+"Dziekujemy i zapraszamy ponownie");
                System.exit(0);
            }
        }

        odp = in.nextLine();
        if("BACK".compareTo(odp.toUpperCase()) == 0 || "BACK".compareTo(odp.toLowerCase()) == 0){
            opcja3(osoba);
        }else if("ESC".compareTo(odp.toUpperCase()) == 0 || "ESC".compareTo(odp.toLowerCase()) == 0){
            System.out.println();
            System.out.println(BLUE+"Dziekujemy i zapraszamy ponownie");
            System.exit(0);
        }
    }

//-------------------------------------------------------------------------------------------------------------------------------------------

    public void usunRower(Pomieszczenie pomieszczenie,Osoba osoba) throws TooManyThingsException {
        System.out.print("Podaj numer seryjny roweru: ");
        odp = in.nextLine();
        for (Przedmiot p : pomieszczenie.getListaPrzedmiotow()) {
            if(odp.equals(p.getNrVIN())){
                pomieszczenie.getListaPrzedmiotow().remove(p);
                pomieszczenie.setWolnaPowierzchnia(pomieszczenie.getWolnaPowierzchnia() + p.getWolnaPowierzchnia());
                System.out.println(BLUE+"    Po usunieciu powierzchnia twojgo pomieszczenia wynosi " + (int)pomieszczenie.getWolnaPowierzchnia()+ " metrow szesciennych"+ RESET);
                System.out.println("Jezeli chcesz wrocic do glownego menu wpisz BACK i nacisnij 'Enter'");
                System.out.println("Jezeli chcesz wyjsc wpisz ESC i nacisnij 'Enter'");
                odp = in.nextLine();
                if("BACK".compareTo(odp.toUpperCase()) == 0 || "BACK".compareTo(odp.toLowerCase()) == 0){
                    opcja4(osoba);
                }else if("ESC".compareTo(odp.toUpperCase()) == 0 || "ESC".compareTo(odp.toLowerCase()) == 0){
                    System.out.println();
                    System.out.println(BLUE+"Dziekujemy i zapraszamy ponownie");
                    System.exit(0);
                }
            }else{
                System.out.println(RED +"    Brak takiego roweru"+ RESET);
            }
        }
        System.out.println("Jezeli chcesz sprobowac jeszcze raz wpisz AGAIN i nacisnij 'Enter'");
        System.out.println("Jezeli chcesz wrocic do glownego menu wpisz BACK i nacisnij 'Enter'");
        System.out.println("Jezeli chcesz wyjsc wpisz ESC i nacisnij 'Enter'");
        odp = in.nextLine();
        if("BACK".compareTo(odp.toUpperCase()) == 0 || "BACK".compareTo(odp.toLowerCase()) == 0){
            menu(osoba);
        }else if("ESC".compareTo(odp.toUpperCase()) == 0 || "ESC".compareTo(odp.toLowerCase()) == 0){
            System.out.println();
            System.out.println(BLUE+"Dziekujemy i zapraszamy ponownie");
            System.exit(0);
        }else if("AGAIN".compareTo(odp.toUpperCase()) == 0 || "AGAIN".compareTo(odp.toLowerCase()) == 0){
            usunRower(pomieszczenie,osoba);
        }
    }

//-------------------------------------------------------------------------------------------------------------------------------------------

    public void dodajMotocykl(Pomieszczenie pomieszczenie, Osoba osoba) throws TooManyThingsException {
        System.out.print("marka: ");
        String marka = in.next();
        System.out.print("numerVIN: ");
        String nrVIN = in.next();
        System.out.print("szerokosc: ");
        double szerokosc = in.nextDouble();
        System.out.print("wysokosc: ");
        double wysokosc = in.nextDouble();
        System.out.print("dlugosc: ");
        double dlugosc = in.nextDouble();
        System.out.println("Czy ma homologacje? Wpisz:");
        System.out.println("- TAK");
        System.out.println("- NIE");
        Homologacja homologacja = Homologacja.valueOf(in.next().toUpperCase());

        Motocykl motocykl = new Motocykl(marka,nrVIN,szerokosc,wysokosc,dlugosc,homologacja);

        if (pomieszczenie.getWolnaPowierzchnia() - motocykl.getWolnaPowierzchnia() <= 0) {
            try{
                pomieszczenie.wyjatekTooManyThings(pomieszczenie,motocykl);
            }catch (TooManyThingsException e){}
            System.out.println("Jezeli chcesz wrocic do glownego menu wpisz BACK i nacisnij 'Enter'");
            System.out.println("Jezeli chcesz wyjsc wpisz ESC i nacisnij 'Enter'");
            odp = in.nextLine();
            if("BACK".compareTo(odp.toUpperCase()) == 0 || "BACK".compareTo(odp.toLowerCase()) == 0){
                opcja3(osoba);
            }else if("ESC".compareTo(odp.toUpperCase()) == 0 || "ESC".compareTo(odp.toLowerCase()) == 0) {
                System.out.println();
                System.out.println(BLUE+"Dziekujemy i zapraszamy ponownie");
                System.exit(0);
            }
        } else {
            pomieszczenie.setWolnaPowierzchnia(pomieszczenie.getWolnaPowierzchnia() - motocykl.getWolnaPowierzchnia());
            pomieszczenie.getListaPrzedmiotow().add(motocykl);
            System.out.println(BLUE+"    Po dodaniu powierzchnia twojgo pomieszczenia wynosi " + (int)pomieszczenie.getWolnaPowierzchnia()+" metrów szesciennych"+ RESET);
            System.out.println("    Jezeli chcesz wrocic do glownego menu wpisz BACK i nacisnij 'Enter'");
            System.out.println("Jezeli chcesz wyjsc wpisz ESC i nacisnij 'Enter'");
            odp = in.nextLine();
            if("BACK".compareTo(odp.toUpperCase()) == 0 || "BACK".compareTo(odp.toLowerCase()) == 0){
                opcja3(osoba);
            }else if("ESC".compareTo(odp.toUpperCase()) == 0 || "ESC".compareTo(odp.toLowerCase()) == 0) {
                System.out.println();
                System.out.println(BLUE+"Dziekujemy i zapraszamy ponownie");
                System.exit(0);
            }
        }

        odp = in.nextLine();
        if("BACK".compareTo(odp.toUpperCase()) == 0 || "BACK".compareTo(odp.toLowerCase()) == 0){
            opcja3(osoba);
        }else if("ESC".compareTo(odp.toUpperCase()) == 0 || "ESC".compareTo(odp.toLowerCase()) == 0){
            System.out.println();
            System.out.println(BLUE+"Dziekujemy i zapraszamy ponownie");
            System.exit(0);
        }
    }

//-------------------------------------------------------------------------------------------------------------------------------------------

    public void usunMotocykl(Pomieszczenie pomieszczenie, Osoba osoba) throws TooManyThingsException {
        System.out.print("Podaj numerVIN motocyklu: ");
        odp = in.nextLine();
        for (Przedmiot p : pomieszczenie.getListaPrzedmiotow()) {
            if(odp.equals(p.getNrVIN())){
                pomieszczenie.getListaPrzedmiotow().remove(p);
                pomieszczenie.setWolnaPowierzchnia(pomieszczenie.getWolnaPowierzchnia() + p.getWolnaPowierzchnia());
                System.out.println(BLUE+"    Po usunieciu powierzchnia twojgo pomieszczenia wynosi " + (int)pomieszczenie.getWolnaPowierzchnia()+ " metrow szesciennych"+ RESET);
                System.out.println("Jezeli chcesz wrocic do glownego menu wpisz BACK i nacisnij 'Enter'");
                System.out.println("Jezeli chcesz wyjsc wpisz ESC i nacisnij 'Enter'");
                odp = in.nextLine();
                if("BACK".compareTo(odp.toUpperCase()) == 0 || "BACK".compareTo(odp.toLowerCase()) == 0){
                    opcja4(osoba);
                }else if("ESC".compareTo(odp.toUpperCase()) == 0 || "ESC".compareTo(odp.toLowerCase()) == 0){
                    System.out.println();
                    System.out.println(BLUE+"Dziekujemy i zapraszamy ponownie");
                    System.exit(0);
                }
            }else{
                System.out.println(RED +"    Brak takiego motocyklu"+ RESET);
            }
        }
        System.out.println("Jezeli chcesz sprobowac jeszcze raz wpisz AGAIN i nacisnij 'Enter'");
        System.out.println("Jezeli chcesz wrocic do glownego menu wpisz BACK i nacisnij 'Enter'");
        System.out.println("Jezeli chcesz wyjsc wpisz ESC i nacisnij 'Enter'");
        odp = in.nextLine();
        if("BACK".compareTo(odp.toUpperCase()) == 0 || "BACK".compareTo(odp.toLowerCase()) == 0){
            menu(osoba);
        }else if("ESC".compareTo(odp.toUpperCase()) == 0 || "ESC".compareTo(odp.toLowerCase()) == 0){
            System.out.println();
            System.out.println(BLUE+"Dziekujemy i zapraszamy ponownie");
            System.exit(0);
        }else if("AGAIN".compareTo(odp.toUpperCase()) == 0 || "AGAIN".compareTo(odp.toLowerCase()) == 0){
            usunMotocykl(pomieszczenie,osoba);
        }
    }

//-------------------------------------------------------------------------------------------------------------------------------------------

    public void dodajPrzedmiot(Pomieszczenie pomieszczenie, Osoba osoba) throws TooManyThingsException {
        System.out.print("nazwa: ");
        String nazwa = in.next();
        System.out.print("jakis znak szegolny :");
        String znak = in.next();
        System.out.print("szerokosc: ");
        double szerokosc = in.nextDouble();
        System.out.print("wysokosc: ");
        double wysokosc = in.nextDouble();
        System.out.print("dlugosc: ");
        double dlugosc = in.nextDouble();

        Przedmiot przedmiot = new Przedmiot(nazwa,szerokosc,wysokosc,dlugosc,znak);

        if (pomieszczenie.getWolnaPowierzchnia() - przedmiot.getWolnaPowierzchnia() <= 0) {
            try{
                pomieszczenie.wyjatekTooManyThings(pomieszczenie,przedmiot);
            }catch (TooManyThingsException e){}
            odp = in.nextLine();
            if("BACK".compareTo(odp.toUpperCase()) == 0 || "BACK".compareTo(odp.toLowerCase()) == 0){
                opcja3(osoba);
            }else if("ESC".compareTo(odp.toUpperCase()) == 0 || "ESC".compareTo(odp.toLowerCase()) == 0) {
                System.out.println();
                System.out.println(BLUE+"Dziekujemy i zapraszamy ponownie");
                System.exit(0);
            }
        }else {
            pomieszczenie.setWolnaPowierzchnia(pomieszczenie.getWolnaPowierzchnia() - przedmiot.getWolnaPowierzchnia());
            pomieszczenie.getListaPrzedmiotow().add(przedmiot);
            System.out.println(BLUE+"    Dodano przedmiot o nazwie " +nazwa+ RESET);
            System.out.println(BLUE+"    Po dodaniu powierzchnia twojgo pomieszczenia wynosi " + (int)pomieszczenie.getWolnaPowierzchnia()+ " metrow szesciennych"+ RESET);
            System.out.println("Jezeli chcesz wrocic do glownego menu wpisz BACK i nacisnij 'Enter'");
            System.out.println("Jezeli chcesz wyjsc napisz ESC i nacisnij 'Enter'");
            odp = in.nextLine();
            if("BACK".compareTo(odp.toUpperCase()) == 0 || "BACK".compareTo(odp.toLowerCase()) == 0){
                opcja3(osoba);
            }else if("ESC".compareTo(odp.toUpperCase()) == 0 || "ESC".compareTo(odp.toLowerCase()) == 0) {
                System.out.println();
                System.out.println(BLUE+"Dziekujemy i zapraszamy ponownie");
                System.exit(0);
            }
        }

        odp = in.nextLine();
        if("BACK".compareTo(odp.toUpperCase()) == 0 || "BACK".compareTo(odp.toLowerCase()) == 0){
            opcja3(osoba);
        }else if("ESC".compareTo(odp.toUpperCase()) == 0 || "ESC".compareTo(odp.toLowerCase()) == 0){
            System.out.println();
            System.out.println(BLUE+"Dziekujemy i zapraszamy ponownie");
            System.exit(0);
        }
    }

//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    public void usunPrzedmiot(Pomieszczenie pomieszczenie,Osoba osoba) throws TooManyThingsException {
        System.out.print("Podaj nazwe przedmiotu");
        odp = in.nextLine();
        for (Przedmiot p : pomieszczenie.getListaPrzedmiotow()) {
            if(odp.equals(p.getNazwa())){
                pomieszczenie.getListaPrzedmiotow().remove(p);
                pomieszczenie.setWolnaPowierzchnia(pomieszczenie.getWolnaPowierzchnia() + p.getWolnaPowierzchnia());
                System.out.println(BLUE+"    Po usunieciu powierzchnia twojgo pomieszczenia wynosi " + (int)pomieszczenie.getWolnaPowierzchnia()+ " metrow szesciennych"+ RESET);
                System.out.println("Jezeli chcesz wrocic do glownego menu wpisz BACK i nacisnij 'Enter'");
                System.out.println("Jezeli chcesz wyjsc wpisz ESC i nacisnij 'Enter'");
                odp = in.nextLine();
                if("BACK".compareTo(odp.toUpperCase()) == 0 || "BACK".compareTo(odp.toLowerCase()) == 0){
                    opcja4(osoba);
                }else if("ESC".compareTo(odp.toUpperCase()) == 0 || "ESC".compareTo(odp.toLowerCase()) == 0){
                    System.out.println();
                    System.out.println(BLUE+"Dziekujemy i zapraszamy ponownie");
                    System.exit(0);
                }
            }else{
                System.out.println(RED +"    Brak takiego samochodu"+ RESET);
            }
        }
        System.out.println("Jezeli chcesz sprobowac jeszcze raz wpisz AGAIN i nacisnij 'Enter'");
        System.out.println("Jezeli chcesz wrocic do glownego menu wpisz BACK i nacisnij 'Enter'");
        System.out.println("Jezeli chcesz wyjsc wpisz ESC i nacisnij 'Enter'");
        odp = in.nextLine();
        if("BACK".compareTo(odp.toUpperCase()) == 0 || "BACK".compareTo(odp.toLowerCase()) == 0){
            menu(osoba);
        }else if("ESC".compareTo(odp.toUpperCase()) == 0 || "ESC".compareTo(odp.toLowerCase()) == 0){
            System.out.println();
            System.out.println(BLUE+"Dziekujemy i zapraszamy ponownie");
            System.exit(0);
        }else if("AGAIN".compareTo(odp.toUpperCase()) == 0 || "AGAIN".compareTo(odp.toLowerCase()) == 0){
            usunPrzedmiot(pomieszczenie,osoba);
        }
    }

    public void zapiszDoPliku(){

        FileWriter fw = null;
        try{
            fw = new FileWriter("stanMagazynu.txt");
            fw.write("Lista Osob: \n");
            fw.flush();
            for(Osoba osoba : listaOsob){
               fw.write(osoba.toString()+"\n");
               fw.flush();
            }
            fw.write("\n");

            fw.write("Lista pomieszczen: \n");
            fw.flush();
            for (Pomieszczenie pomieszczenie : magazyn.getMagazyn().keySet()){
                if(magazyn.getMagazyn().get(pomieszczenie) == null){
                    fw.write(pomieszczenie.toString()+ " nie nalezy do nikogo \n");
                }else{
                    fw.write(pomieszczenie.toString()+ " nalezy do " +magazyn.getMagazyn().get(pomieszczenie).getImie()+ " " +magazyn.getMagazyn().get(pomieszczenie).getNazwisko()+"\n");
                }
                fw.flush();
            }
            fw.write("\n");

            fw.write("Przedmioty: \n");
            fw.flush();
            for (Pomieszczenie pomieszczenie1 : magazyn.getMagazyn().keySet()){
                for (int i = 0; i < pomieszczenie1.getListaPrzedmiotow().size(); i++) {
                    fw.write("Pomieszczenie nr " +pomieszczenie1.getId()+ " " + pomieszczenie1.getListaPrzedmiotow().get(i).toString()+ "\n");
                    fw.flush();
                }
            }
            fw.close();
        }catch(IOException io){}

    }

}