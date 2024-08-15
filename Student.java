import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Student {
    private final String FILE_NAME = "studentdata.csv";
    public String studentNo;
    public String studentName;

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
                System.out.println("Please enter student name:");
                return false;
            }
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
            pw.println(studentNo + "," + studentName);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    public boolean printStudentInformation() {
        List<String[]> students = readAllStudents();
        if (students.isEmpty()) {
            System.out.println("No student data found.");
            return false;
        }

        System.out.println(String.format("%-15s %-40s", "Student No", "Student Name"));
        for (String[] student : students) {
            System.out.println(String.format("%-15s %-40s", student[0], student[1]));
        }
        return true;
    }

    private List<String[]> readAllStudents() {
        List<String[]> students = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 2) {
                    students.add(data);
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return students;
    }
}
