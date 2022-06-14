package pl.edu.mimuw.input;

import java.util.Map;

// Klasa wykorzystywana do wczytania pliku JSON.
// Z niej są tworzone obiekty w symulacji.
public class SpekulantInput {
  // Atrybuty publiczne, żeby móc modyfikować wejście.
  public int id;
  public Map<String, Object> kariera;
  public Map<String, Integer> zasoby;
}
