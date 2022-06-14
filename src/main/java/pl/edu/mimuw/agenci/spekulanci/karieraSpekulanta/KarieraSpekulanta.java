package pl.edu.mimuw.agenci.spekulanci.karieraSpekulanta;

import java.util.List;

import pl.edu.mimuw.agenci.spekulanci.Spekulant;
import pl.edu.mimuw.transakcje.OfertaSpekulanta;

public abstract class KarieraSpekulanta {
  public abstract List<OfertaSpekulanta> sprzedaj(Spekulant spekulant);

  public abstract List<OfertaSpekulanta> kupuj(Spekulant spekulant);

  @Override
  public abstract String toString();
}
