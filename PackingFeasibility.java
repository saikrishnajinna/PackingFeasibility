import java.util.ArrayList;
import java.util.Scanner;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

class Package{
    public int id;
    public int width;
    public int height;
public Package(int height, int width, int id){
    this.height=height;
    this.width=width;
    this.id=id;
}
}

public class PackingFeasibility {
private ArrayList<Package> packageList = new ArrayList<Package>(); 
private int minimumArea;
private int maximumArea;  

public static void main(String[] args) throws IOException {

    System.out.println("Give inputs and click enter twice:");
    PackingFeasibility p = new PackingFeasibility();
    Scanner input = new Scanner(System.in);

    int id = 1;

    try {

        while (true) { 

            String line = input.nextLine();

            if (line.equals("")) {
                break; 
            }

            String split[] = line.split(",");

            int height = Integer.parseInt(split[0]);
            int width = Integer.parseInt(split[1]);

            p.packageList.add(new Package(height, width, id));              
            id++;
        }

        input.close();
    } catch (Exception ex) {
        ex.printStackTrace();
    }

    p.calcMinimumArea(); 
    
  }

private int getMinimumArea() {

    for (Package p : packageList) {
        minimumArea = minimumArea + p.height * p.width;
    }       
    return minimumArea;
}
private int getMaximumArea() {
    int totalWidth = 0;
    int height = 0;
    int maximumHeight = 0;
    for (Package p : packageList) {
        totalWidth = totalWidth + p.width;
        height = p.height;
        if (height >= maximumHeight) {
            maximumHeight = height;
        }
    }
    maximumArea = totalWidth * maximumHeight;
    return maximumArea;
}
private ArrayList<Point> possiblePlaces(int field[][], int id) {
    ArrayList<Point> places = new ArrayList<Point>();
    for (int i = 0; i < field.length; i++) {
        for (int j = 0; j < field[0].length; j++) {
            if (isEmpty(field, i, j, packageList.get(id).height, packageList.get(id).width)) {
                places.add(new Point(i, j));
            }
        }
    }
    return places;
}
public boolean isEmpty(int[][] field, int x, int y, int height, int width) {
    for (int i = x; i < x + height; i++) {
        for (int j = y; j < y + width; j++) {
            if (i > field.length - 1 || j > field[0].length - 1 || field[i][j] != 0) {
                return false;
            }
        }
    }
    return true;
}
private int[][] markPackage(int field[][], int x, int y, int height, int width, int id) {
    id = id + 1;
    for (int i = x; i < x + height; i++) {
        for (int j = y; j < y + width; j++) {
            field[i][j] = id;
        }
    }
    return field;
}
private void removePackage(int field[][], int x, int y, int height, int width) {
    for (int i = x; i < x + height; i++) {
        for (int j = y; j < y + width; j++) {
            field[i][j] = 0;
        }
    }
}
private void placePackage(int field[][], int id) {
    if (id == packageList.size()) { 
        printSolution(field);           
        System.exit(0);                          
    } 
    ArrayList<Point> possiblePlaces = possiblePlaces(field, id);
    for (int i = 0; i < possiblePlaces.size(); i++) {
        Point p = possiblePlaces.get(i);
        field = markPackage(field, p.x, p.y, packageList.get(id).height, packageList.get(id).width, id);
        placePackage(field, id + 1);      
        removePackage(field, p.x, p.y, packageList.get(id).height, packageList.get(id).width);
    }
}
private ArrayList<Point> calcPossibleAreas() {

    ArrayList<Point> possibleAreas= new ArrayList<Point>();
    ArrayList<Point> factors = new ArrayList<Point>();
    for (int i = getMinimumArea(); i <= getMaximumArea(); i++) {
        factors = calcFactors(i);
        for (int j = 0; j < factors.size(); j++) {
             possibleAreas.add(new Point(factors.get(j).x, factors.get(j).y));
        }
    }
    return possibleAreas;
}
private ArrayList<Point> calcFactors(int number) {
    ArrayList<Point> factors = new ArrayList<Point>();
    for (int i = 1; i <= number; i++) {
        if (number % i == 0) {
            factors.add(new Point(i, number/i));
            factors.add(new Point(number/i, i));
        }
    }
    return factors;
}
private void calcMinimumArea() {
    ArrayList<Point> possibleAreas =  calcPossibleAreas();
    int field[][];
    for (int i = 0; i < possibleAreas.size(); i++) {
        int x = possibleAreas.get(i).x;
        int y = possibleAreas.get(i).y;
        field = new int[x][y];                      
        placePackage(field, 0);              
    }       
}
private void printSolution(int field[][]) { 
    System.out.println("Packing Feasibility truck is:");
    
    for (int i = 0; i < field.length; i++) {
        for (int j = 0; j < field[0].length; j++) {
            System.out.print(" "+field[i][j] + " ");
        }
        System.out.println();
    }               
}


}
