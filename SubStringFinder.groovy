public class SubStringFinder {

    static char[] patternCharArray
    static char[] inputCharArray
    static int[] charIntArray = new int[256]

    static Integer matchedIndex

    static String inputString
    static String pattern

    public static void main(String[] args) {

        inputString = getValue('Enter Input String')//.toCharArray()//inputString.toCharArray()
        inputCharArray = inputString.toCharArray()
        pattern = getValue('Enter Patter')//.toCharArray()//pattern.toCharArray()
        patternCharArray = pattern.toCharArray()

        printGiven()
        display(findPatternIndex())
    }

    public static Integer findPatternIndex() {


        constructShiftTable()

        printShiftTable()

        println "Shifting Process: \n"
        int i = 0, j, r = 0
        while (i <= inputCharArray.size() - patternCharArray.size()) {
            j = patternCharArray.size() - 1
            while (j >= 0 && patternCharArray[j] == inputCharArray[i + j]) {
                j--
            }
            r = i
            if (i != 0) {
                if (j < 0) {
                    matchedIndex = i
                    displayShift(r)
                    break
                }
            }
            def character = inputCharArray[i + patternCharArray.size() - 1]
            i += charIntArray[(int)inputCharArray[i + patternCharArray.size() - 1]]
            displayShift(r)
            println "\nThe last matching character is ${character == ' ' ? "' '(emptyString)" : character}, So the pattern needs to be shifted by : ${i - r}"
        }
        matchedIndex
    }

    static constructShiftTable() {

        for (int n = 0; n < 256; n++) { charIntArray[n] = -1 }

        int index = 1
        for (int l = patternCharArray.size() - 2; l >= 0; l--) {
            if (charIntArray[(int) patternCharArray[l]] != -1) { index = index + 1 }
            else { charIntArray[(int) patternCharArray[l]] = patternCharArray.size() - index - l }
        }

        for (int n = 0; n < 256; n++) {
            if (charIntArray[n] == -1) { charIntArray[n] = patternCharArray.size() }
        }
    }

    static printShiftTable() {

        println '\nShift Table:'

        StringBuffer heading = new StringBuffer()
        StringBuffer values = new StringBuffer()

        char character = 'A'
        for (int i = 0; i < 26; i++) {
            heading << (String)character + ' '
            values << charIntArray[(int)character] + ' '
            character = (char)((int)character + 1)
        }
        println heading + "''"
        println values + patternCharArray.size() + '\n'
    }

    static printGiven() {

        println "Input String: $inputString"
        println "Pattern to Find: $pattern"
    }

    static displayShift(Integer length) {
        println inputString
        String emptySpace = ''
        length.times { emptySpace += ' ' }
        println emptySpace + pattern }

    static display(Integer matchedIndex) {
        if (matchedIndex == null) { println '\n!!No Match Found!!' }
        else { println "\n**Match found at index $matchedIndex**" }
    }


    private static String getValue(String printStatement) {
        println "$printStatement"
        boolean condition = true
        int i = 0
        String input
        while (condition) {
            if (i != 0) { println 'Please enter again...' }
            input = System.in.newReader().readLine()
                if (input) {
                    condition = false
                } else {println 'Empty Strings not allowed'}
            i++
        }
        input
    }
}
