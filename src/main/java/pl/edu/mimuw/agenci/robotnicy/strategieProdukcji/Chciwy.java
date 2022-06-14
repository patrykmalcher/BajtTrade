package pl.edu.mimuw.agenci.robotnicy.strategieProdukcji;

import java.util.HashMap;
import java.util.Map;

import pl.edu.mimuw.agenci.robotnicy.Robotnik;
import pl.edu.mimuw.symulacja.Symulacja;
import pl.edu.mimuw.zasoby.Zasób;

public class Chciwy extends StrategiaProdukcji {
  @Override
  public Zasób coProdukujesz(Robotnik r) {
    int tura = Symulacja.getTura();

    double zyskZJedzenia = Symulacja.średniaCena(tura - 1, Zasób.Jedzenie) 
      * r.ileWyprodukuje(Zasób.Jedzenie);
    double zyskZUbrań = Symulacja.średniaCena(tura - 1, Zasób.Ubrania)
      * r.ileWyprodukuje(Zasób.Ubrania);
    double zyskZNarzędzi = Symulacja.średniaCena(tura - 1, Zasób.Narzędzia)
      * r.ileWyprodukuje(Zasób.Narzędzia);
    double zyskZProgramów = Symulacja.średniaCena(tura - 1, Zasób.Programy)
      * r. ileWyprodukuje(Zasób.Programy);

    double max = Math.max(zyskZJedzenia, zyskZUbrań);
    max = Math.max(max, zyskZNarzędzi);
    max = Math.max(max, zyskZProgramów);

    if (max == zyskZProgramów)
      return Zasób.Programy;
    if (max == zyskZNarzędzi)
      return Zasób.Narzędzia;
    if (max == zyskZUbrań)
      return Zasób.Ubrania;
   
    return Zasób.Jedzenie;
  }

  @Override
  public Map<String, Object> wypiszSię() {
    Map<String, Object> res = new HashMap<>();
    res.put("typ", "chciwy");
    return res;
  }
}
