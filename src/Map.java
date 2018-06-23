import java.util.Random;

public class Map {
    private int map;
    private int posY;
    private int [] mas = new int[10];
    public Map() {
        map = new Random().nextInt(1);
        switch (map) {
            case (0):
                for(int i = 0; i < 10; i++){
                    mas[i] = i * 2 * 16;
                    posY = 32;
                }


        }
    }

    public int getMas(int ind) {
        return mas[ind];
    }

    public int getPosY() {
        return posY;
    }
}
