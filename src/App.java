public class App {

    public static void testArrayMatrix(int row, int col) {

        // Generate random entries for Array Matrix //
        ArrayMatrix arrMat = new ArrayMatrix(row, col);
        System.out.println("Generated Matrix:");
        arrMat.generateRandomMatrix();
        arrMat.display();

        // DETERMINANT TEST: Calculate the determinant of the matrix //
        long a_start = System.currentTimeMillis();

        System.out.println("\nDeterminant of above matrix:");
        System.out.println(arrMat.determinant());

        long a_end = System.currentTimeMillis();

        // ROW REDUCTION TEST: Row reduce the matrix into Row Echelon Form //
        long b_start = System.currentTimeMillis();

        ArrayMatrix reduced = arrMat.reduce();
        System.out.println("\nRow reduced version:");
        reduced.display();

        long b_end = System.currentTimeMillis();

        // MATRIX MULTIPLICATION TEST: Calculate the product between two matrices //
        long c_start = System.currentTimeMillis();

        ArrayMatrix A = new ArrayMatrix(row, col);
        System.out.println("\nlet A = ");
        A.generateRandomMatrix();
        A.display();

        ArrayMatrix B = new ArrayMatrix(row, col);
        System.out.println("\nlet B = ");
        B.generateRandomMatrix();
        B.display();

        System.out.println("\nA . B =");
        A.matrixMultiply(B).display();

        long c_end = System.currentTimeMillis();


        System.out.println("\nArrayMatrix executions:");
        System.out.println("- Determinant\t: " + (a_end - a_start) + " ms");
        System.out.println("- Reduction\t: " + (b_end - b_start) + " ms");
        System.out.println("- Multiply\t: " + (c_end - c_start) + " ms");
    }

    public static void testLinkedMatrix(int row, int col){
        long start = System.currentTimeMillis();

        // Generate random entries for Array Matrix //
        LinkedMatrix linkMat = new LinkedMatrix(row, col);
        System.out.println("Generated Matrix:");
        linkMat.generateRandomMatrix();
        linkMat.display();

        // DETERMINANT TEST: Calculate the determinant of the matrix //
        long a_start = System.currentTimeMillis();

        System.out.println("\nDeterminant of above matrix:");
        System.out.println(linkMat.determinant());
        
        long a_end = System.currentTimeMillis();

        // ROW REDUCTION TEST: Row reduce the matrix into Row Echelon Form //
        long b_start = System.currentTimeMillis();

        LinkedMatrix reduced = linkMat.reduce();
        System.out.println("\nRow reduced version:");
        reduced.display();

        long b_end = System.currentTimeMillis();

        // MATRIX MULTIPLICATION TEST: Calculate the product between two matrices //
        long c_start = System.currentTimeMillis();

        ArrayMatrix A = new ArrayMatrix(row, col);
        System.out.println("\nlet A = ");
        A.generateRandomMatrix();
        A.display();

        ArrayMatrix B = new ArrayMatrix(row, col);
        System.out.println("\nlet B = ");
        B.generateRandomMatrix();
        B.display();

        System.out.println("\nA . B =");
        A.matrixMultiply(B).display();

        long c_end = System.currentTimeMillis();

        System.out.println("\nLinkedMatrix executions:");
        System.out.println("- Determinant\t: " + (a_end - a_start) + " ms");
        System.out.println("- Reduction\t: " + (b_end - b_start) + " ms");
        System.out.println("- Multiply\t: " + (c_end - c_start) + " ms");
    }

    public static void main(String[] args) throws Exception {

        int row = 10, col = 10;

        // Set matrix entries //
        // float[][] values = {
        //     {3, 0, 2, -1},
        //     {1, 2, 0, 4},
        //     {-2, 1, 5, 2},
        //     {1, 0, 3, 2}
        // };

        // for (int i = 0; i < row; i++) {
        //     for (int j = 0; j < col; j++) {
        //         linkMat.setEntry(i+1, j+1, values[i][j]);
        //     }
        // }

        testArrayMatrix(row, col);
        System.out.println("\n----------------------------------------------------------------------------------------\n");
        testLinkedMatrix(row,col);

    }
}
