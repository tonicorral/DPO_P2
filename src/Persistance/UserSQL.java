package Persistance;

public class UserSQL {


    public void addUser(int studentId, String courseCode, int grade) {
        String query = "INSERT INTO Grade(student_id, course_code, grade) VALUES (" +
                studentId + ", '" +
                courseCode + "', " +
                grade +
                ");";
        SQLConnector.getInstance().insertQuery(query);
    }

}
