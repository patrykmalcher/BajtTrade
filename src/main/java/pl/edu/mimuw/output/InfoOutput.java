package pl.edu.mimuw.output;

import java.util.HashMap;
import java.util.Map;

import pl.edu.mimuw.symulacja.Symulacja;
import pl.edu.mimuw.zasoby.Zasób;

// Klasa wykorzystywana do wypisania do pliku JSON.
@SuppressWarnings("unused") // Moshi korzysta!!
public class InfoOutput {
  private int dzien;
  private Map<String, Double> ceny_srednie;
  private Map<String, Double> ceny_max;
  private Map<String, Double> ceny_min;

  public InfoOutput(Symulacja symulacja) {
    dzien = Symulacja.getTura() - 1;
    
    Map<Zasób, Double> średnie = symulacja.średniaCenaZOstatniegoDnia();

    ceny_srednie = new HashMap<>();
    ceny_srednie.put("programy", średnie.get(Zasób.Programy));
    ceny_srednie.put("jedzenie", średnie.get(Zasób.Jedzenie));
    ceny_srednie.put("ubrania", średnie.get(Zasób.Ubrania));
    ceny_srednie.put("narzedzia", średnie.get(Zasób.Narzędzia));

    Map<Zasób, Double> minimalne = symulacja.getMinimalneCeny();

    ceny_min = new HashMap<>();
    ceny_min.put("programy", minimalne.get(Zasób.Programy));
    ceny_min.put("jedzenie", minimalne.get(Zasób.Jedzenie));
    ceny_min.put("ubrania", minimalne.get(Zasób.Ubrania));
    ceny_min.put("narzedzia", minimalne.get(Zasób.Narzędzia));

    Map<Zasób, Double> maksymalne = symulacja.getMaksymalneCeny();

    ceny_max = new HashMap<>();
    ceny_max.put("programy", maksymalne.get(Zasób.Programy));
    ceny_max.put("jedzenie", maksymalne.get(Zasób.Jedzenie));
    ceny_max.put("ubrania", maksymalne.get(Zasób.Ubrania));
    ceny_max.put("narzedzia", maksymalne.get(Zasób.Narzędzia));
  }
}
