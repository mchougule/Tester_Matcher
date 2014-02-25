package models;

import java.util.*;
import javax.persistence.*;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;

import com.avaje.ebean.*;

@Entity
public class Bug extends Model {

	@Id
	public Long bugId;

	@ManyToOne
	public Device device;

	@ManyToOne
	public Tester tester;

	public static Finder<Long, Bug> find = new Finder<Long, Bug> (
		Long.class, Bug.class
	);

	/*
		Find bugs and return a list of it
	*/ 
	public static List<Bug> findBugs(Long id, Long devId) {

		// Find bugs from testerId and deviceId
		List<Bug> bugMe = find.where()
							.eq("tester.testerId", id)
							.eq("device.deviceId", devId)
							.findList();
		return bugMe;

	}
}