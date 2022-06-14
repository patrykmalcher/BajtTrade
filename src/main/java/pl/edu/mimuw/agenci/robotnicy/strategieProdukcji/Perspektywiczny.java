package pl.edu.mimuw.agenci.robotnicy.strategieProdukcji;

import java.util.HashMap;
import java.util.Map;

import pl.edu.mimuw.agenci.robotnicy.Robotnik;
import pl.edu.mimuw.symulacja.Symulacja;
import pl.edu.mimuw.zasoby.Zasób;

public class Perspektywiczny extends StrategiaProdukcji {
  private int historiaPerspektywy;

  public Perspektywiczny(int historiaPerspektywy) {
    this.historiaPerspektywy = historiaPerspektywy;
  }

  @Override
  public Zasób coProdukujesz(Robotnik r) {
    int tura = Symulacja.getTura();

    double różnicaJedzenia = Symulacja.średniaCena(tura - 1, Zasób.Jedzenie);
    double różnicaUbrań = Symulacja.średniaCena(tura - 1, Zasób.Ubrania);
    double róznicaNarzedzi = Symulacja.średniaCena(tura - 1, Zasób.Narzędzia);
    double różnicaProgramów = Symulacja.średniaCena(tura - 1, Zasób.Programy);

    różnicaJedzenia -= Symulacja.średniaCena(Math.max(0, tura - historiaPerspektywy), Zasób.Jedzenie);
    różnicaUbrań -= Symulacja.średniaCena(Math.max(0, tura - historiaPerspektywy), Zasób.Ubrania);
    róznicaNarzedzi -= Symulacja.średniaCena(Math.max(0, tura - historiaPerspektywy), Zasób.Narzędzia);
    różnicaProgramów -= Symulacja.średniaCena(Math.max(0, tura - historiaPerspektywy), Zasób.Programy);

    double max = Math.max(różnicaJedzenia, różnicaUbrań);
    max = Math.max(max, róznicaNarzedzi);
    max = Math.max(max, różnicaProgramów);

    if (max == różnicaProgramów)
      return Zasób.Programy;
    if (max == róznicaNarzedzi)
      return Zasób.Narzędzia;
    if (max == różnicaUbrań)
      return Zasób.Ubrania;

    return Zasób.Jedzenie;
  }

  @Override
  public Map<String, Object> wypiszSię() {
    Map<String, Object> res = new HashMap<>();
    res.put("typ", "perspektywiczny");
    res.put("historia_perspektywy", historiaPerspektywy);
    return res;
  }
}
