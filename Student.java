package StudentManagement;

import java.util.Scanner;

public class Student {

    private String firstName;
    private String lastName;
    private int gradeYear;
    private String studentID;
    private String courses = "";
    private int tuitionBalance;
    private static int costOfCourse = 600;
    private static int id = 1000;
    private Scanner scanner;

    public Student() {
        scanner = new Scanner(System.in);
        System.out.print("Enter first name: ");
        this.firstName = scanner.nextLine();

        System.out.print("Enter last name: ");
        this.lastName = scanner.nextLine();

        System.out.print("\n1 - Freshman\n2 - Sophomore\n3 - Junior\n4 - Senior\nEnter grade year: ");
        this.gradeYear = scanner.nextInt();
        setStudentID();
    }

    // Generate an ID
    private void setStudentID() {
        id++;
        this.studentID = gradeYear + "" + id;
    }

    // Enroll in courses
    public void enroll() {
        System.out.println();
        String course;
        while (true) {
            System.out.print("Enter course to enroll (Q to quit): ");
            course = scanner.next();
            if (course.equalsIgnoreCase("Q")) {
                break;
            }
            courses += " " + course;
            tuitionBalance += costOfCourse;
        }
    }

    // View Balance
    public void viewBalance() {
        System.out.println("Your balance is $: " + tuitionBalance);
    }

    // Pay Tuition
    public void payTuition() {
        viewBalance();
        System.out.print("Enter amount to pay $: ");
        int payment = scanner.nextInt();
        tuitionBalance -= payment;
        viewBalance();
    }

    // Show Info
    public String showInfo() {
        return "\n\nStudent Details\n" +
                "Name: " + firstName + " " + lastName +
                "\nGrade Year: " + gradeYear +
                "\nStudent ID: " + studentID +
                "\nCourses Enrolled: " + courses +
                "\nTuition Balance$: " + tuitionBalance;
    }

    // Close scanner
    public void closeScanner() {
        scanner.close();
    }
}
