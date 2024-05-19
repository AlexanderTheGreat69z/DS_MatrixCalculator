public class App {
    public static void main(String[] args) throws Exception {
        int row = 2, col = 2;
        ArrayMatrix arrMat = new ArrayMatrix(row, col);

        // float[][] values = {
        //     {3, 0, 2, -1},
        //     {1, 2, 0, 4},
        //     {-2, 1, 5, 2},
        //     {1, 0, 3, 2}
        // };

        // for (int i = 0; i < row; i++) {
        //     for (int j = 0; j < col; j++) {
        //         arrMat.setEntry(i+1, j+1, values[i][j]);
        //     }
        // }

        // Randomly generate entries for matrix //
        System.out.println("Randomly Generated Matrix:");
        arrMat.generateRandomMatrix();
        arrMat.display();

        // ROW REDUCTION TEST: Row reduce the matrix into Row Echelon Form //
        System.out.println("\nRow reduced version:");
        arrMat.reduce();
        arrMat.display();

        // DETERMINANT TEST: Calculate the determinant of the matrix //
        System.out.println("\nDeterminant of above matrix:");
        System.out.println(arrMat.determinant());

        // MATRIX MULTIPLICATION TEST: Calculate the product between two matrices //
        ArrayMatrix A = new ArrayMatrix(row, col);
        ArrayMatrix B = new ArrayMatrix(row, col);

        float[][] val_a = {
            {2, 3},
            {4, 1},
        };

        float[][] val_b = {
            {5, 6},
            {7, 8},
        };

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                A.setEntry(i+1, j+1, val_a[i][j]);
                B.setEntry(i+1, j+1, val_b[i][j]);
            }
        }

        System.out.println("\nA . B =");
        A.matrixMultiply(B).display();
    }
}