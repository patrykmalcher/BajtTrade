package pl.edu.mimuw.agenci.robotnicy.strategieKariery;

import pl.edu.mimuw.symulacja.Symulacja;
import pl.edu.mimuw.zasoby.Zasób;

public class Rewolucjonista extends StrategiaKariery {
  @Override
  public KarieraRobotnika zmieńKarierę(KarieraRobotnika kariera, int id) {
    if (Symulacja.getTura() % 7 != 0)
      return kariera;

    int n = Math.max(1, id % 17);

    int ileJedzenia = 0;
    int ileUbrań = 0;
    int ileNarzędzi = 0;
    int ileProgramów = 0;

    int tura = Symulacja.getTura();

    for (int i = tura - 1; i >= Math.max(0, tura - n); i--) {
      ileJedzenia += Symulacja.ilośćOfert(i, Zasób.Jedzenie);
      ileUbrań += Symulacja.ilośćOfert(i, Zasób.Ubrania);
      ileNarzędzi += Symulacja.ilośćOfert(i, Zasób.Narzędzia);
      ileProgramów += Symulacja.ilośćOfert(i, Zasób.Programy);
    }

    int max = Math.max(ileJedzenia, ileUbrań);
    max = Math.max(max, ileNarzędzi);
    max = Math.max(max, ileProgramów);

    if (max == ileProgramów)
      return KarieraRobotnika.Programista;
    if (max == ileNarzędzi)
      return KarieraRobotnika.Inżynier;
    if (max == ileUbrań)
      return KarieraRobotnika.Rzemieślnik;

    return KarieraRobotnika.Rolnik;
  }

  @Override
  public String toString() {
    return "rewolucjonista";
  }
}
