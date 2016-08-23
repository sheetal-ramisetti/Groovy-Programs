import java.io.Console
import java.math.BigDecimal

class PatientCharges {
	
	
	public static void main(String[] args) {
		Integer choice
		PatientCharges patientCharges = new PatientCharges()
		println "Please enter what kind of a patient you are admitted as"
		println "Enter 1 if you are an inpatient"
		println "Enter 2 if you are an outpatient"
		try {
			def inp = System.console().readLine 'PatientType: '
			choice = Integer.parseInt(inp)//Thorws number format exception if the inp is a string
			if (choice == 1 || choice == 2) {
				patientCharges.getStarted(choice)
			} else {
				println 'Only 1 or 2 is allowed, try again'
				return
			}
		} catch(NumberFormatException nfe) {
            println('Only numbers allowed try again..')
            return
        }
	}
	
	public getStarted(Integer choice) {
		BigDecimal daysSpent
		BigDecimal dayRate
		BigDecimal hospitalServiceCharges
		BigDecimal hospitalMedicationCharges
		
		BigDecimal totalCharges

		hospitalServiceCharges = getValue('Enter the charges for hospital(lab tests, etc.)')
		hospitalMedicationCharges = getValue('Enter the hospital mediacation charges')
		if (choice == 1) {
			daysSpent = getValue('Enter the No. of days spent in hospital')
			dayRate = getValue('Enter the day Rate of the hospital')
			totalCharges = calculateTotalCharges(hospitalServiceCharges, hospitalMedicationCharges, daysSpent, dayRate)
		} else {
			totalCharges = calculateTotalCharges(hospitalServiceCharges, hospitalMedicationCharges)
		}
		println "\n\nThe total charges incurred are $totalCharges"
	}
	
	private getValue(String printStatement) {
		int i = 0
		Boolean condition = true
		println "$printStatement"
		def x 
		while(condition) {
			if ((condition == true) && (i != 0)) {
				println 'Please enter the input again........'
				println "$printStatement"
			}
			def input = System.console().readLine 'Enter here: '
			if (input.isNumber()) {
				if (input.isInteger()) {
					x = Integer.parseInt(input)
				}
				else if (input.isBigDecimal()) { 
					x = new BigDecimal(input)
				}
				if ((x < 0)) {
					println 'Only positive numbers are allowed'
				} else { condition = false }
			} else { println 'Only Numbers Allowed' }
			i++
		}
		return x
 	}
	
	private BigDecimal calculateTotalCharges(BigDecimal hospitalServiceCharges, BigDecimal hospitalMedicationCharges, BigDecimal daysSpent, BigDecimal dayRate) {
		//return dayRate * (hospitalServiceCharges + hospitalMedicationCharges + daysSpent)
		return hospitalServiceCharges + hospitalMedicationCharges + (daysSpent * dayRate)
	}
	
	private BigDecimal calculateTotalCharges(BigDecimal hospitalServiceCharges, BigDecimal hospitalMedicationCharges) {
		return hospitalServiceCharges + hospitalMedicationCharges
	}
}	