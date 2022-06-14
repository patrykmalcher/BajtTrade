package pl.edu.mimuw.agenci.robotnicy.strategieNauki;

import java.util.Map;

public abstract class StrategiaNauki {
  public abstract boolean czySięUczysz(double diamenty);

  // Wykorzystywana do wypisywania do pliku JSON.
  public abstract Map<String, Object> wypiszSię();
}
