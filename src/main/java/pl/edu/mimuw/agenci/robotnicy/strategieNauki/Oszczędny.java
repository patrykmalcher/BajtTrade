package pl.edu.mimuw.agenci.robotnicy.strategieNauki;

import java.util.HashMap;
import java.util.Map;

public class Oszczędny extends StrategiaNauki {
  private double limitDiamentów;

  public Oszczędny(double limitDiamentów) {
    this.limitDiamentów = limitDiamentów;
  }

  @Override
  public boolean czySięUczysz(double diamenty) {
    return diamenty > limitDiamentów;
  }

  @Override
  public Map<String, Object> wypiszSię() {
    Map<String, Object> res = new HashMap<>();
    res.put("typ", "oszczedny");
    res.put("limit_diamentow", limitDiamentów);
    return res;
  } 
}
