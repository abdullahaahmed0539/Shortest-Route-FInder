import javax.swing.*;
import java.util.Scanner;

public class Main {

    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        Graph g = new Graph(10);
        String departure;
        String arrival;

        g.addVertex("karachi");
        g.addVertex("hyderabad");
        g.addVertex("lahore");
        g.addVertex("islamabad");
        g.addVertex("peshawar");
        g.addVertex("quetta");
        g.addVertex("Gilgit");
        g.addVertex("faisalabad");
        g.addVertex("Sukkur");
        g.addVertex("multan");
        g.addEdge("karachi","hyderabad",164);
        g.addEdge("karachi","sukkur",475);
        g.addEdge("hyderabad","sukkur",319);
        g.addEdge("multan","sukkur",433);
        g.addEdge("lahore","peshawar",516);
        g.addEdge("karachi","quetta",689);
        g.addEdge("quetta","peshawar",840);
        g.addEdge("peshawar","gilgit",589);
        g.addEdge("multan","faisalabad",244);
        g.addEdge("multan","lahore",339);
        g.addEdge("faislabad","lahore",186);
        g.addEdge("faisalabad","islamabad",322);
        g.addEdge("lahore","islamabad",377);

        System.out.println("************CITIES TO CHOOSE FROM***********");
        System.out.println("karachi");
        System.out.println("hyderabad");
        System.out.println("lahore");
        System.out.println("islamabad");
        System.out.println("peshawar");
        System.out.println("quetta");
        System.out.println("gilgit");
        System.out.println("faisalabad");
        System.out.println("Sukkur");
        System.out.println("multan");
        System.out.println("****************************************");
        System.out.println();

        System.out.print("Enter departure city:");
        departure = sc.nextLine();
        System.out.println();
        System.out.print("Enter arrival city:");
        arrival = sc.nextLine();
        System.out.println();

        System.out.println("*******************BFS**********************");
        g.Bst(g,new Vertex(departure),new Vertex(arrival));
        System.out.println();
        System.out.println("*********************************************");
        System.out.println("Shortest distance from .........");
        g.dijakstras(g,new Vertex(departure),new Vertex(arrival));
    }
}
