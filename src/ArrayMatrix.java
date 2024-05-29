public class ArrayMatrix {
    private int row;
    private int col;
    private float[][] matrix;

    ArrayMatrix(int row, int col){
        this.row = row;
        this.col = col;
        this.matrix = new float[row][col];
    }

    public void setEntry(int row, int col, float value){this.matrix[row - 1][col - 1] = value;}
    public float getEntry(int row, int col){return this.matrix[row - 1][col - 1];}

    private boolean isZeroRow(int row){
        for (float entry : this.matrix[row - 1]){
            if (entry != 0){return false;}
        }
        return true;
    }

    private void interchange(int row1, int row2){
        float[] temp = this.matrix[row1 - 1];
        this.matrix[row1 - 1] = this.matrix[row2 - 1];
        this.matrix[row2 - 1] = temp;
    }

    private void addEntry(int row, int col, float add){
        this.matrix[row - 1][col - 1] += add;
    }

    private ArrayMatrix copy(){
        ArrayMatrix matCopy = new ArrayMatrix(this.row, this.col);
        for (int r = 1 ; r <= this.row ; r++){
            for (int c = 1 ; c <= this.col; c++){
                matCopy.setEntry(r, c, this.matrix[r-1][c-1]);
            }
        }
        return matCopy;
    }

    public void display(){
        for(int r = 0; r < this.row; r++){
            System.out.print("[ ");
            for(int c = 0; c < this.col; c++){
                if (c > 0){System.out.print("  ");}
                System.out.printf("%.2f", this.matrix[r][c]);
            }
            System.out.print(" ]");
            System.out.println();
        }
    }

    public void generateRandomMatrix(){
        for (int r = 0; r < this.row; r++){
            for(int c = 0 ; c < this.col; c++){
                int num = (int)(Math.random() * 10);
                this.matrix[r][c] = num;
            }
        }
    }

    public ArrayMatrix minorMatrix(int mr, int mc){
        ArrayMatrix minorMat = new ArrayMatrix(this.row - 1, this.col - 1);
        int row = 1;
        int col = 1;
        for (int r = 1; r <= this.row; r++){
            if (r == mr){
                continue;
            }
            else{
                for (int c = 1; c <= this.col; c++){
                    if(c == mc){
                        continue;
                    }
                    else{
                        minorMat.setEntry(row, col, this.getEntry(r, c));
                        col++;
                    }
                }
                col = 1;
                row++;
            }
        }
        return minorMat;
    }

    public float cofactor(int mr, int mc){
        ArrayMatrix cofactorMat = this.minorMatrix(mr, mc);
        float det = cofactorMat.determinant();
        float control = (float)(Math.pow(-1, mr+mc));
        return control * det;
    }

    public int getRowSize(){return this.row;}
    public int getColSize(){return this.col;}

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // Time comp: O(n^2 / 2) | Space comp: O(m*n)
    public ArrayMatrix reduce(){
        ArrayMatrix reduced = this.copy();
        int lastSlot = this.row;

        for (int c = 1 ; c <= this.col; c++){

            for (int r = c + 1; r <= this.row; r++){
                
                float pivot = reduced.getEntry(c, c);
                float scale = reduced.getEntry(r, c);

                if (pivot != 0){
                    for(int i = 1; i <= this.col; i++){
                        float new_entry = reduced.getEntry(r, i) - (scale/pivot) * reduced.getEntry(c, i);
                        reduced.setEntry(r, i, new_entry);
                    }
                }
            }
        }

        for (int i = 1; i <= this.row; i++){
            if(this.isZeroRow(i)){
                this.interchange(i, lastSlot);
                lastSlot--;
            }
        }
        return reduced;
    }

    // Time comp: O(n^3) | Space comp: O(m*n)
    public ArrayMatrix matrixMultiply(ArrayMatrix b){
        if(this.col == b.getRowSize()){
            int prod_row = this.row;
            int prod_col = b.getColSize();

            ArrayMatrix product = new ArrayMatrix(prod_row, prod_col);
            for(int i = 1; i <= prod_row; i++){

                for(int j = 1; j <= prod_col; j++){

                    for(int k = 1; k <= this.col; k++){

                        float A = this.getEntry(i, k);
                        float B = b.getEntry(k, j);
                        product.addEntry(i, j, A*B);
                        
                    }
                }
            }
            return product;
        }
        else{
            return null;
        }
    }

    // Time comp: O(1) (best-case) or O(n! / 3!) (worst-case) | Space comp: O(1) (best-case) or O(?) (worst-case)
    public float determinant(){
        if(this.row == this.col){
            if (this.row == 1){
                return this.matrix[0][0];
            }
            if (this.row == 2){
                float a = this.matrix[0][0] * this.matrix[1][1];
                float b = this.matrix[1][0] * this.matrix[0][1];
                return a - b;
            }
            if (this.row == 3){
                float aei = this.matrix[0][0] * this.matrix[1][1] * this.matrix[2][2];
                float bfg = this.matrix[0][1] * this.matrix[1][2] * this.matrix[2][0];
                float cdh = this.matrix[0][2] * this.matrix[1][0] * this.matrix[2][1];

                float ceg = this.matrix[0][2] * this.matrix[1][1] * this.matrix[2][0];
                float bdi = this.matrix[0][1] * this.matrix[1][0] * this.matrix[2][2];
                float afh = this.matrix[0][0] * this.matrix[1][2] * this.matrix[2][1];

                float x = aei + bfg + cdh;
                float y = ceg + bdi + afh;

                return x - y;
            }

            if (this.row > 3){
                // ArrayMatrix reduced = this.reduce();
                // int det = 1;

                // for (int i = 1; i <= this.row; i++){
                //     det *= reduced.getEntry(i, i);
                // }

                int defaultRow = 1;
                float det = 0;
                for (int i = 1; i <= this.col; i++){
                    det += this.matrix[defaultRow - 1][i - 1] * this.cofactor(defaultRow, i);
                }
                return det;
            }
        }
        return 0;
    }
}
