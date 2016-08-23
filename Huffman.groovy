public class Huffman {

    static List characters = []
    static List characterProbabilities = []
    static int noOfCharacters
    static PriorityQueue<Tree> trees = new PriorityQueue<Tree>()

    public static void main(String[] args) {

        takeInputs()
        validateInputs()
        createNodes()
        createHuffmanTree()

        Tree tree = trees.poll()
        printTheGraph(tree)

        List info = displayCodes(tree)
        calculateAndPrintHuffmanLength(info)
    }

    static takeInputs() {
        noOfCharacters = getIntegerValue('Enter the number of characters')
        String character  = getStringValue("Enter the characters as a string")
        characters = character.toCharArray()
        if (noOfCharacters != characters.size()) {
            println 'number of characters input did not match the size of the character string, plese try again'
            System.exit(1)
        }
        for (int i = 0; i < noOfCharacters ; i++) {
            characterProbabilities << getIntegerOrDecimalValue("Enter the probability for character: ${characters[i]}")
        }
    }

    static validateInputs() {
        if (characterProbabilities.every { it.class == Integer.class }) {
            if (characterProbabilities.sum { it } != 100) {
                println 'The sum of probabilities should be 100, if entering integers, please try again'
                System.exit(1)
            }
        } else if (characterProbabilities.every { it.class == Double.class }) {
            if (characterProbabilities.sum { it } != 1.0) {
                println 'The sum of probabilities should be 1, if entering decimals, please try again'
                System.exit(1)
            }
        } else {
            println 'Either all Integers or all decimals are only allowed'
            System.exit(1)
        }
    }

    static createNodes () {
        for (int i = 0; i < noOfCharacters; i++) {
            if (characterProbabilities[i] > 0) {
                trees.offer(new Tree(characterProbabilities[i], (char)characters[i]))
            }
        }
    }

    static createHuffmanTree() {
        while (trees.size() > 1) {
            Tree a = trees.poll()
            Tree b = trees.poll()
            trees.offer(new Tree(a, b))
        }
    }

    static printTheGraph(Tree tree) {
        println '\n*************************'
        println '*****|Huffman Graph|*****'
        println '*************************'
        tree.printGraph('', true)
        println '*************************\n'
    }

    static List displayCodes(Tree tree) {
        println "\n========================="
        println "LETTER\tPROBABILITY\tCODE"
        println "-------------------------"
        List info = tree.displayCode(new StringBuffer(), [])
        println "=========================\n"
        info
    }

    static calculateAndPrintHuffmanLength(List info) {
        def length = 0
        info.each {
            def a = it.probability
            def b = it.code.length()
            length +=  a*b
        }
        println "The huffman length is: $length"
    }

    static getStringValue(String printStatement) {
        boolean conditon = true
        String input
        def output
        while(conditon) {
            println "$printStatement"
            input = System.in.newReader().readLine()
            if (input == '') { println 'Empty node names not allowed' }
            else {
                output = input
                conditon = false
            }
        }
        output
    }

    static getIntegerValue(String printStatement) {
        boolean conditon = true
        String input
        def output
        while(conditon) {
            println "$printStatement"
            input = System.in.newReader().readLine()
            try {
                output = Integer.parseInt(input)
                conditon = false
            } catch (Exception e) {
                println 'Only Integers allowed'
            }
        }
        output
    }

    static getIntegerOrDecimalValue(String printStatement) {
        boolean conditon = true
        String input
        def output
        String type
        while(conditon) {
            println "$printStatement"
            input = System.in.newReader().readLine()

            if (input.isInteger()) { type = 'integer'}
            else if (input.isDouble()) { type = 'decimal'}
            if(type == 'integer') {
                try {
                    output = Integer.parseInt(input)
                    conditon = false

                } catch (Exception e) {
                    println 'Only Integers allowed'
                }
            } else if (type == 'decimal') {
                try {
                    output = Double.parseDouble(input)
                    conditon = false
                } catch (Exception e) {
                    println 'Only Decimals allowed'
                }
            }
        }
        output
    }
}


class Tree implements Comparable<Tree> {

    double probability
    char character
    Tree left
    Tree right

    Tree(double probability) {
        this.probability = probability
    }

    Tree(double probability, char character) {
        this.probability = probability
        this.character = character
    }

    Tree(Tree left, Tree right) {
        this.probability = left.probability + right.probability
        this.left = left
        this.right = right
    }

    void printGraph(String prefix, boolean isTail) {
        if (right) { right.printGraph(prefix + (isTail ? "│   " : "    "), false) }
        println prefix + (isTail ? '└── ' : '┌── ') + probability + (character ? '(' + character +')' : '')
        if (left) { left.printGraph(prefix +  (isTail ? "    " : "│   "), true) }
    }

    List displayCode(StringBuffer code, List info) {
        if (left) {
            code.append('0')
            left.displayCode(code, info)
            code.deleteCharAt(code.length() - 1)
        }
        if (right) {
            code.append('1')
            right.displayCode(code, info)
            code.deleteCharAt(code.length() - 1)
        }
        if (character) {
            info << [character: character, probability: probability, code: code.toString()]
            println((String) character + "\t\t" + (String) probability + "\t\t" + code)
        }
        info
    }

    int compareTo(Tree tree) {
        if (probability > tree.probability) { 1 }
        else if (probability < tree.probability) { -1 }
        else { 0 }
    }
}

