public class TestRegex2 {
    public static void main(String[] args) {
        String[] testCases = {
            "CSE123456", // should be valid
            "CS123456",  // should be valid  
            "CSE10789",  // should be invalid (5 digits)
            "CSED123456", // should be invalid (4 letters)
            "cse123456", // should be invalid (lowercase)
            "CSE1234567" // should be invalid (7 digits)
        };
        
        for (String testCase : testCases) {
            boolean result = testCase != null && testCase.matches("^[A-Z]{2,3}\\d{6}$");
            System.out.println(testCase + " -> " + result);
        }
    }
}
