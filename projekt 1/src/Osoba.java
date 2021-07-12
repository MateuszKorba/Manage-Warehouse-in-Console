import java.time.LocalDate;
import java.util.Date;

public class Osoba {

    private String login;
    private String imie;
    private String nazwisko;
    private String pesel;
    private String adresZamieszkania;
    private LocalDate dataUrodzenia;
    private LocalDate dataNajmu;

    public Osoba(String login, String imie, String nazwisko, String pesel, String adresZamieszkania, LocalDate dataUrodzenia, LocalDate dataNajmu) {
        this.login = login;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.pesel = pesel;
        this.adresZamieszkania = adresZamieszkania;
        this.dataUrodzenia = dataUrodzenia;
        this.dataNajmu = dataNajmu;
    }

    public void getDataNajmu() throws NeverRentException{
        if(this.dataNajmu == null){
            throw new NeverRentException();
        }
    }

    public String getLogin(){
        return login;
    }

    public String getImie(){
        return imie;
    }

    public String getNazwisko(){
        return nazwisko;
    }

    public String getAdresZamieszkania(){
        return adresZamieszkania;
    }

    public LocalDate getDataUrodzenia(){
        return dataUrodzenia;
    }

    public String getPesel(){
        return pesel;
    }

    @Override
    public String toString() {
        return imie + " " +nazwisko+ " " +adresZamieszkania+ " " +dataUrodzenia+ " " +dataUrodzenia;
    }
}
