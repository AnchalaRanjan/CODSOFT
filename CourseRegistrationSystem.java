import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Course {
    private String code;
    private String title;
    private String description;
    private int capacity;
    private int availableSlots;

    public Course(String code, String title, String description, int capacity) {
        this.code = code;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.availableSlots = capacity;
    }

    public String getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getAvailableSlots() {
        return availableSlots;
    }

    public void registerStudent() {
        if (availableSlots > 0) {
            availableSlots--;
        } else {
            System.out.println("Course is full. Cannot register.");
        }
    }

    public void removeStudent() {
        if (availableSlots < capacity) {
            availableSlots++;
        } else {
            System.out.println("No students to remove.");
        }
    }

    public String toString() {
        return "Course Code: " + code + "\nTitle: " + title + "\nDescription: " + description + "\nCapacity: " + capacity + "\nAvailable Slots: " + availableSlots;
    }
}

class Student {
    private String studentID;
    private String name;
    private List<Course> registeredCourses;

    public Student(String studentID, String name) {
        this.studentID = studentID;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }

    public String getStudentID() {
        return studentID;
    }

    public String getName() {
        return name;
    }

    public void registerCourse(Course course) {
        registeredCourses.add(course);
        course.registerStudent();
    }

    public void removeCourse(Course course) {
        registeredCourses.remove(course);
        course.removeStudent();
    }

    public String toString() {
        return "Student ID: " + studentID + "\nName: " + name + "\nRegistered Courses: " + registeredCourses.size();
    }
}

public class CourseRegistrationSystem {
    public static void main(String[] args) {
        List<Course> courses = new ArrayList<>();
        List<Student> students = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Add Course\n2. Register Student\n3. Register Student for Course\n4. Remove Student from Course\n5. Exit");
            System.out.print("Select an option: ");
            int choice = scanner.nextInt();

            if (choice == 1) {
                
                System.out.print("Enter Course Code: ");
                String code = scanner.next();
                System.out.print("Enter Course Title: ");
                String title = scanner.next();
                System.out.print("Enter Course Description: ");
                String description = scanner.next();
                System.out.print("Enter Course Capacity: ");
                int capacity = scanner.nextInt();
                courses.add(new Course(code, title, description, capacity));
            } else if (choice == 2) {
                System.out.print("Enter Student ID: ");
                String studentID = scanner.next();
                System.out.print("Enter Student Name: ");
                String name = scanner.next();
                students.add(new Student(studentID, name));
            } else if (choice == 3) {
                System.out.print("Enter Student ID: ");
                String studentID = scanner.next();
                System.out.print("Enter Course Code: ");
                String courseCode = scanner.next();
                Student student = findStudentByID(studentID, students);
                Course course = findCourseByCode(courseCode, courses);
                if (student != null && course != null) {
                    student.registerCourse(course);
                    System.out.println("Student registered for the course.");
                } else {
                    System.out.println("Student or course not found.");
                }
            } else if (choice == 4) {
                System.out.print("Enter Student ID: ");
                String studentID = scanner.next();
                System.out.print("Enter Course Code: ");
                String courseCode = scanner.next();
                Student student = findStudentByID(studentID, students);
                Course course = findCourseByCode(courseCode, courses);
                if (student != null && course != null) {
                    student.removeCourse(course);
                    System.out.println("Student removed from the course.");
                } else {
                    System.out.println("Student or course not found.");
                }
            } else if (choice == 5) {
                break;
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static Student findStudentByID(String studentID, List<Student> students) {
        for (Student student : students) {
            if (student.getStudentID().equals(studentID)) {
                return student;
            }
        }
        return null;
    }

    private static Course findCourseByCode(String courseCode, List<Course> courses) {
        for (Course course : courses) {
            if (course.getCode().equals(courseCode)) {
                return course;
            }
        }
        return null;
    }
}
