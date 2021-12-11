package acme.testing;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;


public class AuthenticatedDutyListTest extends AcmePlannerTest {
	
	//Este método prueba que se liste bien las tareas estando autenticado
		
	@ParameterizedTest
	@CsvFileSource(resources = "/authenticated/duty.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)	
	public void listPositive(final int recordIndex, final String title, final String description , final String start, final String end,final String workload) {		
		super.signIn("officer1", "officer1");
		super.clickOnMenu("Authenticated", "Duties");		
		
		
		super.checkColumnHasValue(recordIndex, 0, title);
		super.checkColumnHasValue(recordIndex, 1, description);
		super.checkColumnHasValue(recordIndex, 2, start);
		super.checkColumnHasValue(recordIndex, 3, end);
		super.checkColumnHasValue(recordIndex, 4, workload);
		
		super.clickOnListingRecord(recordIndex);
		
		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("start", start);
		super.checkInputBoxHasValue("end", end);
		super.checkInputBoxHasValue("workload", workload);
		
		super.signOut();
	}
	
}
