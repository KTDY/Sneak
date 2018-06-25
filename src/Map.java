public class Map {
    private int map;
    private int posY;
    private int [] X = new int[10];
    private int [] Y = new int[10];
    public Map(int map) {
        if (map == 1) {
            for (int i = 0; i < 10; i++) {
                X[i] = i * 2 * 16;
                Y[i] = 32;
            }

        }
        if(map == 2){
            for(int i = 0; i <10; i++){
                X[i] = Y[i] = i * 48 + 16;

            }
        }
        if(map == 3){
            //с движемым блоком
        }
    }

    public int getMas(int ind) {
        return X[ind];
    }

    public int getPosY(int ind) {
        return Y[ind];
    }
}
