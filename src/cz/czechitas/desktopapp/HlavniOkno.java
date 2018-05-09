package cz.czechitas.desktopapp;

import java.awt.*;
import java.awt.event.*;
import java.text.*;
import javax.swing.*;
import net.miginfocom.swing.*;

public class HlavniOkno extends JFrame {

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner non-commercial license
    JLabel labNadpis;
    JLabel labNadpisKralici;
    JLabel labNadpisHusy;
    JLabel labPocetSamcuKraliku;
    JTextField editPocetSamcuKraliku;
    JLabel labPocetSamcuHus;
    JTextField editPocetSamcuHus;
    JLabel labErrorMessageSamciKraliku;
    JLabel labErrorMessageSamciHus;
    JLabel labPocetSamicKraliku;
    JTextField editPocetSamicKraliku;
    JLabel labPocetSamicHus;
    JTextField editPocetSamicHus;
    JLabel labErrorMessageSamiceKraliku;
    JLabel labErrorMessageSamiceHus;
    JLabel labErrorMessageProUzivatele;
    JButton btnVypocti;
    JLabel labNadpisVelikostChovu;
    JLabel labVelikostChovuKraliku;
    JLabel labVelikostChovuHus;
    JLabel labNadpisPotrebaKrmiva;
    JLabel labSpotrebaMrkveAPocetRadku;
    JLabel labSpotrebaZrniAPocetRadku;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
    JPanel contentPane;
    MigLayout migLayoutManager;

    public HlavniOkno() {
        initComponents();
    }

    private void poStisknutiBtnVypoctiVypocetKrmeniARadku(ActionEvent e) {   //event při kliknutí na tlač. Vypočti

        vypocetPoctuMrkveARadku();      //jednotlive metody pro vypocty jidla a velikosti chovu
        vypocetPoctuZrniARadku();
        velikostChovuKraliku();
        velikostChovuHus();
    }

    public void vypocetPoctuMrkveARadku() {
        //String pocetKralikuText;
        //pocetKralikuText = editPocetSamicKraliku.getText() + editPocetSamcuKraliku.getText(); - nelze, sčítám stringy a ne integery, tj. "1" + "1" = "11" :-)

        String pocetSamicKralikuText = editPocetSamicKraliku.getText();  //ulozeni vstupu do promene
        String pocetSamcuKralikuText = editPocetSamcuKraliku.getText();  //ulozeni vstupu do promene

        if (!jeCislo(pocetSamicKralikuText)) {
            return;
        }

        if (!jeCislo(pocetSamcuKralikuText)) {
            return;
        }

        Double pocetSamicKralikuCislo = prevedTextNaCislo(pocetSamicKralikuText);
        Double pocetSamcuKralikuCislo = prevedTextNaCislo(pocetSamcuKralikuText);

        Double pocetMladatCislo = pocetSamicKralikuCislo * 40;  //vypocet kralicat

        Double spotrebaMrkve;
        Double pocetRadkuMrkve;

        if (pocetSamcuKralikuCislo > 0) {
            spotrebaMrkve = ((pocetSamcuKralikuCislo + pocetSamicKralikuCislo + pocetMladatCislo) * 0.5 * 183);  //vypocet spotreby mrkve, nejdriv scitam chov, pak nasobim mnozstvim mrkve na 1 kralika a poctem dni v zime

        } else {
            spotrebaMrkve = ((pocetSamicKralikuCislo) * 0.5 * 183);

        }

        pocetRadkuMrkve = (spotrebaMrkve / 5); //vypocet radku, ktere musim osit mrkvi, delim celou spotrebu mrkve poctem kg, ktere vypestuji na jednom osetem radku

        String spotrebaMrkveText = prevedCisloNaText(spotrebaMrkve); //prevod Double na string, nutne pro zobrazeni v labSpotrebaMrkveAPocetRadku
        String pocetRadkuMrkveText = prevedCisloNaText(pocetRadkuMrkve);  //prevod Double na string, nutne pro zobrazeni v labSpotrebaMrkveAPocetRadku

        labSpotrebaMrkveAPocetRadku.setText(spotrebaMrkveText + " kg mrkve, tedy " + pocetRadkuMrkveText + " řádků."); //zobrazeni vysledku v labSpotrebaMrkveAPocetRadku

    }

    public void velikostChovuKraliku() {
        //String pocetKralikuText;
        //pocetKralikuText = editPocetSamcuKraliku.getText() + editPocetSamicKraliku.getText();  - nelze, sčítám stringy a ne integery, takže "1" a "1" je "11" :-)

        String pocetSamicKralikuText = editPocetSamicKraliku.getText();       //ulozeni vstupu do promene
        String pocetSamcuKralikuText = editPocetSamcuKraliku.getText();       //ulozeni vstupu do promene

        if (!jeCislo(pocetSamicKralikuText)) {
            return;
        }

        if (!jeCislo(pocetSamcuKralikuText)) {
            return;
        }

        Double pocetSamicKralikuCislo = prevedTextNaCislo(pocetSamicKralikuText);
        Double pocetSamcuKralikuCislo = prevedTextNaCislo(pocetSamcuKralikuText);

        //int pocetSamicKralikuCislo = Integer.parseInt(pocetSamicKralikuText);      //prevod stringu na integer, puvodni reseni bez pouziti metodyprevedTextNaCislo
        //int pocetSamcuKralikuCislo = Integer.parseInt(pocetSamcuKralikuText);      //prevod stringu na integer, puvodni reseni

        Double pocetKralicatCislo;       //zadefinovani promene, se kterou pracuji v "if" bloku
        Double velikostChovuKralikuCislo;   //zadefinovani promene, se kterou pracuji v "if" bloku

        if (pocetSamcuKralikuCislo > 0) {
            pocetKralicatCislo = (pocetSamicKralikuCislo * 40);     //inicializace promene pocetKralicatCislo, vypocet kralicat
            velikostChovuKralikuCislo = pocetSamcuKralikuCislo + pocetSamicKralikuCislo + pocetKralicatCislo;  //inicializace promene velikostChovuKralikuCislo, vypocet velikosti chovu kraliku, kdyz je pocet samcu > 0
        } else {
            velikostChovuKralikuCislo = pocetSamicKralikuCislo;     //vypocet velikosti chovu kraliku, kdyz nemam zadne samce

        }

        String velikostChovuKralikuText = prevedCisloNaText(velikostChovuKralikuCislo);       //prevod integeru na string, nutne pro zobrazeni v labVelikostChovuKraliku

        labVelikostChovuKraliku.setText(velikostChovuKralikuText);    //zobrazeni velikosti chovu v labVelikostChovuKraliku
    }

    public void vypocetPoctuZrniARadku() {

        String pocetSamicHusText = editPocetSamicHus.getText();  //ulozeni vstupu do promene
        String pocetSamcuHusText = editPocetSamcuHus.getText();  //ulozeni vstupu do promene

        if (!jeCislo(pocetSamcuHusText)) {
            return;
        }

        if (!jeCislo(pocetSamicHusText)) {
            return;
        }

        Double pocetSamicHusCislo = prevedTextNaCislo(pocetSamicHusText);   //prevod stringu na integer
        Double pocetSamcuHusCislo = prevedTextNaCislo(pocetSamcuHusText);   //prevod stringuna integer

        Double pocetHousatCislo = pocetSamicHusCislo * 15;      //vypocet housat

        Double spotrebaZrni;
        Double pocetRadkuPsenice;

        if (pocetSamcuHusCislo > 0) {
            spotrebaZrni = ((pocetSamicHusCislo + pocetSamcuHusCislo + pocetHousatCislo) * 0.25 * 183);  //secteni celeho chovu, pak nasobim poctem psenice na 1 husu a poctem dni v zime

        } else {
            spotrebaZrni = (pocetSamicHusCislo * 0.25 * 183);
        }

        pocetRadkuPsenice = (spotrebaZrni / 2);  //celkovy pocet zrni delim poctem zrni, ktere vypestuji na 1 osetem radku

        String spotrebaZrniText = prevedCisloNaText(spotrebaZrni); //prevod Double na string, nutne pro zobrazeni v labSpotrebaZrniAPocetRadku
        String pocetRadkuPseniceText = prevedCisloNaText(pocetRadkuPsenice); //prevod Double na string, nutne pro zobrazeni v labSpotrebaZrniAPocetRadku

        labSpotrebaZrniAPocetRadku.setText(spotrebaZrniText + " kg pšenice, tedy " + pocetRadkuPseniceText + " řádků.");   //zobrazeni vysledku v labSpotrebaZrniAPocetRadku

    }

    public void velikostChovuHus() {

        String pocetSamcuHusText = editPocetSamcuHus.getText();     //ulozeni vstupu do promene
        String pocetSamicHusText = editPocetSamicHus.getText();     //ulozeni vstupu do promene

        if (!jeCislo(pocetSamcuHusText)) {
            return;
        }

        if (!jeCislo(pocetSamicHusText)) {
            return;
        }

        Double pocetSamcuHusCislo = prevedTextNaCislo(pocetSamcuHusText);   //prevod stringu na integer
        Double pocetSamicHusCislo = prevedTextNaCislo(pocetSamicHusText);   //prevod stringu na integer

        Double pocetHousatCislo;       //zadefinovani promene, se kterou pracuji v "if" bloku
        Double velikostChovuHusCislo;   //zadefinovani promene, se kterou pracuji v "if" bloku

        if (pocetSamcuHusCislo > 0) {
            pocetHousatCislo = (pocetSamicHusCislo * 15);     //inicializace promene pocetHousatCislo, vypocet housat
            velikostChovuHusCislo = pocetSamcuHusCislo + pocetSamicHusCislo + pocetHousatCislo;  //inicializace promene velikostChovuHusCislo, vypocet velikosti chovu hus, kdyz je pocet samcu > 0

        } else {
            velikostChovuHusCislo = pocetSamicHusCislo;     //vypocet velikosti chovu hus, kdyz nemam zadne samce
        }
        
        String velikostChovuHusText = prevedCisloNaText(velikostChovuHusCislo);  //prevod integeru na string, nutne pro zobrazeni v labVelikostChovuHus

        labVelikostChovuHus.setText(velikostChovuHusText); //zobrazeni vysledku v labVelikostChovuHus

    }

    private double prevedTextNaCislo(String text) {
        NumberFormat formatovac = NumberFormat.getInstance();
        ParsePosition pozice = new ParsePosition(0);
        Number cislo = formatovac.parse(text, pozice);

        // pokud nebylo nacteno cislo NEBO se text nedocetl do konce (napriklad pro "11a2")
        if ((cislo == null) || (pozice.getIndex() != text.length())) {
            return Double.NaN;  // Specialni hodnota, ktera NENI cislo (napriklad vysledek  0.0 / 0.0)
        }

        return cislo.doubleValue();
    }

    private String prevedCisloNaText(double cislo) {
        NumberFormat formatovac = NumberFormat.getInstance();
        formatovac.setMaximumFractionDigits(2);
        String text = formatovac.format(cislo);
        return text;
    }

    // Metoda zjisti jestli text je cislo (tak ze jej zkusi prevest)
    private boolean jeCislo(String text) {
        double cislo = prevedTextNaCislo(text);
        return !Double.isNaN(cislo);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner non-commercial license
        labNadpis = new JLabel();
        labNadpisKralici = new JLabel();
        labNadpisHusy = new JLabel();
        labPocetSamcuKraliku = new JLabel();
        editPocetSamcuKraliku = new JTextField();
        labPocetSamcuHus = new JLabel();
        editPocetSamcuHus = new JTextField();
        labErrorMessageSamciKraliku = new JLabel();
        labErrorMessageSamciHus = new JLabel();
        labPocetSamicKraliku = new JLabel();
        editPocetSamicKraliku = new JTextField();
        labPocetSamicHus = new JLabel();
        editPocetSamicHus = new JTextField();
        labErrorMessageSamiceKraliku = new JLabel();
        labErrorMessageSamiceHus = new JLabel();
        labErrorMessageProUzivatele = new JLabel();
        btnVypocti = new JButton();
        labNadpisVelikostChovu = new JLabel();
        labVelikostChovuKraliku = new JLabel();
        labVelikostChovuHus = new JLabel();
        labNadpisPotrebaKrmiva = new JLabel();
        labSpotrebaMrkveAPocetRadku = new JLabel();
        labSpotrebaZrniAPocetRadku = new JLabel();

        //======== this ========
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("farmarka 3.0");
        setBackground(new Color(204, 204, 255));
        Container contentPane = getContentPane();
        contentPane.setLayout(new MigLayout(
                "insets rel,hidemode 3",
                // columns
                "[fill]" +
                        "[fill]" +
                        "[grow,fill]" +
                        "[fill]" +
                        "[grow,fill]" +
                        "[grow,fill]" +
                        "[fill]",
                // rows
                "[grow,fill]" +
                        "[]" +
                        "[]" +
                        "[]" +
                        "[]" +
                        "[]" +
                        "[]" +
                        "[]" +
                        "[]" +
                        "[]" +
                        "[]" +
                        "[]" +
                        "[]" +
                        "[]" +
                        "[]" +
                        "[]" +
                        "[]" +
                        "[]" +
                        "[]" +
                        "[23,grow]"));
        this.contentPane = (JPanel) this.getContentPane();
        this.contentPane.setBackground(this.getBackground());
        LayoutManager layout = this.contentPane.getLayout();
        if (layout instanceof MigLayout) {
            this.migLayoutManager = (MigLayout) layout;
        }

        //---- labNadpis ----
        labNadpis.setText("Evidence kr\u00e1l\u00edk\u016f a hus");
        labNadpis.setFont(new Font("LM Roman Unslanted 10", Font.PLAIN, 18));
        contentPane.add(labNadpis, "cell 1 1");

        //---- labNadpisKralici ----
        labNadpisKralici.setText("Kr\u00e1l\u00edc\u00ed");
        labNadpisKralici.setFont(new Font("LM Sans 10", Font.BOLD, 14));
        contentPane.add(labNadpisKralici, "cell 1 3");

        //---- labNadpisHusy ----
        labNadpisHusy.setText("Husy");
        labNadpisHusy.setFont(new Font("LM Sans 10", Font.BOLD, 14));
        contentPane.add(labNadpisHusy, "cell 4 3");

        //---- labPocetSamcuKraliku ----
        labPocetSamcuKraliku.setText("Po\u010det samc\u016f");
        labPocetSamcuKraliku.setFont(labPocetSamcuKraliku.getFont().deriveFont(labPocetSamcuKraliku.getFont().getStyle() & ~Font.BOLD));
        contentPane.add(labPocetSamcuKraliku, "cell 1 5");

        //---- editPocetSamcuKraliku ----
        editPocetSamcuKraliku.setBackground(Color.lightGray);
        contentPane.add(editPocetSamcuKraliku, "cell 2 5,growx");

        //---- labPocetSamcuHus ----
        labPocetSamcuHus.setText("Po\u010det samc\u016f");
        labPocetSamcuHus.setFont(labPocetSamcuHus.getFont().deriveFont(labPocetSamcuHus.getFont().getStyle() & ~Font.BOLD));
        contentPane.add(labPocetSamcuHus, "cell 4 5");

        //---- editPocetSamcuHus ----
        editPocetSamcuHus.setBackground(Color.lightGray);
        contentPane.add(editPocetSamcuHus, "cell 5 5,growx");
        contentPane.add(labErrorMessageSamciKraliku, "cell 2 6");
        contentPane.add(labErrorMessageSamciHus, "cell 5 6");

        //---- labPocetSamicKraliku ----
        labPocetSamicKraliku.setText("Po\u010det samic");
        labPocetSamicKraliku.setFont(labPocetSamicKraliku.getFont().deriveFont(labPocetSamicKraliku.getFont().getStyle() & ~Font.BOLD));
        contentPane.add(labPocetSamicKraliku, "cell 1 7");

        //---- editPocetSamicKraliku ----
        editPocetSamicKraliku.setBackground(Color.lightGray);
        contentPane.add(editPocetSamicKraliku, "cell 2 7");

        //---- labPocetSamicHus ----
        labPocetSamicHus.setText("Po\u010det samic");
        labPocetSamicHus.setFont(labPocetSamicHus.getFont().deriveFont(labPocetSamicHus.getFont().getStyle() & ~Font.BOLD));
        contentPane.add(labPocetSamicHus, "cell 4 7");

        //---- editPocetSamicHus ----
        editPocetSamicHus.setBackground(Color.lightGray);
        contentPane.add(editPocetSamicHus, "cell 5 7");
        contentPane.add(labErrorMessageSamiceKraliku, "cell 2 8");
        contentPane.add(labErrorMessageSamiceHus, "cell 5 8");
        contentPane.add(labErrorMessageProUzivatele, "cell 1 9");

        //---- btnVypocti ----
        btnVypocti.setText("Vypo\u010dti");
        btnVypocti.setFont(btnVypocti.getFont().deriveFont(btnVypocti.getFont().getStyle() | Font.BOLD));
        btnVypocti.setBackground(new Color(204, 204, 204));
        btnVypocti.addActionListener(e -> poStisknutiBtnVypoctiVypocetKrmeniARadku(e));
        contentPane.add(btnVypocti, "cell 1 10 5 1");

        //---- labNadpisVelikostChovu ----
        labNadpisVelikostChovu.setText("Velikost chovu p\u0159ed zimou:");
        labNadpisVelikostChovu.setFont(new Font("LM Sans 10", Font.BOLD, 14));
        contentPane.add(labNadpisVelikostChovu, "cell 1 12");
        contentPane.add(labVelikostChovuKraliku, "cell 1 14");
        contentPane.add(labVelikostChovuHus, "cell 5 14");

        //---- labNadpisPotrebaKrmiva ----
        labNadpisPotrebaKrmiva.setText("Pot\u0159eba krmiva");
        labNadpisPotrebaKrmiva.setFont(new Font("LM Sans 10", Font.BOLD, 14));
        contentPane.add(labNadpisPotrebaKrmiva, "cell 1 16");
        contentPane.add(labSpotrebaMrkveAPocetRadku, "cell 1 17");
        contentPane.add(labSpotrebaZrniAPocetRadku, "cell 1 18");
        pack();
        setLocationRelativeTo(null);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }
}