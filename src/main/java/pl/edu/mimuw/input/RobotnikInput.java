package pl.edu.mimuw.input;

import java.util.Map;

// Klasa wykorzystywana do wczytania pliku JSON.
// Z niej są tworzone obiekty w symulacji.
public class RobotnikInput {
  // Atrybuty publiczne, żeby móc modyfikować wejście.
  public int id;
  public int poziom;
  public String kariera;
  public Map<String, Object> kupowanie;
  public Map<String, Object> produkcja;
  public Map<String, Object> uczenie;
  public String zmiana;
  public Map<String, Integer> produktywnosc;
  public Map<String, Integer> zasoby;
}
