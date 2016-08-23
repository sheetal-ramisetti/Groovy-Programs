import java.io.Console
import java.util.Stack

class StackExample {

    Stack stack = new Stack()

    public static void main(String[] args) {
        StackExample stackExample = new StackExample()
        stackExample.getStarted()
    }

    public getStarted() {
        try {
            println 'Please Enter your expression'
            def inp = System.console().readLine 'Input: '
            def input = inp.tokenize()
            letItGo(input)
            printTheStack()
        } catch (Exception ex) { throwErrorAndGetStarted() }
    }

    private letItGo(input) {
        input.each { it ->
            processInput(it)
        }
    }

    private processInput(inp) {

        switch (inp) {
            case '+':
                add()
                break
            case '-':
                subtract()
                break
            case '*':
                multiply()
                break
            case '/':
                divide()
                break
            default:
                addNumber(inp)
                break
        }
    }


    private add() {
        def b = stack.pop()
        def a = stack.pop()
        stack.push(a+b)
    }

    private subtract() {
        def b = stack.pop()
        def a = stack.pop()
        stack.push(a-b)
    }

    private multiply() {
        def b = stack.pop()
        def a = stack.pop()
        stack.push(a*b)
    }

    private divide() {
        def b = stack.pop()
        def a = stack.pop()
        stack.push(a/b)
    }

    private addNumber(inp) {
        def y = Integer.parseInt(inp)
        stack.push(y)
    }

    private printTheStack() {
        if (stack.size() == 1) {
            println "Output: ${stack.peek()}"
        } else { throwErrorAndGetStarted() }
    }

    private throwErrorAndGetStarted() {
        println '\n!!!!Error in your Input, Check and enter again!!!!\n'
        clearStack()
        getStarted()
    }

    private clearStack() {
        while (!stack.empty()) {
            stack.pop()
        }
    }
}