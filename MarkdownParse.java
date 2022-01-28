// File reading code from https://howtodoinjava.com/java/io/java-read-file-to-string-examples/
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.regex.*;


public class MarkdownParse {
	public static ArrayList<String> getLinks(String markdown) {
		ArrayList<String> toReturn = new ArrayList<>();
		Pattern p = Pattern.compile("\\[([^\\]]*)]\\(([^)]*)\\)");
		Matcher m = p.matcher(markdown);
		while (m.find()) {
			toReturn.add(m.group(1));
			toReturn.add(m.group(2));
		}
		return toReturn;
	}
	public static void main(String[] args) throws IOException {
		Path fileName = Path.of(args[0]);
		String contents = Files.readString(fileName);
		ArrayList<String> links = getLinks(contents);
		System.out.println(links);
	}
}