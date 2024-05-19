class Entry{
    float value;
    Entry top, bottom, left, right;

    Entry(float value, Entry top, Entry bottom, Entry right, Entry left){
        this.value = value;
        this.top = top;
        this.bottom = bottom;
        this.right = right;
        this.left = left;
    }

    public float getValue(String pos){
        String position = pos.toLowerCase();
        switch(position){
            case "top":
            return top.value;
            
            case "bottom":
            return bottom.value;
            
            case "left":
            return left.value;
            
            case "right":
            return right.value;

            default:
            return 0;
        }
    }
}

class LinkedMatrix {
    private int row, col;
    private Entry[][] matrix;
    LinkedMatrix(int row, int col){
        this.row = row;
        this.col = col;
        this.matrix = new Entry[this.row][this.col];
        generate();
        link();
    }

    private void generate(){
        for(int r = 0; r < this.row; r++){
            for(int c = 0; c < this.col; c++){
                Entry matEntr = new Entry(0, null, null, null, null);
                this.matrix[r][c] = matEntr;
            }
        }
    }

    private void link(){
        for(int r = 0; r < this.row; r++){
            for(int c = 0; c < this.col; c++){
                Entry entry = this.matrix[r][c];
                if(r > 0){entry.top = this.matrix[r-1][c];}
                if(r < this.row - 1){entry.bottom = this.matrix[r+1][c];}
                if(c > 0){entry.left = this.matrix[r][c-1];}
                if(c < this.col - 1){entry.right = this.matrix[r][c+1];}
            }
        }
    }

    public int colSize(){return this.col;}
    public int rowSize(){return this.row;}

    public void display(){
        for(int r = 0; r < this.row; r++){
            System.out.print("[ ");
            for(int c = 0; c < this.col; c++){
                System.out.print(this.matrix[r][c].value);
                if (c < this.col - 1){System.out.print("  ");}
            }
            System.out.print(" ]");
            System.out.println();
        }
    }

    public void editEntry(int row, int col, float value){
        this.matrix[row-1][col-1].value = value;
    }

    public Entry getEntry(int row, int col){
        return this.matrix[row-1][col-1];
    }

    public Entry[] getRow(int r){
        Entry entry = this.matrix[row-1][0];

        int i = 0;
        Entry[] row = new Entry[this.row];
        while(entry != null){
            row[i] = entry;
            entry = entry.right;
            i++;
        }

        return row;
    }

    public Entry[] getColumn(int c){
        Entry entry = this.matrix[0][c-1];

        int i = 0;
        Entry[] col = new Entry[this.col];
        while(entry != null){
            col[i] = entry;
            entry = entry.bottom;
            i++;
        }

        return col;
    }

}
