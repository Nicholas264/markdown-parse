import static org.junit.Assert.*;
import org.junit.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;

public class MarkdownParseTest {
	@Test
	public void testMDFiles() throws java.io.IOException {
		ArrayList<ArrayList<String>> expected = new ArrayList<>(Arrays.asList(
			new ArrayList<>(),
			new ArrayList<>(),
			new ArrayList<>(),
			new ArrayList<>(Arrays.asList("a link!", "https://something.com", "another link!", "some-page.html")),
			new ArrayList<>(Arrays.asList("link__**", "sdf"))
		));
		String[] files = new String[]{
			"empty-file.md",
			"empty-link.md",
			"image-file.md",
			"test-file.md",
			"hell.md"
		};
		for (int i = 0; i < files.length; i++) {
			System.out.println("Testing file " + files[i]);
			Path fileName = Path.of(files[i]);
			String contents = Files.readString(fileName);
			ArrayList<String> links = MarkdownParse.getLinks(contents);
	   		assertEquals(expected.get(i), links);
		}
	}
}