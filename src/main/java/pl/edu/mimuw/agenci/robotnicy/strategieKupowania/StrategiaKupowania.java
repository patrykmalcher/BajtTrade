package pl.edu.mimuw.agenci.robotnicy.strategieKupowania;

import java.util.List;
import java.util.Map;

import pl.edu.mimuw.agenci.robotnicy.Robotnik;
import pl.edu.mimuw.transakcje.OfertaRobotnika;

public abstract class StrategiaKupowania {
  public abstract List<OfertaRobotnika> kupuj(Robotnik r);
  public abstract Map<String, Object> wypiszSię();
}
