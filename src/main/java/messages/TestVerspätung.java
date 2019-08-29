package messages;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TestVerspätung {

  public static void main(String[] args) {
    LocalDateTime jetzt = LocalDateTime.now();
    VerspätungsNachrichtGenerator generator = new VerspätungsNachrichtGenerator(65, jetzt);
    for (int minuten = 0; minuten < 100; minuten++) {
      LocalDateTime fakeJetzt = jetzt.plusMinutes(minuten);
      if (generator.sollZugverspätungAngezeigtWerden(fakeJetzt)) {
        System.out.println("Zeit: " + fakeJetzt.format(DateTimeFormatter.ISO_DATE_TIME)
            + " Nachricht: " + generator.generiereVerspätungsNachricht(fakeJetzt));
      } else {
        System.out.println("Zeit: " + fakeJetzt.format(DateTimeFormatter.ISO_DATE_TIME) + " - ");
      }
    }
  }

}
