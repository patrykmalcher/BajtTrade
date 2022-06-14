package pl.edu.mimuw.symulacja;

import java.util.HashMap;
import java.util.Map;

import pl.edu.mimuw.gielda.Giełda;
import pl.edu.mimuw.gielda.Kapitalistyczna;
import pl.edu.mimuw.gielda.Socjalistyczna;
import pl.edu.mimuw.gielda.Zrównoważona;
import pl.edu.mimuw.input.InfoInput;
import pl.edu.mimuw.zasoby.Zasób;

public class Info {
  private int długość;
  private Giełda giełda;
  private static int karaZaBrakUbrań;
  private Map<Zasób, Double> ceny;

  public Info(InfoInput input) {
    długość = input.dlugosc;
    switch (input.gielda) {
      case "socjalistyczna": giełda = new Socjalistyczna(); break;
      case "kapitalistyczna": giełda = new Kapitalistyczna(); break;
      case "zrownowazona": giełda = new Zrównoważona(); break;
      default: giełda = null;
    }
    karaZaBrakUbrań = input.kara_za_brak_ubran;
    ceny = new HashMap<>();

    ceny.put(Zasób.Programy, input.ceny.get("programy"));
    ceny.put(Zasób.Jedzenie, input.ceny.get("jedzenie"));
    ceny.put(Zasób.Ubrania, input.ceny.get("ubrania"));
    ceny.put(Zasób.Narzędzia, input.ceny.get("narzedzia"));
  }

  public static int getKaraZaBrakUbrań() {
    return karaZaBrakUbrań;
  }

  public int getDługość() {
    return długość;
  }

  public Giełda getGiełda() {
    return giełda;
  }

  public Map<Zasób, Double> getCeny() {
    return ceny;
  }
}
