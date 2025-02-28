# FakeCall App
## Linter
```
./gradlew ktlintCheck
```

## Änderung gegenüber der Planung
Ich habe in meinen Mockups dem User die Möglichkeit gegeben, einen Ringtone und Vibrationsmuster auszuwählen, jedoch habe ich bemerkt, dass dies einen zu grossen Aufwand ist. Aus diesem Grund habe ich diese beiden Einstellungsmöglichkeiten nicht implementiert.
Zudem wollte ich dem Benutzer die Möglichkeit bieten, dass er über einen Shortcut (z.B. 3 Mal auf den Ausschalt-Knopf klicken) ermöglichen, dass ein simulierter Anruf gestartet wird. Doch auch dieses Feature hat zu viel Zeit in Anspruch genommen und es hat nie korrekt funktioniert.

## Weitere Infos
Wenn die App gestartet wird, erscheint eine Fenster "Über anderen App anzeigen". Dort muss die FakeCall-App gesucht werden und die Erlaubnis muss erteilt werden, dass die App über andere Apps angezeigt werden darf.

## Verwendung von AI
Ich habe in meinem Projekt ChatGPT o3-mini-high, 4o und Claude 3.7 verwendet. Ich habe primär AI verwendet um einen Lösungsansatz zu generieren, habe aber meistens den Code selst geschrieben. Einzig die HTML-Seite, die einen App-Store Eintrag simulieren soll, habe ich vollständig mit Claude generieren lassen. Zudem habe ich Teile der Logik für den Timer (```IncomingCallReceiver``` & ```IncomingCallService```, sprich, dass der Anruf angezeigt wird, auch wenn die App im Hintergrund ist, von AI übernommen, da die Logik sehr komplex wurde und ich keinen einfachen Ansatz gefunden habe.
