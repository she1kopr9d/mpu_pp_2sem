.PHONY: compile run clean

compile: clean
	mkdir -p target/classes
	find src -name "*.java" -exec javac -d target/classes {} +

run: compile
	java -cp target/classes Main

clean:
	rm -rf target