public class NeverRentException extends Exception{

    private static final String ANSI_RED = "\u001B[31m"; // biblioteka do koloru (ustwianie koloru)
    public static final String ANSI_RESET = "\u001B[0m"; // przywrocenie koloru

    public NeverRentException () {
        System.out.println(ANSI_RED+"    Brak daty najmu"+ANSI_RESET);
    }

}
