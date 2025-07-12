package org.graded;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;


// Main class
public class StudentDataLoader {

    private final LinkedHashMap<String, Student> studentTreeMap = new LinkedHashMap<>(20);

    public LinkedHashMap<String, Student> getStudentTreeMap() {
        return studentTreeMap;
    }

    public List<Student> getStudentStream() {
        List<Student> list = new ArrayList<>(studentTreeMap.values());
        list.sort(Comparator.comparing(Student::points, Comparator.reverseOrder()));
        System.out.println(list);
        return list;
    }

    StudentDataLoader() {
        init();
    }

    private void init() {
        DatabaseLoader databaseLoader = new DatabaseLoader("G:/My Drive/", "LeaderBoard.db");
        try (var x = databaseLoader.getStatement().executeQuery("SELECT * FROM LEADERS")) {

            while (x.next()) {
                var stu = new Student(x.getString("ED No."),
                        x.getString("Name"), x.getString("Class"),
                        Double.parseDouble(x.getString("Points")));
                studentTreeMap.put(x.getString("ED No."), stu);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
