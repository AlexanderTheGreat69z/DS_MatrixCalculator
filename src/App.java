public class App {

    public static void determinantTest(int row, int col) {
        // Initialize Matrix Data Structures //
        ArrayMatrix arrMat = new ArrayMatrix(row, col);
        LinkedMatrix linkMat = new LinkedMatrix(row, col);

        // Generate Random Entries //
        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= col; j++) {
                float entry = (int) (Math.random() * 10);
                arrMat.setEntry(i, j, entry);
                linkMat.setEntry(i, j, entry);
            }
        }

        // Display Matrix //
        System.out.println("Generated Matrix:");
        arrMat.display();
        System.out.println();

        // Array test //
        long arr_det_start = System.currentTimeMillis();
        System.out.println("Determinant (Array)\t: " + arrMat.determinant());
        long arr_det_end = System.currentTimeMillis();

        // Linked test //
        long link_det_start = System.currentTimeMillis();
        System.out.println("Determinant of (Linked)\t: " + linkMat.determinant());
        long link_det_end = System.currentTimeMillis();

        System.out.println();

        // Runtime output //
        System.out.println("ArrayMatrix Determinant Execution time\t= " + (arr_det_end - arr_det_start) + " ms");
        System.out.println("LinkedMatrix Determinant Execution time\t= " + (link_det_end - link_det_start) + " ms");
    }

    public static void reductionTest(int row, int col) {
        // Initialize Matrix Data Structures //
        ArrayMatrix arrMat = new ArrayMatrix(row, col);
        LinkedMatrix linkMat = new LinkedMatrix(row, col);

        // Generate Random Entries //
        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= col; j++) {
                float entry = (int) (Math.random() * 10);
                arrMat.setEntry(i, j, entry);
                linkMat.setEntry(i, j, entry);
            }
        }

        // Display Matrix //
        System.out.println("Generated Matrix:");
        arrMat.display();

        // Array test //
        long arr_red_start = System.currentTimeMillis();
        Matrix arrRed = arrMat.reduce();
        System.out.println("\nReduced arrMat:");
        arrRed.display();
        long arr_red_end = System.currentTimeMillis();

        // Linked test //
        long link_red_start = System.currentTimeMillis();
        Matrix linkRed = linkMat.reduce();
        System.out.println("\n Reduced linkMat:");
        linkRed.display();
        long link_red_end = System.currentTimeMillis();

        System.out.println();

        // Runtime output //
        System.out.println("ArrayMatrix Reduction Execution time\t= " + (arr_red_end - arr_red_start) + " ms");
        System.out.println("LinkedMatrix Reduction Execution time\t= " + (link_red_end - link_red_start) + " ms");
    }

    public static void multiplicationTest(int row, int col){
        // MULTIPLICATION TEST //
        ArrayMatrix arr_A = new ArrayMatrix(row, col);
        ArrayMatrix arr_B = new ArrayMatrix(row, col);

        LinkedMatrix link_A = new LinkedMatrix(row, col);
        LinkedMatrix link_B = new LinkedMatrix(row, col);

        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= col; j++) {
                float val_A = (int) (Math.random() * 10);
                float val_B = (int) (Math.random() * 10);

                arr_A.setEntry(i, j, val_A);
                link_A.setEntry(i, j, val_A);

                arr_B.setEntry(i, j, val_B);
                link_B.setEntry(i, j, val_B);
            }
        }

        System.out.println("\nConsider ArrayMatrix A = ");
        arr_A.display();
        System.out.println("\nConsider ArrayMatrix B = ");
        arr_B.display();

        long arr_mul_start = System.currentTimeMillis();
        System.out.println("\nthen A . B =");
        arr_A.matrixMultiply(arr_B).display();
        long arr_mul_end = System.currentTimeMillis();

        System.out.println("\nConsider LinkedMatrix A = ");
        link_A.display();
        System.out.println("\nConsider LinkedMatrix B = ");
        link_B.display();

        long link_mul_start = System.currentTimeMillis();
        System.out.println("\nthen A . B =");
        link_A.matrixMultiply(link_B).display();
        long link_mul_end = System.currentTimeMillis();

        System.out.println("ArrayMatrix Multiplication Execution time\t= " + (arr_mul_end - arr_mul_start) + " ms");
        System.out.println("LinkedMatrix Multiplication Execution time\t= " + (link_mul_end - link_mul_start) + " ms");

    }
    
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

        Matrix reduced = arrMat.reduce();
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

        Matrix reduced = linkMat.reduce();
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

        // Modify matrix size
        int row = 3, col = 3;
        
        // Determinant Test
        determinantTest(row, col);

        // Reduction Test
        reductionTest(row, col);

        // Multiplication Test
        multiplicationTest(row, col);
    }
}
