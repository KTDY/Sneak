<<<<<<< HEAD
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
=======
import java.util.Random;

class Map {
    private int map;
    private int posY;
    private int [] mas = new int[10];
    private int posY2;
    private int [] masY = new int[10];
    private int posX;
    private int posX2;


    Map(int map) {
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



    int getMas(int ind) {
        return mas[ind];
    }

    int getMasY(int ind) {
        return masY[ind];
    }

    int getPosX() {
        return posX;
    }

    int getPosX2() {
        return posX2;
    }

    int getPosY() {
        return posY;
>>>>>>> 08c54748154010240202bfd85d817863975c2406
    }

    int getPosY2() {
        return posY2;
    }
}
