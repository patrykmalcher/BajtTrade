package pl.edu.mimuw.agenci.spekulanci.karieraSpekulanta;

import java.util.ArrayList;
import java.util.List;

import pl.edu.mimuw.agenci.spekulanci.Spekulant;
import pl.edu.mimuw.symulacja.Symulacja;
import pl.edu.mimuw.transakcje.OfertaSpekulanta;
import pl.edu.mimuw.zasoby.Zasób;

public class Średni extends KarieraSpekulanta {
  private int historiaSpekulantaŚredniego;

  @Override
  public List<OfertaSpekulanta> sprzedaj(Spekulant spekulant) {
    List<OfertaSpekulanta> res = new ArrayList<>();

    for (Zasób zasób: spekulant.getZasoby().keySet()) {
      double ceny =  0;
      int ile = 0;

      for (int i = Symulacja.getTura() - 1; i >= Math.max(Symulacja.getTura() - historiaSpekulantaŚredniego, 0); i--) {
        ile ++;
        ceny += Symulacja.średniaCena(i, zasób);
      }

      for (int i = 0; i < spekulant.getZasoby().get(zasób).size(); i++) {
        res.add(new OfertaSpekulanta(zasób, ceny / ile * 1.1, spekulant, i + 1, spekulant.getZasoby().get(zasób).get(i)));
      }
    }

    return res;
  }

  @Override
  public List<OfertaSpekulanta> kupuj(Spekulant spekulant) {
    List<OfertaSpekulanta> res = new ArrayList<>();

    for (Zasób zasób: spekulant.getZasoby().keySet()) {
      double ceny =  0;
      int ile = 0;

      for (int i = Symulacja.getTura() - 1; i >= Math.max(Symulacja.getTura() - historiaSpekulantaŚredniego, 0); i--) {
        ile ++;
        ceny += Symulacja.średniaCena(i, zasób);
      }

      for (int i = 0; i < spekulant.getZasoby().get(zasób).size(); i++) {
        if (spekulant.getZasoby().get(zasób).get(i) > 0)
          res.add(new OfertaSpekulanta(zasób, ceny / ile * 0.9, spekulant, i + 1, spekulant.getZasoby().get(zasób).get(i)));
        else 
        res.add(new OfertaSpekulanta(zasób, ceny / ile * 0.95, spekulant, i + 1, spekulant.getZasoby().get(zasób).get(i)));
      }
    }

    return res;
  }

  @Override
  public String toString() {
    return "sredni";
  }
}
