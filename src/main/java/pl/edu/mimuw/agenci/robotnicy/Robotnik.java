package pl.edu.mimuw.agenci.robotnicy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pl.edu.mimuw.agenci.Agent;
import pl.edu.mimuw.agenci.robotnicy.strategieKariery.KarieraRobotnika;
import pl.edu.mimuw.agenci.robotnicy.strategieKariery.Konserwatysta;
import pl.edu.mimuw.agenci.robotnicy.strategieKariery.Rewolucjonista;
import pl.edu.mimuw.agenci.robotnicy.strategieKariery.StrategiaKariery;
import pl.edu.mimuw.agenci.robotnicy.strategieKupowania.Czyścioszek;
import pl.edu.mimuw.agenci.robotnicy.strategieKupowania.Gadżeciarz;
import pl.edu.mimuw.agenci.robotnicy.strategieKupowania.StrategiaKupowania;
import pl.edu.mimuw.agenci.robotnicy.strategieKupowania.Technofob;
import pl.edu.mimuw.agenci.robotnicy.strategieKupowania.Zmechanizowany;
import pl.edu.mimuw.agenci.robotnicy.strategieNauki.Okresowy;
import pl.edu.mimuw.agenci.robotnicy.strategieNauki.Oszczędny;
import pl.edu.mimuw.agenci.robotnicy.strategieNauki.Pracuś;
import pl.edu.mimuw.agenci.robotnicy.strategieNauki.Rozkładowy;
import pl.edu.mimuw.agenci.robotnicy.strategieNauki.StrategiaNauki;
import pl.edu.mimuw.agenci.robotnicy.strategieNauki.Student;
import pl.edu.mimuw.agenci.robotnicy.strategieProdukcji.Chciwy;
import pl.edu.mimuw.agenci.robotnicy.strategieProdukcji.Krótkowzroczny;
import pl.edu.mimuw.agenci.robotnicy.strategieProdukcji.Losowy;
import pl.edu.mimuw.agenci.robotnicy.strategieProdukcji.Perspektywiczny;
import pl.edu.mimuw.agenci.robotnicy.strategieProdukcji.StrategiaProdukcji;
import pl.edu.mimuw.agenci.robotnicy.strategieProdukcji.Średniak;
import pl.edu.mimuw.input.RobotnikInput;
import pl.edu.mimuw.symulacja.Info;
import pl.edu.mimuw.transakcje.OfertaRobotnika;
import pl.edu.mimuw.zasoby.Zasób;

public class Robotnik extends Agent {
  private Map<Zasób, Integer> produktywność;

  private StrategiaNauki nauka;
  private StrategiaKariery zmianaKariery;
  private StrategiaProdukcji produkcja;
  private StrategiaKupowania kupowanie;

  private int poziom;
  private KarieraRobotnika aktualnaKariera;
  private Map<KarieraRobotnika, Integer> kariera;

  private int ileDniBezJedzenia;
  private int ileWyprodukowałWRundzie;

  public Robotnik(RobotnikInput input) {
    super(input);

    produktywność = new HashMap<>();

    produktywność.put(Zasób.Programy, input.produktywnosc.get("programy"));
    produktywność.put(Zasób.Jedzenie, input.produktywnosc.get("jedzenie"));
    produktywność.put(Zasób.Diamenty, input.produktywnosc.get("diamenty"));
    produktywność.put(Zasób.Ubrania, input.produktywnosc.get("ubrania"));
    produktywność.put(Zasób.Narzędzia, input.produktywnosc.get("narzedzia"));

    switch ((String) input.uczenie.get("typ")) {
      case "okresowy": nauka = new Okresowy((int) input.uczenie.get("okresowosc_nauki")); break;
      case "oszczedny": nauka = new Oszczędny((double) input.uczenie.get("limit_diamentow")); break;
      case "pracus": nauka = new Pracuś(); break;
      case "rozkladowy": nauka = new Rozkładowy(); break;
      case "student": nauka = new Student(((Double) input.uczenie.get("zapas")).intValue(),
                                          ((Double) input.uczenie.get("okres")).intValue()); break;
      default: nauka = null; 
    }

    switch (input.zmiana) {
      case "konserwatysta": zmianaKariery = new Konserwatysta(); break;
      case "rewolucjonista": zmianaKariery = new Rewolucjonista(); break;
      default: zmianaKariery = null;
    }

    switch ((String) input.produkcja.get("typ")) {
      case "chciwy": produkcja = new Chciwy(); break;
      case "krotkowzroczny": produkcja = new Krótkowzroczny(); break;
      case "losowy": produkcja = new Losowy(); break;
      case "perspektywiczny": produkcja = new Perspektywiczny((int) input.produkcja.get("historia_perspektywy")); break;
      case "sredniak": produkcja = new Średniak((int) input.produkcja.get("historia_spekulanta_sredniego")); break;
      default: produkcja = null;
    }

    switch ((String) input.kupowanie.get("typ")) {
      case "czyscioszek": kupowanie = new Czyścioszek(); break;
      case "gadzeciarz": kupowanie = new Gadżeciarz(((Double) input.kupowanie.get("liczba_narzedzi")).intValue()); break;
      case "technofob": kupowanie = new Technofob(); break;
      case "zmechanizowany": kupowanie = new Zmechanizowany((int) input.kupowanie.get("liczba_narzedzi")); break;
      default: kupowanie = null;
    }

    poziom = input.poziom;
    
    switch (input.kariera) {
      case "rolnik": aktualnaKariera = KarieraRobotnika.Rolnik; break;
      case "rzemieslnik": aktualnaKariera = KarieraRobotnika.Rzemieślnik; break;
      case "inzynier": aktualnaKariera = KarieraRobotnika.Inżynier; break;
      case "gornik": aktualnaKariera = KarieraRobotnika.Górnik; break;
      case "programista": aktualnaKariera = KarieraRobotnika.Programista; break;
      default: aktualnaKariera = null;
    }

    kariera = new HashMap<>();
    kariera.put(KarieraRobotnika.Rolnik, 1);
    kariera.put(KarieraRobotnika.Rzemieślnik, 1);
    kariera.put(KarieraRobotnika.Inżynier, 1);
    kariera.put(KarieraRobotnika.Górnik, 1);
    kariera.put(KarieraRobotnika.Programista, 1);

    ileDniBezJedzenia = 0;
    ileWyprodukowałWRundzie = 0;
  }

  public boolean czySięUczysz() {
    return nauka.czySięUczysz(getDiamenty());
  }

  public void uczSię() {
    KarieraRobotnika k = zmianaKariery.zmieńKarierę(aktualnaKariera, getId());

    if (k == aktualnaKariera) {
      kariera.put(k, poziom + 1);
      poziom ++;
    }
    else {
      aktualnaKariera = k;
      poziom = kariera.get(k);
    }
  }

  public List<OfertaRobotnika> produkuj() {
    Zasób produkt = produkcja.coProdukujesz(this);
    int ile = ileWyprodukuje(produkt);
    ileWyprodukowałWRundzie = ile;

    List<OfertaRobotnika> res = new ArrayList<>();

    if (produkt == Zasób.Diamenty) {
      setDiamenty(getDiamenty() + ile);
      return res;
    }

    if (produkt == Zasób.Ubrania) {
      if (ile > 0)
        res.add(new OfertaRobotnika(produkt, ile, this, 1));
      return res;
    }

    if (produkt == Zasób.Programy) {
      if (ile > 0) {
        if (aktualnaKariera == KarieraRobotnika.Programista)
          res.add(new OfertaRobotnika(produkt, ile, this, poziom));
        else 
          res.add(new OfertaRobotnika(produkt, ile, this, 1));
      }
      return res;
    }

    List<Integer> programy = getZasoby().get(Zasób.Programy);
    int maksymalnyPoziomProgramu = programy.size();

    for (int i = maksymalnyPoziomProgramu - 1; i >= 1; i--) 
      if (programy.get(i) != 0 && ile > 0) {
        int ileDanegoPoziomu = Math.min(ile, programy.get(i));
        res.add(new OfertaRobotnika(produkt, ileDanegoPoziomu, this, i));

        if (ileDanegoPoziomu == programy.get(i))
          programy.remove(i);
        else 
          programy.set(i, programy.get(i) - ileDanegoPoziomu);
        
        ile -= ileDanegoPoziomu;
      }

    if (ile > 0)
      res.add(new OfertaRobotnika(produkt, ile, this, 1));
    return res;
  }

  public int ileWyprodukuje(Zasób zasób) {
    int baza = produktywność.get(zasób);

    int premia = 0;

    if ((aktualnaKariera == KarieraRobotnika.Rolnik && zasób == Zasób.Jedzenie) ||
        (aktualnaKariera == KarieraRobotnika.Rzemieślnik && zasób == Zasób.Ubrania) ||
        (aktualnaKariera == KarieraRobotnika.Inżynier && zasób == Zasób.Narzędzia) ||
        (aktualnaKariera == KarieraRobotnika.Górnik && zasób == Zasób.Diamenty) ||
        (aktualnaKariera == KarieraRobotnika.Programista && zasób == Zasób.Programy))
      premia = premia(poziom);

    if (ileDniBezJedzenia == 1)
      premia -= 100;
    if (ileDniBezJedzenia == 2)
      premia -= 300;

    int ileUbrań = 0;

    for (int i: getZasoby().get(Zasób.Ubrania))
      ileUbrań += i;

    if (ileUbrań < 100)
      premia -= Info.getKaraZaBrakUbrań();

    for (int i = 0; i < getZasoby().get(Zasób.Narzędzia).size(); i++) {
      premia += getZasoby().get(Zasób.Narzędzia).get(i) * (i + 1);
    }

    return Math.max(baza + baza / 100 * premia, 0);
  }

  private int premia(int poziom) {
    switch (poziom) {
      case 1: return 50;
      case 2: return 150;
      case 3: return 300;
      default: return 300 + (poziom - 3) * 25;
    }
  }

  public List<OfertaRobotnika> kupuj() {
    return kupowanie.kupuj(this);
  }

  public int ileUbrańPoTurze() {
    List<Integer> ubrania = getZasoby().get(Zasób.Ubrania);

    int ilePowyżejPierwszego = 0;

    for (int i = 1; i < ubrania.size(); i++)
      ilePowyżejPierwszego += ubrania.get(i);

    return ilePowyżejPierwszego + Math.max(0, ubrania.get(0) - 100 + ilePowyżejPierwszego);
  }

  public int ileWyprodukowałWRundzie() {
    return ileWyprodukowałWRundzie;
  }

  public int getAktualnyPoziom() {
    return poziom;
  }

  public KarieraRobotnika getAktualnaKariera() {
    return aktualnaKariera;
  }

  public StrategiaKupowania getStrategiaKupowania() {
    return kupowanie;
  }

  public StrategiaProdukcji geStrategiaProdukcji() {
    return produkcja;
  }
  
  public StrategiaNauki getStrategiaNauki() {
    return nauka;
  }

  public StrategiaKariery geStrategiaKariery() {
    return zmianaKariery;
  }

  public Map<Zasób, Integer> getProduktywnosc() {
    return produktywność;
  }
}
