all: test-output

Movie.class: /usr/bin/java FileReadDaily.java
	@javac FileReadDaily.java

test-output: Movie.class user-input program-output
	@echo Testing output...
	@java FileReadDaily < user-input | diff -bEZ --suppress-common-lines -y - program-output

single: Movie.class
	@java FileReadDaily

test: test-output

clean:
	rm -rf *.class
