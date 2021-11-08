

public class SzallitasiFeladat {

    private static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) {

        double[][] matrix = {
                {1, 3, 4, 3, 4},
                {3, 3, 2, 1,3},
                {5, 4, 3, 3,3},
                {2,2,4,2,10}
        };

        double[][] matrix2 = Pivot.matrixMasolas(matrix);


        kezdetiMegoldas(matrix);
        double megoldas = megoldas(matrix, matrix2);
        matrixKiiras(matrix);
        sb.append(megoldas);

    }

    public static void matrixKiiras(double[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                sb.append(matrix[i][j] + " ");
            }
            sb.append("\n");
        }


    }

    public static String go(double[][] matrix) {
        double[][] matrix2 = Pivot.matrixMasolas(matrix);

        kezdetiMegoldas(matrix);

        double megoldas = megoldas(matrix, matrix2);

        sb.append(megoldas);

        return sb.toString();
    }


    public static void kezdetiMegoldas(double[][] matrix) {
        double mentes;
        int kezdoI = 0;
        int kezdoJ = 0;
        int i = 0;
        int j = 0;

        while (i < matrix.length-1 && j < matrix[0].length-1) {
            if (oszlopVagySor(matrix, i, j) == true) {
                mentes = matrix[i][matrix[0].length - 1];
                matrix[i][j] = matrix[i][matrix[0].length - 1];
                for (int k = j + 1; k < matrix[0].length; k++)
                    matrix[i][k] = 0;
                matrix[matrix.length - 1][j] = matrix[matrix.length - 1][j] - mentes;
                i++;
                kezdoI++;
                j = kezdoJ;
            } else {
                mentes = matrix[matrix.length - 1][j];
                matrix[i][j] = matrix[matrix.length - 1][j];
                if (i + 1 < matrix.length ) {
                    for (int k = i + 1; k < matrix.length; k++)
                        matrix[k][j] = 0;
                }

                matrix[i][matrix[0].length - 1] = matrix[i][matrix[0].length - 1] - mentes;
                j++;
                kezdoJ++;
                i = kezdoI;
            }

            matrixKiiras(matrix);
            sb.append("\n");
        }




    }

    public static boolean oszlopVagySor(double[][] matrix, int sor, int oszlop) {
        //true -t ad vissza ha sor kisebb, false -t ha az oszlop
        if (matrix[sor][matrix[0].length - 1] < matrix[matrix.length - 1][oszlop] || matrix[sor][matrix[0].length - 1] == matrix[matrix.length - 1][oszlop])
            return true;
        return false;


    }

    public static double megoldas(double[][] matrix, double[][] matrix2) {
        double megoldas = 0;
        for (int i = 0; i < matrix.length-1; i++)
            for (int j = 0; j < matrix[0].length-1; j++) {
                if (matrix[i][j] != 0)
                    megoldas = megoldas +  matrix[i][j] * matrix2[i][j];
            }
        return megoldas;

    }


}
