package donnu.nikasov.testjamsoft;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Миша on 08.06.2018.
 */

public class Automobile {

    private double price;
    private double speed;
    private double weight;
    private String modelName;

    public Automobile(double price, double speed, double weight, String modelName) {
        this.price = price;
        this.speed = speed;
        this.weight = weight;
        this.modelName = modelName;
    }

    public static ArrayList<Automobile> getAutoList(){

        ArrayList<Automobile> automobileList = new ArrayList<>();

        automobileList.add(new Automobile(38000, 243, 1395, "Audi TT 3.2 Quattro"));
        automobileList.add(new Automobile(45000, 245, 1165, "Renault Clio v6"));
        automobileList.add(new Automobile(50000, 220, 690, "Lotus Elise"));
        automobileList.add(new Automobile(54000, 255, 1480, "Subaru Impreza WRX STi"));
        automobileList.add(new Automobile(65000, 277, 1330, "Porsche Cayman S"));
        automobileList.add(new Automobile(75000, 260, 1575, "Mercedes-Benz CLK 500"));
        automobileList.add(new Automobile(80000, 300, 1459, "Chevrolet Corvette C6"));
        automobileList.add(new Automobile(93000, 295, 1760, "Aston Martin DB9"));
        automobileList.add(new Automobile(105000, 308, 1585, "Porsche 911 turbo S"));
        automobileList.add(new Automobile(118000, 315, 1280, "Lamborghini Gallardo"));
//        automobileList.add(new Automobile(180000, 330, 	1380, "Porsche Carrera GT"));

        return automobileList;
    }

    public double getPrice() {
        return price;
    }

    public double getSpeed() {
        return speed;
    }

    public double getWeight() {
        return weight;
    }

    public String getModelName() {
        return modelName;
    }

    public static Automobile getAutoInPosition(float x, float y){

        ArrayList<Automobile> automobiles = getAutoList();
        Automobile automobile = new Automobile(250000,300,900,"opa");

        for (Automobile auto :
                automobiles) {
            if (auto.getSpeed()==y && auto.getPrice()==x){
                automobile = auto;
            }
            else if (auto.getWeight()==y && auto.getPrice()==x){
                automobile = auto;
            }
        }

        return automobile;
    }
}
