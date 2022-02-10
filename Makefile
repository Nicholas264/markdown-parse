libs=-cp ".:./lib/*"

test: MarkdownParse.class MarkdownParseTest.class
	java $(libs) org.junit.runner.JUnitCore MarkdownParseTest

run: MarkdownParse.class
	java MarkdownParse hell.md

MarkdownParse.class: MarkdownParse.java
	javac MarkdownParse.java

MarkdownParseTest.class: MarkdownParseTest.java
	javac $(libs) MarkdownParseTest.java