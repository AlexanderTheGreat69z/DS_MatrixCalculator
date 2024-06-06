abstract class Matrix {

    protected int row;
    protected int col;

    Matrix(int row, int col){
        this.row = row;
        this.col = col;
    }

    public int getRowSize(){return this.row;}
    public int getColSize(){return this.col;}

    public abstract float getEntry(int row, int col);
    public abstract void setEntry(int row, int col, float value);
    public abstract void addEntry(int row, int col, float add);

    public abstract Matrix copy();
    // public abstract boolean isZeroRow(int row);
    // public abstract void interchange(int a, int b);


    public abstract Matrix minorMatrix(int mr, int mc);
    public abstract float cofactor(int mr, int mc);

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // Time comp: O(n^2 / 2) | Space comp: O(m*n)
    public Matrix reduce(){
        Matrix reduced = this.copy();
        // int lastSlot = this.row;

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

        // for (int i = 1; i <= this.row; i++){
        //     if(this.isZeroRow(i)){
        //         this.interchange(i, lastSlot);
        //         lastSlot--;
        //     }
        // }
        return reduced;
    };
    
    // Time comp: O(1) (best-case) or O(n) (worst-case) | Space comp: O(1) (best-case) or O(n^2) (worst-case)
    public float determinant(){
        if(this.row == this.col){
            if (this.row == 1){
                return this.getEntry(1, 1);
            }
            if (this.row == 2){
                float a = this.getEntry(1, 1) * this.getEntry(2, 2);
                float b = this.getEntry(2, 1) * this.getEntry(1, 2);
                return a - b;
            }
            if (this.row == 3){
                float aei = this.getEntry(1, 1) * this.getEntry(2,2) * this.getEntry(3, 3);
                float bfg = this.getEntry(1, 2) * this.getEntry(2,3) * this.getEntry(3, 1);
                float cdh = this.getEntry(1, 3) * this.getEntry(2,1) * this.getEntry(3, 2);

                float ceg = this.getEntry(1, 3) * this.getEntry(2,2) * this.getEntry(3, 1); 
                float bdi = this.getEntry(1, 2) * this.getEntry(2,1) * this.getEntry(3, 3);
                float afh = this.getEntry(1, 1) * this.getEntry(2,3) * this.getEntry(3, 2);

                float x = aei + bfg + cdh;
                float y = ceg + bdi + afh;

                return x - y;
            }

            if (this.row > 3){
                Matrix reduced = this.reduce();
                int det = 1;

                for (int i = 1; i <= this.row; i++){
                    det *= reduced.getEntry(i, i);
                }

                // int defaultRow = 1;
                // float det = 0;
                // for (int i = 1; i <= this.col; i++){
                //     det += this.getEntry(defaultRow, i) * this.cofactor(defaultRow, i);
                // }

                return det;
            }
        }
        return 0;
    }
    
    // Time comp: O(n^3) | Space comp: O(m*n)
    public Matrix matrixMultiply(Matrix b){
        if(this.col == b.getRowSize()){
            int prod_row = this.row;
            int prod_col = b.getColSize();

            Matrix product = new ArrayMatrix(prod_row, prod_col);
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

    public void display(){
        for(int r = 0; r < this.row; r++){
            System.out.print("[ ");
            for(int c = 0; c < this.col; c++){
                if (c > 0){System.out.print("  ");}
                System.out.printf("%.2f", this.getEntry(r+1, c+1));
            }
            System.out.print(" ]");
            System.out.println();
        }
    };
}
