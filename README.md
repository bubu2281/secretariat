# Detaliere Implementare #
## Clase ##
In clasa `Main` se gestioneaza comenzile citite din fisierele din input si se apeleaza
corespunzator metodele aferente din clasa `Secretariat`.  
In clasa `Student` sunt retinute informatii referitoare la Student, respectiv numele, media
preferintele si cursul la care acesta este asignat. Clasele `BachelorStudent` si `MasterStudent`
extind aceasta clasa.  
In clasa `Course` sunt retinute informatii referitoare la Curs, respectiv numele, 
capacitatea maxima si lista de studenti ce sunt asignati la cursul respectiv.  
In clasa `Secretariat` se afla toate metodele aferente actiunilor pe care secretariatul
le poate efectua.
## Colectii folosite ##
In cadrul clasei `Student` pentru preferinte am utilizat `LinkedHashSet<String>` pentru eficienta
si datorita faptului ca preferintele trebuiau retinute in ordinea in care au fost citite.  
In cadrul clasei `Course` pentru lista de studenti inrolati la curs am utilizat `ArrayList<T>` deoarece
au fost necesare diverse operatii cu aceasta lista de studenti, spre exemplu sortari diferite
fie alfabetic, fie in functie de medie, cautari, parcurgeri, etc... .  
In cadrul clasei `Secretariat` pentru lista cu tot studentii gestionati de secretariat am
utilizat `ArrayList<Student>` din aceleasi motive ca mai sus. De asemenea, pentru lista cu toate
cursurile gestionate de secretariat am utilizat `TreeMap<String, Course<?>>` pentru eficienta si pentru
a nu mai sorta cursurile alfabetic de fiecare data.  
## Bonus ##
Daca vreun student ramane pe dinafara in urma repartizarii, acesta va fi asignat la primul curs din
ordinea alfabetica a cursurilor care mai are loc liber.  
## Altele ##
Clasele `ComparatorCourseAlphabetically` si `ComparatorStudentsAlphabetically` au fost utilizate pentru
a sorta listele cu cursuri, respectiv cele cu studenti.  
Clasa `DuplicatedStudent` a fost utilizata pentru a defini exceptia pentru cazul in care se incearca
adaugarea unui student care se afla deja in vederea secretariatului.  