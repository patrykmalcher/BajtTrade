package pl.edu.mimuw.agenci.spekulanci;

import java.util.List;

import pl.edu.mimuw.agenci.Agent;
import pl.edu.mimuw.agenci.spekulanci.karieraSpekulanta.KarieraSpekulanta;
import pl.edu.mimuw.agenci.spekulanci.karieraSpekulanta.Regulujący;
import pl.edu.mimuw.agenci.spekulanci.karieraSpekulanta.Wypukły;
import pl.edu.mimuw.agenci.spekulanci.karieraSpekulanta.Średni;
import pl.edu.mimuw.input.SpekulantInput;
import pl.edu.mimuw.transakcje.OfertaSpekulanta;

public class Spekulant extends Agent {
  private KarieraSpekulanta kariera;

  public Spekulant(SpekulantInput input) {
    super(input);

    switch ((String) input.kariera.get("typ")) {
      case "regulujacy_rynek": kariera = new Regulujący(); break;
      case "sredni": kariera = new Średni(((Double) input.kariera.get("historia_spekulanta_sredniego")).intValue()); break;
      case "wypukly": kariera = new Wypukły(); break;
      default: kariera = null;
    }
  }

  public List<OfertaSpekulanta> sprzedaj() {
    return kariera.sprzedaj(this);
  }

  public List<OfertaSpekulanta> kupuj() {
    return kariera.kupuj(this);
  }

  public KarieraSpekulanta getKariera() {
    return kariera;
  }
}
