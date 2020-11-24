package com.example.multiappproyecto.level;

import java.util.Random;

public class operation {

    int firstRandomNum;
    int secondRandomNum;
    int result;
    int lowerbound;
    int upperbound;

    public operation(int Lowerbound, int Upperbound){
        lowerbound = Lowerbound;
        upperbound = Upperbound;

        generateRandomOperation();
    }

    private void generateRandomOperation(){

        Random rand = new Random();

        firstRandomNum = rand.nextInt((upperbound - lowerbound) + 1) + lowerbound;
        secondRandomNum = rand.nextInt(11);

        result = firstRandomNum * secondRandomNum;
    }

    public int getFistRandomNumber(){
        return firstRandomNum;
    }

    public int getSecondRandomNumber(){
        return secondRandomNum;
    }

    public int getResult(){
        return result;
    }

}
