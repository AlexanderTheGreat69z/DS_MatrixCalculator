public class ArrayMatrix extends Matrix{
    private float[][] matrix;

    ArrayMatrix(int row, int col){
        super(row, col);
        this.matrix = new float[row][col];
    }

    public void setEntry(int row, int col, float value){this.matrix[row - 1][col - 1] = value;}
    public float getEntry(int row, int col){return this.matrix[row - 1][col - 1];}

    // private boolean isZeroRow(int row){
    //     for (float entry : this.matrix[row - 1]){
    //         if (entry != 0){return false;}
    //     }
    //     return true;
    // }

    // private void interchange(int row1, int row2){
    //     float[] temp = this.matrix[row1 - 1];
    //     this.matrix[row1 - 1] = this.matrix[row2 - 1];
    //     this.matrix[row2 - 1] = temp;
    // }

    public void addEntry(int row, int col, float add){
        this.matrix[row - 1][col - 1] += add;
    }

    public Matrix copy(){
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

    public Matrix minorMatrix(int mr, int mc){
        Matrix minorMat = new ArrayMatrix(this.row - 1, this.col - 1);
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
        Matrix cofactorMat = this.minorMatrix(mr, mc);
        float det = cofactorMat.determinant();
        float control = (float)(Math.pow(-1, mr+mc));
        return control * det;
    }
}
