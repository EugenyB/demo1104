package main;

import model.Student;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
    List<Student> students;

    public static void main(String[] args) {
        new Main().run();
    }

    private void run() {
        int m;
        while ((m = menu())!=0) {
            switch (m) {
                case 1 -> {
                    printList(students);
                }
                case 2 -> {
                    students = createList();
                }
                case 3 -> {
                    List<Student> found = findByRating(75);
                    printList(found);
                }
                case 4 -> {
                    writeToTextFile(students);
                }
                case 5 -> {
                    students = readFromTextFile();
                }
                case 6 -> {
                    writToBinaryFile(students);
                }
                case 7 -> {
                    students = readFromBinaryFile();
                }
            }
        }
    }

    private List<Student> readFromBinaryFile() {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("students.dat"))) {
            return (List<Student>) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error");
            return Collections.emptyList();
        }
    }

    private void writToBinaryFile(List<Student> students) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("students.dat"))) {
            outputStream.writeObject(students);
        } catch (IOException e) {
            System.err.println("Error");
        }
    }

    private List<Student> readFromTextFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("students.txt"))) {
            List<Student> list = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] s = line.split(" ");
                list.add(new Student(Integer.parseInt(s[0]), s[1], Double.parseDouble(s[2])));
            }
            return list;
        } catch (IOException e) {
            return Collections.emptyList();
        }
    }

    private void writeToTextFile(List<Student> students) {
        try (PrintWriter out = new PrintWriter("students.txt")) {
            for (Student student : students) {
                out.println(student.getId() + " " + student.getName() + " " + student.getRating());
            }
        } catch (FileNotFoundException e) {
            System.out.println("error");
        }
    }



    private List<Student> findByRating(int min) {
        ArrayList<Student> list = new ArrayList<>();
        for (Student student : students) {
            if (student.getRating() >= min) {
                list.add(student);
            }
        }
        return list;
    }

    private int menu() {
        System.out.println("1. Show list");
        System.out.println("2. Create list");
        System.out.println("3. Find with rating");
        System.out.println("4. Write To Text file");
        System.out.println("5. Read from Text file");
        System.out.println("6. Write To Binary file");
        System.out.println("7. Read from Binary file");
        System.out.println("0. Exit");
        return new Scanner(System.in).nextInt();
    }

    private void printList(List<Student> students) {
        if (students!=null) {
            for (Student student : students) {
                System.out.println(student);
            }
        }
    }

    public List<Student> createList() {
        return new ArrayList<>(List.of(
            new Student(1, "Vova", 75),
            new Student(2, "Vasya", 75),
            new Student(3, "Katya", 74),
            new Student(4, "Petya", 85),
            new Student(5, "Vova", 65)
        ));
    }
}
