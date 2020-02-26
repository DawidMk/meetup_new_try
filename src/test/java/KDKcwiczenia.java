public class KDKcwiczenia {
    public static void main(String[] args) {

        String jakisCiagZnakow = "jeden";
        Integer liczba = null;
        try {
            liczba = Integer.valueOf(jakisCiagZnakow);
//        } catch (NumberFormatException nfe) {
//            System.out.println("Wyjątek - zły format liczby");
        } catch (Exception e) {
            System.out.println("Wyjątek, ale jakiś inny");
        }

    }}
