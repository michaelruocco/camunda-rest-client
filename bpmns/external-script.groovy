String input = execution.getVariable("inputString")
String output = new StringBuilder(input).reverse()
execution.setVariable("outputString", output)

Long count = execution.getVariable("inputLong") + 1
execution.setVariable("outputLong", count)