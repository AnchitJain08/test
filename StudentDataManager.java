import java.io.*;
import java.util.*;
import java.nio.file.*;

public class StudentDataManager {
    private static final String CSV_FILE = "students.csv";

    public static void addStudent(String studentNo, String studentName, String email, String phone, String course) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(CSV_FILE, true))) {
            writer.println(String.join(",", studentNo, studentName, email, phone, course));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Map<String, String>> getAllStudents() {
        List<Map<String, String>> students = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(CSV_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 5) {
                    Map<String, String> student = new HashMap<>();
                    student.put("studentNo", data[0]);
                    student.put("studentName", data[1]);
                    student.put("email", data[2]);
                    student.put("phone", data[3]);
                    student.put("course", data[4]);
                    students.add(student);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return students;
    }

    public static List<Map<String, String>> searchStudents(String searchTerm) {
        List<Map<String, String>> allStudents = getAllStudents();
        List<Map<String, String>> matchingStudents = new ArrayList<>();
        for (Map<String, String> student : allStudents) {
            if (student.values().stream().anyMatch(value -> value.toLowerCase().contains(searchTerm.toLowerCase()))) {
                matchingStudents.add(student);
            }
        }
        return matchingStudents;
    }

    public static void updateStudent(String studentNo, String studentName, String email, String phone, String course) {
        List<Map<String, String>> students = getAllStudents();
        List<String> updatedLines = new ArrayList<>();
        boolean found = false;

        for (Map<String, String> student : students) {
            if (student.get("studentNo").equals(studentNo)) {
                updatedLines.add(String.join(",", studentNo, studentName, email, phone, course));
                found = true;
            } else {
                updatedLines.add(String.join(",", student.values().toArray(new String[0])));
            }
        }

        if (!found) {
            updatedLines.add(String.join(",", studentNo, studentName, email, phone, course));
        }

        try {
            Files.write(Paths.get(CSV_FILE), updatedLines);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void deleteStudent(String studentNo) {
        List<Map<String, String>> students = getAllStudents();
        List<String> updatedLines = new ArrayList<>();

        for (Map<String, String> student : students) {
            if (!student.get("studentNo").equals(studentNo)) {
                updatedLines.add(String.join(",", student.values().toArray(new String[0])));
            }
        }

        try {
            Files.write(Paths.get(CSV_FILE), updatedLines);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
