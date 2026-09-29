import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class StudentGradeTracker extends JFrame {

    JTextField name, roll, javaMarks, cppMarks, seMarks, iwdMarks;
    JTable table;
    DefaultTableModel model;
    JLabel title;

    ArrayList<Student> students = new ArrayList<>();

    StudentGradeTracker() {

        setTitle("Student Grade Tracker");
        setSize(1100, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout(10, 10));
        // Professional UI Settings
getContentPane().setBackground(new Color(245, 247, 250));

UIManager.put("Button.font",
        new Font("Arial", Font.BOLD, 14));

UIManager.put("Label.font",
        new Font("Arial", Font.PLAIN, 14));

UIManager.put("TextField.font",
        new Font("Arial", Font.PLAIN, 14));

UIManager.put("Table.font",
        new Font("Arial", Font.PLAIN, 13));

        UIManager.put("TableHeader.font",
        new Font("Arial", Font.BOLD, 13));

        title = new JLabel("STUDENT GRADE TRACKER", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 26));
        title.setForeground(new Color(35,55,85));

        JPanel titleBox = new JPanel(new BorderLayout());
        titleBox.setBorder(
             BorderFactory.createEmptyBorder(15,15,10,15));
        titleBox.add(title);

        add(titleBox, BorderLayout.NORTH);

     JPanel center = new JPanel(new BorderLayout(10, 10));

        JPanel top = new JPanel(new GridLayout(1, 2, 10, 10));

        JPanel studentBox = new JPanel(new GridLayout(3, 8, 10, 10));
        studentBox.setBorder(
                BorderFactory.createTitledBorder("Student Details"));

        studentBox.add(new JLabel("Student Name:"));
        name = new JTextField();
        studentBox.add(name);

        studentBox.add(new JLabel("Roll Number:"));
        roll = new JTextField();
        studentBox.add(roll);

        JPanel subjectBox = new JPanel(new GridLayout(4, 2, 10, 10));
        subjectBox.setBorder(
                BorderFactory.createTitledBorder("Subject Marks"));

        subjectBox.add(new JLabel("Java:"));
        javaMarks = new JTextField();
        subjectBox.add(javaMarks);

        subjectBox.add(new JLabel("C++:"));
        cppMarks = new JTextField();
        subjectBox.add(cppMarks);

        subjectBox.add(new JLabel("Software Engineering:"));
        seMarks = new JTextField();
        subjectBox.add(seMarks);

        subjectBox.add(new JLabel("Internet and Web Design:"));
        iwdMarks = new JTextField();
        subjectBox.add(iwdMarks);

        top.add(studentBox);
        top.add(subjectBox);

        center.add(top, BorderLayout.NORTH);

        JPanel recordsBox = new JPanel(new BorderLayout());
        recordsBox.setBorder(
                BorderFactory.createTitledBorder("Student Records"));

        model = new DefaultTableModel();

        model.addColumn("Roll");
        model.addColumn("Name");
        model.addColumn("Java");
        model.addColumn("C++"); 
        model.addColumn("SE");
        model.addColumn("IWD");
        model.addColumn("Total");
        model.addColumn("Average");
        model.addColumn("Grade");
        model.addColumn("Result");

        table = new JTable(model);
        table.setRowHeight(25);
        table.getTableHeader().setPreferredSize(new Dimension(0,35));

        recordsBox.add(new JScrollPane(table),
                BorderLayout.CENTER);

        center.add(recordsBox, BorderLayout.CENTER);

        add(center, BorderLayout.CENTER);

        JPanel bottom = new JPanel(new GridLayout(2, 1, 5, 5));

        JPanel studentButtons = new JPanel();
        studentButtons.setBorder(
                BorderFactory.createTitledBorder("Student Actions"));

        JButton add = new JButton("Add Student");
        JButton edit = new JButton("Edit");
        JButton delete = new JButton("Delete");
        JButton clear = new JButton("Clear");

        studentButtons.add(add);
        studentButtons.add(edit);
        studentButtons.add(delete);
        studentButtons.add(clear);

        JPanel functionButtons = new JPanel();
        functionButtons.setBorder(
                BorderFactory.createTitledBorder("Functions"));

        JButton search = new JButton("Search");
        JButton summary = new JButton("Summary");
        JButton highest = new JButton("Highest");
        JButton lowest = new JButton("Lowest");
        JButton sort = new JButton("Sort");
        JButton report = new JButton("Report");
        JButton save = new JButton("Save");
        JButton load = new JButton("Load");
        JButton exit = new JButton("Exit");

        functionButtons.add(search);
        functionButtons.add(summary);
        functionButtons.add(highest);
        functionButtons.add(lowest);
        functionButtons.add(sort);
        functionButtons.add(report);
        functionButtons.add(save);
        functionButtons.add(load);
        functionButtons.add(exit);

        bottom.add(studentButtons);
        bottom.add(functionButtons);

        add(bottom, BorderLayout.SOUTH);

        add.addActionListener(e -> addStudent());
        edit.addActionListener(e -> editStudent());
        delete.addActionListener(e -> deleteStudent());
        clear.addActionListener(e -> clearFields());

        search.addActionListener(e -> searchStudent());
        summary.addActionListener(e -> showSummary());
        highest.addActionListener(e -> showHighest());
        lowest.addActionListener(e -> showLowest());
        sort.addActionListener(e -> sortStudents());
        report.addActionListener(e -> showReport());

        save.addActionListener(e -> saveData());
        load.addActionListener(e -> loadData());

        exit.addActionListener(e -> System.exit(0));
        name.addActionListener(e -> roll.requestFocusInWindow());

roll.addActionListener(e -> javaMarks.requestFocusInWindow());

javaMarks.addActionListener(e -> cppMarks.requestFocusInWindow());

cppMarks.addActionListener(e -> seMarks.requestFocusInWindow());

seMarks.addActionListener(e -> iwdMarks.requestFocusInWindow());

iwdMarks.addActionListener(e -> add.doClick());

        addHover(add);
        addHover(edit);
        addHover(delete);
        addHover(clear);
        addHover(search);
        addHover(summary);
        addHover(highest);
        addHover(lowest);
        addHover(sort);
        addHover(report);
        addHover(save);
        addHover(load);
        addHover(exit);

        table.getSelectionModel().addListSelectionListener(e -> {

            int row = table.getSelectedRow();

            if (row != -1) {
                loadSelectedStudent(row);
            }
        });
    }

   void addHover(JButton button) {

    button.addMouseListener(
            new java.awt.event.MouseAdapter() {

                public void mouseEntered(
                        java.awt.event.MouseEvent e) {

                    button.setBackground(Color.LIGHT_GRAY);
                }

                public void mouseExited(
                        java.awt.event.MouseEvent e) {

                    button.setBackground(null);
                }
            });
}

    void addStudent() {

        try {

            String studentName = name.getText().trim();
            int rollNo = Integer.parseInt(
                    roll.getText().trim());

            if (studentName.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Enter student name!");

                return;
            }
            if (!studentName.matches("[a-zA-Z ]+")) {

    JOptionPane.showMessageDialog(
            this,
            "Name can contain only letters!");

    name.requestFocusInWindow();

    return;
}

            for (Student s : students) {

                if (s.rollNo == rollNo) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Roll number already exists!");

                    return;
                }
            }

            double java = getMark(
                    javaMarks.getText());

            double cpp = getMark(
                    cppMarks.getText());

            double se = getMark(
                    seMarks.getText());

            double iwd = getMark(
                    iwdMarks.getText());

            Student s = new Student(
                    studentName,
                    rollNo,
                    java,
                    cpp,
                    se,
                    iwd);

            students.add(s);

            showStudents();
            clearFields();

            JOptionPane.showMessageDialog(
                    this,
                    "Student added successfully!");

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter valid details!");
        }
    }

    double getMark(String text) {

        double mark = Double.parseDouble(
                text.trim());

        if (mark < 0 || mark > 100) {

            throw new IllegalArgumentException();
        }

        return mark;
    }

    void showStudents() {

        model.setRowCount(0);

        for (Student s : students) {

            model.addRow(new Object[]{

                    s.rollNo,
                    s.name,
                    s.java,
                    s.cpp,
                    s.se,
                    s.iwd,
                    s.getTotal(),
                    String.format("%.2f",
                            s.getAverage()),
                    s.getGrade(),
                    s.getResult()
            });
        }
    }

    void loadSelectedStudent(int row) {

        Student s = students.get(row);

        name.setText(s.name);
        roll.setText(String.valueOf(s.rollNo));

        javaMarks.setText(
                String.valueOf(s.java));

        cppMarks.setText(
                String.valueOf(s.cpp));

        seMarks.setText(
                String.valueOf(s.se));

        iwdMarks.setText(
                String.valueOf(s.iwd));
    }

    void editStudent() {

        int row = table.getSelectedRow();

        if (row == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Select a student first!");

            return;
        }

        try {

            Student s = students.get(row);

            s.name = name.getText().trim();

            s.rollNo = Integer.parseInt(
                    roll.getText().trim());

            s.java = getMark(
                    javaMarks.getText());

            s.cpp = getMark(
                    cppMarks.getText());

            s.se = getMark(
                    seMarks.getText());

            s.iwd = getMark(
                    iwdMarks.getText());

            showStudents();

            JOptionPane.showMessageDialog(
                    this,
                    "Student updated successfully!");

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter valid details!");
        }
    }

    void deleteStudent() {

        int row = table.getSelectedRow();

        if (row == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Select a student first!");

            return;
        }

        students.remove(row);

        showStudents();
        clearFields();

        JOptionPane.showMessageDialog(
                this,
                "Student deleted!");
    }

    void searchStudent() {

        String text = JOptionPane.showInputDialog(
                this,
                "Enter Name or Roll Number:");

        if (text == null)
            return;

        for (Student s : students) {

            if (s.name.equalsIgnoreCase(text)
                    || String.valueOf(s.rollNo)
                    .equals(text)) {

                JOptionPane.showMessageDialog(

                        this,

                        "Name: " + s.name
                                + "\nRoll No: "
                                + s.rollNo
                                + "\nTotal: "
                                + s.getTotal()
                                + "\nAverage: "
                                + String.format(
                                "%.2f",
                                s.getAverage())
                                + "\nGrade: "
                                + s.getGrade()
                                + "\nResult: "
                                + s.getResult());

                return;
            }
        }

        JOptionPane.showMessageDialog(
                this,
                "Student not found!");
    }

    void showSummary() {

        if (students.size() == 0) {

            JOptionPane.showMessageDialog(
                    this,
                    "No students available!");

            return;
        }

        double total = 0;

        int aPlus = 0;
        int a = 0;
        int b = 0;
        int c = 0;
        int d = 0;
        int f = 0;

        for (Student s : students) {

            total += s.getAverage();

            String grade = s.getGrade();

            if (grade.equals("A+"))
                aPlus++;
            else if (grade.equals("A"))
                a++;
            else if (grade.equals("B"))
                b++;
            else if (grade.equals("C"))
                c++;
            else if (grade.equals("D"))
                d++;
            else
                f++;
        }

        double classAverage =
                total / students.size();

        JOptionPane.showMessageDialog(

                this,

                "Total Students: "
                        + students.size()

                        + "\nClass Average: "
                        + String.format(
                        "%.2f",
                        classAverage)

                        + "\n\nA+ : " + aPlus
                        + "\nA  : " + a
                        + "\nB  : " + b
                        + "\nC  : " + c
                        + "\nD  : " + d
                        + "\nF  : " + f,

                "Class Summary",

                JOptionPane.INFORMATION_MESSAGE);
    }

    void showHighest() {

        if (students.size() == 0)
            return;

        Student highest =
                students.get(0);

        for (Student s : students) {

            if (s.getAverage()
                    > highest.getAverage()) {

                highest = s;
            }
        }

        JOptionPane.showMessageDialog(

                this,

                "Highest Scorer\n\n"
                        + "Name: "
                        + highest.name
                        + "\nRoll No: "
                        + highest.rollNo
                        + "\nAverage: "
                        + String.format(
                        "%.2f",
                        highest.getAverage()));
    }

    void showLowest() {

        if (students.size() == 0)
            return;

        Student lowest =
                students.get(0);

        for (Student s : students) {

            if (s.getAverage()
                    < lowest.getAverage()) {

                lowest = s;
            }
        }

        JOptionPane.showMessageDialog(

                this,

                "Lowest Scorer\n\n"
                        + "Name: "
                        + lowest.name
                        + "\nRoll No: "
                        + lowest.rollNo
                        + "\nAverage: "
                        + String.format(
                        "%.2f",
                        lowest.getAverage()));
    }

    void sortStudents() {

        for (int i = 0;
             i < students.size();
             i++) {

            for (int j = i + 1;
                 j < students.size();
                 j++) {

                if (students.get(i)
                        .getAverage()
                        < students.get(j)
                        .getAverage()) {

                    Student temp =
                            students.get(i);

                    students.set(
                            i,
                            students.get(j));

                    students.set(
                            j,
                            temp);
                }
            }
        }

        showStudents();
    }

    void showReport() {

        int row =
                table.getSelectedRow();

        if (row == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Select a student first!");

            return;
        }

        Student s =
                students.get(row);

        JDialog dialog =
                new JDialog(
                        this,
                        "Student Report",
                        true);

        dialog.setSize(450, 450);
        dialog.setLocationRelativeTo(this);

        JPanel panel =
                new JPanel();

        panel.setLayout(
                new BoxLayout(
                        panel,
                        BoxLayout.Y_AXIS));

        panel.setBorder(
                BorderFactory.createEmptyBorder(
                        20, 20, 20, 20));

        JLabel heading =
                new JLabel(
                        "STUDENT REPORT");

        heading.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        24));

        heading.setAlignmentX(
                Component.CENTER_ALIGNMENT);

        panel.add(heading);

        panel.add(Box.createVerticalStrut(15));

        panel.add(new JLabel(
                "Name: " + s.name));

        panel.add(new JLabel(
                "Roll No: " + s.rollNo));

        panel.add(new JLabel(
                "Java: " + s.java));

        panel.add(new JLabel(
                "C++: " + s.cpp));

        panel.add(new JLabel(
                "Software Engineering: "
                        + s.se));

        panel.add(new JLabel(
                "Internet and Web Design: "
                        + s.iwd));

        panel.add(new JLabel(
                "Total: " + s.getTotal()));

        panel.add(new JLabel(
                "Average: "
                        + String.format(
                        "%.2f",
                        s.getAverage())));

        panel.add(new JLabel(
                "Grade: " + s.getGrade()));

        panel.add(new JLabel(
                "Result: " + s.getResult()));

        panel.add(Box.createVerticalStrut(15));

        JProgressBar progress =
                new JProgressBar(
                        0,
                        100);

        progress.setValue(
                (int) s.getAverage());

        progress.setStringPainted(true);

        panel.add(progress);

        panel.add(Box.createVerticalStrut(15));

        JButton close =
                new JButton("Close");

        close.setAlignmentX(
                Component.CENTER_ALIGNMENT);

        close.addActionListener(
                e -> dialog.dispose());

        panel.add(close);

        dialog.add(panel);

        dialog.setVisible(true);
    }

    void saveData() {

        try {

            FileWriter file =
                    new FileWriter(
                            "students.txt");

            for (Student s : students) {

                file.write(

                        s.rollNo + "|"
                                + s.name + "|"
                                + s.java + "|"
                                + s.cpp + "|"
                                + s.se + "|"
                                + s.iwd
                                + "\n");
            }

            file.close();

            JOptionPane.showMessageDialog(
                    this,
                    "Data saved successfully!");

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Data could not be saved!");
        }
    }
    void loadData() {

        try {

            BufferedReader file =
                    new BufferedReader(
                            new FileReader(
                                    "students.txt"));

            students.clear();

            String line;

            while ((line =
                    file.readLine()) != null) {

                String[] data =
                        line.split("\\|");

                Student s =
                        new Student(

                                data[1],

                                Integer.parseInt(
                                        data[0]),

                                Double.parseDouble(
                                        data[2]),

                                Double.parseDouble(
                                        data[3]),

                                Double.parseDouble(
                                        data[4]),

                                Double.parseDouble(
                                        data[5]));
                
                students.add(s);
            }

            file.close();

            showStudents();

            JOptionPane.showMessageDialog(
                    this,
                    "Data loaded successfully!");

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "No saved data found!");
        }
    }

    void clearFields() {

        name.setText("");
        roll.setText("");

        javaMarks.setText("");
        cppMarks.setText("");
        seMarks.setText("");
        iwdMarks.setText("");

        table.clearSelection();
    }

    public static void main(String[] args) {

        new StudentGradeTracker().setVisible(true);
    }
}

class Student {

    String name;
    int rollNo;

    double java;
    double cpp;
    double se;
    double iwd;

    Student(
            String name,
            int rollNo,
            double java,
            double cpp,
            double se,
            double iwd) {

        this.name = name;
        this.rollNo = rollNo;

        this.java = java;
        this.cpp = cpp;
        this.se = se;
        this.iwd = iwd;
    }

    double getTotal() {

        return java
                + cpp
                + se
                + iwd;
    }

    double getAverage() {

        return getTotal() / 4;
    }

    String getGrade() {

        double average =
                getAverage();

        if (average >= 90)
            return "A+";

        else if (average >= 80)
            return "A";

        else if (average >= 70)
            return "B";

        else if (average >= 60)
            return "C";

        else if (average >= 50)
            return "D";

        else
            return "F";
    }

    String getResult() {

        if (java >= 40
                && cpp >= 40
                && se >= 40
                && iwd >= 40) {

            return "PASS";
        }

        return "FAIL";
    }
}