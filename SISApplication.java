{\rtf1\ansi\ansicpg1252\cocoartf2820
\cocoatextscaling0\cocoaplatform0{\fonttbl\f0\fswiss\fcharset0 Helvetica;}
{\colortbl;\red255\green255\blue255;}
{\*\expandedcolortbl;;}
\paperw11900\paperh16840\margl1440\margr1440\vieww11520\viewh8400\viewkind0
\pard\tx720\tx1440\tx2160\tx2880\tx3600\tx4320\tx5040\tx5760\tx6480\tx7200\tx7920\tx8640\pardirnatural\partightenfactor0

\f0\fs24 \cf0 import javax.swing.*;\
import java.awt.*;\
import java.awt.event.*;\
import java.util.ArrayList;\
import java.util.List;\
\
public class SISApplication \{\
\
    public static void main(String[] args) \{\
        SwingUtilities.invokeLater(() -> new LoginWindow());\
    \}\
\
    // Login Window\
    public static class LoginWindow extends JFrame \{\
        private JTextField usernameField;\
        private JPasswordField passwordField;\
        private static final String USERNAME = "admin";\
        private static final String PASSWORD = "password";\
\
        public LoginWindow() \{\
            setTitle("Login");\
            setSize(300, 150);\
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);\
\
            usernameField = new JTextField(15);\
            passwordField = new JPasswordField(15);\
\
            JButton loginButton = new JButton("Login");\
            loginButton.addActionListener(e -> handleLogin());\
\
            JPanel panel = new JPanel();\
            panel.setLayout(new GridLayout(3, 2));\
            panel.add(new JLabel("Username:"));\
            panel.add(usernameField);\
            panel.add(new JLabel("Password:"));\
            panel.add(passwordField);\
            panel.add(loginButton);\
\
            add(panel);\
            setLocationRelativeTo(null);\
            setVisible(true);\
        \}\
\
        private void handleLogin() \{\
            String username = usernameField.getText();\
            String password = new String(passwordField.getPassword());\
\
            if (USERNAME.equals(username) && PASSWORD.equals(password)) \{\
                dispose();\
                new SISApplicationWindow();\
            \} else \{\
                JOptionPane.showMessageDialog(this, "Invalid username or password", "Login Failed", JOptionPane.ERROR_MESSAGE);\
            \}\
        \}\
    \}\
\
    // Main SIS Application Window\
    public static class SISApplicationWindow extends JFrame \{\
        private List<Student> students = new ArrayList<>();\
        private List<Course> courses = new ArrayList<>();\
        private List<Instructor> instructors = new ArrayList<>();\
        private List<LibraryBook> libraryBooks = new ArrayList<>();\
        private List<LeaveApplication> leaveApplications = new ArrayList<>();\
\
        public SISApplicationWindow() \{\
            setTitle("Student Information System");\
            setSize(1200, 600);\
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);\
            setLayout(new GridLayout(1, 6));  // Added one more panel for Leave Application\
\
            add(createStudentPanel());\
            add(createCoursePanel());\
            add(createInstructorPanel());\
            add(createHostelAndFeePanel());\
            add(createLibraryPanel());\
            add(createLeaveApplicationPanel()); // New Panel\
\
            setVisible(true);\
        \}\
\
        // Student Panel\
        private JPanel createStudentPanel() \{\
            JPanel panel = new JPanel(new GridLayout(3, 1));\
            panel.setBorder(BorderFactory.createTitledBorder("Students"));\
\
            JButton addStudentButton = new JButton("Add Student");\
            addStudentButton.addActionListener(e -> showAddStudentDialog());\
            panel.add(addStudentButton);\
\
            JButton viewStudentsButton = new JButton("View Students");\
            viewStudentsButton.addActionListener(e -> showViewStudentsDialog());\
            panel.add(viewStudentsButton);\
\
            JButton removeStudentButton = new JButton("Remove Student");\
            removeStudentButton.addActionListener(e -> showRemoveStudentDialog());\
            panel.add(removeStudentButton);\
\
            return panel;\
        \}\
\
        private void showAddStudentDialog() \{\
            JTextField nameField = new JTextField(20);\
            JTextField ageField = new JTextField(20);\
            JTextField gradeField = new JTextField(20);\
            JTextField hostelNameField = new JTextField(20);\
            JTextField roomNumberField = new JTextField(20);\
            JTextField feePaidField = new JTextField(20);\
            JTextField courseField = new JTextField(20);\
\
            JPanel panel = new JPanel(new GridLayout(7, 2));\
            panel.add(new JLabel("Name:"));\
            panel.add(nameField);\
            panel.add(new JLabel("Age:"));\
            panel.add(ageField);\
            panel.add(new JLabel("Grade:"));\
            panel.add(gradeField);\
            panel.add(new JLabel("Hostel Name:"));\
            panel.add(hostelNameField);\
            panel.add(new JLabel("Room Number:"));\
            panel.add(roomNumberField);\
            panel.add(new JLabel("Fee Paid:"));\
            panel.add(feePaidField);\
            panel.add(new JLabel("Course:"));\
            panel.add(courseField);\
\
            int option = JOptionPane.showConfirmDialog(this, panel, "Add Student", JOptionPane.OK_CANCEL_OPTION);\
            if (option == JOptionPane.OK_OPTION) \{\
                students.add(new Student(nameField.getText(), Integer.parseInt(ageField.getText()), gradeField.getText(),\
                        hostelNameField.getText(), roomNumberField.getText(), Double.parseDouble(feePaidField.getText()), courseField.getText()));\
            \}\
        \}\
\
        private void showViewStudentsDialog() \{\
            StringBuilder studentsList = new StringBuilder();\
            for (Student student : students) \{\
                studentsList.append(student).append("\\n");\
            \}\
            JOptionPane.showMessageDialog(this, studentsList.toString(), "Students List", JOptionPane.INFORMATION_MESSAGE);\
        \}\
\
        private void showRemoveStudentDialog() \{\
            String studentId = JOptionPane.showInputDialog(this, "Enter Student ID to Remove:");\
            students.removeIf(student -> Integer.toString(student.getId()).equals(studentId));\
        \}\
\
        // Course Panel\
        private JPanel createCoursePanel() \{\
            JPanel panel = new JPanel(new GridLayout(3, 1));\
            panel.setBorder(BorderFactory.createTitledBorder("Courses"));\
\
            JButton addCourseButton = new JButton("Add Course");\
            addCourseButton.addActionListener(e -> showAddCourseDialog());\
            panel.add(addCourseButton);\
\
            JButton viewCoursesButton = new JButton("View Courses");\
            viewCoursesButton.addActionListener(e -> showViewCoursesDialog());\
            panel.add(viewCoursesButton);\
\
            JButton removeCourseButton = new JButton("Remove Course");\
            removeCourseButton.addActionListener(e -> showRemoveCourseDialog());\
            panel.add(removeCourseButton);\
\
            return panel;\
        \}\
\
        private void showAddCourseDialog() \{\
            JTextField courseNameField = new JTextField(20);\
            JTextField instructorNameField = new JTextField(20);\
\
            JPanel panel = new JPanel(new GridLayout(2, 2));\
            panel.add(new JLabel("Course Name:"));\
            panel.add(courseNameField);\
            panel.add(new JLabel("Instructor:"));\
            panel.add(instructorNameField);\
\
            int option = JOptionPane.showConfirmDialog(this, panel, "Add Course", JOptionPane.OK_CANCEL_OPTION);\
            if (option == JOptionPane.OK_OPTION) \{\
                Instructor instructor = new Instructor(instructorNameField.getText());\
                instructors.add(instructor);\
                courses.add(new Course(courseNameField.getText(), instructor));\
            \}\
        \}\
\
        private void showViewCoursesDialog() \{\
            StringBuilder coursesList = new StringBuilder();\
            for (Course course : courses) \{\
                coursesList.append(course).append("\\n");\
            \}\
            JOptionPane.showMessageDialog(this, coursesList.toString(), "Courses List", JOptionPane.INFORMATION_MESSAGE);\
        \}\
\
        private void showRemoveCourseDialog() \{\
            String courseName = JOptionPane.showInputDialog(this, "Enter Course Name to Remove:");\
            courses.removeIf(course -> course.getCourseName().equals(courseName));\
        \}\
\
        // Instructor Panel\
        private JPanel createInstructorPanel() \{\
            JPanel panel = new JPanel(new GridLayout(3, 1));\
            panel.setBorder(BorderFactory.createTitledBorder("Instructors"));\
\
            JButton addInstructorButton = new JButton("Add Instructor");\
            addInstructorButton.addActionListener(e -> showAddInstructorDialog());\
            panel.add(addInstructorButton);\
\
            JButton viewInstructorsButton = new JButton("View Instructors");\
            viewInstructorsButton.addActionListener(e -> showViewInstructorsDialog());\
            panel.add(viewInstructorsButton);\
\
            JButton removeInstructorButton = new JButton("Remove Instructor");\
            removeInstructorButton.addActionListener(e -> showRemoveInstructorDialog());\
            panel.add(removeInstructorButton);\
\
            return panel;\
        \}\
\
        private void showAddInstructorDialog() \{\
            JTextField nameField = new JTextField(20);\
            JPanel panel = new JPanel(new GridLayout(1, 2));\
            panel.add(new JLabel("Name:"));\
            panel.add(nameField);\
\
            int option = JOptionPane.showConfirmDialog(this, panel, "Add Instructor", JOptionPane.OK_CANCEL_OPTION);\
            if (option == JOptionPane.OK_OPTION) \{\
                instructors.add(new Instructor(nameField.getText()));\
            \}\
        \}\
\
        private void showViewInstructorsDialog() \{\
            StringBuilder instructorsList = new StringBuilder();\
            for (Instructor instructor : instructors) \{\
                instructorsList.append(instructor).append("\\n");\
            \}\
            JOptionPane.showMessageDialog(this, instructorsList.toString(), "Instructors List", JOptionPane.INFORMATION_MESSAGE);\
        \}\
\
        private void showRemoveInstructorDialog() \{\
            String instructorName = JOptionPane.showInputDialog(this, "Enter Instructor Name to Remove:");\
            instructors.removeIf(instructor -> instructor.getName().equals(instructorName));\
        \}\
\
        // Hostel and Fee Panel\
        private JPanel createHostelAndFeePanel() \{\
            JPanel panel = new JPanel(new GridLayout(1, 1));\
            JButton viewFeeButton = new JButton("View Fee and Hostel Details");\
            viewFeeButton.addActionListener(e -> showViewFeeAndHostelDetails());\
            panel.add(viewFeeButton);\
            panel.setBorder(BorderFactory.createTitledBorder("Hostel and Fee Details"));\
            return panel;\
        \}\
\
        private void showViewFeeAndHostelDetails() \{\
            StringBuilder feeAndHostelDetails = new StringBuilder();\
            for (Student student : students) \{\
                feeAndHostelDetails.append(student.getHostelName()).append(" - ").append(student.getFeePaid()).append("\\n");\
            \}\
            JOptionPane.showMessageDialog(this, feeAndHostelDetails.toString(), "Fee and Hostel Details", JOptionPane.INFORMATION_MESSAGE);\
        \}\
\
        // Library Panel\
        private JPanel createLibraryPanel() \{\
            JPanel panel = new JPanel(new GridLayout(3, 1));\
            panel.setBorder(BorderFactory.createTitledBorder("Library"));\
\
            JButton addBookButton = new JButton("Add Book");\
            addBookButton.addActionListener(e -> showAddBookDialog());\
            panel.add(addBookButton);\
\
            JButton viewBooksButton = new JButton("View Books");\
            viewBooksButton.addActionListener(e -> showViewBooksDialog());\
            panel.add(viewBooksButton);\
\
            JButton borrowBookButton = new JButton("Borrow Book");\
            borrowBookButton.addActionListener(e -> showBorrowBookDialog());\
            panel.add(borrowBookButton);\
\
            return panel;\
        \}\
\
        private void showAddBookDialog() \{\
            JTextField bookNameField = new JTextField(20);\
            JTextField authorField = new JTextField(20);\
\
            JPanel panel = new JPanel(new GridLayout(2, 2));\
            panel.add(new JLabel("Book Name:"));\
            panel.add(bookNameField);\
            panel.add(new JLabel("Author:"));\
            panel.add(authorField);\
\
            int option = JOptionPane.showConfirmDialog(this, panel, "Add Book", JOptionPane.OK_CANCEL_OPTION);\
            if (option == JOptionPane.OK_OPTION) \{\
                libraryBooks.add(new LibraryBook(bookNameField.getText(), authorField.getText()));\
            \}\
        \}\
\
        private void showViewBooksDialog() \{\
            StringBuilder booksList = new StringBuilder();\
            for (LibraryBook book : libraryBooks) \{\
                booksList.append(book).append("\\n");\
            \}\
            JOptionPane.showMessageDialog(this, booksList.toString(), "Library Books", JOptionPane.INFORMATION_MESSAGE);\
        \}\
\
        private void showBorrowBookDialog() \{\
            String bookName = JOptionPane.showInputDialog(this, "Enter Book Name to Borrow:");\
            for (LibraryBook book : libraryBooks) \{\
                if (book.getBookName().equals(bookName)) \{\
                    book.setBorrowed(true);\
                    JOptionPane.showMessageDialog(this, "You have borrowed the book: " + book.getBookName(), "Book Borrowed", JOptionPane.INFORMATION_MESSAGE);\
                    return;\
                \}\
            \}\
            JOptionPane.showMessageDialog(this, "Book not found", "Error", JOptionPane.ERROR_MESSAGE);\
        \}\
\
        // Leave Application Panel\
        private JPanel createLeaveApplicationPanel() \{\
            JPanel panel = new JPanel(new GridLayout(3, 1));\
            panel.setBorder(BorderFactory.createTitledBorder("Leave Applications"));\
\
            JButton applyLeaveButton = new JButton("Apply for Leave");\
            applyLeaveButton.addActionListener(e -> showApplyLeaveDialog());\
            panel.add(applyLeaveButton);\
\
            JButton viewLeaveApplicationsButton = new JButton("View Leave Applications");\
            viewLeaveApplicationsButton.addActionListener(e -> showViewLeaveApplicationsDialog());\
            panel.add(viewLeaveApplicationsButton);\
\
            return panel;\
        \}\
\
        private void showApplyLeaveDialog() \{\
            JTextField studentIdField = new JTextField(20);\
            JTextField leaveDateField = new JTextField(20);\
            JTextField reasonField = new JTextField(20);\
\
            JPanel panel = new JPanel(new GridLayout(3, 2));\
            panel.add(new JLabel("Student ID:"));\
            panel.add(studentIdField);\
            panel.add(new JLabel("Leave Date:"));\
            panel.add(leaveDateField);\
            panel.add(new JLabel("Reason:"));\
            panel.add(reasonField);\
\
            int option = JOptionPane.showConfirmDialog(this, panel, "Apply for Leave", JOptionPane.OK_CANCEL_OPTION);\
            if (option == JOptionPane.OK_OPTION) \{\
                leaveApplications.add(new LeaveApplication(studentIdField.getText(), leaveDateField.getText(), reasonField.getText()));\
            \}\
        \}\
\
        private void showViewLeaveApplicationsDialog() \{\
            StringBuilder leaveApplicationsList = new StringBuilder();\
            for (LeaveApplication leaveApplication : leaveApplications) \{\
                leaveApplicationsList.append(leaveApplication).append("\\n");\
            \}\
            JOptionPane.showMessageDialog(this, leaveApplicationsList.toString(), "Leave Applications", JOptionPane.INFORMATION_MESSAGE);\
        \}\
    \}\
\
    // Models for Students, Courses, Instructors, LibraryBook, LeaveApplication\
    static class Student \{\
        private static int counter = 0;\
        private int id;\
        private String name;\
        private int age;\
        private String grade;\
        private String hostelName;\
        private String roomNumber;\
        private double feePaid;\
        private String course;\
\
        public Student(String name, int age, String grade, String hostelName, String roomNumber, double feePaid, String course) \{\
            this.id = ++counter;\
            this.name = name;\
            this.age = age;\
            this.grade = grade;\
            this.hostelName = hostelName;\
            this.roomNumber = roomNumber;\
            this.feePaid = feePaid;\
            this.course = course;\
        \}\
\
        public int getId() \{\
            return id;\
        \}\
\
        public String getName() \{\
            return name;\
        \}\
\
        public double getFeePaid() \{\
            return feePaid;\
        \}\
\
        public String getHostelName() \{\
            return hostelName;\
        \}\
\
        @Override\
        public String toString() \{\
            return "ID: " + id + ", Name: " + name + ", Grade: " + grade + ", Hostel: " + hostelName + ", Room: " + roomNumber;\
        \}\
    \}\
\
    static class Course \{\
        private String courseName;\
        private Instructor instructor;\
\
        public Course(String courseName, Instructor instructor) \{\
            this.courseName = courseName;\
            this.instructor = instructor;\
        \}\
\
        public String getCourseName() \{\
            return courseName;\
        \}\
\
        @Override\
        public String toString() \{\
            return "Course: " + courseName + ", Instructor: " + instructor.getName();\
        \}\
    \}\
\
    static class Instructor \{\
        private String name;\
\
        public Instructor(String name) \{\
            this.name = name;\
        \}\
\
        public String getName() \{\
            return name;\
        \}\
\
        @Override\
        public String toString() \{\
            return name;\
        \}\
    \}\
\
    static class LibraryBook \{\
        private String bookName;\
        private String author;\
        private boolean isBorrowed;\
\
        public LibraryBook(String bookName, String author) \{\
            this.bookName = bookName;\
            this.author = author;\
            this.isBorrowed = false;\
        \}\
\
        public String getBookName() \{\
            return bookName;\
        \}\
\
        public void setBorrowed(boolean borrowed) \{\
            isBorrowed = borrowed;\
        \}\
\
        @Override\
        public String toString() \{\
            return "Book: " + bookName + " by " + author + (isBorrowed ? " (Borrowed)" : " (Available)");\
        \}\
    \}\
\
    static class LeaveApplication \{\
        private String studentId;\
        private String leaveDate;\
        private String reason;\
\
        public LeaveApplication(String studentId, String leaveDate, String reason) \{\
            this.studentId = studentId;\
            this.leaveDate = leaveDate;\
            this.reason = reason;\
        \}\
\
        @Override\
        public String toString() \{\
            return "Student ID: " + studentId + ", Leave Date: " + leaveDate + ", Reason: " + reason;\
        \}\
    \}\
\}}