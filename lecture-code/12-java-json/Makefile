all: single

prJSONRead.class: /usr/bin/java prJSONRead.java
	@export CLASSPATH=.:./org-json-simple.jar || setenv CLASSPATH ".:./org-json-simple.jar" && javac prJSONRead.java


single: prJSONRead.class
	@export CLASSPATH=.:./org-json-simple.jar || setenv CLASSPATH ".:./org-json-simple.jar" && java prJSONRead


clean:
	rm -rf *.class
