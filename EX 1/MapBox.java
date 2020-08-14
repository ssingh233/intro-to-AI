//Lab Partner: David Philip


import java.util.ArrayList;
import java.util.HashSet;

public class MapBox {

	ArrayList<Location> locations = new ArrayList<Location>();
	HashSet<String> trackedLocationNames = new HashSet<String>();

// set to 0
	double y_max = 0;
	double y_min = 0;
	double x_max = 0;
	double x_min = 0;
	
	// set default values to 0.0
	// return all
	public Object Westmost() {
		return x_min;
	}
	public Object Eastmost() {
		return x_max;
	}
	public Object Northmost() {
		return y_min;
	}
	public Object Southmost() {
		return y_max;	
	}
	
	//values given set value
	//and located 
	public void update(Location loc) {
		//compare min and max
		if (locations.isEmpty()) {
		x_min = loc.longitude;
		y_min = loc.latitude;
		x_max = loc.longitude;
		y_max = loc.latitude;
		} else {
			
		// values compared 
			if (loc.longitude < x_min) {
			x_min = loc.longitude;
			} 
			
			if (loc.longitude > x_min) {
				x_max = loc.longitude;
			} 
			
			if (loc.latitude < y_min) {
				y_min = loc.latitude;
			}
			
			if (loc.latitude>y_min) {
				y_max = loc.latitude;
			}	
		}	
	}
	
	public boolean recordLocation(Location loc) {
		// uses duplicate to see if there are duplicate names
		// if location file finds name, update, if not return//
		if(!trackedLocationNames.contains(loc.name)){
			update(loc);
				locations.add(loc);
				return true;
		}
		else 
		{
			return false;
		}
	}
}