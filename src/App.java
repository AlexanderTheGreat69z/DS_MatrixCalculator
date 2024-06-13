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
        long arr_det_start = System.nanoTime();
        System.out.println("Determinant (Array)\t: " + arrMat.determinant());
        long arr_det_end = System.nanoTime();

        // Linked test //
        long link_det_start = System.nanoTime();
        System.out.println("Determinant of (Linked)\t: " + linkMat.determinant());
        long link_det_end = System.nanoTime();

        System.out.println();

        // Runtime output //
        System.out.println("ArrayMatrix Determinant Execution time\t= " + String.format("%.5f", (arr_det_end - arr_det_start) / 1000000) + " ms");
        System.out.println("LinkedMatrix Determinant Execution time\t= " + String.format("%.5f", (link_det_end - link_det_start) / 1000000) + " ms");
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
        long arr_red_start = System.nanoTime();
        Matrix arrRed = arrMat.reduce();
        System.out.println("\nReduced arrMat:");
        arrRed.display();
        long arr_red_end = System.nanoTime();

        // Linked test //
        long link_red_start = System.nanoTime();
        Matrix linkRed = linkMat.reduce();
        System.out.println("\n Reduced linkMat:");
        linkRed.display();
        long link_red_end = System.nanoTime();

        System.out.println();

        // Runtime output //
        System.out.println("ArrayMatrix Reduction Execution time\t= " + String.format("%.5f", (arr_red_end - arr_red_start) / 1000000) + " ms");
        System.out.println("LinkedMatrix Reduction Execution time\t= " + String.format("%.5f", (link_red_end - link_red_start) / 1000000) + " ms");
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

        long arr_mul_start = System.nanoTime();
        System.out.println("\nthen A . B =");
        arr_A.matrixMultiply(arr_B).display();
        long arr_mul_end = System.nanoTime();

        System.out.println("\nConsider LinkedMatrix A = ");
        link_A.display();
        System.out.println("\nConsider LinkedMatrix B = ");
        link_B.display();

        long link_mul_start = System.nanoTime();
        System.out.println("\nthen A . B =");
        link_A.matrixMultiply(link_B).display();
        long link_mul_end = System.nanoTime();

        System.out.println("ArrayMatrix Multiplication Execution time\t= " + String.format("%.5f", (arr_mul_end - arr_mul_start) / 1000000) + " ms");
        System.out.println("LinkedMatrix Multiplication Execution time\t= " + String.format("%.5f", (link_mul_end - link_mul_start) / 1000000) + " ms");

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
