import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Student {
    private static final String FILE_NAME = "studentdata.csv";
    public String studentNo;
    public String studentName;
    public String email;
    public String phone;
    public String course;

    public Student(String studentNo, String studentName, String email, String phone, String course) {
        this.studentNo = studentNo;
        this.studentName = studentName;
        this.email = email;
        this.phone = phone;
        this.course = course;
    }

    public static boolean saveStudent(Student student) {
        try (FileWriter fw = new FileWriter(FILE_NAME, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            // Check if file is empty and add header if it is
           File file = new File(FILE_NAME);
            boolean fileExists = file.exists();
            if (!fileExists || file.length() == 0) {
                out.println("Student No,Name,Email,Phone,Course");
            }

            out.println(student.studentNo + "," + student.studentName + "," + student.email + "," + student.phone + "," + student.course);
            return true;
        } catch (IOException e) {
            System.out.println("Error saving student: " + e.getMessage());
            return false;
        }
    }

    import org.apache.commons.csv.CSVFormat;
    import org.apache.commons.csv.CSVRecord;
    
    public static List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        try (Reader reader = new FileReader(FILE_NAME)) {
            Iterable<CSVRecord> records = CSVFormat.DEFAULT.withHeader().withFirstRecordAsHeader().parse(reader);
            for (CSVRecord record : records) {
                students.add(new Student(
                    record.get("Student No"),
                    record.get("Name"),
                    record.get("Email"),
                    record.get("Phone"),
                    record.get("Course")
                ));
            }
        } catch (IOException e) {
            System.out.println("Error reading students: " + e.getMessage());
        }
        return students;
    }


    public static List<Student> searchStudents(String searchTerm) {
        List<Student> allStudents = getAllStudents();
        List<Student> matchingStudents = new ArrayList<>();
        for (Student student : allStudents) {
            if (student.toString().toLowerCase().contains(searchTerm.toLowerCase())) {
                matchingStudents.add(student);
            }
        }
        return matchingStudents;
    }

    @Override
    public String toString() {
        return studentNo + "," + studentName + "," + email + "," + phone + "," + course;
    }
}
