import java.io.*;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Student {
    private final String FILE_NAME = "studentdata.csv";
    public String studentNo;
    public String studentName;
    public LocalDate dateOfBirth;
    public String gender;
    public String major;

    public Student() {}

    public Student(String studentNo, String studentName, LocalDate dateOfBirth, String gender, String major) {
        this.studentNo = studentNo;
        this.studentName = studentName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.major = major;
    }

    private boolean acceptStudentInformation() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter the student no:");
            studentNo = br.readLine();
            if (studentNo.isEmpty()) {
                System.out.println("Please enter student no");
                return false;
            }
            System.out.println("Enter the student name:");
            studentName = br.readLine();
            if (studentName.isEmpty()) {
                System.out.println("Please enter student name");
                return false;
            }
            System.out.println("Enter date of birth (YYYY-MM-DD):");
            String dobString = br.readLine();
            dateOfBirth = LocalDate.parse(dobString);
            System.out.println("Enter gender:");
            gender = br.readLine();
            System.out.println("Enter major:");
            major = br.readLine();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    public boolean saveStudentInformation() {
        if (!acceptStudentInformation()) {
            return false;
        }
        try (FileWriter fw = new FileWriter(FILE_NAME, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter pw = new PrintWriter(bw)) {
            pw.println(String.join(",", studentNo, studentName, dateOfBirth.toString(), gender, major));
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    public boolean printStudentInformation() {
        List<Student> students = readAllStudents();
        if (students.isEmpty()) {
            System.out.println("No student data found.");
            return false;
        }

        System.out.println(String.format("%-15s %-40s %-15s %-10s %-20s %-5s", 
            "Student No", "Student Name", "Date of Birth", "Gender", "Major", "Age"));
        for (Student student : students) {
            System.out.println(String.format("%-15s %-40s %-15s %-10s %-20s %-5d",
                student.studentNo, student.studentName, student.dateOfBirth, 
                student.gender, student.major, calculateAge(student.dateOfBirth)));
        }
        return true;
    }

    private List<Student> readAllStudents() {
        List<Student> students = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 5) {
                    students.add(new Student(
                        data[0], data[1], LocalDate.parse(data[2]), data[3], data[4]
                    ));
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return students;
    }

    private int calculateAge(LocalDate dateOfBirth) {
        return Period.between(dateOfBirth, LocalDate.now()).getYears();
    }

    public void searchStudents(String searchTerm) {
        List<Student> allStudents = readAllStudents();
        List<Student> filteredStudents = new ArrayList<>();
        for (Student student : allStudents) {
            if (student.studentNo.toLowerCase().contains(searchTerm.toLowerCase()) ||
                student.studentName.toLowerCase().contains(searchTerm.toLowerCase())) {
                filteredStudents.add(student);
            }
        }
        if (filteredStudents.isEmpty()) {
            System.out.println("No matching students found.");
        } else {
            System.out.println(String.format("%-15s %-40s %-15s %-10s %-20s %-5s", 
                "Student No", "Student Name", "Date of Birth", "Gender", "Major", "Age"));
            for (Student student : filteredStudents) {
                System.out.println(String.format("%-15s %-40s %-15s %-10s %-20s %-5d",
                    student.studentNo, student.studentName, student.dateOfBirth, 
                    student.gender, student.major, calculateAge(student.dateOfBirth)));
            }
        }
    }
}
