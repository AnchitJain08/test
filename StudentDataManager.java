import java.io.*;
import java.util.*;
import java.nio.file.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import com.google.gson.Gson;

@WebServlet("/api/*")
public class StudentManagementServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private Gson gson = new Gson();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String pathInfo = request.getPathInfo();
        if (pathInfo == null || pathInfo.equals("/")) {
            // Get all students
            List<Map<String, String>> students = StudentDataManager.getAllStudents();
            response.getWriter().write(gson.toJson(students));
        } else if (pathInfo.startsWith("/search")) {
            // Search students
            String searchTerm = request.getParameter("term");
            List<Map<String, String>> results = StudentDataManager.searchStudents(searchTerm);
            response.getWriter().write(gson.toJson(results));
        } else {
            // Get specific student
            String studentNo = pathInfo.substring(1);
            List<Map<String, String>> students = StudentDataManager.getAllStudents();
            Map<String, String> student = students.stream()
                .filter(s -> s.get("studentNo").equals(studentNo))
                .findFirst()
                .orElse(null);
            if (student != null) {
                response.getWriter().write(gson.toJson(student));
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        BufferedReader reader = request.getReader();
        Map<String, String> studentData = gson.fromJson(reader, Map.class);

        StudentDataManager.addStudent(
            studentData.get("studentNo"),
            studentData.get("studentName"),
            studentData.get("email"),
            studentData.get("phone"),
            studentData.get("course")
        );

        response.getWriter().write("{\"success\": true}");
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String studentNo = request.getPathInfo().substring(1);
        BufferedReader reader = request.getReader();
        Map<String, String> studentData = gson.fromJson(reader, Map.class);

        StudentDataManager.updateStudent(
            studentNo,
            studentData.get("studentName"),
            studentData.get("email"),
            studentData.get("phone"),
            studentData.get("course")
        );

        response.getWriter().write("{\"success\": true}");
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String studentNo = request.getPathInfo().substring(1);
        StudentDataManager.deleteStudent(studentNo);

        response.getWriter().write("{\"success\": true}");
    }
}

class StudentDataManager {
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
