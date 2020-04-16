/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeli;

import domen.Rezervacija;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author sleza
 */
public class ModelTabeleRezervacija extends AbstractTableModel {

    SimpleDateFormat smfDatum = new SimpleDateFormat("dd.MM.yyyy");
    SimpleDateFormat smfVreme = new SimpleDateFormat("HH:mm");
    SimpleDateFormat smfDatumIVreme = new SimpleDateFormat("dd.MM.yyyy HH:mm");
    String[] kolone = {"Stanica polaska", "Datum polaska", "Vreme polaska", "Stanica krajnja", "Datum dolaska", "Vreme dolaska", "Datum rezervacije karte", "Napomene"};
    ArrayList<Rezervacija> list = new ArrayList<>();

    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Rezervacija r = list.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return r.getPolazak().getLinija().getStanicaPocetna();
            case 1:
                return smfDatum.format(r.getPolazak().getDatumPolaska());
            case 2:
                return smfVreme.format(r.getPolazak().getDatumPolaska());
            case 3:
                return r.getPolazak().getLinija().getStanicaKrajnja();
            case 4:
                return smfDatum.format(r.getPolazak().getDatumDolaska());
            case 5:
                return smfVreme.format(r.getPolazak().getDatumDolaska());
            case 6:
                return smfDatumIVreme.format(r.getDatum());
            case 7:
                return r.getPolazak().getNapomena();
            default:
                return "";
        }
    }

    @Override
    public String getColumnName(int column) {
        for (int i = 0; i < kolone.length; i++) {
            return kolone[column];
        }
        return "Cao";
    }

    public ArrayList<Rezervacija> getList() {
        return list;
    }

    public void setList(ArrayList<Rezervacija> list) {
        this.list = list;
        fireTableDataChanged();

    }

    public void ocistiTabelu() {
        list = new ArrayList<>();
        fireTableDataChanged();

    }

    public void izbrisiIzTabele(int broj) {
        list.remove(broj);
        fireTableDataChanged();
    }

}
