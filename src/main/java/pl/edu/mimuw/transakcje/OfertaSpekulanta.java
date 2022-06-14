package pl.edu.mimuw.transakcje;

import pl.edu.mimuw.agenci.spekulanci.Spekulant;
import pl.edu.mimuw.zasoby.Zasób;

public class OfertaSpekulanta implements Comparable<OfertaSpekulanta> {
  private Zasób zasób;
  private double cena;
  private Spekulant spekulant;
  private int poziom;

  // Przy kupnie ta wartość jest stale równa 100.
  private int ilość;

  public OfertaSpekulanta(Zasób zasób, double cena, Spekulant spekulant, int poziom, int ilość) {
    this.zasób = zasób;
    this.cena = cena;
    this.spekulant = spekulant;
    this.poziom = poziom;
    this.ilość = ilość;
  }

  public double getCena() {
    return cena;
  }

  public int getPoziom() {
    return poziom;
  }

  // Ustawienie ofert w odpowiedniej kolejności, żeby robotnicy
  // zaczynali od kupna najlepszych produktów.
  @Override
  public int compareTo(OfertaSpekulanta o) {
    if (poziom == o.getPoziom()) {
      if (cena < o.getCena())
        return 1;
      return -1;
    }
    return poziom - o.getPoziom();
  }

  public Zasób getZasób() {
    return zasób;
  }

  public int getIlość() {
    return ilość;
  }

  public Spekulant getSpekulant() {
    return spekulant;
  }
}
