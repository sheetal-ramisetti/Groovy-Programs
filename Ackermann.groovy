class Ackermann {

	public static void main(String[] args) {
		Ackermann ackermann = new Ackermann()
		int m
		int n
		m = ackermann.getValue("Enter m value")
		n = ackermann.getValue("Enter n value")
		println '\nvalue: ' + ackermann.A(m,n)
	}

	def getValue(String printStatement) {
		println "$printStatement"
		boolean condition = true
		int inp, i = 0
		while (condition) { 
			if (i != 0) { println "Only numbers between 0 and 3 are allowed. Please $printStatement again..." }
			def input = System.console().readLine 'Enter here: '
			try {
				inp = Integer.parseInt(input)
				if (inp >= 0 && inp <= 3) { condition = false }
			} catch (Exception e) {
				println('Only numbers allowed try again..')
			}
			i++
		}
		inp
	}

	def A(m, n) {
		if (m == 0) {
			return n + 1
		} else if (n == 0) {
			return A(m-1, 1)
		} else {
			return A(m-1, A(m, n-1))
		}
	}	
}