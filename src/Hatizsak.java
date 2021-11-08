import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;

public class Hatizsak {

    static int hanyadik = 0;

    public static void main(String[] args) {

        double[] ertek = {52, 54, 54, 1567, 324, 2};
        double[] tomeg = {85, 465, 5, 5, 103, 1999};
        double hatizsak = 2282;
        double[] aranyTomb = aranyTomb(ertek, tomeg);
        ArrayListDouble eredmenyLista = new ArrayList();


        rendezes(aranyTomb, tomeg, ertek);
        eredmeny(tomeg, ertek, hatizsak, eredmenyLista);
        listKiiras(eredmenyLista, tomeg.length + 1);
        maximumKiiras(eredmenyLista, tomeg);
    }

    public static double[][] go(double[] ertekek, double[] sulyok, double meret) {
        double[] ertek = ertekek;
        double[] tomeg = sulyok;
        double hatizsak = meret;
        hanyadik = 0;

        double[] aranyTomb = aranyTomb(ertek, tomeg);
        ArrayListDouble eredmenyLista = new ArrayList();


        rendezes(aranyTomb, tomeg, ertek);
        eredmeny(tomeg, ertek, hatizsak, eredmenyLista);
        maximumKiiras(eredmenyLista, tomeg);

        return convertToArray(eredmenyLista, tomeg.length + 1);
    }

    public static double[][] convertToArray(ArrayListDouble eredmenyLista, int meret){
        double[][] output = new double[eredmenyLista.size()meret][meret];
        NumberFormat nf = new DecimalFormat(#0.0000);

        for (int i = 0; i  eredmenyLista.size(); i++) {
            output[Math.floorDiv(i, meret)][i%meret] = Double.parseDouble(nf.format(eredmenyLista.get(i)).replaceAll(,, .));
        }

        return output;
    }


    public static double[] aranyTomb(double[] ertek, double[] tomeg) {
        double[] aranyTomb = new double[ertek.length];
        for (int i = 0; i  ertek.length; i++) {
            aranyTomb[i] = ertek[i]  tomeg[i];
        }
        return aranyTomb;

    }

    public static void rendezes(double[] aranyTomb, double[] tomeg, double[] ertek) {
        double s1;

        double[] aranyTombEredeti = tombMasolas(aranyTomb);
        double[] rendezettTomeg;
        double[] rendezettErtek;

        rendezettTomeg = tombMasolas(tomeg);
        rendezettErtek = tombMasolas((ertek));

        Arrays.sort(aranyTomb);


        sorrend megforditas
        for (int i = 0; i  aranyTomb.length  2; i++) {
            s1 = aranyTomb[(aranyTomb.length - 1) - i];
            aranyTomb[(aranyTomb.length - 1) - i] = aranyTomb[i];
            aranyTomb[i] = s1;
        }

        int nemvaltozott = 1;
        for (int i = 0; i  tomeg.length; i++) {
            if (aranyTomb[i] != aranyTombEredeti[i]) {
                nemvaltozott = 0;
                break;
            }

        }
        if (nemvaltozott == 1) {
            return;
        }


        A tomegek es ertekek sorrendjet modositja abban a sorrendben, mint az aranyokat
        for (int i = 0; i  aranyTomb.length; i++) {
            for (int j = 0; j  aranyTomb.length; j++) {
                if (aranyTomb[j] == aranyTombEredeti[i]) {
                    tomeg[j] = rendezettTomeg[i];
                    ertek[j] = rendezettErtek[i];

                }
            }
        }


    }

    public static double[] tombMasolas(double[] tomb) {
        double[] masoltTomb = new double[tomb.length];
        for (int i = 0; i  tomb.length; i++) {
            masoltTomb[i] = tomb[i];
        }

        return masoltTomb;
    }



    public static void eredmeny(double[] tomeg, double[] ertek, double hatizsak, ArrayListDouble eredmenyLista) {
a maradekot kell elosztani a tomeggel, es azt beirni az eredmenybe
        double z = 0;

        double hatizsakEredeti = hatizsak;
        int[] fixek = new int[tomeg.length];
        Arrays.fill(fixek, -1);

        for (int i = 0; i = tomeg.length; i++) {
            eredmenyLista.add(0.0);
        }


        for (int i = 0; i  tomeg.length; i++) {
            if (tomeg[i] = hatizsak) {
                eredmenyLista.set(i, 1.0);
                hatizsak = hatizsak - tomeg[i];
                z = z + ertek[i];
                eredmenyLista.set(tomeg.length, z);
            } else {
                nemFerBele(tomeg, ertek, hatizsak, eredmenyLista, fixek, z, hatizsakEredeti, i);
            }

        }
    }

    public static void tortHelyereNulla(double[] tomeg, double[] ertek, double hatizsak, ArrayListDouble eredmenyLista, int[] fixek, int tortIndex) {
        for (int i = 0; i = tomeg.length; i++) {
            eredmenyLista.add(0.0);
        }

        double z = 0;
        hanyadik++;
        double hatizsakEredeti = hatizsak;

        for (int i = 0; i  fixek.length; i++) {
            if (fixek[i] == 1) {

                hatizsak = hatizsak - tomeg[i];
                z = z + ertek[i];
                eredmenyLista.set(hanyadik  (tomeg.length + 1) + i, 1.0);
                eredmenyLista.set(hanyadik  (tomeg.length + 1) + tomeg.length, z);
            } else if (fixek[i] == 0)
                eredmenyLista.set(hanyadik  (tomeg.length + 1) + i, 0.0);
        }


        for (int i = 0; i  tomeg.length; i++) {
            if (fixek[i] == -1) {
                if (tomeg[i] = hatizsak) {
                    eredmenyLista.set(hanyadik  (tomeg.length + 1) + i, 1.0);
                    hatizsak = hatizsak - tomeg[i];
                    z = z + ertek[i];
                    eredmenyLista.set(hanyadik  (tomeg.length + 1) + tomeg.length, z);
                } else {
                    nemFerBele(tomeg, ertek, hatizsak, eredmenyLista, fixek, z, hatizsakEredeti, i);
                    break;
                }
            }
        }

        fixek[tortIndex] = -1;
    }

    public static void nemFerBele(double[] tomeg, double[] ertek, double hatizsak, ArrayListDouble eredmenyLista, int[] fixek, double z, double hatizsakEredeti, int i) {
        eredmenyLista.set(hanyadik  (tomeg.length + 1) + i, hatizsak  tomeg[i]);
        z = z + (hatizsak  tomeg[i])  ertek[i];
        eredmenyLista.set(hanyadik  (tomeg.length + 1) + tomeg.length, z);
        fixek[i] = 0;
        tortHelyereNulla(tomeg, ertek, hatizsakEredeti, eredmenyLista, fixek, i);
        fixek[i] = 1;
        tortHelyereEgy(tomeg, ertek, hatizsakEredeti, eredmenyLista, fixek, i);
        return;
    }

    public static void tortHelyereEgy(double[] tomeg, double[] ertek, double hatizsak, ArrayListDouble eredmenyLista, int[] fixek, int tortIndex) {
        for (int i = 0; i = tomeg.length; i++) {
            eredmenyLista.add(0.0);
        }
        hanyadik++;
        double z = 0;

        double hatizsakEredeti = hatizsak;

        for (int i = 0; i  fixek.length; i++) {
            if (fixek[i] == 1) {

                hatizsak = hatizsak - tomeg[i];
                z = z + ertek[i];
                eredmenyLista.set(hanyadik  (tomeg.length + 1) + i, 1.0);
                eredmenyLista.set(hanyadik  (tomeg.length + 1) + i, 1.0);
            } else if (fixek[i] == 0)
                eredmenyLista.set(hanyadik  (tomeg.length + 1) + i, 0.0);
        }

        if (hatizsak  0) {
            for (int i = 0; i  tomeg.length+1; i++) {
                eredmenyLista.remove(eredmenyLista.size() - 1);
            }
            hanyadik--;
            fixek[tortIndex] = -1;
            return;
        }


        for (int i = 0; i  tomeg.length; i++) {
            if (fixek[i] == -1) {
                if (tomeg[i] = hatizsak) {
                    eredmenyLista.set(hanyadik  (tomeg.length + 1) + i, 1.0);
                    hatizsak = hatizsak - tomeg[i];
                    z += ertek[i];
                    eredmenyLista.set(hanyadik  (tomeg.length + 1) + tomeg.length, z);
                } else {
                    nemFerBele(tomeg, ertek, hatizsak, eredmenyLista, fixek, z, hatizsakEredeti, i);
                }
            }
        }

        fixek[tortIndex] = -1;
    }

    public static void listKiiras(ArrayListDouble lista, int meret) {
        for (int i = 0; i  lista.size(); i++) {
            if (i % meret == 0)
                System.out.println();
            System.out.print(lista.get(i) +  );

        }
    }

    public static double maximumKiiras(ArrayListDouble lista, double[] tomb) {
        double max = 0;
        int vanTort;

        for (int i = tomb.length; i  lista.size(); i = i + tomb.length + 1) {
            vanTort = 0;
            for (int j = 1; j  tomb.length; j++) {
                if (lista.get(i - j) % 1 != 0) {
                    vanTort = 1;
                    break;
                }
            }
            if (vanTort == 0 && lista.get(i)  max) {
                max = lista.get(i);
            }
        }


         System.out.println();
         System.out.println(Optimalis ize  + max);
        return max;
    }

    public static double searchmax(double[][] array) {
        double max = 0;

        for (int i = 0; i  array.length; i++) {
            double hasRational = 0;
            for (int j = 0; j  array[i].length - 1; j++) {
                hasRational += array[i][j];
            }

            if (Math.floor(hasRational) != hasRational)
                continue;

            double localMax = array[i][array[i].length-1];

            max = max  localMax  localMax  max;  ternary operator
        }

        return max;
    }


}



