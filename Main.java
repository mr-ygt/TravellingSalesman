package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class Main {

    public int tsp(ArrayList<ArrayList<Integer>> distances, int rowNumber, ArrayList<Integer> sequence)
    {
        int min;
        int sum = 0;
        boolean[] isVisited = new boolean[rowNumber];
        for (int k = 0; k < rowNumber; k++){
            isVisited[k] = false;
        }
        int index = 0;
        int start = 0, finish = 0;
        while(true){
            min = Integer.MAX_VALUE-1;
            for(int j = 1; j < rowNumber; j++){
                if(start != j && distances.get(start).get(j) < min && isVisited[j] == false){
                    min = distances.get(start).get(j);
                    index = j;
                }
            }
            isVisited[index] = true;


            sequence.add(index);
           // System.out.println(index);
            start = index;
            if(min < Integer.MAX_VALUE-1)
                sum += min;
            finish++;
            if(finish == rowNumber){
                sum += distance2Origin.get(index);
                break;
            }
        }
        return sum;
    }

    static ArrayList<Integer> citySq = new ArrayList<Integer>();
    static ArrayList<Integer> distance2Origin = new ArrayList<>();
    public static void main(String[] args) throws IOException {
	// write your code here

        File file = new File("example-input-1.txt");
        Scanner sc = new Scanner(file);
        String[] row;
        int[] numbers = new int[3], numbers2 = new int[3];
        //String line;
        int distance;

        ArrayList<ArrayList<Integer>> distances = new ArrayList<ArrayList<Integer>>();
        Scanner sc2 = new Scanner(file);
        int i,rowNumber = 0,columnNumber = 0;
        while(sc.hasNextLine()){
            //System.out.println(line = sc.nextLine());
            row = sc.nextLine().split(" ");
            for(i = 0; i < 3; i++){
                numbers[i] = Integer.parseInt(row[i]);
            }
            distances.add(new ArrayList<Integer>());
            while(sc2.hasNextLine()){
                //System.out.println(line = sc2.nextLine());
                row = sc2.nextLine().split(" ");
                for(i = 0; i < 3; i++){
                    numbers2[i] = Integer.parseInt(row[i]);
                }
                distance = (int) Math.round(Math.sqrt(Math.pow(numbers[1] - numbers2[1],2) + Math.pow(numbers[2] - numbers2[2],2)));
                //System.out.println(distance);
                distances.get(rowNumber).add(columnNumber,distance);
                columnNumber++;
                //System.out.println(distance);
                if(rowNumber == 0)distance2Origin.add(distance);
            }
            sc2.close();
            sc2 = new Scanner(file);
            columnNumber = 0;
            rowNumber++;
        }
        sc.close();
        //int[] citySq = new int[rowNumber];
        int totalDistance = Integer.MAX_VALUE;
        Main obj = new Main();
        totalDistance = obj.tsp(distances,rowNumber,citySq);

        File writeFile = new File("test-output-3.txt");
        writeFile.createNewFile();
        FileWriter mywriter = new FileWriter("test-output-3.txt", true);
        mywriter.write(totalDistance + " \n");
        System.out.println(totalDistance);
        for(int m = 0; m < rowNumber-1; m++){
            mywriter.write(citySq.get(m)+ " \n");
            System.out.println(citySq.get(m));
        }
        mywriter.close();

    }
}
