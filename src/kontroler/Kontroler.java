/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroler;

import domen.Klijent;
import domen.Linija;
import domen.MedjuStanica;
import domen.Polazak;
import domen.Rezervacija;
import domen.Stanica;
import java.util.ArrayList;
import komunikacija.KomunikacijaSaServerom;
import kons.Konstante;
import kons.ResponseStatus;
import transfer.KlijentskiZahtev;
import transfer.ServerskiOdgovor;

/**
 *
 * @author sleza
 */
public class Kontroler {

    private static Kontroler instance;
    private KlijentskiZahtev kz;

    private Kontroler() {
    }

    public static Kontroler getInstance() {
        if (instance == null) {
            instance = new Kontroler();
        }
        return instance;
    }

    public Klijent UlogujSe(Klijent k) throws Exception {
        kz = new KlijentskiZahtev();
        kz.setParametar(k);
        kz.setOperacija(Konstante.PRIJAVLJIVANJE);
        KomunikacijaSaServerom.getInstance().posaljiZahtev(kz);
        ServerskiOdgovor so = KomunikacijaSaServerom.getInstance().primiOdgovor();
        if (so.getStatus() == ResponseStatus.ERROR) {
            Exception ex = (Exception) so.getError();
            throw ex;
        }
        return (Klijent) so.getOdgovor();
    }

    public void RegistrujSe(Klijent k) throws Exception {
        kz = new KlijentskiZahtev();
        kz.setOperacija(Konstante.REGISTRACIJA);
        kz.setParametar(k);
        KomunikacijaSaServerom.getInstance().posaljiZahtev(kz);
        ServerskiOdgovor so = KomunikacijaSaServerom.getInstance().primiOdgovor();
        if (so.getStatus() == ResponseStatus.ERROR) {
            Exception ex = (Exception) so.getError();
            throw ex;
        }
    }

    public void rezervisiPolazak(Rezervacija r) throws Exception {
        kz = new KlijentskiZahtev();
        kz.setOperacija(Konstante.REZERVISI_POLAZAK);
        kz.setParametar(r);
        KomunikacijaSaServerom.getInstance().posaljiZahtev(kz);
        ServerskiOdgovor so = KomunikacijaSaServerom.getInstance().primiOdgovor();
        if (so.getStatus() == ResponseStatus.ERROR) {
            Exception ex = (Exception) so.getError();
            throw ex;
        }
    }

    public ArrayList<Rezervacija> vratiMojeRezervacije(Rezervacija r) throws Exception {
        kz = new KlijentskiZahtev();
        kz.setOperacija(Konstante.VRATI_REZERVACIJE_ZA_KLIJENTA);
        kz.setParametar(r);
        KomunikacijaSaServerom.getInstance().posaljiZahtev(kz);
        ServerskiOdgovor so = KomunikacijaSaServerom.getInstance().primiOdgovor();
        ArrayList<Rezervacija> lista = (ArrayList<Rezervacija>) so.getOdgovor();
        return lista;
    }

    public void IzmeniNalog(Klijent klijent) throws Exception {
        kz = new KlijentskiZahtev();
        kz.setOperacija(Konstante.IZMENA_KORISNICKOG_NALOGA);
        kz.setParametar(klijent);
        KomunikacijaSaServerom.getInstance().posaljiZahtev(kz);
        ServerskiOdgovor so = KomunikacijaSaServerom.getInstance().primiOdgovor();
        if (so.getStatus() == ResponseStatus.ERROR) {
            Exception ex = (Exception) so.getError();
            throw ex;
        }
    }

    public int vratiBrojRezervacija(Rezervacija r) throws Exception {
        int broj = 0;
        kz = new KlijentskiZahtev();
        kz.setOperacija(kons.Konstante.VRATI_REZERVACIJE_ZA_POLAZAK);
        kz.setParametar(r);
        KomunikacijaSaServerom.getInstance().posaljiZahtev(kz);
        ServerskiOdgovor so = KomunikacijaSaServerom.getInstance().primiOdgovor();
        broj = ((ArrayList<Rezervacija>) so.getOdgovor()).size();
        return broj;
    }

    public void otkaziRezervaciju(Rezervacija r) throws Exception {
        kz = new KlijentskiZahtev();
        kz.setOperacija(Konstante.OTKAZI_REZERVACIJU);
        kz.setParametar(r);
        KomunikacijaSaServerom.getInstance().posaljiZahtev(kz);
        ServerskiOdgovor so = KomunikacijaSaServerom.getInstance().primiOdgovor();
        if (so.getStatus() == ResponseStatus.ERROR) {
            Exception ex = (Exception) so.getError();
            throw ex;
        }
    }

    public ArrayList<MedjuStanica> vratiMiSveMedjustanica() throws Exception {
        KlijentskiZahtev kz = new KlijentskiZahtev();
        kz.setOperacija(Konstante.VRATI_MEDJUSTANICE);
        KomunikacijaSaServerom.getInstance().posaljiZahtev(kz);
        ServerskiOdgovor so = KomunikacijaSaServerom.getInstance().primiOdgovor();
        ArrayList<MedjuStanica> listaMedjuStanica = (ArrayList<MedjuStanica>) so.getOdgovor();
        if (so.getStatus() == ResponseStatus.ERROR) {
            Exception ex = (Exception) so.getError();
            throw ex;
        }
        return listaMedjuStanica;
    }

    public ArrayList<MedjuStanica> vratiMiMedjustaniceLiniju(MedjuStanica medjuStanica) throws Exception {
        kz = new KlijentskiZahtev();
        kz.setOperacija(kons.Konstante.VRATI_MEDJUSTANICE_LINIJE);
        kz.setParametar(medjuStanica);
        KomunikacijaSaServerom.getInstance().posaljiZahtev(kz);
        ServerskiOdgovor so = KomunikacijaSaServerom.getInstance().primiOdgovor();
        ArrayList<MedjuStanica> listaMedjustanica = (ArrayList<MedjuStanica>) so.getOdgovor();
        if (so.getStatus() == ResponseStatus.ERROR) {
            Exception ex = (Exception) so.getError();
            throw ex;
        }
        return listaMedjustanica;
    }

    public ArrayList<Stanica> vratiMiSveStanice() throws Exception {
        kz = new KlijentskiZahtev();
        kz.setOperacija(Konstante.VRATI_STANICE);
        KomunikacijaSaServerom.getInstance().posaljiZahtev(kz);
        ServerskiOdgovor so = KomunikacijaSaServerom.getInstance().primiOdgovor();
        ArrayList<Stanica> listaStanica = (ArrayList<Stanica>) so.getOdgovor();
        if (so.getStatus() == ResponseStatus.ERROR) {
            Exception ex = (Exception) so.getError();
            throw ex;
        }
        return listaStanica;
    }

    public ArrayList<Polazak> vratiMiPolaskeZaDatum(Polazak p) throws Exception {
        kz = new KlijentskiZahtev();
        kz.setOperacija(Konstante.VRATI_POLASKE);
        kz.setParametar(p);
        KomunikacijaSaServerom.getInstance().posaljiZahtev(kz);
        ServerskiOdgovor so = KomunikacijaSaServerom.getInstance().primiOdgovor();
        ArrayList<Polazak> listaPolazaka = (ArrayList<Polazak>) so.getOdgovor();
        if (so.getStatus() == ResponseStatus.ERROR) {
            Exception ex = (Exception) so.getError();
            throw ex;
        }
        return listaPolazaka;
    }

}
