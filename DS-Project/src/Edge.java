public class Edge {
    Vertex src;
    Vertex des;
    Long dis;
    public Edge(Vertex src, Vertex des, long dis){
        this.src = src;
        this.des = des;
        this.dis = dis;
    }
}
