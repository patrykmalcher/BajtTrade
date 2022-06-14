package pl.edu.mimuw.agenci.robotnicy.strategieNauki;

import java.util.HashMap;
import java.util.Map;

import pl.edu.mimuw.symulacja.Symulacja;

public class Okresowy extends StrategiaNauki {
  private int okresowośćNauki;

  public Okresowy(int okresowośćNauki) {
    this.okresowośćNauki = okresowośćNauki;
  }

  @Override
  public boolean czySięUczysz(double diamenty) {
    return Symulacja.getTura() % okresowośćNauki == 0;
  }

  @Override
  public Map<String, Object> wypiszSię() {
    Map<String, Object> res = new HashMap<>();
    res.put("typ", "okresowy");
    res.put("okresowosc_nauki", okresowośćNauki);
    return res;
  } 
}
