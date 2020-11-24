package com.example.multiappproyecto.level;

import android.media.VolumeShaper;
import android.util.Log;

import com.example.multiappproyecto.level.operation;

public class level {

   int globalUserScore;
   operation currentOperation;
   int currentLevelNumber;
    int levelPoints = 5;

   public level(int GlobalUserScore) {

       globalUserScore = GlobalUserScore;
   }

   private void generateLevelOperation(){

       operation tempOperation = null;

        if (globalUserScore <= levelPoints ){
            tempOperation = new operation(0, 2);
            currentLevelNumber = 1;
        }

       if (globalUserScore >= levelPoints  && globalUserScore <= levelPoints * 2 ){
           tempOperation = new operation(3, 5);
           currentLevelNumber = 2;
       }

       if (globalUserScore >= levelPoints * 2 && globalUserScore <= levelPoints * 3 ){
           tempOperation = new operation(6, 8);
           currentLevelNumber = 3;
       }

       if (globalUserScore >= levelPoints * 3 && globalUserScore <= levelPoints * 4 ){
           tempOperation = new operation(9, 10);
           currentLevelNumber = 4;
       }

       if (globalUserScore >= levelPoints * 4 ){
           tempOperation = new operation(0, 10);
           currentLevelNumber = 5;
       }

       currentOperation = tempOperation;
   }

   public operation getLevelOneOperation(){
       return new operation(0, 2);
   }

    public operation getLevelSecondOperation(){
        return new operation(3, 5);
    }

    public operation getLevelThirdOperation(){
        return new operation(6, 8);
    }

    public operation getLevelFourthOperation(){
        return new operation(9, 10);
    }

    public operation getLevelFithOperation(){
        return new operation(0, 10);
    }

   public operation getNextOperation(){
       generateLevelOperation();
       return currentOperation;
   }

   public void incrementScore(){
       globalUserScore++;
   }

   public int getGlobalUserScore(){
       return globalUserScore;
   }

   public operation getCurrentOperation(){
       return currentOperation;
   }

   public int getCurrentLevelNumber(){
       return currentLevelNumber;
   }

   public int getCurrentLevelNumberScored(int score){

       if (score <= levelPoints ){
           currentLevelNumber = 1;
       }

       if (score >= levelPoints  && score <= levelPoints * 2 ){
           currentLevelNumber = 2;
       }

       if (score >= levelPoints * 2 && score <= levelPoints * 3 ){
           currentLevelNumber = 3;
       }

       if (score >= levelPoints * 3 && score <= levelPoints * 4 ){
           currentLevelNumber = 4;
       }

       if (score >= levelPoints * 4 ){
           currentLevelNumber = 5;
       }

       Log.i("cargando", "nivel de usuario" + currentLevelNumber);

       return  currentLevelNumber;
   }

    public void incrementLevel(){
        currentLevelNumber++;
    }


}
