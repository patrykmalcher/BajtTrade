package pl.edu.mimuw.agenci.robotnicy.strategieKupowania;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pl.edu.mimuw.agenci.robotnicy.Robotnik;
import pl.edu.mimuw.transakcje.OfertaRobotnika;
import pl.edu.mimuw.zasoby.Zasób;

public class Czyścioszek extends Technofob {
  @Override
  public List<OfertaRobotnika> kupuj(Robotnik r) {
    List<OfertaRobotnika> res = super.kupuj(r);

    if (r.ileUbrańPoTurze() < 100)
    res.add(new OfertaRobotnika(Zasób.Jedzenie, 100 - r.ileUbrańPoTurze(), r, 0));
    return res;
  }

  @Override
  public Map<String, Object> wypiszSię() {
    Map<String, Object> res = new HashMap<>();
    res.put("typ", "czyscioszek");
    return res;
  }
}
