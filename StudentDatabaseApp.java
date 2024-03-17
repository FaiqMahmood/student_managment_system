package StudentManagement;

import java.util.Scanner;

public class StudentDatabaseApp {
    public static void main(String[] args) {
        try {
            // Ask how many students we want to add
            System.out.print("Enter the number of students to add: ");
            Scanner in = new Scanner(System.in);
            int numOfStudents = in.nextInt();
            in.nextLine(); // Consume left over empty characters if any.

            if (numOfStudents <= 0) {
                throw new IllegalArgumentException("Number of students must be a positive integer.");
            }

            Student students[] = new Student[numOfStudents];

            // Create n number of students
            for (int i = 0; i < numOfStudents; i++) {
                students[i] = new Student();
                students[i].enroll();
                students[i].payTuition();
                System.out.println(students[i].showInfo());
            }

        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            e.printStackTrace(); // Print stack trace for debugging
            System.exit(1);
        }
    }
}
