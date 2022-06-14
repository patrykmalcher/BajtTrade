package pl.edu.mimuw.output;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pl.edu.mimuw.agenci.spekulanci.Spekulant;
import pl.edu.mimuw.zasoby.Zasób;

// Klasa wykorzystywana do wypisania do pliku JSON.
@SuppressWarnings("unused") // Moshi korzysta!!
public class SpekulantOutput {
  private int id;
  private Map<String, Object> kariera;
  private Map<String, Object> zasoby;

  public SpekulantOutput(Spekulant spekulant) {
    id = spekulant.getId();
    kariera = spekulant.getKariera().wypiszSię();

    double diamentyOrg = spekulant.getDiamenty();
    Map<Zasób, List<Integer>> zasobyOrg = spekulant.getZasoby();

    zasoby = new HashMap<>();

    zasoby.put("diamenty", diamentyOrg);
    zasoby.put("jedzenie", zasobyOrg.get(Zasób.Jedzenie).get(0));
    zasoby.put("ubrania", zasobyOrg.get(Zasób.Ubrania));
    zasoby.put("narzedzia", zasobyOrg.get(Zasób.Narzędzia));
    zasoby.put("programy", zasobyOrg.get(Zasób.Programy));
  }
}
