import java.io.*;

public class StudentMenu {
    public static void main(String args[]) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            Student s = new Student();
            String select = "";
            do {
                showMenu();
                select = br.readLine();
                switch (select) {
                    case "1":
                        if (s.saveStudentInformation())
                            System.out.println("Save successful...");
                        else
                            System.out.println("Error occurred while saving student information..");
                        break;
                    case "2":
                        if (s.printStudentInformation())
                            System.out.println("Printing successful..");
                        else
                            System.out.println("Error occurred while printing student information..");
                        break;
                    case "3":
                        System.out.println("Enter search term (name or student number):");
                        String searchTerm = br.readLine();
                        s.searchStudents(searchTerm);
                        break;
                    case "4":
                        return;
                }
            } while (!select.equals("4"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void showMenu() {
        System.out.println("1: Add student");
        System.out.println("2: Print all students");
        System.out.println("3: Search students");
        System.out.println("4: Exit");
        System.out.println("Select Number (1, 2, 3, or 4):");
    }
}
