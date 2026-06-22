.PHONY: compile run clean

compile:
	mkdir -p target/classes
	javac -d target/classes src/*.java

run: compile
	java -cp target/classes Main

clean:
	rm -rf target
