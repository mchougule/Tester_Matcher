package models;

import java.util.*;
import javax.persistence.*;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;

@Entity
public class Device extends Model {

	@Id
	public Long deviceId;

	public String description;

	@ManyToMany(mappedBy="devices", cascade=CascadeType.ALL)
	public List<Tester> testers;

	// finder
	public static Finder<Long, Device> find = new Finder<Long, Device> (
		Long.class, Device.class
	);
}