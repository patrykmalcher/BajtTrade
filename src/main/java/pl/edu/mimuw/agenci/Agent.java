package pl.edu.mimuw.agenci;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pl.edu.mimuw.input.RobotnikInput;
import pl.edu.mimuw.input.SpekulantInput;
import pl.edu.mimuw.zasoby.Zasób;

public abstract class Agent {
  private int id;
  private double diamenty;
  private Map<Zasób, List<Integer>> zasoby;

  public Agent(RobotnikInput input) {
    this.id = input.id;
    this.diamenty = input.zasoby.get("diamenty");
    zasoby = new HashMap<>();

    zasoby.put(Zasób.Ubrania, new ArrayList<Integer>());
    zasoby.get(Zasób.Ubrania).add(input.zasoby.get("ubrania"));

    zasoby.put(Zasób.Narzędzia, new ArrayList<Integer>());
    zasoby.get(Zasób.Narzędzia).add(input.zasoby.get("narzedzia"));

    zasoby.put(Zasób.Jedzenie, new ArrayList<Integer>());
    zasoby.get(Zasób.Jedzenie).add(input.zasoby.get("jedzenie"));

    zasoby.put(Zasób.Programy, new ArrayList<Integer>());
    zasoby.get(Zasób.Programy).add(input.zasoby.get("programy"));
  }

  public Agent(SpekulantInput input) {
    this.id = input.id;
    this.diamenty = input.zasoby.get("diamenty");
    zasoby = new HashMap<>();

    zasoby.put(Zasób.Ubrania, new ArrayList<Integer>());
    zasoby.get(Zasób.Ubrania).add(input.zasoby.get("ubrania"));

    zasoby.put(Zasób.Narzędzia, new ArrayList<Integer>());
    zasoby.get(Zasób.Narzędzia).add(input.zasoby.get("narzedzia"));

    zasoby.put(Zasób.Jedzenie, new ArrayList<Integer>());
    zasoby.get(Zasób.Jedzenie).add(input.zasoby.get("jedzenie"));

    zasoby.put(Zasób.Programy, new ArrayList<Integer>());
    zasoby.get(Zasób.Programy).add(input.zasoby.get("programy"));
  }

  public double getDiamenty() {
    return diamenty;
  }

  public int getId() {
    return id;
  }

  public Map<Zasób, List<Integer>> getZasoby() {
    return zasoby;
  }

  public void setDiamenty(double x) {
    diamenty = x;
  }

  public void dodajZasób(Zasób zasób, int poziom, int ilość) {
    List<Integer> lista = zasoby.get(zasób);

    while (lista.size() < poziom)
      lista.add(0);

    lista.set(poziom - 1, lista.get(poziom - 1) + ilość);
  }
}
