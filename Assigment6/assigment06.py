import gi
gi.require_version('Gtk', '3.0')
from gi.repository import Gtk


def getIndex(model, item):
        for i in range(0, len(model)):
            if model[i][0] == item:
                builder.get_object("sporocila").set_text("Sporocila: Vnos ze obstaja")
                return i
        return -1


class Handler:



    def aboutCB(self, *args):
        builder.get_object("status").set_text("Avtor: Sebastjan Mevlja")

    def quitCB(self, *args):
        Gtk.main_quit(*args)

    def openCB(self, *args):
        builder.get_object("fcd").show_all()

    def cancelCB(self, *args):
        builder.get_object("fcd").hide()
        builder.get_object("status").set_text("Odpiranje datoteke preklicano.")

    def selectCB(self, *args):
        builder.get_object("fcd").hide()
        builder.get_object("status").set_text("Odprta datoteka: "+builder.get_object("fcd").get_filename())
    
    def actionCB(self, *args):
        sporocila = builder.get_object("sporocila")
        vneseno = builder.get_object("vnos").get_text()
        izbrani = builder.get_object("element").get_value_as_int()
        combo = builder.get_object("prebivalisce")
        if builder.get_object("dodaj").get_active():
            if  vneseno:
                if ( not builder.get_object("dvojniki").get_active() or getIndex(combo.get_model(), vneseno) == -1 ):
                    combo.append_text(vneseno)
                    sporocila.set_text("Sporocila: Dodajam")
                    builder.get_object("vnos").set_text("")
        elif len(combo.get_model()) > 0:
            if builder.get_object("odstraniPrvega").get_active():
                combo.remove(0)
                combo.set_active(0)
                sporocila.set_text("Sporocila: Odstranjujem prvega")
            elif builder.get_object("odstraniIzbranega").get_active():
                combo.remove(izbrani)
                combo.set_active(0)
                sporocila.set_text("Sporocila: Odstranjujem izbranega")

    

    def pobrisiCB(self, *args):
        builder.get_object("status").set_text("Status: ")
        builder.get_object("sporocila").set_text("Sporocila:")

    def prikaziElement(self, *args):
        izbrani = builder.get_object("element").get_value_as_int()
        prebivalisceModel = builder.get_object("prebivalisce").get_model() 
        if len(prebivalisceModel )> izbrani:
            
            builder.get_object("sporocila").set_text("Sporocila: "+prebivalisceModel[izbrani][0])
        else:
            builder.get_object("sporocila").set_text("Sporocila: Ni elementa")

    def dodajCrkoS(self, *args):
        builder.get_object("status").set_text(builder.get_object("status").get_text() + "S")
    def dodajCrkoE(self, *args):
        builder.get_object("status").set_text(builder.get_object("status").get_text() + "E")
    def dodajCrkoB(self, *args):
        builder.get_object("status").set_text(builder.get_object("status").get_text() + "B")
    def dodajCrkoA(self, *args):
        builder.get_object("status").set_text(builder.get_object("status").get_text() + "A")
    def dodajCrkoT(self, *args):
        builder.get_object("status").set_text(builder.get_object("status").get_text() + "T")
    def dodajCrkoJ(self, *args):
        builder.get_object("status").set_text(builder.get_object("status").get_text() + "J")
    def dodajCrkoN(self, *args):
        builder.get_object("status").set_text(builder.get_object("status").get_text() + "N")
    def dodajCrkoM(self, *args):
        builder.get_object("status").set_text(builder.get_object("status").get_text() + "M")
    def dodajCrkoV(self, *args):
        builder.get_object("status").set_text(builder.get_object("status").get_text() + "V")
    def dodajCrkoL(self, *args):
        builder.get_object("status").set_text(builder.get_object("status").get_text() + "L")
    def dodajCrkoJ(self, *args):
        builder.get_object("status").set_text(builder.get_object("status").get_text() + "J")
    
    


builder = Gtk.Builder()
builder.add_from_file("assignment06.glade")
builder.connect_signals(Handler())
windows = builder.get_object("window")
builder.get_object("element").set_range(0,20)
windows.show_all()

Gtk.main()
