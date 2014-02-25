package models;

import java.util.*;
import javax.persistence.*;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;

@Entity
public class Tester extends Model {

	@Id
	public Long testerId;

	public String firstName;
	public String lastName;
	public String country;

	@Formats.DateTime(pattern="dd/MM/yyyy")
	public Date lastLogin = new Date();

	@ManyToMany(cascade=CascadeType.ALL)
	public List<Device> devices;

	// Retrieve testers for country
	public static List<Tester> findTester(String country) {

		// Create a new ArrayList of Tester objects
		List<Tester> testMe = new ArrayList<Tester>();
		String al = new String("ALL");
		String countr = new String(country);

		// If user selected all the testers, then query find all the testers
		if (countr.equals(al)) {
			testMe = Tester.find.all();
		} else {
			// Create the country into an array (Cause they can be chosen multiples)
			String[] countryArr = country.split(",");

			for (int i = 0; i < countryArr.length; i++) {
				// query to find testers from country
				testMe = find.where()
					.eq("country", countryArr[i])
					.findList();
			}
		}
		return testMe;
	}

	public static Finder<Long, Tester> find = new Finder<Long, Tester> (
		Long.class, Tester.class
	);
}