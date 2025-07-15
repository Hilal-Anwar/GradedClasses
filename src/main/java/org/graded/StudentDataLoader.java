package org.graded;


import java.sql.SQLException;
import java.util.*;



public class StudentDataLoader {
    private final LinkedHashMap<String, Student> studentLinkedHashMap = new LinkedHashMap<>(20);
    public DatabaseLoader databaseLoader;
    public LinkedHashMap<String, Student> getStudentLinkedHashMap() {
        return studentLinkedHashMap;
    }

    public List<Student> getSortedStudentList() {
        List<Student> list = new ArrayList<>(studentLinkedHashMap.values());
        list.sort(Comparator.comparing(Student::points, Comparator.reverseOrder()));
        return list;
    }

    public List<Student> getStudentList() {
        return new ArrayList<>(studentLinkedHashMap.values());
    }

    StudentDataLoader() {
        init();
    }

    private void init() {
        databaseLoader = new DatabaseLoader("G:/My Drive/", "LeaderBoard.db");
        try (var x = databaseLoader.getStatement().executeQuery("SELECT * FROM LEADERS")) {

            while (x.next()) {
                var stu = new Student(x.getString("ED No."),
                        x.getString("Name"), x.getString("Class"),
                        Double.parseDouble(x.getString("Points")));
                studentLinkedHashMap.put(x.getString("ED No."), stu);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
