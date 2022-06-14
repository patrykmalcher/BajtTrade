package pl.edu.mimuw.agenci.robotnicy.strategieProdukcji;

import java.util.HashMap;
import java.util.Map;

import pl.edu.mimuw.agenci.robotnicy.Robotnik;
import pl.edu.mimuw.zasoby.Zasób;

public class Losowy extends StrategiaProdukcji {
  @Override
  public Zasób coProdukujesz(Robotnik r) {
    double ran = Math.random();

    if (ran <= 0.2)
      return Zasób.Jedzenie;
    if (ran <= 0.4)
      return Zasób.Ubrania;
    if (ran <= 0.6)
      return Zasób.Narzędzia;
    if (ran <= 0.8)
      return Zasób.Diamenty;

    return Zasób.Programy;
  }

  @Override
  public Map<String, Object> wypiszSię() {
    Map<String, Object> res = new HashMap<>();
    res.put("typ", "losowy");
    return res;
  }
}
