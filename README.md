# Proiect GlobalWaves  - Etapa 3
# <Copyrigts> Bîrleanu Teodor Matei 324CA 2024

      In cele ce urmeaza voi prezenta noile functionalitati adaugate proiectului.
      Ca desgign pattern am folosit un SINGLETON pentru instanta de admin , OBSERVER pentru user si Factory pattern
    pentru sistemul de pagini. Am folosit in implementarea temei solutia oficiala a etapei 2.
      WRAPPED
        Pentru aceasta functiona am monitorizat tot ce se afla in playere-le userilor prin intermediului functiei
    simulateTime care simuleaza timpul trecut din punctul de veder al playerului pentru toti userii. Pentru fiecare
    melodie ascultat am realizat statistici asa cum se cere(topSongs, topAlbums, topFans etc..).
      NOTIFICATIONS
      Am creat o clasa speciala de notificari care retine notificare in sinefiecare user avand o lista de acest 
    tip pentru a pastra notificarile primite de a lungul rularii programului. De asemenea, la fiecare comanda
    la care se primeste notificare am adaugat in lista de notif a userilor care urmaresc artistul/hostul actiunea
    pe care acesta a facut o. In cadrul acestei parti a proiectului, am realizat si actiunea de SUBSCRIBE, fiecare
    user avand o lista de "ContentCreators" la care este abonat. Pentru comanda GETNOTIFICATIONS,am realizat afisarea
    listei de notificari a fiecarui user dupa care golirea acesteia.
      RECOMMANDATIONS
      Pentru aceasta functie am realizat liste pentru fiecare user care contine recomandarile acestuia folosite si la
    printarea paginii pe care se aflata acesta. Pentru random_playlist am folosit comanda de Random din java pe lista
    contruita conform cerintei iar la cea din random_playlist am facut de asemenea cautarile conform filtrelor impuse.
    
    
      

