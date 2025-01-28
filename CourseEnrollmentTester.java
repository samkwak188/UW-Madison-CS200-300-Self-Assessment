import java.util.Arrays;

public class CourseEnrollmentTester {

    public static boolean createEmptyListTester() {
        String[][] actual = CourseEnrollment.createEmptyList(5);
        String[][] expected = new String[5][];
        if (!Arrays.deepEquals(actual, expected)) {
            System.out.println("Bug detected: createEmptyList did not return the expected array.");
            return false;
        }
        return true;
    }

    public static boolean indexOfPerfectSizeArrayTester() {
        // Test case 1: Match found at index 0
        String[][] testArray = {{"Alice", "alice@wisc.edu", "1234567890"}, 
                              {"Bob", "bob@wisc.edu", "0987654321"}};
        int result = CourseEnrollment.indexOf("1234567890", testArray);
        if (result != 0) return false;
        
        // Test case 2: Match found at last index
        result = CourseEnrollment.indexOf("0987654321", testArray);
        if (result != 1) return false;
        
        // Test case 3: No match found
        result = CourseEnrollment.indexOf("9999999999", testArray);
        if (result != -1) return false;
        
        return true;
    }

    public static boolean indexOfOversizeSizeArrayTester() {
        String[][] testArray = {{"Alice", "alice@wisc.edu", "1234567890"}, 
                              {"Bob", "bob@wisc.edu", "0987654321"}, 
                              null, null};
        int size = 2;
        
        // Test case 1: Match found at index 0
        int result = CourseEnrollment.indexOf("1234567890", testArray, size);
        if (result != 0) return false;
        
        // Test case 2: Match found at last index
        result = CourseEnrollment.indexOf("0987654321", testArray, size);
        if (result != 1) return false;
        
        // Test case 3: No match found
        result = CourseEnrollment.indexOf("9999999999", testArray, size);
        if (result != -1) return false;
        
        return true;
    }

    public static boolean enrollOneStudentTester() {
        String[][] roster = new String[3][];
        int size = 0;
        String[][] waitlist = new String[2][];
        
        // Test case 1: Enroll first student
        size = CourseEnrollment.enrollOneStudent("Alice", "alice@wisc.edu", "1234567890", 
                true, roster, size, waitlist);
        if (size != 1) return false;
        
        // Test case 2: Enroll second student
        size = CourseEnrollment.enrollOneStudent("Bob", "bob@wisc.edu", "0987654321", 
                true, roster, size, waitlist);
        if (size != 2) return false;
        
        // Test case 3: Try to enroll same student again
        int newSize = CourseEnrollment.enrollOneStudent("Alice", "alice@wisc.edu", "1234567890", 
                true, roster, size, waitlist);
        if (newSize != size) return false;
        
        return true;
    }

    public static boolean enrollOneStudentMoveFromWaitlistTester() {
        String[][] roster = new String[2][];
        int size = 0;
        String[][] waitlist = new String[2][];
        
        // Add student to waitlist
        CourseEnrollment.addWaitlist("Alice", "alice@wisc.edu", "1234567890", true, waitlist);
        
        // Enroll student from waitlist
        size = CourseEnrollment.enrollOneStudent("Alice", "alice@wisc.edu", "1234567890", 
                true, roster, size, waitlist);
        
        if (size != 1) return false;
        if (waitlist[0] != null) return false;
        
        return true;
    }

    public static boolean successfulDropCourseTester() {
        String[][] roster = {{"Alice", "alice@wisc.edu", "1234567890"}, 
                           {"Bob", "bob@wisc.edu", "0987654321"}, 
                           null};
        int size = 2;
        
        size = CourseEnrollment.dropCourse("1234567890", roster, size);
        
        if (size != 1) return false;
        if (!roster[0][2].equals("0987654321")) return false;
        if (roster[1] != null) return false;
        
        return true;
    }

    public static boolean unsuccessfulDropCourseTester() {
        String[][] roster = {{"Alice", "alice@wisc.edu", "1234567890"}, 
                           {"Bob", "bob@wisc.edu", "0987654321"}, 
                           null};
        int size = 2;
        
        int newSize = CourseEnrollment.dropCourse("9999999999", roster, size);
        
        if (newSize != size) return false;
        if (!roster[0][2].equals("1234567890")) return false;
        if (!roster[1][2].equals("0987654321")) return false;
        
        return true;
    }

    public static boolean runAllTests() {
        boolean createEmptyListTesterResult = createEmptyListTester();
        System.out.println("createEmptyListTester: " + (createEmptyListTesterResult ? "Pass" : "Failed!"));
        
        boolean indexOfPerfectSizeArrayTesterResult = indexOfPerfectSizeArrayTester();
        System.out.println("indexOfPerfectSizeArrayTester: " + (indexOfPerfectSizeArrayTesterResult ? "Pass" : "Failed!"));
        
        boolean indexOfOversizeSizeArrayTesterResult = indexOfOversizeSizeArrayTester();
        System.out.println("indexOfOversizeSizeArrayTester: " + (indexOfOversizeSizeArrayTesterResult ? "Pass" : "Failed!"));
        
        boolean enrollOneStudentTesterResult = enrollOneStudentTester();
        System.out.println("enrollOneStudentTester: " + (enrollOneStudentTesterResult ? "Pass" : "Failed!"));
        
        boolean enrollOneStudentMoveFromWaitlistTesterResult = enrollOneStudentMoveFromWaitlistTester();
        System.out.println("enrollOneStudentMoveFromWaitlistTester: " + (enrollOneStudentMoveFromWaitlistTesterResult ? "Pass" : "Failed!"));
        
        boolean successfulDropCourseTesterResult = successfulDropCourseTester();
        System.out.println("successfulDropCourseTester: " + (successfulDropCourseTesterResult ? "Pass" : "Failed!"));
        
        boolean unsuccessfulDropCourseTesterResult = unsuccessfulDropCourseTester();
        System.out.println("unsuccessfulDropCourseTester: " + (unsuccessfulDropCourseTesterResult ? "Pass" : "Failed!"));
        
        return createEmptyListTesterResult && indexOfPerfectSizeArrayTesterResult &&
               indexOfOversizeSizeArrayTesterResult && enrollOneStudentTesterResult &&
               enrollOneStudentMoveFromWaitlistTesterResult && successfulDropCourseTesterResult &&
               unsuccessfulDropCourseTesterResult;
    }

    public static void main(String[] args) {
        System.out.println("Running all tests...");
        System.out.println("All tests " + (runAllTests() ? "passed!" : "failed!"));
    }
}
