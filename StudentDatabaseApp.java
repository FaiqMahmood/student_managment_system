package StudentManagement;

import java.util.Scanner;

public class StudentDatabaseApp {
    public static void main(String a[]) {

        // Ask how many students we want to add
        System.out.println("Enter the number of students to add.");
        Scanner in = new Scanner(System.in);
        int numOfStudents = in.nextInt();
        Student students[] = new Student[numOfStudents];

        // Create n number of students
        for (int i = 0; i < numOfStudents; i++) {
            students[i] = new Student();
            students[i].enroll();
            students[i].payTuition();
            System.out.println(students[i].showInfo());

        }

        in.close();
    }
}
