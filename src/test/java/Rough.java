import org.hamcrest.CoreMatchers;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class Rough {

	public static void main(String[] args) {

		assertThat("My Name Is Anthony", containsString("My"));

	}
}
