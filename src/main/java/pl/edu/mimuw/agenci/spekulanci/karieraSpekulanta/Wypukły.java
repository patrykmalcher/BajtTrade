package pl.edu.mimuw.agenci.spekulanci.karieraSpekulanta;

import java.util.ArrayList;
import java.util.List;

import pl.edu.mimuw.agenci.spekulanci.Spekulant;
import pl.edu.mimuw.symulacja.Symulacja;
import pl.edu.mimuw.transakcje.OfertaSpekulanta;
import pl.edu.mimuw.zasoby.Zasób;

public class Wypukły extends KarieraSpekulanta {
  @Override
  public List<OfertaSpekulanta> sprzedaj(Spekulant spekulant) {
    List<OfertaSpekulanta> res = new ArrayList<>();

    for (Zasób zasób: spekulant.getZasoby().keySet()) {
      double cena = Symulacja.średniaCena(Symulacja.getTura() - 1, zasób);
      int tura = Symulacja.getTura();

      if (tura < 3) {
        for (int i = 0; i < spekulant.getZasoby().get(zasób).size(); i++)
          res.add(new OfertaSpekulanta(zasób, cena * 1.1, spekulant, i + 1, spekulant.getZasoby().get(zasób).get(i)));
      }
      else {
        double średnia1 = Symulacja.średniaCena(tura - 3, zasób);
        double średnia2 = Symulacja.średniaCena(tura - 2, zasób);
        double średnia3 = Symulacja.średniaCena(tura - 1, zasób);

        if ((średnia2 - średnia1) > (średnia3 - średnia2))
          for (int i = 0; i < spekulant.getZasoby().get(zasób).size(); i++)
            res.add(new OfertaSpekulanta(zasób, cena * 1.1, spekulant, i + 1, spekulant.getZasoby().get(zasób).get(i)));
      }
    }

    return res;
  }

  @Override
  public List<OfertaSpekulanta> kupuj(Spekulant spekulant) {
    List<OfertaSpekulanta> res = new ArrayList<>();

    for (Zasób zasób: spekulant.getZasoby().keySet()) {
      double cena = Symulacja.średniaCena(Symulacja.getTura() - 1, zasób);
      int tura = Symulacja.getTura();

      if (tura < 3) {
        for (int i = 0; i < spekulant.getZasoby().get(zasób).size(); i++)
          res.add(new OfertaSpekulanta(zasób, cena * 0.9, spekulant, i + 1, spekulant.getZasoby().get(zasób).get(i)));
      }
      else {
        double średnia1 = Symulacja.średniaCena(tura - 3, zasób);
        double średnia2 = Symulacja.średniaCena(tura - 2, zasób);
        double średnia3 = Symulacja.średniaCena(tura - 1, zasób);

        if ((średnia2 - średnia1) > (średnia3 - średnia2))
          for (int i = 0; i < spekulant.getZasoby().get(zasób).size(); i++)
            res.add(new OfertaSpekulanta(zasób, cena * 0.9, spekulant, i + 1, spekulant.getZasoby().get(zasób).get(i)));
      }
    }

    return res;
  }

  @Override
  public String toString() {
    return "wypukly";
  }
}
