

import java.io.BufferedReader;

import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import Entities.Activity;
import Entities.Camp;
import Entities.Guardian;
import Entities.Child;

public class FileAccessor {

	private static ArrayList<Guardian> guardianList = new ArrayList<Guardian>();
	private static ArrayList<Child> childList = new ArrayList<Child>();
	private static ArrayList<Activity> activityList = new ArrayList<Activity>();
	private static ArrayList<Camp> campList = new ArrayList<Camp>();

	public static ArrayList<Guardian> getGuardianList() {
		return guardianList;
	}

	public static void setGuardianList(ArrayList<Guardian> guardianList) {
		FileAccessor.guardianList = guardianList;
	}

	public static ArrayList<Child> getChildList() {
		return childList;
	}

	public static void setChildList(ArrayList<Child> childList) {
		FileAccessor.childList = childList;
	}

	public static ArrayList<Activity> getActivityList() {
		return activityList;
	}

	public static void setActivityList(ArrayList<Activity> activityList) {
		FileAccessor.activityList = activityList;
	}

	public static ArrayList<Camp> getCampList() {
		return campList;
	}

	public static void setCampList(ArrayList<Camp> campList) {
		FileAccessor.campList = campList;
	}

	public static void readChildFile(String filename) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(filename));

		String linea = br.readLine();
		while ((linea = br.readLine()) != null) {

			String tokens[] = linea.split(",");
			int childId = Integer.parseInt(tokens[0]);
			String name = tokens[1];

			Date birthDate = null;
			String dniGuardian = null;
			boolean specialMenu = false;
			Guardian guardian = null;

			try {

				SimpleDateFormat dateformat = new SimpleDateFormat("YYYY-MM-DD");
				birthDate = dateformat.parse(tokens[2]);

				dniGuardian = tokens[3];
				specialMenu = Boolean.parseBoolean(tokens[4]);

				for (Guardian g : guardianList) {

					if (g.getDni().equals(dniGuardian)) {
						guardian = g;
					}

				}
			} catch (ParseException e) {
				e.printStackTrace();
			}

			Child toBeAdded = new Child(childId, name, birthDate, specialMenu, guardian);
			childList.add(toBeAdded);
		}
		br.close();
	}

	public static void readGuardianFile(String filename) {

		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(filename));

			String line = bufferedReader.readLine();
			while ((line = bufferedReader.readLine()) != null) {
				String[] tokens = line.split(",");

				String dni = tokens[0];
				String name = tokens[1];
				String phone = tokens[2];
				String address = tokens[3];
				String email = tokens[4];

				guardianList.add(new Guardian(dni, name, phone, address, email));

			}

			bufferedReader.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void readActivityFile(String filename) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));

			String linea = br.readLine();
			while ((linea = br.readLine()) != null) {
				String tokens[] = linea.split(",");
				int id = Integer.parseInt(tokens[0]);
				String description = tokens[1];
				activityList.add(new Activity(id, description));
			}

			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void readCampFile(String filename) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));

			String linea = br.readLine();
			while ((linea = br.readLine()) != null) {
				String tokens[] = linea.split(",");

				int id = Integer.parseInt(tokens[0]);
				String description = tokens[1];
				String location = tokens[2];

				SimpleDateFormat dateformat = new SimpleDateFormat("DD-MM-YYYY");
				Date startDate = dateformat.parse(tokens[3]);
				Date endDate = dateformat.parse(tokens[4]);

				campList.add(new Camp(id, description, location, startDate, endDate));
			}

			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public static void readChildCampFile(String filename) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));

			String linea = br.readLine();
			while ((linea = br.readLine()) != null) {
				String tokens[] = linea.split(",");

				int idCamp = Integer.parseInt(tokens[0]);
				int idChild = Integer.parseInt(tokens[1]);

				for (Camp currentCamp : campList) {
					for (Child currentChild : childList) {
						if (currentChild.getIdChild() == idChild && currentCamp.getId() == idCamp) {
							currentCamp.getChildren().add(currentChild);
						}
					}
				}

			}

			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void readActivityCampFile(String filename) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));

			String linea = br.readLine();
			while ((linea = br.readLine()) != null) {
				String tokens[] = linea.split(",");

				int idCamp = Integer.parseInt(tokens[0]);
				int idAct = Integer.parseInt(tokens[1]);

				for (Camp currentCamp : campList) {
					for (Activity currentActivity : activityList) {
						if (currentActivity.getId() == idAct && currentCamp.getId() == idCamp) {
							currentCamp.getActivities().add(currentActivity);
						}
					}
				}

			}

			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
