import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;

public class Graph {
    Vertex[] adjList;
    Edge[] list;
    int count;
    int ecount;
    Graph(int s) {
        adjList = new Vertex[s];
        list = new Edge[5*s];
        count = 0;
        ecount = 0;
    }
    public void addVertex(String l){
        Vertex v = new Vertex(l,0);
        adjList[count] = v;
        count++;
    }
    public void addEdge(String source, String des, long dis){
        for(int i=0; i<adjList.length; i++) {
            if (adjList[i] != null) {
                if (adjList[i].name.equalsIgnoreCase(source)) {
                    adjList[i].friendsList.insert(des,dis);
                }
                if (adjList[i].name.equalsIgnoreCase(des)) {
                    adjList[i].friendsList.insert(source,dis);
                }
            }
        }
        Edge e = new Edge(new Vertex(source,0),new Vertex(des,0),dis);
        list[ecount] = e;
        ecount++;
        Edge f = new Edge(new Vertex(des,0),new Vertex(source,0),dis);
        list[ecount] = f;
        ecount++;

    }
    public void addEdge(String source, String des){
        for(int i=0; i<adjList.length; i++) {
            if (adjList[i] != null) {
                if (adjList[i].name.equalsIgnoreCase(source)) {
                    adjList[i].friendsList.insert(des);
                }
                if (adjList[i].name.equalsIgnoreCase(des)) {
                    adjList[i].friendsList.insert(source);
                }
            }
        }
    }
    public void display(long a){
        for(int i=0; i<adjList.length; i++) {
            if (adjList[i] != null) {
                System.out.print(adjList[i].name + "---> ");
                Vertex temp = adjList[i].friendsList.head;
                while (temp != null) {
                    System.out.print(temp.name + " = " + temp.distance + ",  ");
                    temp = temp.next;
                }
                System.out.println("  ");
            }
        }
    }
    public void display(){
        for(int i=0; i<adjList.length; i++) {
            if (adjList[i] != null) {
                System.out.print(adjList[i].name + "---> ");
                Vertex temp = adjList[i].friendsList.head;
                while (temp != null) {
                    System.out.print(temp.name + ", ");
                    temp = temp.next;
                }
                System.out.println("  ");
            }
        }
    }
    public void Bst(Graph g, Vertex src, Vertex des){
        ArrayDeque<Vertex> q = new ArrayDeque<>();
        boolean[] visited = new boolean[adjList.length];
        Vertex[] prev = new Vertex[adjList.length];
        q = add(src,q);
        mark(src,visited);
        while(!q.isEmpty()){
            Vertex v = q.remove();
            //System.out.println(v.name);
            Vertex temp = v.friendsList.head;
            //System.out.println(temp.name);
            while(temp != null){
                //System.out.println(temp.name);
                if(!hasVisited(temp,visited)) {
                    q = add(temp,q);
                    mark(temp,visited);
                    mark_prev(temp,prev,v);
                    //System.out.println(temp.name);
                    temp = temp.next;
                }
                else{
                temp=temp.next;}
            }
            //System.out.println("exit");
        }
        shortes(des,prev,adjList);

    }
    public void dijakstras(Graph g, Vertex src, Vertex des){
        for(int i=0; i<adjList.length; i++) {
            if (adjList[i] != null) {
                if(adjList[i].name.equalsIgnoreCase(src.name)) {
                    adjList[i].distance = 0;
                }
                else{
                    adjList[i].distance = Integer.MAX_VALUE;
                }
                Vertex temp = adjList[i].friendsList.head;
                while (temp != null) {
                    temp.distance = Integer.MAX_VALUE;
                    temp = temp.next;
                }
            }
        }
        //System.out.println(adjList[].distance);
        boolean[] visited  = new boolean[adjList.length];
        ArrayList<Vertex> q = new ArrayList<>();
        Vertex[] prev = new Vertex[adjList.length];
        long[] low = new long[adjList.length];
        for(int i=0;i<low.length; i++) {
           low[i] = Integer.MAX_VALUE;
        }
        q = add2(src,q);

        while (!q.isEmpty()){

            Vertex v = lowest(q);
            //System.out.println(v.name);
            q.remove(v);
            mark(v,visited);
            low = evaluate(v,visited,q,prev,low);
        }
        for(int i=0; i<adjList.length; i++){
            if(adjList[i] != null){
                if(adjList[i].name.equalsIgnoreCase(src.name)) continue;
                else System.out.println(src.name + " " + "--> " + adjList[i].name + " " + low[i]);
            }
        }
        System.out.println("***********************Dijakstra's****************************");
        shortes(des,prev,adjList);
        System.out.println();
        System.out.println("***************************************************************");
    }
    public long[] evaluate(Vertex v,boolean[] visited,ArrayList q,Vertex[] prev,long[] low){
        Vertex temp = v.friendsList.head;
        while(temp != null){
            if(!hasVisited(temp,visited)) {
                long dis = getDis(temp,v);
                long newdis = v.distance + dis;
                if(temp.distance > newdis){
                    //update(temp,v,newdis);
                    temp.distance = newdis;
                    //System.out.println(temp.name+ " " + temp.distance);

                    for(int i=0; i<adjList.length; i++){
                        if(adjList[i] != null){
                            if(adjList[i].name.equalsIgnoreCase(temp.name)){
                                adjList[i].distance = newdis;
                                if(low[i] > newdis){
                                    low[i] = newdis;
                                    mark_prev(temp,prev,v);
                                }
                            }
                        }
                    }
                    add2(temp,q);
                }
                temp=temp.next;
            }
            else temp=temp.next;
        }
        return low;
    }
    public long getDis(Vertex v,Vertex e){
        for(int i=0; i< list.length; i++){
            if(list[i] != null) {
                if (list[i].src.name.equalsIgnoreCase(v.name) && list[i].des.name.equalsIgnoreCase(e.name)) {
                    return list[i].dis;
                }
            }
        }
        return -1;
    }

    public boolean hasVisited(Vertex v, boolean[] visited){
        for(int i=0; i< adjList.length; i++){
            if(adjList[i] != null) {
                if (adjList[i].name.equalsIgnoreCase(v.name)) {
                    if (visited[i]) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public void update(Vertex temp,Vertex v,long dis){
        for(int i=0; i< adjList.length; i++){
            if(adjList[i] != null) {
                if (adjList[i].name.equalsIgnoreCase(v.name)) {
                   Vertex temp1 = adjList[i].friendsList.head;
                   while(temp1 != null){
                       if(temp1.name.equalsIgnoreCase(temp.name)){
                           temp1.distance = dis;
                       }
                       temp1 = temp1.next;
                   }
                }
            }
        }
    }
    public void mark(Vertex v, boolean[] visited){
        for(int i=0; i< adjList.length; i++){
            if(adjList[i] != null) {
                if (adjList[i].name.equalsIgnoreCase(v.name)) {
                    visited[i] = true;
                }
            }
        }
    }
    public ArrayDeque add(Vertex v,ArrayDeque q){
        for(int i=0; i<adjList.length; i++){
            if(adjList[i] != null) {
                if (adjList[i].name.equalsIgnoreCase(v.name)) {
                    q.add(adjList[i]);
                    //System.out.println(adjList[i].friendsList.toString());
                }
            }
        }
        return q;
    }
    public ArrayList add2(Vertex v,ArrayList q){
        for(int i=0; i<adjList.length; i++){
            if(adjList[i] != null) {
                if (adjList[i].name.equalsIgnoreCase(v.name)) {
                    q.add(adjList[i]);
                    //System.out.println(adjList[i].friendsList.toString());
                }
            }
        }
        return q;
    }
    public Vertex lowest(ArrayList<Vertex> q){
        long lowest = Integer.MAX_VALUE;
        int index = -1;
        for(int i=0; i<q.size(); i++){
            //System.out.println(q.get(i).distance);
            if(q.get(i).distance<lowest){
                lowest = q.get(i).distance;
                index = i;
            }
        }
        return q.get(index);
    }
    public void mark_prev(Vertex v, Vertex[] prev, Vertex p){
        for(int i=0; i< adjList.length; i++){
            if(adjList[i] != null) {
                if (adjList[i].name.equalsIgnoreCase(v.name)) {
                    prev[i] = p;
                }
            }
        }
    }
    public int getIn(Vertex v, Vertex[] v1){
        for(int i=0; i<v1.length; i++){
            if(adjList[i] != null) {
                if (v1[i].name.equalsIgnoreCase(v.name)) {
                    return i;
                }
            }
        }
        return -1;
    }
    public void shortes(Vertex des, Vertex[] prev, Vertex[] adj){
        Vertex parent = des;
        int x = 0;
        Vertex[] path = new Vertex[prev.length];

        while(parent != null){
            path[x] = parent;
            x++;
            int index = getIn(parent,adj);
            parent = prev[index];
        }
        for(int i=path.length-1; i>=0; i--){
            if(path[i] != null) {
                if(i == 0){
                    System.out.print(path[i].name + " ");
                }
                else System.out.print(path[i].name + " " + "--> ");
            }
        }
    }
}
