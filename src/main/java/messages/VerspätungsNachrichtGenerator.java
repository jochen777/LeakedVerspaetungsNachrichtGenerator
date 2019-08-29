package messages;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class VerspätungsNachrichtGenerator {

  private final int echteVerspätungInMinuten;
  private final LocalDateTime reguläreAnkunftszeit;
  private final static int ZEIGE_KEINE_VERSPÄTUNG_AN_FÜR_MINUTEN = 10;

  public VerspätungsNachrichtGenerator(int echteVerspätungInMinuten,
      LocalDateTime reguläreAnkunftszeit) {
    this.echteVerspätungInMinuten = echteVerspätungInMinuten;
    this.reguläreAnkunftszeit = reguläreAnkunftszeit;
  }

  public String generiereVerspätungsNachricht(LocalDateTime jetzt) {
    return "+++ Verspätung ca. " + errechneFakeVerspätungMinuten(jetzt) + " Minuten +++";
  }

  public boolean sollZugverspätungAngezeigtWerden(LocalDateTime jetzt) {
    return errechneAktuelleVerspätung(jetzt) > ZEIGE_KEINE_VERSPÄTUNG_AN_FÜR_MINUTEN
        && errechneAktuelleVerspätung(jetzt) <= echteVerspätungInMinuten;
  }

  private int errechneFakeVerspätungMinuten(LocalDateTime jetzt) {
    int aktuelleVerspätung = errechneAktuelleVerspätung(jetzt);
    return rundMinutenNachBahnArt(aktuelleVerspätung);
  }

  private int rundMinutenNachBahnArt(int aktuelleVerspätung) {
    int zehnerStelle = ((int) (aktuelleVerspätung / 10)) * 10;
    int differenz = aktuelleVerspätung - zehnerStelle;
    int rundung = differenz == 9 ? 5 : 0;
    return zehnerStelle + rundung;
  }

  private int errechneAktuelleVerspätung(LocalDateTime jetzt) {
    return (int) reguläreAnkunftszeit.until(jetzt, ChronoUnit.MINUTES);
  }

}
