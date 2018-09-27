package File;

import java.io.*;

class ObjetStream {

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO自动生成的方法存根
        ObjectOutputStream objectwriter = null;
        ObjectInputStream objectreader = null;

        try {
            objectwriter = new ObjectOutputStream(new FileOutputStream("objectStreamTestOut.txt"));
            objectwriter.writeObject(new Student("gg", 22));
            objectwriter.writeObject(new Student("tt", 18));
            objectwriter.writeObject(new Student("rr", 17));
            objectreader = new ObjectInputStream(new FileInputStream("objectStreamTestOut.txt"));
            for (int i = 0; i < 3; i++) {
                System.out.println(objectreader.readObject());
            }
        } catch (IOException | ClassNotFoundException e) {
            // TODO自动生成的 catch 块
            e.printStackTrace();
        } finally {
            try {
                objectreader.close();
                objectwriter.close();
            } catch (IOException e) {
                // TODO自动生成的 catch 块
                e.printStackTrace();
            }

        }

    }

}

class Student implements Serializable {
    private static final long serialVersionUID = 8591042851620441891L;
    private String name;
    private int age;

    public Student(String name, int age) {
        super();
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student [name=" + name + ", age=" + age + "]";
    }


}
