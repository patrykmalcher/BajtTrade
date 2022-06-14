package pl.edu.mimuw.agenci.robotnicy.strategieNauki;

import java.util.HashMap;
import java.util.Map;

import pl.edu.mimuw.symulacja.Symulacja;

public class Rozkładowy extends StrategiaNauki {
  @Override
  public boolean czySięUczysz(double diamenty) {
    double r = Math.random();

    return r <= (double) 1 / (Symulacja.getTura() + 3);
  }

  @Override
  public Map<String, Object> wypiszSię() {
    Map<String, Object> res = new HashMap<>();
    res.put("typ", "rozkladowy");
    return res;
  }
}
