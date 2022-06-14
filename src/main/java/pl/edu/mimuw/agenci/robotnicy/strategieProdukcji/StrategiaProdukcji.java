package pl.edu.mimuw.agenci.robotnicy.strategieProdukcji;

import java.util.Map;

import pl.edu.mimuw.agenci.robotnicy.Robotnik;
import pl.edu.mimuw.zasoby.Zasób;

public abstract class StrategiaProdukcji {
  public abstract Zasób coProdukujesz(Robotnik r); 

  public abstract Map<String, Object> wypiszSię();
}
