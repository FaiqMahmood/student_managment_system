package StudentManagement;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

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
        try {
            System.out.print("Enter first name: ");
            String firstNameInput = scanner.nextLine();
            if (firstNameInput.trim().isEmpty() || firstNameInput.matches(".*\\d+.*")) {
                throw new IllegalArgumentException("First name cannot be empty or contain numbers");
            }
            this.firstName = firstNameInput;

            System.out.print("Enter last name: ");
            String lastNameInput = scanner.nextLine();
            if (lastNameInput.trim().isEmpty() || firstNameInput.matches(".*\\d+.*")) {
                throw new IllegalArgumentException("Last name cannot be empty or contain numbers");
            }
            this.lastName = lastNameInput;

            System.out.print("\n1 - Freshman\n2 - Sophomore\n3 - Junior\n4 - Senior\nEnter grade year: ");
            this.gradeYear = scanner.nextInt();
            if (gradeYear < 1 || gradeYear > 4) {
                throw new IllegalArgumentException("Grade year must be between 1 and 4.");
            }
            setStudentID();

        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            e.printStackTrace(); // Print stack trace for debugging
            System.exit(1);
        }
    }

    // Generate an ID
    private void setStudentID() {
        id++;
        this.studentID = gradeYear + "" + id;
    }

    // Enroll in courses
    public void enroll() {
        Set<String> uniqueCourses = new HashSet<>();
        String course;
        // Consume any leftover newline characters
        scanner.nextLine();

        while (true) {
            System.out.print("Enter course to enroll (Q to quit): ");
            course = scanner.nextLine().trim();

            if (course.equalsIgnoreCase("Q")) {
                break;
            }

            // Check if the course is already enrolled
            if (uniqueCourses.contains(course)) {
                System.out.println("Course '" + course + "' is already enrolled.");
                continue;
            }

            // Add the course to the set of unique courses
            uniqueCourses.add(course);
        }

        // Add the courses to the courses string and update tuition balance
        for (String enrolledCourse : uniqueCourses) {
            courses += " " + enrolledCourse;
            tuitionBalance += costOfCourse;
        }
    }

    // View Balance
    public void viewBalance() {
        System.out.println("Your balance is $: " + tuitionBalance);
    }

    // Pay Tuition
    public void payTuition() {
        try {
            viewBalance();
            System.out.print("Enter amount to pay $: ");
            int payment = scanner.nextInt();
            if (payment <= 0) {
                throw new IllegalArgumentException("Payment cannot be zero");
            }
            tuitionBalance -= payment;
            viewBalance();

        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            e.printStackTrace(); // Print stack trace for debugging
            System.exit(1);
        }

    }

    // Show Info
    public String showInfo() {
        // Capitalize the first letter of first name and last name
        String capitalizedFirstName = firstName.substring(0, 1).toUpperCase() + firstName.substring(1);
        String capitalizedLastName = lastName.substring(0, 1).toUpperCase() + lastName.substring(1);

        return "\n\nStudent Details\n" +
                "Name: " + capitalizedFirstName + " " + capitalizedLastName +
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
