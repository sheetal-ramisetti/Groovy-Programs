import java.util.Random

class Sorting {

	Vector<Integer> vector = new Vector<Integer>()
	List<Integer> list = []

	static void main(String[] args) {
		Sorting sorting = new Sorting()
		
		int random
		10.times {
			random = sorting.randomNumber
			sorting.addToVector(random)
			sorting.addToList(random)
			sorting.sortVector()
			sorting.sortList()
			sorting.printBoth()
		}
	}

	public int getRandomNumber() {
		int rand = new Random().nextInt(200) - 100
		println "Number generated: $rand"
		rand
	}
	
	public void addToVector(int random) {
		vector.add(random)
	}
	
	public void addToList(int random) {
		list << random
	}
	
	public void sortVector() {
		boolean swapped = true
		while (swapped) {
	        swapped = false
	        for (int i = 0; i < vector.size() - 1; i++) {
                if (vector.elementAt(i).compareTo(vector.elementAt(i + 1)) > 0) {
                    Integer temp = vector.elementAt(i)
                    vector.setElementAt(vector.elementAt(i + 1), i)
                    vector.setElementAt(temp, i + 1)
                    swapped = true
                }
	        }
		}
	}
	
	public void sortList() {
		boolean flag = true  
		int temp  
		while (flag) {
			flag = false    
			for (int j = 0; j < list.size() -1; j++) {
			   if (list[j] > list[j+1]) {
			       temp = list[j]  
			       list[j] = list[j+1]
			       list[j+1] = temp
			       flag = true   
			    } 
			} 
		} 
	}
	
	public printBoth() {
		println "Vector: $vector"
		println "List: $list"
	}
}
