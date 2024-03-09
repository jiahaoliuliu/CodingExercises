package Graph.easy

class Graph {
    var adjList: HashMap<Int, ArrayList<Int>>

    constructor() {
        adjList = HashMap()
    }

    constructor(adjList: HashMap<Int, ArrayList<Int>>) {
        this.adjList = adjList
    }
}
