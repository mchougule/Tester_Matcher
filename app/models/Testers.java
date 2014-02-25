package models;

import java.util.*;
import javax.persistence.*;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;

@Entity
public class Testers extends Model {

//	@id
	public Long testerId;

	public String firstName;
	public String lastName;
	public String country;

	@Formats.DateTime(pattern="dd/MM/yyyy")
	public Date lastLogin = new Date();

	public static Finder<Long, Tester> find = new Finder<Long, Tester> (
		Long.class, Tester.class
	);
}