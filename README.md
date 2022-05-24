# jwarezreader

UN semplice script java che permette di cercare stringhe in file ods multipli, usando le librerie jopendocument.
Andrebbero usate le regex, se qualcuno lo vuole pacthate è il benvenuto.

IL programma è stato richiesto da antonio adamo che ha un negozio che si chiama "warez" e non ha nulla a che fare con il "warez"

Per compilarlo è necessario gson e jopendocument

# Localizzazione

Per localizzare il programma basta dotarsi di eclipse o di qualsiasi altro editor visuale, scaricare uno dei files JWarezRearder_xy.properties e tradurre tutto quello che è a destra del simbolo "=".
A questo punto, bisogna rinominare il file usando la nominazione a due caratteri della traduzione (se prendete JWarezReader_it.properties e volete tradurlo in tedesco bisogna rinominarlo in JWarezReader_de.properties).
A questo punto basta avviare il programma con le opzioni "-Duser.language=xy -Duser.region=XY" (esempio: java -Duser.language=en -Duser.region=EN -jar ./JWarezReader-0.5-1.jar)
Sarò felice di darvi il credit se mi mandate le vostre localizzazioni.
Ricordatevi che la GPL obbliga a mantenere i credits originali, quindi, per fare, fate i seri...

![screen-2022-05-24-20-17-20](https://user-images.githubusercontent.com/49764967/170104746-6f4be52d-aeab-4ac9-82d8-05b2f013803b.png)
![screen-2022-05-24-20-16-28](https://user-images.githubusercontent.com/49764967/170104751-e9f6659b-9801-42ab-a5c9-27fc58df5814.png)
![screen-2022-05-24-20-15-56](https://user-images.githubusercontent.com/49764967/170104754-607a1463-cc76-4f68-9c5d-2bfb2e9f6bad.png)
![screen-2022-05-24-20-19-14](https://user-images.githubusercontent.com/49764967/170105046-18c79e3c-d03b-4365-88e6-23dd44b85031.png)
![screen-2022-05-24-20-18-37](https://user-images.githubusercontent.com/49764967/170105050-66605044-0855-40e7-a623-3c34080a5022.png)


# bug noti
Se si apre due volte il programma si corrompe il file delle opzioni. Il programma va aperto una volta soltanto.
Se si desidera cambiare path di ricerca è necessario riavviare manualmente il programma.

# Bibliografia

https://docs.oracle.com/cd/E23095_01/Platform.93/ATGProgGuide/html/s1809settingthejavavirtualmachineloca01.html
