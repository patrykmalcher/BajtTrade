package pl.edu.mimuw.input;

import java.util.Map;

// Klasa wykorzystywana do wczytania pliku JSON.
// Z niej są tworzone obiekty w symulacji.
public class InfoInput {
  // Atrybuty publiczne, żeby móc modyfikować wejście.
  public int dlugosc;
  public String gielda;
  public int kara_za_brak_ubran;
  public Map<String, Double> ceny;
}
