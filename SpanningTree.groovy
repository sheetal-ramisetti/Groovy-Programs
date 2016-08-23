public class SpanningTree {

    static int[][] matrix

    static List nodeNames
    static List visitedNodes
    static int noOfNodes

    static private List<Edge> edges = []
    static int[][] spanningTree

    def getStarted() {
        createEdgesList()
        sortEdges()
        calculateSpanningTree()
    }

    def createEdgesList() {
        for (int i = 0; i < noOfNodes; i++) {
            for (int j = 0; j < noOfNodes; j++) {
                if (matrix[i][j] && i != j) {
                    Edge edge = new Edge(i, j, matrix[i][j])
                    matrix[j][i] = 0
                    edges << edge
                }
            }
        }
    }

    def sortEdges() {
        edges.sort { it.weight }
    }

    def calculateSpanningTree() {
        edges.each {
            if (!visitedNodes.every() { it == 1}) {
                setSpanningTreeEdge(it)
                if (checkCycle(spanningTree, it.startNode)) { invalidateSpanningTreeEdge(it) }
                setEdgeVisited(it)
                if (it.weight != -1) { printEdge(it) }
            }
        }
    }

    def invalidateSpanningTreeEdge(Edge edge) {
        spanningTree[edge.startNode][edge.endNode] = 0
        spanningTree[edge.endNode][edge.startNode] = 0
        edge.weight = -1
    }

    def setSpanningTreeEdge(Edge edge) {
        spanningTree[edge.startNode][edge.endNode] = edge.weight
        spanningTree[edge.endNode][edge.startNode] = edge.weight
    }

    def setEdgeVisited(Edge edge) {
        visitedNodes[edge.startNode] = 1
        visitedNodes[edge.endNode] = 1
    }

    def printEdge(Edge edge) { println "${nodeNames[edge.startNode]} ${nodeNames[edge.endNode]} $edge.weight" }

    def checkCycle(int[][] adjacency_matrix, int source) {

        int[][] cycleCheckMatrix = new int[noOfNodes][noOfNodes]

        boolean cyclepresent = false

        for (int i = 0; i < noOfNodes; i++) {
            for (int j = 0; j < noOfNodes; j++) {
                cycleCheckMatrix[i][j] = adjacency_matrix[i][j]
            }
        }

        int[] visited = new int[noOfNodes]

        visited[source] = 1
        Stack<Integer> stack = new Stack<Integer>()
        stack.push(source)
        while (!stack.isEmpty()) {
            int element = stack.peek()
            for (int i = element; i < noOfNodes; i++) {
                if (cycleCheckMatrix[element][i] && visited[i] == 1) {
                    if (stack.contains(i)) { cyclepresent = true }
                } else if (cycleCheckMatrix[element][i] && visited[i] == 0) {
                    stack.push(i)
                    visited[i] = 1
                    cycleCheckMatrix[element][i] = 0
                    cycleCheckMatrix[i][element] = 0
                    element = i
                    i = 1
                }
            }
            stack.pop()
        }
        cyclepresent
    }

    public static void main(String[] args) {

        noOfNodes = getValue('Please enter the number of nodes')
        nameTheNodes(noOfNodes)
        //matrix = [[0,1,10,6,0,0,0], [1,0,0,9,0,4,0], [10,0,0,0,7,0,0], [6,9,0,0,3,8,5], [0,0,7,3,0,0,2],[0,4,0,8,0,0,0], [0,0,0,5,2,0,0]]
        takeMatrixInput()
        println "The result is: "
        new SpanningTree().getStarted()
    }

    private static nameTheNodes(int noOfNodes) {
        matrix = new int[noOfNodes][noOfNodes]
        spanningTree = new int[noOfNodes][noOfNodes]
        nodeNames = new List[noOfNodes]
        visitedNodes = new List[noOfNodes]

        for (int i = 0; i < noOfNodes; i++) {
            println "Enter the name of the node at position ${i + 1}: "
            nodeNames[i] = System.in.newReader().readLine()
            visitedNodes[i] = 0 as boolean
        }
        println 'Assigned following names to the nodes: '+nodeNames
    }

    private static takeMatrixInput() {
        for (int i = 0; i < noOfNodes; i++) {
            for (int j = 0; j < noOfNodes; j++) {
                if (i == j) {
                    println "___________________________________\nAssigning '0' to the edge between \'${nodeNames[i]}\'and \'${nodeNames[j]}\'........\n___________________________________"
                    matrix[i][j] = 0
                }
                else {
                    matrix[i][j] = getValue("Please enter the edge value between \'${nodeNames[i]} \'and \'${nodeNames[j]}\', Enter 0 if no vertex")
                }
            }
        }
    }

    private static getValue(String printStatement) {
        println "$printStatement"
        boolean condition = true
        int inp, i = 0
        while (condition) {
            if (i != 0) { println 'Please enter again...' }
            def input = System.in.newReader().readLine()//System.console().readLine 'Enter here: '
            try {
                inp = Integer.parseInt(input)
                condition = false
            } catch (Exception e) {
                println('Only numbers allowed try again..')
            }
            i++
        }
        inp
    }
}

class Edge {

    int startNode
    int endNode
    int weight

    Edge(int i, int j, int x) {
        startNode = i
        endNode = j
        weight = x
    }
}
