import java.util.LinkedList;

public class LinkedMatrix {
    int row, col;
    LinkedList<LinkedList<Float>> matrix;
    LinkedMatrix(int row, int col){
        this.row = row;
        this.col = col;
        this.matrix = new LinkedList<>();

        for(int r = 0; r < row; r++){
            LinkedList<Float> matRow = new LinkedList<>();
            for(int c = 0; c < col; c++){
                matRow.add(0.0f);
            }
            this.matrix.add(matRow);
        }
    }

    public float getEntry(int row, int col){
        LinkedList<Float> matRow = this.matrix.get(row-1);
        return matRow.get(col-1);
    }

    public void setEntry(int row, int col, float value){
        LinkedList<Float> matRow = this.matrix.get(row-1);
        matRow.set(col-1, value);
    }

    // private boolean isZeroRow(int row){
    //     LinkedList<Float> matRow = this.matrix.get(row-1);
    //     for(int i = 0; i < this.col; i++){
    //         if(matRow.get(i) != 0){return false;}
    //     }
    //     return true;
    // }

    // private void interchange(int a, int b){
    //     LinkedList<Float> A = this.matrix.get(a);
    //     LinkedList<Float> B = this.matrix.get(b);
    //     LinkedList<Float> C = A;

    //     A = B;
    //     B = C;
    //     C = A;
    // }

    private void addEntry(int row, int col, float add){
        LinkedList<Float> matRow = this.matrix.get(row-1);
        matRow.set(col-1, matRow.get(col-1) + add);
    }

    private LinkedMatrix copy(){
        LinkedMatrix matCopy = new LinkedMatrix(this.row, this.col);
        for (int r = 1 ; r <= this.row ; r++){
            for (int c = 1 ; c <= this.col; c++){
                matCopy.setEntry(r, c, this.getEntry(r, c));
            }
        }
        return matCopy;
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
    }

    public void generateRandomMatrix(){
        for (int r = 1; r <= this.row; r++){
            for(int c = 1 ; c <= this.col; c++){
                int num = (int)(Math.random() * 10);
                this.setEntry(r, c, num);
            }
        }
    }

    public LinkedMatrix minorMatrix(int mr, int mc){
        LinkedMatrix minorMat = new LinkedMatrix(this.row - 1, this.col - 1);
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
        LinkedMatrix cofactorMat = this.minorMatrix(mr, mc);
        float det = cofactorMat.determinant();
        float control = (float)(Math.pow(-1, mr+mc));
        return control * det;
    }

    public int getRowSize(){return this.row;}
    public int getColSize(){return this.col;}

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    public LinkedMatrix reduce(){
        LinkedMatrix reduced = this.copy();
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

        // for (int i = 0; i < this.row; i++){
        //     if(this.isZeroRow(i)){
        //         this.interchange(i, lastSlot-1);
        //         lastSlot--;
        //     }
        // }
        return reduced;
    }

    public LinkedMatrix matrixMultiply(ArrayMatrix b){
        if(this.col == b.getRowSize()){
            int prod_row = this.row;
            int prod_col = b.getColSize();

            LinkedMatrix product = new LinkedMatrix(prod_row, prod_col);
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
                // ArrayMatrix reduced = this.reduce();
                // int det = 1;

                // for (int i = 1; i <= this.row; i++){
                //     det *= reduced.getEntry(i, i);
                // }

                int defaultRow = 1;
                float det = 0;
                for (int i = 1; i <= this.col; i++){
                    det += this.getEntry(defaultRow, i) * this.cofactor(defaultRow, i);
                }
                return det;
            }
        }
        return 0;
    }
}
