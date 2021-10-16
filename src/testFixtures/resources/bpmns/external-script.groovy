package bpmns

String input = execution.getVariable("input")
String output = new StringBuilder(input).reverse()
execution.setVariable("output", output)