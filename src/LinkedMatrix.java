import java.util.LinkedList;

public class LinkedMatrix extends Matrix{
    LinkedList<LinkedList<Float>> matrix;
    
    LinkedMatrix(int row, int col){
        super(row, col);
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

    public void addEntry(int row, int col, float add){
        LinkedList<Float> matRow = this.matrix.get(row-1);
        matRow.set(col-1, matRow.get(col-1) + add);
    }

    public Matrix copy(){
        Matrix matCopy = new LinkedMatrix(this.row, this.col);
        for (int r = 1 ; r <= this.row ; r++){
            for (int c = 1 ; c <= this.col; c++){
                matCopy.setEntry(r, c, this.getEntry(r, c));
            }
        }
        return matCopy;
    }


    public void generateRandomMatrix(){
        for (int r = 1; r <= this.row; r++){
            for(int c = 1 ; c <= this.col; c++){
                int num = (int)(Math.random() * 10);
                this.setEntry(r, c, num);
            }
        }
    }

    public Matrix minorMatrix(int mr, int mc){
        Matrix minorMat = new LinkedMatrix(this.row - 1, this.col - 1);
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
