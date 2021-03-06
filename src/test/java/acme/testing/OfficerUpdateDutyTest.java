package acme.testing;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public class OfficerUpdateDutyTest extends AcmePlannerTest {
	//Este metodo prueba que se crea correctamente una task del Officer con los datos pertinentes
	@ParameterizedTest
	@CsvFileSource(resources = "/officer/update-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)	
	public void updatePositive(final int recordIndex, final String title, final String description,final String workload,final String start, final String end, final String link) {		
		
		super.signIn("officer5", "officer5");
		super.clickOnMenu("Officer", "My Duties");	
		
		super.clickOnListingRecord(recordIndex);
		
		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("workload",workload);
		super.fillInputBoxIn("start",start);
		super.fillInputBoxIn("end",end);
		super.fillInputBoxIn("link",link);
		super.clickOnSubmitButton("Update");

		super.clickOnListingRecord(recordIndex);

		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("workload",workload);
		super.checkInputBoxHasValue("start",start);
		super.checkInputBoxHasValue("end",end);
		super.checkInputBoxHasValue("link",link);
		
		super.signOut();
	}
	//Este metodo prueba que no se crea correctamente una task del officer por meter de forma incorrecta algun dato
	@ParameterizedTest
	@CsvFileSource(resources = "/officer/update-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)	
	public void updateNegative(final int recordIndex, final String title, final String description,final String workload,final String start, final String end, final String link) {
		
		super.signIn("officer5", "officer5");
		super.clickOnMenu("Officer", "My Duties");
		
		super.clickOnListingRecord(recordIndex);
		
		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("workload",workload);
		super.fillInputBoxIn("start",start);
		super.fillInputBoxIn("end",end);
		super.fillInputBoxIn("workload",workload);
		super.fillInputBoxIn("link",link);
		super.clickOnSubmitButton("Update");
		
		super.checkErrorsExist();
		super.signOut();

	}

}

