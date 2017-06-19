import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Predicate;

import Entities.*;
//import Utilities.FileAccessor;

/**
 * @author Manuel Lorenzo Cobacho
 * @since 19/02/2017
 *
 */

public class MLorenzoColonies {
	public static ArrayList<Camp> camps;
	static ObjectContainer db;

	public static void main(String[] args) throws IOException, ParseException {
		MLorenzoColonies CM = new MLorenzoColonies();

		CM.loadData(); 
		CM.connect(); // 0,5 points
		try {

			CM.addCamps(); // 0,5 points
			CM.listNumberOfChildrenFromCamps(); // 1 point
			CM.addNewChildToCamp(1); // 2 points
			CM.llistCampsByActivityName("Rafting"); // 2 points
			CM.showChildrenByGuardianName("Joan Anton Perez"); // 2 points
			CM.retrieveAllActivitiesFromCampLocation("Can Zam"); //1 point
			CM.deleteAll(); // 1 point
			
			} finally {

			db.close();
		}
	}

	private void loadData() throws IOException {
		FileAccessor.readActivityFile("activitats.csv");
		FileAccessor.readGuardianFile("cuidadors.csv");
		FileAccessor.readChildFile("nen.csv");
		FileAccessor.readCampFile("camps.csv");
		FileAccessor.readActivityCampFile("activitatsCamps.csv");
		FileAccessor.readChildCampFile("nensCamps.csv");
		camps = FileAccessor.getCampList();
	}

	private void connect() {
		File file = new File("colonies.db");
		String fullPath = file.getAbsolutePath();
		System.out.println(file.getAbsolutePath());
		db = Db4o.openFile(fullPath);
	}

	private void addCamps() {
		for(Camp c:FileAccessor.getCampList()){
			db.store(c);
		}
	}

	private void listNumberOfChildrenFromCamps() {
		Set<Camp> camps = new HashSet<Camp>();
		for (int i=1;i<FileAccessor.getCampList().size();i++){
			ObjectSet<Camp> result = db.queryByExample(new Camp(i,null,null,null,null));
			while (result.hasNext()){
				Camp ca= (Camp)result.next();
				camps.add(ca);
			}
		}		
		for(Camp c:camps){
			System.out.println(c.toString());
			System.out.println("Numero de nens inscrits: "+c.getChildren().size());
		}
	}

	private void addNewChildToCamp(int i) throws ParseException {
		String pattern = "yyyy-MM-dd";
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		Date data= sdf.parse("2013-12-14");
		Child child= new Child(45, "Pepet Pep",data , false, new Guardian("dni","nom","Phone", "Address", "mail"));
		
		ObjectSet<Camp> result = db.queryByExample(new Camp(i,null,null,null,null));
		Camp found = (Camp) result.next();
		found.getChildren().add(child);
		db.store(found);
	}

	private void llistCampsByActivityName(String string) {
		System.out.println("CAMPS AMB LA ACTIVITAT "+string+ ":");
		
		Activity ac= new Activity();
		ObjectSet<Activity> actis = db.queryByExample(new Activity(0,string));
		while (actis.hasNext()){
			ac=(Activity)actis.next();
		}
		ObjectSet<Camp> camps = db.queryByExample(new Camp(0,null,null,null,null));
		while (camps.hasNext()){
			Camp found = (Camp) camps.next();
			if (found.getActivities().contains(ac)){
				System.out.println(found.toString());
			}
		}		
	}

	private void showChildrenByGuardianName(String string) {
		Set<Child> children = new HashSet<Child>();
		System.out.println("NENS AMB GUARDIA "+string+ ":");
		Guardian guard= new Guardian();
		ObjectSet<Guardian> g = db.queryByExample(new Guardian(null,string, null,null,null));
		while (g.hasNext()){
			guard=(Guardian)g.next();
		}
		
		ObjectSet<Child> childs = db.queryByExample(new Child(0,null,null, true, guard));
		while (childs.hasNext()){
			Child c= (Child)childs.next();
			children.add(c);
		}
		ObjectSet<Child> childsf = db.queryByExample(new Child(0,null,null, false, guard));
		while (childsf.hasNext()){
			Child c= (Child)childsf.next();
			children.add(c);
		}
		for (Child cc:children){
			System.out.println(cc.toString());
		}
	}

	@SuppressWarnings("unchecked")
	private void retrieveAllActivitiesFromCampLocation(String string) {
		
		System.out.println("ACTIVITIES FROM CAMP LOCATION : ");
		ObjectSet<Camp> result = db.query(new Predicate() {
			public boolean match(Camp camp) {
			return camp.getLocation().equals(string);
			}

			@Override
			public boolean match(Object arg0) {
				return false;
			}
			});
		while (result.hasNext()){
			Camp c= (Camp) result.next();
			System.out.println("Camp: "+c.getLocation()+ " Activitats : ");
			Set<Activity> actis= c.getActivities();
			for (Activity a: actis){
				System.out.println(a.toString());
			}
		}
	}

	private void deleteAll() {
		ObjectSet<Camp> result =db.queryByExample(Camp.class);
		while(result.hasNext()){
			db.delete(result.next());
		}
		System.out.println("Borrado Camps");
		ObjectSet<Child> result1 =db.queryByExample(Child.class);
		while(result1.hasNext()){
			db.delete(result1.next());
		}
		System.out.println("Borrado Childs");
		ObjectSet<Guardian> result2 =db.queryByExample(Guardian.class);
		while(result2.hasNext()){
			db.delete(result2.next());
		}
		System.out.println("Borrado Guardian");
		ObjectSet<Activity> result3 =db.queryByExample(Activity.class);
		while(result3.hasNext()){
			db.delete(result3.next());
		}
		System.out.println("Borrado Activity");
		}
	}
