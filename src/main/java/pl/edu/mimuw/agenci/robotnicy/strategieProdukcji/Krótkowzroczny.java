package pl.edu.mimuw.agenci.robotnicy.strategieProdukcji;

import java.util.HashMap;
import java.util.Map;

import pl.edu.mimuw.agenci.robotnicy.Robotnik;
import pl.edu.mimuw.symulacja.Symulacja;
import pl.edu.mimuw.zasoby.Zasób;

public class Krótkowzroczny extends StrategiaProdukcji {
  @Override
  public Zasób coProdukujesz(Robotnik r) {
    int tura = Symulacja.getTura();

    double cenaJedzenia = Symulacja.średniaCena(tura, Zasób.Jedzenie);
    double cenaUbrań = Symulacja.średniaCena(tura, Zasób.Ubrania);
    double cenaNarzędzi = Symulacja.średniaCena(tura, Zasób.Narzędzia);
    double cenaProgramów = Symulacja.średniaCena(tura, Zasób.Programy);

    double max = Math.max(cenaJedzenia, cenaUbrań);
    max = Math.max(max, cenaNarzędzi);
    max = Math.max(max, cenaProgramów);

    if (cenaProgramów == max)
      return Zasób.Programy;
    if (cenaNarzędzi == max)
      return Zasób.Narzędzia;
    if (cenaUbrań == max)
      return Zasób.Ubrania;

    return Zasób.Jedzenie;
  }

  @Override
  public Map<String, Object> wypiszSię() {
    Map<String, Object> res = new HashMap<>();
    res.put("typ", "krotkowzroczny");
    return res;
  }
}
