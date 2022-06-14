package pl.edu.mimuw.output;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pl.edu.mimuw.agenci.robotnicy.Robotnik;
import pl.edu.mimuw.agenci.robotnicy.strategieKariery.KarieraRobotnika;
import pl.edu.mimuw.agenci.robotnicy.strategieKariery.StrategiaKariery;
import pl.edu.mimuw.agenci.robotnicy.strategieKupowania.StrategiaKupowania;
import pl.edu.mimuw.agenci.robotnicy.strategieNauki.StrategiaNauki;
import pl.edu.mimuw.agenci.robotnicy.strategieProdukcji.StrategiaProdukcji;
import pl.edu.mimuw.zasoby.Zasób;

// Klasa wykorzystywana do wypisania do pliku JSON.
@SuppressWarnings("unused") // Moshi korzysta!!
public class RobotnikOutput {
  private int id;
  private int poziom;
  private String kariera;
  private Map<String, Object> kupowanie;
  private Map<String, Object> produkcja;
  private Map<String, Object> uczenie;
  private String zmiana;
  private Map<String, Integer> produktywnosc;
  private Map<String, Object> zasoby; 

  public RobotnikOutput(Robotnik robotnik) {
    id = robotnik.getId();
    poziom = robotnik.getAktualnyPoziom();

    KarieraRobotnika karieraorg = robotnik.getAktualnaKariera();

    switch (karieraorg) {
      case Rolnik: kariera = "rolnik"; break;
      case Rzemieślnik: kariera = "rzemieslnik"; break;
      case Inżynier: kariera = "inzynier"; break;
      case Górnik: kariera = "gornik"; break;
      case Programista: kariera = "programista"; break;
      default: kariera = " ";
    }

    StrategiaKupowania kupowanieOrg = robotnik.getStrategiaKupowania();
    kupowanie = kupowanieOrg.wypiszSię();

    StrategiaProdukcji produkcjaOrg = robotnik.geStrategiaProdukcji();
    produkcja = produkcjaOrg.wypiszSię();

    StrategiaNauki naukaOrg = robotnik.getStrategiaNauki();
    uczenie = naukaOrg.wypiszSię();

    StrategiaKariery zmianaOrg = robotnik.geStrategiaKariery();
    zmiana = zmianaOrg.toString();

    Map<Zasób, Integer> produktywnoscOrg = robotnik.getProduktywnosc();
    produktywnosc = new HashMap<>();
    produktywnosc.put("programy", produktywnoscOrg.get(Zasób.Programy));
    produktywnosc.put("jedzenie", produktywnoscOrg.get(Zasób.Jedzenie));
    produktywnosc.put("diamenty", produktywnoscOrg.get(Zasób.Diamenty));
    produktywnosc.put("ubrania", produktywnoscOrg.get(Zasób.Ubrania));  
    produktywnosc.put("narzedzia", produktywnoscOrg.get(Zasób.Narzędzia));

    double diamentyOrg = robotnik.getDiamenty();
    Map<Zasób, List<Integer>> zasobyOrg = robotnik.getZasoby();

    zasoby = new HashMap<>();

    zasoby.put("diamenty", diamentyOrg);
    zasoby.put("jedzenie", zasobyOrg.get(Zasób.Jedzenie).get(0));
    zasoby.put("ubrania", zasobyOrg.get(Zasób.Ubrania));
    zasoby.put("narzedzia", zasobyOrg.get(Zasób.Narzędzia));
    zasoby.put("programy", zasobyOrg.get(Zasób.Programy));
  }
}
