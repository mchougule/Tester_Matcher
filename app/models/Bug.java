package models;

import java.util.*;
import javax.persistence.*;
import com.avaje.ebean.annotation.Sql;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;
import com.avaje.ebean.RawSql.*;

import com.avaje.ebean.*;

@Entity
public class Bug extends Model {

	@Id
	public Long bugId;

	@ManyToOne
	@JoinColumn(name="device_id", nullable=false)
	public Device device;

	@ManyToOne
	@JoinColumn(name="tester_id", nullable=false)
	public Tester tester;

	public static Finder<Long, Bug> find = new Finder<Long, Bug> (
		Long.class, Bug.class
	);

	/*
		Find bugs and return a list of it
	*/ 
	public static List<Bug> findBugs(Long id, String devName) {



		// Find bugs from testerId and deviceId
		List<Bug> bugMe = new ArrayList<Bug>();
		bugMe = find.where()
							.eq("tester.testerId", id)
							.eq("device.description", devName)
							.findList();
		return bugMe;

	}
}