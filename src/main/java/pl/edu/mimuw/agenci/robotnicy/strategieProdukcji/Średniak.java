package pl.edu.mimuw.agenci.robotnicy.strategieProdukcji;

import java.util.HashMap;
import java.util.Map;

import pl.edu.mimuw.agenci.robotnicy.Robotnik;
import pl.edu.mimuw.symulacja.Symulacja;
import pl.edu.mimuw.zasoby.Zasób;

public class Średniak extends StrategiaProdukcji {
  private int historiaŚredniejProdukcji;

  public Średniak(int historiaŚredniejProdukcji) {
    this.historiaŚredniejProdukcji = historiaŚredniejProdukcji;
  }

  @Override
  public Zasób coProdukujesz(Robotnik r) {
    double maxJedzenia = 0;
    double maxUbrań = 0;
    double maxNarzędzi = 0;
    double maxProgramów = 0;

    int tura = Symulacja.getTura();

    for (int i = tura - 1; i >= Math.max(0, tura - historiaŚredniejProdukcji); i--) {
      maxJedzenia = Math.max(maxJedzenia, Symulacja.średniaCena(i, Zasób.Jedzenie));
      maxUbrań = Math.max(maxUbrań, Symulacja.średniaCena(i, Zasób.Ubrania));
      maxNarzędzi = Math.max(maxNarzędzi, Symulacja.średniaCena(i, Zasób.Narzędzia));
      maxProgramów = Math.max(maxProgramów, Symulacja.średniaCena(i, Zasób.Programy));
    }

    double max = Math.max(maxJedzenia, maxUbrań);
    max = Math.max(max, maxNarzędzi);
    max = Math.max(max, maxProgramów);

    if (max == maxProgramów)
      return Zasób.Programy;
    if (max == maxNarzędzi)
      return Zasób.Narzędzia;
    if (max == maxUbrań)
      return Zasób.Ubrania;

    return Zasób.Jedzenie;
  }

  @Override
  public Map<String, Object> wypiszSię() {
    Map<String, Object> res = new HashMap<>();
    res.put("typ", "sredniak");
    res.put("historia_sredniej_produkcji", historiaŚredniejProdukcji);
    return res;
  }    
}
