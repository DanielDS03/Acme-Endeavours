package acme.testing;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public class OfficerDeleteDutyTest extends AcmePlannerTest {
	
	
	@ParameterizedTest
	@CsvFileSource(resources = "/officer/delete.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)	
	public void deleteDuties(final int recordIndex, final String title,final String workload,final String description,final String start, final String end, final String link) {		
		
		super.signIn("officer5", "officer5");
		super.clickOnMenu("Officer", "My Duties");	
		
		// Checkea que se cumple que las columnas coinciden con los valores que indicamos
		super.checkColumnHasValue(recordIndex, 0, title);
		super.checkColumnHasValue(recordIndex, 1, workload);
		super.checkColumnHasValue(recordIndex, 2, description);
		super.checkColumnHasValue(recordIndex, 3, start);
		super.checkColumnHasValue(recordIndex, 4, end);

		super.clickOnListingRecord(recordIndex);

		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("workload",workload);
		super.checkInputBoxHasValue("start",start);
		super.checkInputBoxHasValue("end",end);
		super.checkInputBoxHasValue("link",link);
		super.clickOnSubmitButton("Delete");
		
		super.signOut(); 
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/officer/deleteNegative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void deleteDutiesNegativeOfficer(final int recordIndex, final String title, final String description,final String workload,final String start, final String end, final String link) {		

		// Se logea con el usuario officer
		super.signIn("officer2", "officer2");

		// Clica en el menú para acceder a las tareas del officer
		this.driver.get("localhost:8080/Acme-Endeavours/officer/duty/delete?id=76");
		
		super.checkPanicExists();
		
		super.signOut();
	}

}

