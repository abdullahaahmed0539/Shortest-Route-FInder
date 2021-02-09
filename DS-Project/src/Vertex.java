public class Vertex {
    String name;
    long distance;
    LinkedList friendsList = new LinkedList();
    Vertex next;
    public Vertex(String n, long dis){
        name = n;
        distance = dis;
    }
    public Vertex(String n){
        name = n;
    }
    public String toString(){
        return this.name;
    }
}
