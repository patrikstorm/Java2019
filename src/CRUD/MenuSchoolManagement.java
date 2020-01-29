package CRUD;

import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import schoolmanagement.domain.Education;
import schoolmanagement.domain.Student;

public class MenuSchoolManagement {

    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU");

    static Scanner sc = new Scanner(System.in);

    static boolean loop = true;

    public static void menu() {

        System.out.println("1. StudentManagement");
        System.out.println("2. TeacherManagement");
        System.out.println("3. EducationManagement");
        System.out.println("4. CourseManagement");
        System.out.println("0. Exit");
        
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 0:
                    System.exit(0);
                    break;
                case 1:
                    StudentManagementMenu();
                    break;

                case 2:
                    TeacherManagementMenu();
                    break;

                case 3:
                    EducationManagementMenu();
                    break;

                case 4:
                    CourseManagementMenu();
                    break;

                default:
                    System.out.println("No such option");

            }
        

    }

    private static void StudentManagementMenu() {
        loop = true;
        System.out.println("1. Create student");
        System.out.println("2. Update student");
        System.out.println("3. Delete student");
        System.out.println("4. Show student");
        System.out.println("5. Add student to education");
        System.out.println("6. Show all students");
        System.out.println("0. Exit");

        int choice = sc.nextInt();
        sc.nextLine();

        EntityManager em = emf.createEntityManager();

        switch (choice) {
            case 0:
                loop = false;
                break;
            case 1:
                System.out.println("What is the name of the student?");
                String name = sc.nextLine();

                Student student = new Student(name);

                StudentDAO.createStudent(student);
                break;

            case 2:
                StudentDAO.showAll();
                System.out.println("Which student do you want to update? (by id)");
                try {
                    Long studentId = sc.nextLong();
                    sc.nextLine();

                    StudentDAO.updateStudent(studentId);
                } catch (Exception e) {
                    System.out.println("Exception occured");
                }
                break;

            case 3:
                StudentDAO.showAll();
                System.out.println("Which student do you want to delete? (by id)");
                try {
                    Long studentId;
                    studentId = sc.nextLong();
                    sc.nextLine();

                    em.getTransaction().begin();
                    em.remove(em.find(Student.class, studentId));
                    em.getTransaction().commit();
                } catch (Exception e) {
                    System.out.println("Exception occured");
                }
                break;

            case 4:
                StudentDAO.showAll();
                System.out.println("Which student do you want to show? (by id)");
                try {
                    Long studentId = sc.nextLong();
                    sc.nextLine();

                    Student foundStudent = em.find(Student.class, studentId);
                    System.out.println(foundStudent);
                } catch (Exception e) {
                    System.out.println("Exception occured");
                }
                break;
            case 5:

                StudentDAO.showAll();

                System.out.println("Choose student");

                try {
                    Long studentId = sc.nextLong();
                    sc.nextLine();

                    Student foundStudent = em.find(Student.class, studentId);
                    EducationDAO.showAll();

                    System.out.println("Choose education");
                    Long educationtId = sc.nextLong();
                    sc.nextLine();

                    Education foundEducation = em.find(Education.class, educationtId);

                    em.getTransaction().begin();

                    foundEducation.addStudent(foundStudent);

                    em.getTransaction().commit();

                    System.out.println(foundStudent);
                } catch (Exception e) {
                    System.out.println("Exception occured");
                }
                break;

            case 6:
                StudentDAO.showAll();
                break;

            default:

                System.out.println("No such option");
        }

        em.close();
    }

    private static void TeacherManagementMenu() {
        loop = true;

        System.out.println("1. Create teacher");
        System.out.println("2. Update teacher");
        System.out.println("3. Delete teacher");
        System.out.println("4. Show specific teacher");
        System.out.println("5. Add course to teacher");
        System.out.println("6. Show all teachers");
        System.out.println("0. Exit");

        int choice = sc.nextInt();
        sc.nextLine();

        EntityManager em = emf.createEntityManager();

        switch (choice) {
            case 0:
                loop = false;
                break;
            case 1:
                TeacherDAO.createTeacher();

                break;

            case 2:
                TeacherDAO.showAll();
                System.out.println("Choose the teacher to update (by id)");
                try {
                    Long teacherId = sc.nextLong();
                    sc.nextLine();
                    TeacherDAO.updateTeacher(teacherId);
                } catch (Exception e) {
                    System.out.println("Exception occured");
                }

                break;

            case 3:

                TeacherDAO.showAll();
                System.out.println("Which teacher do you want to delete? (by id)");
                try {
                    Long teacherId = sc.nextLong();
                    sc.nextLine();

                    TeacherDAO.deleteTeacher(teacherId);
                } catch (Exception e) {
                    System.out.println("Exception occured");
                }
                break;

            case 4:

                TeacherDAO.showAll();
                System.out.println("Which teacher do you want to see? (by id)");
                try {
                    Long teacherId = sc.nextLong();
                    sc.nextLine();

                    TeacherDAO.showTeacher(teacherId);
                } catch (Exception e) {
                    System.out.println("Exception occured");
                }
                break;

            case 5:
                try {
                    CourseDAO.showAll();
                    System.out.println("Choose which course to add to the teacher");
                    Long courseId = sc.nextLong();
                    sc.nextLine();

                    TeacherDAO.showAll();
                    System.out.println("Choose which teacher to add the course to");
                    Long teacherId = sc.nextLong();
                    sc.nextLine();

                    TeacherDAO.addCourse(courseId, teacherId);
                } catch (Exception e) {
                    System.out.println("Exception occured");
                }
                break;

            case 6:
                TeacherDAO.showAll();
                break;

            default:
                System.out.println("No such option");
        }

    }

    private static void EducationManagementMenu() {
        loop = true;
        System.out.println("1. Create education");
        System.out.println("2. Update education");
        System.out.println("3. Delete education");
        System.out.println("4. Show specific education");
        System.out.println("5. Show all educations");
        System.out.println("0. Exit");

        int choice = sc.nextInt();
        sc.nextLine();

        EntityManager em = emf.createEntityManager();

        switch (choice) {
            case 0:
                loop = false;
                break;
            case 1:
                EducationDAO.createEducation();

                break;

            case 2:
                EducationDAO.showAll();
                System.out.println("Choose the education to update (by id)");
                try {
                    Long educationId = sc.nextLong();
                    sc.nextLine();

                    EducationDAO.updateEducation(educationId);
                } catch (Exception e) {
                    System.out.println("Exception occured");
                }
                break;

            case 3:

                EducationDAO.showAll();
                System.out.println("Which education do you want to delete? (by id)");
                try {
                    Long educationId = sc.nextLong();
                    sc.nextLine();

                    EducationDAO.deleteEducation(educationId);
                } catch (Exception e) {
                    System.out.println("Exception occured");
                }
                break;

            case 4:
                EducationDAO.showAll();
                System.out.println("Which education do you want to see?");
                try {
                    Long educationId = sc.nextLong();
                    sc.nextLine();

                    EducationDAO.showEducation(educationId);
                } catch (Exception e) {
                    System.out.println("Exception occured");
                }
                break;

            case 5:
                EducationDAO.showAll();
                break;

            default:
                System.out.println("No such option");
        }

    }

    private static void CourseManagementMenu() {
        loop = true;

        System.out.println("1. Create course");
        System.out.println("2. Update course");
        System.out.println("3. Delete course");
        System.out.println("4. Show specific course");
        System.out.println("5. Show all courses");
        System.out.println("0. Exit");

        int choice = sc.nextInt();
        sc.nextLine();

        EntityManager em = emf.createEntityManager();

        switch (choice) {
            case 0:
                loop = false;
                break;
            case 1:
                CourseDAO.createCourse();

                break;

            case 2:
                CourseDAO.showAll();
                System.out.println("Choose the course to update by id");
                try {
                    Long courseId = sc.nextLong();
                    sc.nextLine();

                    CourseDAO.updateCourse(courseId);
                } catch (Exception e) {
                    System.out.println("Exception occured");
                }
                break;

            case 3:

                CourseDAO.showAll();
                System.out.println("Which course do you want to delete?");
                try {
                    Long courseId = sc.nextLong();
                    sc.nextLine();

                    CourseDAO.deleteCourse(courseId);
                } catch (Exception e) {
                    System.out.println("Exception occured");
                }
                break;

            case 4:
                CourseDAO.showAll();
                System.out.println("Which course do you want to see?");
                try {
                    Long courseId = sc.nextLong();
                    sc.nextLine();

                    CourseDAO.showCourse(courseId);
                } catch (Exception e) {
                    System.out.println("Exception occured");
                }
                break;
            case 5:
                CourseDAO.showAll();
                break;
            default:
                System.out.println("No such option");
        }

    }

}
