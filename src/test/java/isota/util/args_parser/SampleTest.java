/**
 *
 */
package isota.util.args_parser;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Before;
import org.junit.Test;

/**
 * @author isota
 *
 */
public class SampleTest {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * {@link isota.util.args_parser.App#main(java.lang.String[])} のためのテスト・メソッド。
	 */
	@Test
	public void testMain() {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		System.setErr(new PrintStream(out));
		Sample.main(new String[] { "-h" });
		String lineCd = System.getProperty("line.separator");
		assertEquals("java isota.util.args_parser.Sample [-h] [-d dir] -f file" + lineCd
				+ "  -h      : show help" + lineCd
				+ "  -d dir  : set directory" + lineCd
				+ "  -f file : set file" + lineCd
				+ "", out.toString());
	}

}
