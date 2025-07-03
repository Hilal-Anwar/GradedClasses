package org.graded;

public record Student(String id, String name, String grade, double points) {

    @Override
    public String toString() {
        return "Student[" +
                "id=" + id + ", " +
                "name=" + name + ", " +
                "grade=" + grade + ", " +
                "points=" + points + ']';
    }

}
