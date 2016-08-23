import java.io.Console
import java.lang.Math

class TestRational {

	static void main(String[] args) {
		def inp
		Boolean dontExit = true
		TestRational testRational = new TestRational()
		println "Please enter two fractions to do the calculations"
		println "The fractions should be a/b format, numeratior->a, denominator->b"
		Map fraction1 = testRational.readInputFraction('Enter fraction 1')
		Rational rational1 = new Rational(fraction1.numerator, fraction1.denominator)
		Map fraction2 = testRational.readInputFraction('Enter fraction 2')
		Rational rational2 = new Rational(fraction2.numerator, fraction2.denominator)
		while (dontExit) {
			println '\nPlease enter what you want to do with the fractions'
			println 'Enter \n1 for Additon, \n2 for Subtraction, \n3 for Multiplication, \n4 for Division and \n5 to exit'
			inp = System.console().readLine 'Input: '
			try {
				Integer choice = Integer.parseInt(inp)
				switch (choice) {
					case 1:
						Rational r1 = rational1.add(rational2)
						r1.printResults()
						r1.printFloat()
						break
					case 2:
						Rational r2 = rational1.sub(rational2)
						r2.printResults()
						r2.printFloat()
						break
					case 3:					   
                        Rational r3 = rational1.multiply(rational2)
						r3.printResults()
						r3.printFloat()
						break
					case 4:
					    if((rational1.numerator==0)&&(rational2.numerator==0)) {println 'Division 0/0 is not possible as it is undefined'}
					    else{
						Rational r4 = rational1.divide(rational2)
						r4.printResults()
						r4.printFloat()
					    }
						break
					case 5:
						dontExit = false
						break
					default:
						println 'Only Numbers 1-5 are allowed'
						break
				}
			} catch(NumberFormatException nfe) {
				println 'Only numbers between 1-4 are allowed'
			}
		}
	}	
	
	def readInputFraction(String printValue) {
		println "$printValue"
		Boolean conditon = true
		int i = 0
		Integer num1
		Integer denom1
		def inp
		while (conditon) {
			if ((conditon == true) && (i !=0)) { println 'Please enter the input again..'}
			inp = System.console().readLine 'Input: '
			if ((inp ==~ /-?[0-9]+\/-?[0-9]+/)) {
 				def (num, denom) = inp.split('/')
				num1 = Integer.parseInt(num)
				denom1 = Integer.parseInt(denom)
				if (denom1 == 0) {
					println 'Denominator cannot be zero'
				} else { conditon = false }
			} else {
				println 'Only a/b format is accepted, a and b being integers'
			}
			i++
		}
		[numerator: num1, denominator: denom1]
	}
}

class Rational {
	
	private Integer numerator 
	private Integer denominator

	def Rational() {
		println 'In the no argument constructor, Assinging some dummy values..'
		println 'setting 0/1 as a fraction'
		this.numerator = 0
		this.denominator = 1
	}

	def Rational(Integer num, Integer denom) {
		Map reduced = reduceFraction(num, denom)
		this.numerator = reduced.numerator
		this.denominator = reduced.denominator
	}
	
	private Integer commonDenom(Integer num, Integer denom) {
		if (denom == 0) { num }
		else { commonDenom(denom, num % denom) }
	}

	def reduceFraction(Integer num, Integer denom) {
		Integer commonDenominator = Math.abs(commonDenom(num, denom))
		Integer numerator = num/commonDenominator
		Integer denominator = denom/commonDenominator
		if (denominator < 0) {
			numerator = -(numerator)
			denominator = -(denominator)
		}
		if ((num != numerator) || (denom != denominator)) {
			print "The fraction is reduced to $numerator/$denominator from $num/$denom. "
			if (numerator == 0) { println 'which is 0.'} else { println '' }
		}
		[numerator: numerator, denominator: denominator]
	}
	
	def Rational add(Rational rational2) {
		println "Adding $numerator/$denominator and $rational2.numerator/$rational2.denominator"
		Integer num = (numerator * rational2.denominator) + (rational2.numerator * denominator)
		Integer denom = (denominator * rational2.denominator)
		new Rational(num, denom)
	}
	
	def Rational sub(Rational rational2) {
		println "Subtracting $numerator/$denominator and $rational2.numerator/$rational2.denominator"
		Integer num = (numerator * rational2.denominator) - (rational2.numerator * denominator)
        Integer denom = denominator * rational2.denominator
		new Rational(num, denom)
	}
	
	def Rational multiply(Rational rational2) {
		println "Multiplying $numerator/$denominator and $rational2.numerator/$rational2.denominator"
		Integer num = numerator * rational2.numerator
        Integer denom = denominator * rational2.denominator
		new Rational(num, denom)
	}
	
	def Rational divide(Rational rational2) {
		println "Dividing $numerator/$denominator and $rational2.numerator/$rational2.denominator"
		Integer num = numerator * rational2.denominator
        Integer denom = denominator * rational2.numerator
		new Rational(num, denom)
	}
	
	def printResults() {
		println "The Resulting value is: $numerator/$denominator"
	}
	
	def printFloat() {
		try {
			float result = numerator/denominator
			println "The floating point value of the fraction is $result"
		} catch (Exception e) {
			println 'The Resulting value is 1/0 which is undefined.'
		}
	}
}
	