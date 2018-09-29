package File;

import java.io.*;

public class DataStreamDemo
{
    public static void main(String[] args)
    {
        Student[] students = {new Student("Justin",90),
                new Student("momor",95),
                new Student("Bush",88)};
        try
        {
            DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream("dataStreamTest.txt"));

            for(Student member:students)
            {
                //写入UTF字符串
                dataOutputStream.writeUTF(member.getName());
                //写入int数据
                dataOutputStream.writeInt(member.getAge());
            }

            //所有数据至目的地
            dataOutputStream.flush();
            //关闭流
            dataOutputStream.close();

            DataInputStream dataInputStream = new DataInputStream(new FileInputStream("dataStreamTest.txt"));

            Student[] newStudents = new Student[3];
            //读出数据并还原为对象
            for(int i=0;i<newStudents.length;i++)
            {
                //读出UTF字符串
                String name =dataInputStream.readUTF();
                //读出int数据
                int score =dataInputStream.readInt();
                newStudents[i] = new Student(name,score);
            }

            //关闭流
            dataInputStream.close();

            //显示还原后的数据
            for(Student student : newStudents)
            {
                System.out.printf("%s\t%d%n",student.getName(),student.getAge());
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}
