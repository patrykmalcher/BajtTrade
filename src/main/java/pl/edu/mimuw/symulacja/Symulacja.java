package pl.edu.mimuw.symulacja;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pl.edu.mimuw.agenci.robotnicy.Robotnik;
import pl.edu.mimuw.agenci.spekulanci.Spekulant;
import pl.edu.mimuw.input.RobotnikInput;
import pl.edu.mimuw.input.SpekulantInput;
import pl.edu.mimuw.input.SymulacjaInput;
import pl.edu.mimuw.transakcje.OfertaRobotnika;
import pl.edu.mimuw.transakcje.OfertaSpekulanta;
import pl.edu.mimuw.zasoby.Zasób;

public class Symulacja {
  private Info info;
  private List<Robotnik> robotnicy;
  private List<Spekulant> spekulanci;

  // Zmienne i funkcje statyczne:
  // Jest tylko jedna symulacja, chemy mieć
  // ułatwiony dostęp do ważnych informacji 
  // z dowolnego miejsca programu.

  private static int tura;

  // Lista średnich cen z każdej tury.
  private static List<Map<Zasób, Double>> średnieCeny;

  // Lista łącznej ilości ofert z każdej tury.
  private static List<Map<Zasób, Integer>> ilośćOfert;

  // Minimalne i maksymalne ceny osiągnięte dla każdego zasobu.
  private Map<Zasób, Double> minimalneCeny;
  private Map<Zasób, Double> maksymalneCeny;

  private Map<Zasób, Double> cenyZAktualnegoDnia;
  private Map<Zasób, Integer> ilośćZAktualnegoDnia;

  public Symulacja(SymulacjaInput input) {
    info = new Info(input.info);

    robotnicy = new ArrayList<>();

    for (RobotnikInput i: input.robotnicy)
      robotnicy.add(new Robotnik(i));

    spekulanci = new ArrayList<>();

    for (SpekulantInput i: input.spekulanci)
      spekulanci.add(new Spekulant(i));

    tura = 1;
    średnieCeny = new ArrayList<>();
    średnieCeny.add(info.getCeny());

    ilośćOfert = new ArrayList<>();
    ilośćOfert.add(new HashMap<Zasób, Integer>());

    for (Zasób i: Zasób.values())
      if (i != Zasób.Diamenty)
        ilośćOfert.get(0).put(i, 1);

    minimalneCeny = new HashMap<>(średnieCeny.get(0));
    maksymalneCeny = new HashMap<>(średnieCeny.get(0));

    ilośćZAktualnegoDnia = new HashMap<>();
    cenyZAktualnegoDnia = new HashMap<>();

    for (Zasób i: ilośćOfert.get(0).keySet()) {
      ilośćZAktualnegoDnia.put(i, 0);
      cenyZAktualnegoDnia.put(i, 0.0);
    }
  }

  public void wykonajTurę() {
    List<OfertaRobotnika> ofertySprzedażyRobotników = new ArrayList<>();
    List<OfertaRobotnika> ofertyKupnaRobotników = new ArrayList<>();
    List<OfertaSpekulanta> ofertySprzedażySpekulantów = new ArrayList<>();
    List<OfertaSpekulanta> ofertyKupnaSpekulantów = new ArrayList<>();

    for (Robotnik i: robotnicy) {
      boolean czy = i.czySięUczysz();

      if (czy) {
        i.uczSię();
      }
      else {
        // Jeśli robotnik nie ma wystarczająco jedzenia na początku
        // rundy ponosi konsekwencje.
        if (i.getZasoby().get(Zasób.Jedzenie).get(0) < 100)
          i.zwiększDniBezJedzenia();
        
        List<OfertaRobotnika> sprzedaż = i.produkuj();
        ofertySprzedażyRobotników.addAll(sprzedaż);

        List<OfertaRobotnika> kupno = i.kupuj();
        ofertyKupnaRobotników.addAll(kupno);

        // Robotnik jak pracuje traci jedzenie!
        i.dodajZasób(Zasób.Jedzenie, 1, -100);
        // i zużywa ubrania!
        i.zużyjUbrania();
        // i wszystkie narzędzia.
        i.utraćNarzędzia();
      }
    }

    Collections.sort(ofertySprzedażyRobotników);
    Collections.sort(ofertyKupnaRobotników);

    for (Spekulant i: spekulanci) {
      List<OfertaSpekulanta> sprzedaż = i.sprzedaj();
      ofertySprzedażySpekulantów.addAll(sprzedaż);

      List<OfertaSpekulanta> kupno = i.kupuj();
      ofertyKupnaSpekulantów.addAll(kupno);
    }

    Collections.sort(ofertySprzedażySpekulantów);
    Collections.sort(ofertyKupnaSpekulantów);

    boolean skąd = info.getGiełda().skąd();

    if (skąd == true) {
      Collections.reverse(ofertyKupnaRobotników);
      Collections.reverse(ofertySprzedażyRobotników);
    }

    // Dopasowanie ofert sprzedaży robotników z ofertami 
    // kupna spekulantów.
    for (OfertaRobotnika i: ofertySprzedażyRobotników) {
      for (OfertaSpekulanta j: ofertyKupnaSpekulantów) {
        if (i.getZasób() == j.getZasób() &&
            i.getPoziom() == j.getPoziom()) {
          int ileSprzeda = Math.min(i.getIlość(), j.getIlość());
          int x = (int) Math.floor(j.getSpekulant().getDiamenty() / j.getCena());
          ileSprzeda = Math.min(ileSprzeda, x); 

          if (ileSprzeda == 0)
            continue;

          minimalneCeny.put(j.getZasób(), Math.min(minimalneCeny.get(j.getZasób()), j.getCena()));
          maksymalneCeny.put(j.getZasób(), Math.max(maksymalneCeny.get(j.getZasób()), j.getCena()));

          double zaIleSprzeda = j.getCena() * ileSprzeda;
          cenyZAktualnegoDnia.put(j.getZasób(), cenyZAktualnegoDnia.get(j.getZasób()) + zaIleSprzeda);
          ilośćZAktualnegoDnia.put(j.getZasób(), ilośćZAktualnegoDnia.get(j.getZasób()) + ileSprzeda);

          i.getRobotnik().setDiamenty(i.getRobotnik().getDiamenty() + zaIleSprzeda);
          j.getSpekulant().setDiamenty(j.getSpekulant().getDiamenty() - zaIleSprzeda);
          j.getSpekulant().dodajZasób(j.getZasób(), j.getPoziom(), ileSprzeda);
        }
      }
    }

    // Dopasowanie ofert kupna robotników z ofertami 
    // sprzedaży spekulantów.
    for (OfertaRobotnika i: ofertyKupnaRobotników) {
      for (OfertaSpekulanta j: ofertySprzedażySpekulantów) {
        if (i.getZasób() == j.getZasób()) {
          int ileKupi = Math.min(i.getIlość(), j.getIlość());
          int x = (int) Math.floor(i.getRobotnik().getDiamenty() / j.getCena());
          ileKupi = Math.min(x, ileKupi);

          if (ileKupi == 0)
            continue;

          minimalneCeny.put(j.getZasób(), Math.min(minimalneCeny.get(j.getZasób()), j.getCena()));
          maksymalneCeny.put(j.getZasób(), Math.max(maksymalneCeny.get(j.getZasób()), j.getCena()));

          double zaIleKupi = j.getCena() * ileKupi;
          cenyZAktualnegoDnia.put(j.getZasób(), cenyZAktualnegoDnia.get(j.getZasób()) + zaIleKupi);
          ilośćZAktualnegoDnia.put(j.getZasób(), ilośćZAktualnegoDnia.get(j.getZasób()) + ileKupi);

          i.getRobotnik().setDiamenty(i.getRobotnik().getDiamenty() - zaIleKupi);
          j.getSpekulant().setDiamenty(j.getSpekulant().getDiamenty() + zaIleKupi);
          i.getRobotnik().dodajZasób(j.getZasób(), j.getPoziom(), ileKupi);
          j.getSpekulant().dodajZasób(j.getZasób(), j.getPoziom(), -ileKupi);
        }
      }
    }

    // Skup giełdy.
    for (OfertaRobotnika i: ofertySprzedażyRobotników) {
      double średnia = średniaCena(tura - 1, i.getZasób());

      i.getRobotnik().setDiamenty(i.getRobotnik().getDiamenty() + średnia * i.getIlość());
    }
  }

  public static int getTura() {
    return tura;
  }

  // Średnia cena zasobu zasób z tury tura.
  public static double średniaCena(int tura, Zasób zasób) {
    return średnieCeny.get(tura).get(zasób);
  }

  public static int ilośćOfert(int tura, Zasób zasób) {
    return ilośćOfert.get(tura).get(zasób);
  }

  public void rozegrajSymulację() {
    for (int i = 0; i < info.getDługość(); i++) {
      wykonajTurę();
      // Trzeba zaaktualizować średnie ceny!
      średnieCeny.add(new HashMap<Zasób, Double>());

      for (Zasób j: ilośćZAktualnegoDnia.keySet()) {
        if (ilośćZAktualnegoDnia.get(j) > 0)
          średnieCeny.get(tura).put(j, cenyZAktualnegoDnia.get(j) / ilośćZAktualnegoDnia.get(j));
        else 
          // Ofert nie było - średnia cena to cena z dnia zerowego.
          średnieCeny.get(tura).put(j, średniaCena(0, j));
      }

      for (Zasób j: ilośćZAktualnegoDnia.keySet()) {
        ilośćZAktualnegoDnia.put(j, 0);
        cenyZAktualnegoDnia.put(j, 0.0);
      }

      tura ++;
    }
  }

  public Map<Zasób, Double> średniaCenaZOstatniegoDnia() {
    return średnieCeny.get(tura - 1);
  }

  public Map<Zasób, Double> getMinimalneCeny() {
    return minimalneCeny;
  }
  
  public Map<Zasób, Double> getMaksymalneCeny() {
    return maksymalneCeny;
  }

  public List<Robotnik> getRobotnicy() {
    return robotnicy;
  }

  public List<Spekulant> getSpekulanci() {
    return spekulanci;
  }
}
