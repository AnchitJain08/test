import java.io.*;
import java.util.List;

public class StudentMenu {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String select = "";
            do {
                showMenu();
                select = br.readLine();
                switch (select) {
                    case "1":
                        addStudent(br);
                        break;
                    case "2":
                        searchStudents(br);
                        break;
                    case "3":
                        generateReport();
                        break;
                    case "4":
                        displayAllStudents();
                        break;
                    case "5":
                        return;
                }
            } while (!select.equals("5"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void showMenu() {
        System.out.println("1: Add student");
        System.out.println("2: Search students");
        System.out.println("3: Generate report");
        System.out.println("4: Display all students");
        System.out.println("5: Exit");
        System.out.println("Select Number (1-5):");
    }

    private static void addStudent(BufferedReader br) throws IOException {
        System.out.println("Enter student number:");
        String studentNo = br.readLine();
        System.out.println("Enter student name:");
        String studentName = br.readLine();
        System.out.println("Enter student email:");
        String email = br.readLine();
        System.out.println("Enter student phone:");
        String phone = br.readLine();
        System.out.println("Enter student course:");
        String course = br.readLine();

        Student student = new Student(studentNo, studentName, email, phone, course);
        if (Student.saveStudent(student)) {
            System.out.println("Student saved successfully!");
        } else {
            System.out.println("Error occurred while saving student information.");
        }
    }

    private static void searchStudents(BufferedReader br) throws IOException {
        System.out.println("Enter search term:");
        String searchTerm = br.readLine();
        List<Student> matchingStudents = Student.searchStudents(searchTerm);
        if (matchingStudents.isEmpty()) {
            System.out.println("No matching students found.");
        } else {
            System.out.println("Matching students:");
            for (Student student : matchingStudents) {
                System.out.println(student);
            }
        }
    }

    private static void generateReport() {
        List<Student> allStudents = Student.getAllStudents();
        System.out.println("Course Enrollment Report:");
        allStudents.stream()
                .map(s -> s.course)
                .distinct()
                .forEach(course -> {
                    long count = allStudents.stream().filter(s -> s.course.equals(course)).count();
                    System.out.println(course + ": " + count + " students");
                });
    }

    private static void displayAllStudents() {
        List<Student> allStudents = Student.getAllStudents();
        System.out.println("All Students:");
        System.out.println("Student No | Name | Email | Phone | Course");
        System.out.println("--------------------------------------------");
        for (Student student : allStudents) {
            System.out.printf("%-11s | %-20s | %-25s | %-15s | %-20s%n",
                    student.studentNo, student.studentName, student.email, student.phone, student.course);
        }
    }
}
