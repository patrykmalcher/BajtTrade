package pl.edu.mimuw.agenci.spekulanci.karieraSpekulanta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pl.edu.mimuw.agenci.spekulanci.Spekulant;
import pl.edu.mimuw.symulacja.Symulacja;
import pl.edu.mimuw.transakcje.OfertaSpekulanta;
import pl.edu.mimuw.zasoby.Zasób;

public class Regulujący extends KarieraSpekulanta {
  @Override
  public List<OfertaSpekulanta> sprzedaj(Spekulant spekulant) {
    List<OfertaSpekulanta> res = new ArrayList<>();

    int tura = Symulacja.getTura();

    if (tura == 1)
      return res;

    for (Zasób zasób: spekulant.getZasoby().keySet()) {
      double ceny = (double) Symulacja.ilośćOfert(tura - 1, zasób);
      ceny /= Math.max(Symulacja.średniaCena(tura - 2, zasób), 1);

      for (int i = 0; i < spekulant.getZasoby().get(zasób).size(); i++) {
        res.add(new OfertaSpekulanta(zasób, ceny * 1.1, spekulant, i + 1, spekulant.getZasoby().get(zasób).get(i)));
      }
    }

    return res;
  }

  @Override
  public List<OfertaSpekulanta> kupuj(Spekulant spekulant) {
    List<OfertaSpekulanta> res = new ArrayList<>();

    int tura = Symulacja.getTura();

    if (tura == 1)
      return res;

    for (Zasób zasób: spekulant.getZasoby().keySet()) {
      double ceny = (double) Symulacja.ilośćOfert(tura - 1, zasób);
      ceny /= Math.max(Symulacja.średniaCena(tura - 2, zasób), 1);

      for (int i = 0; i < spekulant.getZasoby().get(zasób).size(); i++) {
        res.add(new OfertaSpekulanta(zasób, ceny * 0.9, spekulant, i + 1, spekulant.getZasoby().get(zasób).get(i)));
      }
    }

    return res;
  }

  @Override
  public Map<String, Object> wypiszSię() {
    Map<String, Object> res = new HashMap<>();
    res.put("typ", "regulujacy_rynek");
    return res;
  }
}
