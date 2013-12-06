package comp231.DreamHouse.tests;

import static org.junit.Assert.*;

import java.sql.ResultSet;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import DreamHouse.SearchResult;
import DreamHouse.UserDetail;

public class SearchResultTest {

	@Before
	public void setUp() throws Exception {
		System.out.println("Starting test of SearchResult constructor");
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("test of SearchResult constructor complete");
	}

	@Test
	public void testSearchResultConstructor() {
		ResultSet resultset=null;
		UserDetail user=new UserDetail();
		SearchResult sr = new SearchResult(user,resultset);
        assertNotNull(sr);

	}

}
