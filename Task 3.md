

### Subtask 1: Implementarea MapElementBuilder și teste unitare
**Cerință:** Implementarea clasei MapElementBuilder și a testelor unitare pentru aceasta.

**Ce înveți:**
- Principiul deschis-închis (Open-Closed Principle): Vei dezvolta un modul care poate fi extins prin 
adăugarea de noi tipuri de elemente fără a modifica codul existent.
- Principiul responsabilității unice (Single Responsibility Principle): Clasa MapElementBuilder ar trebui 
să aibă o singură responsabilitate - construirea elementelor hărții.

**Detalii:** În acest subtask, vei implementa clasa MapElementBuilder și vei asigura că aceasta este 
acoperită cu teste unitare adecvate.

### Subtask 2: Implementarea MapElementsGenerator și teste unitare
**Cerință:** Implementarea interfeței MapElementsGenerator și a testelor unitare pentru aceasta.

**Ce înveți:**
- Folosirea interfețelor pentru a defini contracte de implementare.
- Principii de Dependency Injection și Interface Segregation.

**Detalii:** În această etapă, vei crea interfața MapElementsGenerator care va fi responsabilă de generarea 
mai multor elemente pentru harta Mars și vei asigura că aceasta este acoperită cu teste unitare adecvate.

### Subtask 3: Implementarea MapElementPlacer și teste unitare
**Cerință:** Implementarea interfeței MapElementPlacer și a testelor unitare pentru aceasta.

**Ce înveți:**
- Principiul responsabilității unice (Single Responsibility Principle): Clasa MapElementPlacer ar trebui 
să se ocupe de plasarea elementelor pe hartă și nimic altceva.
- Conceptul de plasare a elementelor într-un spațiu multidimensional.

**Detalii:** În acest subtask, vei crea interfața MapElementPlacer și clasa concretă MapElementPlacerImpl 
care se va ocupa de logica de plasare a elementelor pe hartă, respectând regulile menționate în cerință.

### Subtask 4: Implementarea MapGenerator și teste unitare
**Cerință:** Implementarea interfeței MapGenerator și a testelor unitare pentru aceasta.

**Ce înveți:**
- Conceptul de generare a hărții pe baza elementelor generate anterior.
- Folosirea compoziției pentru a asambla diferite funcționalități ale aplicației.

**Detalii:** În această etapă, vei crea interfața MapGenerator și clasa concretă MapGeneratorImpl care 
va utiliza celelalte componente (MapElementsGenerator și MapElementPlacer) pentru a genera harta Mars, 
respectând regulile menționate în cerință.

### Subtask 5: Implementarea testelor unitare pentru întreaga aplicație
**Cerință:** Implementarea testelor unitare pentru a asigura că toate componentele lucrează împreună 
corespunzător și că harta generată este în conformitate cu cerințele.

**Ce înveți:**
- Folosirea testelor de integrare pentru a verifica funcționarea corectă a mai multor componente împreună.

**Detalii:** În această etapă, vei crea teste unitare care să acopere întreg fluxul de generare a hărții,
de la configurare până la generarea efectivă și plasarea elementelor pe hartă.

### Subtask 6: Finalizarea și revizuirea codului
**Cerință:** Finalizarea implementării și revizuirea generală a codului pentru a ne asigura că toate
funcționalitățile sunt implementate corect și că respectă cerințele inițiale.

**Ce înveți:**
- Importanța revizuirii și testării constante a codului pentru a identifica și corecta erori sau posibile îmbunătățiri.

**Detalii:** În această etapă, vei face ultimele ajustări la cod și vei verifica dacă toate funcționalitățile
sunt implementate corect și că harta generată corespunde cerințelor.

#### Observație
Pe măsură ce abordăm fiecare subtask, vom discuta și despre principiile și conceptele menționate în cerință,
cum ar fi principiile de proiectare (Open-Closed, Single Responsibility, Interface Segregation) și tehnici precum
Dependency Injection. Voi oferi explicații detaliate și vom putea discuta orice întrebări ai. Vom începe prin
a implementa primul subtask: MapElementBuilder și teste unitare pentru acesta. Dacă ești de acord, putem continua
cu detalii despre cum să abordăm acest subtask.