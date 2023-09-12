package GUI;

import Exceptions.DatabaseStringOverflowException;
import Database.Database;
import java.awt.Component;
import java.awt.Container;
import java.util.ArrayList;
import javax.swing.JList;
import javax.swing.JSpinner;
import javax.swing.JToggleButton;
import Utils.Utils;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JScrollPane;

/*
* **Create new Theme**
* 1) initialize variable
* 2) set colors in initThemes() method (all themes have a default state)
* 3) add case to matchThemes() method
*
*
*
 */
public class ThemeCollection {

	private ArrayList<Theme> themes = new ArrayList<>();

	public ThemeCollection() {
		loadThemes();

	}

	public ArrayList<Theme> getThemes() {
		return themes;
	}

	public void setThemes(ArrayList<Theme> themes) {
		this.themes = themes;
	}

	public static void implementTheme(Component component, Theme theme) {
		String name = component.getName();
		// System.out.println("Component: " + component +"\nName: "+ name);
		// System.out.println(name);

		try {
			switch (name) {
				case "null.glassPane":
					break;
				case "null.layeredPane":
					break;
				case "null.contentPane":
					break;
				case "background":
					component.setBackground(theme.getBackground());
					break;
				case "background2":
					component.setBackground(theme.getSecBackground());
					break;
				case "label":
					component.setForeground(theme.getForeground());
					break;
				case "textbox":
					component.setBackground(theme.getTextBox());
					component.setForeground(theme.getTextBoxForeground());
					break;
				case "spinner":
					if (component instanceof JSpinner jSpinner) {
						jSpinner.getEditor().getComponent(0).setBackground(theme.getTextBox());
						jSpinner.getEditor().getComponent(0).setForeground(theme.getTextBoxForeground());
						// GUIMethods.hackilySetColor(jSpinner, Color.yellow);
					}
					break;
				case "button":
					component.setBackground(theme.getButton());
					// component.setForeground(theme.getButtonForeground());
					if (component instanceof JToggleButton jToggleButton) {
						jToggleButton.setOpaque(false);
						jToggleButton.setBackground(theme.getButton());
						jToggleButton.setForeground(theme.getButtonForeground());
					}
					break;
				case "buttonLabel":
					component.setForeground(theme.getButtonForeground());
				case "list":
					if (component instanceof JList jList) {
						jList.setBackground(theme.getList());
						jList.setForeground(theme.getListForeground());
						jList.setSelectionBackground(theme.getListFocus());
					}
					break;
				// case "tabpane":
				// if(component instanceof JTabbedPane jTabbedPane){
				// jTabbedPane.setBackground(theme.getList());
				// jTabbedPane.setForeground(theme.getListForeground());
				// }
				// break;
				case "toolbar":
					component.setBackground(theme.getToolbar());
					break;
				case "scrollPane":
					if (component instanceof JScrollPane) {
						((JScrollPane) component).getViewport().setBackground(theme.getSecBackground());
					}
				default:
					break;
			}
		} catch (NullPointerException e) {
			// System.out.println("Null name");
		}

		// Recurse through every component
		if (component instanceof Container container) {
			for (Component child : container.getComponents()) {
				implementTheme(child, theme);
			}
		}
	}

	public Theme matchThemes(String theme) {
		for (int i = 0; i < themes.size(); i++) {
			if (themes.get(i).getName().equals(theme)) {
				return themes.get(i);
			}
		}

		System.out.println("Theme was not found");
		return new Theme();
	}

	public void addTheme(Theme theme) {
		if (Utils.themeToJson(theme).length() > 255) {
			throw new DatabaseStringOverflowException("Json over 255 characters");
		}

		try {
			Database.db().INSERT("Themes", new String[] { "Theme_Name", "JSON" },
					new String[] { theme.getName(), Utils.themeToJson(theme) });
		} catch (SQLException ex) {
			Logger.getLogger(ThemeCollection.class.getName()).log(Level.SEVERE, null, ex);
		}

		themes.add(theme);
	}

	public final void loadThemes() {
		themes.clear();
		themes.add(new Theme()); // Adds default theme so it always has at least one

		ArrayList<String> themeJsons = new ArrayList<>();
		ArrayList<Object> objectList = new ArrayList<>();
		ArrayList<String> names = new ArrayList<>();
		try {
			objectList = Database.db().SELECT("Themes", "JSON");
		} catch (SQLException ex) {
			Logger.getLogger(ThemeCollection.class.getName()).log(Level.SEVERE, null, ex);
		}

		for (Object obj : objectList) {
			themeJsons.add((String) obj);
		}

		objectList.clear();

		try {
			objectList = Database.db().SELECT("Themes", "Theme_Name");
		} catch (SQLException ex) {
			Logger.getLogger(ThemeCollection.class.getName()).log(Level.SEVERE, null, ex);
		}

		for (Object obj : objectList) {
			names.add((String) obj);
		}

		for (int i = 0; i < themeJsons.size(); i++) {
			Theme newTheme = Utils.parseThemeJSON(themeJsons.get(i));
			newTheme.setName(names.get(i));
			themes.add(newTheme);
		}
	}
}
