
import org.junit.jupiter.api.*;


import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.journaldev.di.consumer.MyApplication;
import com.journaldev.di.services.MessageService;
import com.journaldev.di.services.MockMessageService;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MyApplicationTest {

	private Injector injector;

	@BeforeAll
	public void setUp() throws Exception {

		injector = Guice.createInjector(new AbstractModule() {
			
			@Override
			protected void configure() {
				bind(MessageService.class).to(MockMessageService.class);
			}
		});
	}

	@AfterAll
	public void tearDown() throws Exception {
		injector = null;
	}

	@Test
	public void test() {
		MyApplication appTest = injector.getInstance(MyApplication.class);
		Assertions.assertEquals(true, appTest.sendMessage("Hi Pankaj", "pankaj@abc.com"));;
	}
}
