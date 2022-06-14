package pl.edu.mimuw.agenci.robotnicy.strategieKupowania;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pl.edu.mimuw.agenci.robotnicy.Robotnik;
import pl.edu.mimuw.transakcje.OfertaRobotnika;
import pl.edu.mimuw.zasoby.Zasób;

public class Zmechanizowany extends Czyścioszek {
  protected int liczbaNarzędzi;

  public Zmechanizowany(int liczbaNarzędzi) {
    this.liczbaNarzędzi = liczbaNarzędzi;
  }
  
  @Override
  public List<OfertaRobotnika> kupuj(Robotnik r) {
    List<OfertaRobotnika> res = super.kupuj(r);

    res.add(new OfertaRobotnika(Zasób.Narzędzia, liczbaNarzędzi, r, 0));
    return res;
  } 

  @Override
  public Map<String, Object> wypiszSię() {
    Map<String, Object> res = new HashMap<>();
    res.put("typ", "zmechanizowany");
    res.put("liczba_narzedzi", liczbaNarzędzi);
    return res;
  }
}
