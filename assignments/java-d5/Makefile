all: test-output

Movie.class: /usr/bin/java JSONReadDaily.java
	@export CLASSPATH=.:./org-json-simple.jar || setenv CLASSPATH ".:./org-json-simple.jar" && javac JSONReadDaily.java

test-output: Movie.class input output
	@echo Testing output...
	@export CLASSPATH=.:./org-json-simple.jar || setenv CLASSPATH ".:./org-json-simple.jar" && java JSONReadDaily < input | diff -bEZ --suppress-common-lines -y - output

single: Movie.class
	@export CLASSPATH=.:./org-json-simple.jar || setenv CLASSPATH ".:./org-json-simple.jar" && java JSONReadDaily

test: test-output

clean:
	rm -rf *.class
