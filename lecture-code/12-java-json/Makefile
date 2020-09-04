all: single

prJSONfromURL.class: /usr/bin/java prJSONfromURL.java
	@export CLASSPATH=.:./org-json-simple.jar || setenv CLASSPATH ".:./org-json-simple.jar" && javac prJSONfromURL.java


single: prJSONfromURL.class
	@export CLASSPATH=.:./org-json-simple.jar || setenv CLASSPATH ".:./org-json-simple.jar" && java prJSONfromURL


clean:
	rm -rf *.class
