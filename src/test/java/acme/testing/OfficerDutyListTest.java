
package acme.testing;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public class OfficerDutyListTest extends AcmePlannerTest {

	@ParameterizedTest
	@CsvFileSource(resources = "/officer/duty.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void OfficerDutyList(final int recordIndex, final String title, final String workload, final String description, final String start, final String end, final String link) {

		super.signIn("officer1", "officer1");
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
}
