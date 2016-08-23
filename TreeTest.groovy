class TreeTest {

    static boolean conditon = true

    static current

    static List leafNodes = []


    static void main(String[] args) {
        String rootName = getValue('Enter the name of the root node', false)
        Node root = new Node(rootName, null)
        current = root
        while (conditon) {
            root.printTheGraph('', true, true)
            def nodName2 = getValue("""Click 'ENTER' if you would like to add another child to the parent: '${current.name}'
(or) The name of the node to which would you want to add child node
(or) Enter -1 if the tree construcion is done.""", true)
            if (nodName2 == '-1') { conditon = false }
            else {
                nodName2 == rootName ? current = root : findInRoot(root, nodName2)
                current.addChild(getValue("""Enter the name of the child to add to parent: '${current.name}'""", false))
            }
        }
        println 'The final tree is:: '
        root.printTheGraph('', true, true)
        findLeafNodes(root)
        println 'Leaf nodes are as follows: '+leafNodes
        println 'Total number of leaf nodes are: '+ leafNodes.size()
    }

    static Node findInRoot(Node root, String nameToFind) {
        for (int i = 0; i < root.childNodes.size(); i++) {
            if (root.childNodes.get(i).name == nameToFind) { current = root.childNodes.get(i) }
            else { findInRoot(root.childNodes.get(i), nameToFind) }
        }
    }

    static findLeafNodes(Node root) {
        if (root.childNodes == []) { leafNodes << root.name }
        else {
            for (int i = 0; i < root.childNodes.size(); i++) {
                if (root.childNodes.get(i)) {
                    findLeafNodes(root.childNodes.get(i))
                }
            }
        }
    }

    static String getValue(String printStatement, boolean emptyAllowed) {
        boolean conditon = true
        String input
        while(conditon) {
            println "$printStatement"
            input = System.in.newReader().readLine()
            if (!emptyAllowed) {
                if (input == '') {
                    println 'Empty node names not allowed'
                } else {
                    conditon = false
                }
            } else {
                conditon = false
            }
        }
        input
    }
}

class Node {

    String name

    List<Node> childNodes = []

    Node parent

    Node(String nam, Node node) {
        this.name = nam
        this.parent = node
    }

    def addChild(String nam) { childNodes << new Node(nam, this) }

    void printTheGraph(String prefix, boolean isTail, boolean initial) {
        if (initial) { println "_______________________\n ${prefix + '   ' + name}" }
        else { println prefix + (isTail ? '└── ' : '├── ') + name }
        for (int i = 0; i < childNodes.size() - 1; i++) { childNodes[i].printTheGraph(prefix + (isTail ? '    ' : '│   '), false, false) }
        if (childNodes.size() > 0) { childNodes[childNodes.size() - 1].printTheGraph(prefix + (isTail ? '    ' : '│   '), true, false) }
    }
}
