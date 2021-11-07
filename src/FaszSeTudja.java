import java.util.Scanner;

public class FaszSeTudja {

    public static void main(String[] args) {

        //elso ertek a oszlop, masodik oszlop
        int[] pivotElemek = {-1, -1};
        int pivotSor = -1;
        int pivotOszlop = -1;
        double[][] endMatrix;
        double[][] matrix = {
                {-4, 0, -2, 8, 8},
                {-11, 9, 3, -5, 17}
        };


        double[] oszlop = oszlop(matrix);
        double[] sor = sor(matrix);


        while (vanESzabadPivotElem(sor, oszlop,matrix) == 1) {
            pivotElemek = pivotElemBekeres();
            pivotSor = pivotElemek[0];
            pivotOszlop = pivotElemek[1];
            if (alljMegKocsog(sor, oszlop, pivotSor, pivotOszlop) != -1) {
                endMatrix = pivotalas(matrix, pivotSor, pivotOszlop, sor, oszlop);
                matrix = endMatrix;
                matrixKiiras(endMatrix);
            } else {
                System.exit(0);
            }
        }
        megallpitas(matrix, sor, oszlop);

    }


    public static void matrixKiiras(double[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }


    }

    public static void sorKiiras(double[] sor) {
        for (int i = 0; i < sor.length; i++) {
            System.out.println(sor[i] + " ");
        }
        System.out.println();

    }

    public static void oszlopKiiras(double[] oszlop) {
        System.out.print("   ");
        for (int i = 0; i < oszlop.length; i++) {
            System.out.print(oszlop[i] + " ");
        }
        System.out.println();

    }

    public static int[] pivotElemBekeres() {
        Scanner input = new Scanner(System.in);
        int[] pivotElemek = {-1, -1};
        System.out.println("Adja meg a pivot elem sorat: ");
        pivotElemek[0] = input.nextInt() - 1;
        System.out.println("Adja meg a pivot elem oszlopat: ");
        pivotElemek[1] = input.nextInt() - 1;

        return (pivotElemek);

    }


    public static double[] sor(double[][] matrix) {
        double[] sor = new double[matrix.length];

        return sor;
    }


    public static double[] oszlop(double[][] matrix) {
        double[] oszlop = new double[matrix[0].length - 1];

        return oszlop;
    }


    public static double[][] pivotalas(double[][] eredetiMatrix, int pivotSor, int pivotOszlop, double[] sor, double[] oszlop) {
        System.out.println();
        System.out.println("PIVOTALOK");
        System.out.println();
        double[][] masoltMatrix = matrixMasolas(eredetiMatrix);


        oszlop[pivotOszlop] = 1;
        oszlopKiiras(oszlop);
        sor[pivotSor] = 1;
        sorKiiras(sor);


        for (int j = 0; j < eredetiMatrix[0].length; j++)
            masoltMatrix[pivotSor][j] = eredetiMatrix[pivotSor][j] / eredetiMatrix[pivotSor][pivotOszlop];
        for (int i = 0; i < eredetiMatrix.length; i++)
            masoltMatrix[i][pivotOszlop] = eredetiMatrix[i][pivotOszlop] / eredetiMatrix[pivotSor][pivotOszlop] * -1;


        for (int i = 0; i < eredetiMatrix.length; i++) {
            for (int j = 0; j < eredetiMatrix[0].length; j++) {
                if (i != pivotSor && j != pivotOszlop) {
                    masoltMatrix[i][j] = eredetiMatrix[i][j] - ((eredetiMatrix[i][pivotOszlop] * eredetiMatrix[pivotSor][j]) / eredetiMatrix[pivotSor][pivotOszlop]);
                }
            }
        }

        masoltMatrix[pivotSor][pivotOszlop] = 1 / eredetiMatrix[pivotSor][pivotOszlop];

        /*for (int i = 0; i< masoltMatrix.length; i++){
            for (int j = 0; j<masoltMatrix[0].length; j++){
                System.out.print(masoltMatrix[i][j] + " ");
            }
            System.out.println();
        }*/

        return masoltMatrix;
    }

    public static double[][] matrixMasolas(double[][] matrix) {

        double[][] copiedMatrix = new double[matrix.length][matrix[0].length];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                copiedMatrix[i][j] = matrix[i][j];
            }
        }


        return copiedMatrix;
    }

    public static int alljMegKocsog(double[] sor, double[] oszlop, int pivotSor, int pivotOszlop) {
        if (oszlop[pivotOszlop] == 1 || sor[pivotSor] == 1)
            return -1;
        return 0;
    }

    public static int vanESzabadPivotElem(double[] oszlop, double[] sor, double[][] matrix) {
        int vanSzabadSor = 0;
        int vanSzabadOszlop = 0;

        for (int i = 0; i < oszlop.length; i++) {
            if (oszlop[i] == 0)
                for (int j = 0; j < sor.length; j++) {
                    if(sor[j]==0){
                        if(matrix[i][j]!=0)
                            vanSzabadOszlop = 1;
                    }
                }
        }

        for (int i = 0; i < sor.length; i++) {
            if (sor[i] == 0) {
                for (int j = 0; j < oszlop.length; j++) {
                    if(oszlop[j]==0){
                        if(matrix[j][i]!=0)
                            vanSzabadSor = 1;
                    }
                }
            }

        }


        if (vanSzabadSor == 1 && vanSzabadOszlop == 1)
            return 1;
        else return 0;

    }

    public static void megallpitas(double[][] matrix, double[] sor, double[] oszlop) {

        int maradtEgysegvektorBazisban = 0;

        for (int i = 0; i < sor.length; i++) {
            if (sor[i] == 0) {
                maradtEgysegvektorBazisban = 1;
                System.out.println("Maradt egysegvektor a bazisban");
                System.out.println(matrix[i][matrix[0].length - 1]);

                if (maradtEgysegvektorBazisban == 1 && matrix[i][matrix[0].length - 1] != 0) {
                    System.out.println("Nincs megoldás");
                    return;
                }
            }
        }

        int MindenOszlopVolt = 1;

        for (int i = 0; i < oszlop.length; i++) {
            if (oszlop[i] == 0)
                MindenOszlopVolt = 0;
        }
        if (MindenOszlopVolt == 1) {
            System.out.println("Egy megoldás van");
            for (int i = 0; i < matrix.length; i++) {
                System.out.println("X" + i + "= " + matrix[i][matrix[0].length - 1]);
            }
        } else System.out.println("Végtelen sok megoldás van");

    }


}

