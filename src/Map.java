import java.util.Random;

public class Map {
    private int map;
    private int posY;
    private int [] mas = new int[10];
    private int posY2;
    private int [] masY = new int[10];
    private int posX;
    private int posX2;


    public Map(int map) {
        if (map == 1) {
            for (int i = 0; i < 10; i++) {
                mas[i] = i * 2 * 16;
                masY[i] = 400;
                posY = 32;
                posY2 = 400;
            }

        }
        if (map == 2){
            for (int i = 0; i < 10; i++) {
                mas[i] = i * 2 * 16;
                posY = 32;
            }
            for (int i = 0; i < 10; i++) {
                posY2 = 288;
            }
        }
        if(map == 3){
            for (int i = 0; i < 10; i++) {
                mas[i] = i * 2 * 16;
                posY = 32;
            }
            for (int i = 0; i < 10; i++) {
                posY2 = 288;
            }
            for (int i = 0; i < 10; i++) {
                masY[i] = i * 2 * 16;
                posX = 32;
            }
            for (int i = 0; i < 10; i++) {
                posX2= 288;
            }
            masY[0] = 400;
        }
    }



    public int getMas(int ind) {
        return mas[ind];
    }

    public int getMasY(int ind) {
        return masY[ind];
    }

    public int getPosX() {
        return posX;
    }

    public int getPosX2() {
        return posX2;
    }

    public int getPosY() {
        return posY;
    }

    public int getPosY2() {
        return posY2;
    }
}
