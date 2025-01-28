import java.util.Scanner;

public class CourseEnrollmentDriver {
    private static final String WELCOME_MSG = "--- Welcome to the Course Enrollment System! ----";
    private static final String GOOD_BYE_MSG = "---------- BYE! Thanks for using our App! ----------";
    private static final String SYNTAX_ERROR_MSG = "Syntax Error: Please enter a valid command!";
    private static final String NO_COURSE_ENROLLMENT_MSG = "Error: Create a new course enrollment first!";

    public static void main(String[] args) {
        System.out.println(WELCOME_MSG);
        Scanner scanner = new Scanner(System.in);
        processUserCommands(scanner);
        scanner.close();
        System.out.println(GOOD_BYE_MSG);
    }

    private static void displayMenu() {
        System.out.println("\n================================ MENU ===============================");
        System.out.println("Enter one of the following options:");
        System.out.println("[1 <roster_capacity> <waitlist_capacity>] Create a new course enrollment");
        System.out.println("[2 <name>:<wisc_email>:<campus_ID>:boolean(true/false)] Enroll student");
        System.out.println("[3 <name>:<wisc_email>:<campus_ID>:boolean] Add student to waitlist");
        System.out.println("[4 <campus_ID>] Drop the course");
        System.out.println("[5] Print roster");
        System.out.println("[6] Print waitlist");
        System.out.println("[7] Logout and EXIT");
        System.out.println("-----------------------------------------------------------------------");
    }

    private static void processUserCommands(Scanner scanner) {
        String[][] roster = null;
        int size = 0;
        String[][] waitlist = null;

        while (true) {
            displayMenu();
            System.out.print("ENTER COMMAND: ");
            String command = scanner.nextLine().trim();

            if (command.isEmpty()) {
                System.out.println(SYNTAX_ERROR_MSG);
                continue;
            }

            if (command.equals("7")) {
                break;
            }

            String[] parts = command.split(" ");
            switch (parts[0]) {
                case "1":
                    if (parts.length == 3) {
                        roster = CourseEnrollment.createEmptyList(Integer.parseInt(parts[1]));
                        waitlist = CourseEnrollment.createEmptyList(Integer.parseInt(parts[2]));
                        size = 0;
                    } else {
                        System.out.println(SYNTAX_ERROR_MSG);
                    }
                    break;
                case "2":
                    if (roster == null || waitlist == null) {
                        System.out.println(NO_COURSE_ENROLLMENT_MSG);
                        break;
                    }
                    if (parts.length == 2) {
                        String[] studentInfo = parts[1].split(":");
                        if (studentInfo.length == 4) {
                            size = CourseEnrollment.enrollOneStudent(
                                    studentInfo[0],
                                    studentInfo[1],
                                    studentInfo[2],
                                    Boolean.parseBoolean(studentInfo[3]),
                                    roster, size, waitlist
                            );
                        } else {
                            System.out.println(SYNTAX_ERROR_MSG);
                        }
                    } else {
                        System.out.println(SYNTAX_ERROR_MSG);
                    }
                    break;
                case "3":
                    if (roster == null || waitlist == null) {
                        System.out.println(NO_COURSE_ENROLLMENT_MSG);
                        break;
                    }
                    if (parts.length == 2) {
                        String[] studentInfo = parts[1].split(":");
                        if (studentInfo.length == 4) {
                            CourseEnrollment.addWaitlist(
                                    studentInfo[0],
                                    studentInfo[1],
                                    studentInfo[2],
                                    Boolean.parseBoolean(studentInfo[3]),
                                    waitlist
                            );
                        } else {
                            System.out.println(SYNTAX_ERROR_MSG);
                        }
                    } else {
                        System.out.println(SYNTAX_ERROR_MSG);
                    }
                    break;
                case "4":
                    if (roster == null || waitlist == null) {
                        System.out.println(NO_COURSE_ENROLLMENT_MSG);
                        break;
                    }
                    if (parts.length == 2) {
                        size = CourseEnrollment.dropCourse(parts[1], roster, size);
                    } else {
                        System.out.println(SYNTAX_ERROR_MSG);
                    }
                    break;
                case "5":
                    if (roster == null) {
                        System.out.println(NO_COURSE_ENROLLMENT_MSG);
                    } else {
                        CourseEnrollment.printRoster(roster, size);
                    }
                    break;
                case "6":
                    if (waitlist == null) {
                        System.out.println(NO_COURSE_ENROLLMENT_MSG);
                    } else {
                        CourseEnrollment.printWaitlist(waitlist);
                    }
                    break;
                default:
                    System.out.println(SYNTAX_ERROR_MSG);
            }
        }
    }
}
