// File reading code from https://howtodoinjava.com/java/io/java-read-file-to-string-examples/
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.regex.*;
import java.util.Map;
import java.util.*;
import java.io.File;

public class MarkdownParse {
	public static ArrayList<String> getLinks(String markdown) {
		String md = removeCode(markdown);
		ArrayList<String> toReturn = new ArrayList<>();
		Pattern p = Pattern.compile("(?:[^!]|^)\\[([^\\]]*)]\\(([^)]+)\\)");
		Matcher m = p.matcher(md);
		while (m.find()) {
			toReturn.add(m.group(1));
			//toReturn.add(m.group(2));
		}
		return toReturn;
	}

	public static Map<String, List<String>> getLinks(File dirOrFile) throws IOException {
        Map<String, List<String>> result = new HashMap<>();
        if(dirOrFile.isDirectory()) {
            for(File f: dirOrFile.listFiles()) {
                result.putAll(getLinks(f));
            }
            return result;
        }
        else {
            Path p = dirOrFile.toPath();
            int lastDot = p.toString().lastIndexOf(".");
            if(lastDot == -1 || !p.toString().substring(lastDot).equals(".md")) {
                return result;
            }
            ArrayList<String> links = getLinks(Files.readString(p));
            result.put(dirOrFile.getPath(), links);
            return result;
        }
    }
	// This isn't a perfect method (far from it) and it only works in basic circumstances
	// parsing markdown rules is quite difficult
	public static String removeCode(String markdown) {
		Pattern p = Pattern.compile("`+");
		Matcher m = p.matcher(markdown);
		int maxlen = 0;
		while (m.find()) {
			maxlen = Math.max(m.group(0).length(), maxlen);
		}
		String fin = markdown;
		for (int i = maxlen; i >= 1; i--) {
			Pattern pp = Pattern.compile("`{"+i+"}.*?`{"+i+"}", Pattern.DOTALL | Pattern.MULTILINE);
			fin = pp.matcher(fin).replaceAll("");
		}
		return fin;
	}
	public static void main(String[] args) throws IOException {
		File input = new File(args[0]);
		/*Path fileName = Path.of(args[0]);
		String contents = Files.readString(fileName);
		ArrayList<String> links = getLinks(contents);*/
		System.out.println(getLinks(input));
	}
}