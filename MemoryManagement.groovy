
class MemoryManagement {

    static List<Integer> memoryArray

    static Map processMap = [:]


    public static void main(String[] args) {

        memoryArray = new ArrayList<Integer>(10)

        println '\nInitialized an empty array'
        printArray(memoryArray)

        println '\nAssigning 0 to each memory location..............'
        for (int i = 0; i < 10; i++) {
            memoryArray[i] = 0
            println "\nAssigning 0 to position ${i + 1} in array"
            printArray(memoryArray)
        }

        boolean condition = true
        int i = 0
        while (condition) {
            Integer option = getValue('\n\nEnter your option to take action..\n1.) Enter 1 to Insert\n2.)Enter 2 to delete\n3.)-1 to exit')
            if (option == 1) {
                int randomNumber = randomNumber()
                println "\nRandom Number generated: $randomNumber\n"
                List freeSpaces = findIndes(randomNumber)
                if (freeSpaces) {
                    i++
                    println "\nProcess Generated: $i"
                    processMap[i]  = fillMemoryArray(randomNumber, i)
                } else {
                    println "\n!!No enough memory available for the random number generated $randomNumber!!\n"
                    printArray(memoryArray)
                }
            } else if (option == 2) {
                Integer deleteProcess = getValue('\nPlease enter the Process Number to Delete')
                emptylMemoryArray(deleteProcess)
            } else if (option == -1) { condition = false }
            else { println '\nOnly 1, 2 and -1 are allowed, Please enter again...' }
        }
    }

    private static List fillMemoryArray (int size, process) {
        List freeSpaces = findIndes(size)
        freeSpaces.each { memoryArray[it] = process }
        printArray(memoryArray)
        freeSpaces
    }

    private static List emptylMemoryArray (int process) {
        List toBeDeleted = processMap.get(process)
        if (toBeDeleted) {
            toBeDeleted.each { memoryArray[it] = 0 }
            processMap.remove(process)
        } else { println "\nNo process $process found to be deleted, available processes ${processMap.keySet()}"}
        printArray(memoryArray)
    }

    private static List findIndes(int size) {
        List indexes = memoryArray.findIndexValues { it == 0 }
        List groups = groupConsecutive(indexes, 0, size)
        List indeces = groups.find { it.size() >= size }
        indeces.collect { it.toInteger()}.collate(size)[0]
    }

    public static List groupConsecutive(List list, int index, int threshold) {
        List groups = []
        List group = []
        while (index < list.size()) {
            group << list[index]
            if (list[index + 1] != (list[index] + 1)) {
                if (group.size() < threshold) { groups << group }
                else { groups << group }
                group = []
            }
            index++
        }
        groups
    }

    private static getValue(String printStatement) {
        println "$printStatement"
        boolean condition = true
        int inp, i = 0
        while (condition) {
            if (i != 0) { println '\nPlease enter again...' }
            def input = System.in.newReader().readLine()
            try {
                inp = Integer.parseInt(input)
                condition = false
            } catch (Exception e) {
                println('\nOnly numbers allowed try again..')
            }
            i++
        }
        inp
    }

    private static randomNumber() { new Random().nextInt(4) + 1 }

    private static void printArray(List list) {
        println '\nMemory Locations:'
        for (int i = 0; i < 5; i++) {
            for (int j = 2; j < 43; j++) {
                if (i == 0) { print('* ') }
                else if (i == 4) { print('* ') }
                else if ( i == 2 && (j-4) % 4 == 0) {
                    print "${list && list.size() > ((j/4)-1)? list[(int)(j/4) - 1].toString() + ' ' : '  '}"
                }
                else if ((j-2) % 4 == 0) { print('* ') }
                else { print '  ' }
            }
            println(' ')
        }
    }
}
