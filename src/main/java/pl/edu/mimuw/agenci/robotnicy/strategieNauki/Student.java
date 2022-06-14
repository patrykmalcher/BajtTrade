package pl.edu.mimuw.agenci.robotnicy.strategieNauki;

import java.util.HashMap;
import java.util.Map;

import pl.edu.mimuw.symulacja.Symulacja;
import pl.edu.mimuw.zasoby.Zasób;

public class Student extends StrategiaNauki {
  private int zapas;
  private int okres;

  public Student(int zapas, int okres) {
    this.zapas = zapas;
    this.okres = okres;
  }

  @Override
  public boolean czySięUczysz(double diamenty) {
    double ceny = 0;
    int ile = 0;
    int tura = Symulacja.getTura();

    for (int i = tura - 1; i >= Math.max(0, tura - okres); i--) {
      ile ++;
      ceny += Symulacja.średniaCena(i, Zasób.Jedzenie);
    }

    double średnia = ceny / ile;

    return diamenty >= średnia * 100 * zapas;
  }

  @Override
  public Map<String, Object> wypiszSię() {
    Map<String, Object> res = new HashMap<>();
    res.put("typ", "student");
    res.put("zapas", zapas);
    res.put("okres", okres);
    return res;
  }
}
