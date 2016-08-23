import java.io.Console
import java.math.MathContext
import java.math.BigDecimal
import java.text.DecimalFormat

class AverageScore {
	
	List scoresList = []
	Integer student_no = 0
	Integer sum = 0
	Integer score
	
	BigDecimal avg
	
	static void main(String[] args) {
		AverageScore averageScore = new AverageScore()
		println "Please select the kind of program to execute"
		println "Enter 1 to execute Sentinel Controlled Structure"
		println "Enter 2 to execute counter Controlled Structure"
		try {
			def inp = System.console().readLine 'Choice: '
			Integer choice = Integer.parseInt(inp)
			if (choice == 1) {
				averageScore.sentinalGetStarted()
			} else if (choice == 2) {
				averageScore.counterGetStarted()
			} else {
				println 'Only 1 or 2 is allowed, try again'
				return
			}
		} catch(NumberFormatException nfe) {
            println('Only numbers allowed try again..')
            return
        }
	}
	
	def sentinalGetStarted() {
		while(score != -1) {
			collectScore()
		}
		student_no --
		calculateAvg()
		printResults()	
	}
	
	def counterGetStarted() {
		fillArrayWithRandom()
		addArrayValuesToSum()
		calculateAvg()
		printResults()
	}
	
	def collectScore() {
		score = 0
		student_no ++
        try{
            println "Enter score of student $student_no or -1 to get the average"
			def input = System.console().readLine 'Score or -1: '
			score = Integer.parseInt(input)
			if((score < -1) || (score > 100)) {
				student_no --
				println 'Only numbers between 1 to 100 are accepted'
				return
			}
        }catch(NumberFormatException nfe){
            println('Please enter a valid number or -1 to exit')
            return
        }
		sum += ((score==-1)?0:score)
	}

	def fillArrayWithRandom() {
		student_no = 10
		println '\n\nInitialized an array and adding random numbers..'
		Random random = new Random()
		def temp
		10.times {
			temp = random.nextInt(101)
			println "Adding $temp to the array"
			scoresList << temp
		}
	}
	
	def addArrayValuesToSum() {
		println '\nAdding the array values to the sum..'
		scoresList.each { it ->
			sum += it
		}
	}
	
	def calculateAvg() {
		avg = sum/student_no
	}
	
	def printResults() {
		DecimalFormat df = new DecimalFormat("####0.00")
		println '\n************Results Start***************\n\n'
		println "No. of students: $student_no"
		println "Total points or score: $sum"
		println "Average: ${df.format(avg)}"
		println '\n************Results End***************\n\n'
	}
}	