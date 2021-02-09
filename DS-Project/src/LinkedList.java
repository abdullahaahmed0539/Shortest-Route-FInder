public class LinkedList {
    Vertex head;
    public void insert(String d,long dis) {
        Vertex n = new Vertex(d,dis);
        {
            if (head == null) {
                head = n;
            } else {
                Vertex temp = head;
                while (temp.next != null) {
                    temp = temp.next;
                }
                temp.next = n;
            }
        }
    }
    public void insert(String d) {
        Vertex n = new Vertex(d);
        {
            if (head == null) {
                head = n;
            } else {
                Vertex temp = head;
                while (temp.next != null) {
                    temp = temp.next;
                }
                temp.next = n;
            }
        }
    }

    public Vertex find(String d) {
        Vertex temp = head;
        while (temp.next != null) {
            if (d.compareTo(temp.name) == 0) {
                return temp;
            }
            temp = temp.next;
        }
        if(d.compareTo(temp.name) == 0){
            return temp;
        }
        return null;
    }

    public void clear() {
        head = null;
    }

    public void delete(String d) {
        Vertex t = find(d);
        if (t == null) {
            System.out.println("value not found");
        }
        boolean flag = false;
        Vertex temp = head;
        while (temp.next != t) {
            if(t == head){
                head = head.next;
                flag = true;
                break;
            }
            temp = temp.next;

        }
        if(flag == false) {
            temp.next = t.next;
        }
    }
    public String toString() {
        String s = " ";
        Vertex temp = head;
            while (temp != null) {
                s += temp.name + " ";
                temp = temp.next;
            }
        return s;
    }
}
