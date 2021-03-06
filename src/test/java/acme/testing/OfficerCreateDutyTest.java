package acme.testing;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public class OfficerCreateDutyTest extends AcmePlannerTest {
	//Este metodo prueba que se crea correctamente un duty del officer con los datos pertinentes
	@ParameterizedTest
	@CsvFileSource(resources = "/officer/create-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)	
	public void create(final int recordIndex, final String title, final String description,final String workload,final String start, final String end, final String link) {		
		
		super.signIn("officer6", "officer6");
		super.clickOnMenu("Officer", "Create Duty");	
		
		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("workload",workload);
		super.fillInputBoxIn("start",start);
		super.fillInputBoxIn("end",end);
		super.fillInputBoxIn("workload",workload);
		super.fillInputBoxIn("link",link);
		super.clickOnSubmitButton("Create");
		
		super.clickOnMenu("Officer", "My Duties");

		super.checkColumnHasValue(recordIndex, 0, title);
		super.checkColumnHasValue(recordIndex, 1, workload);
		super.checkColumnHasValue(recordIndex, 2, description);
		super.checkColumnHasValue(recordIndex, 3, start);
		super.checkColumnHasValue(recordIndex, 4, end);

		super.clickOnListingRecord(recordIndex);

		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("start", start);
		super.checkInputBoxHasValue("end", end);
		super.checkInputBoxHasValue("workload", workload);
		
		super.signOut();
	}
	
	//Este metodo prueba que no se crea correctamente un duty del officer por meter de forma incorrecta algun dato
	@ParameterizedTest
	@CsvFileSource(resources = "/officer/create-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)	
	public void createNegative(final String title, final String description,final String workload,final String start, final String end, final String link) {
		
		super.signIn("officer1", "officer1");
		super.clickOnMenu("Officer", "Create Duty");	
		
		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("workload",workload);
		super.fillInputBoxIn("start",start);
		super.fillInputBoxIn("end",end);
		super.fillInputBoxIn("workload",workload);
		super.fillInputBoxIn("link",link);
		super.clickOnSubmitButton("Create");
		
		super.checkErrorsExist();

	}

}