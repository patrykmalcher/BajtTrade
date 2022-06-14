package pl.edu.mimuw.agenci.robotnicy.strategieNauki;

import java.util.HashMap;
import java.util.Map;

public class Pracuś extends StrategiaNauki {
  @Override
  public boolean czySięUczysz(double diamenty) {
    return false;
  }

  @Override
  public Map<String, Object> wypiszSię() {
    Map<String, Object> res = new HashMap<>();
    res.put("typ", "pracus");
    return res;
  }
}
