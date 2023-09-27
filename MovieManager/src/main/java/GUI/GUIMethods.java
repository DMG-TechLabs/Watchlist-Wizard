package GUI;

import Database.Database;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.lang.reflect.Field;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.basic.BasicArrowButton;
import org.imgscalr.Scalr;
import org.springframework.expression.spel.ast.QualifiedIdentifier;

import kdesp73.databridge.helpers.QueryBuilder;
import kdesp73.themeLib.*;
import java.util.regex.Pattern;
import javax.swing.ImageIcon;

public class GUIMethods {

    public static void loadImage(JLabel imageLabel, String dir) {
        imageLabel.setIcon(new ImageIcon(dir));
    }

    private static boolean openWebpage(URI uri) {
        Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
        if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
            try {
                desktop.browse(uri);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static boolean openWebpage(URL url) {
        try {
            return openWebpage(url.toURI());
        } catch (URISyntaxException e) {
        }
        return false;
    }

    public static Theme setupFrame(JFrame frame, Dimension dimension, String title) {
        Theme theme = null;

        frame.setMinimumSize(dimension);
        frame.setLocationRelativeTo(null); // center frame
        frame.setTitle(title);

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(EditFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
			ResultSet rs = Database.connection().executeQuery(new QueryBuilder().select("Theme").from("Settings").build());
			rs.next();
            String themeName = rs.getString(1);
            ThemeCollection themes = GUIMethods.getThemes();
            theme = themes.matchTheme(themeName);
            ThemeCollection.applyTheme(frame, theme);

        } catch (SQLException ex) {
            Logger.getLogger(SettingsFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (theme == null) {
            System.err.println("Theme is null");
            theme = new Theme(new JsonString("{\"list_focus\":\"120821\",\"fg\":\"ffffff\",\"scrollbar\":\"0b3948\",\"textbox\":\"381863\",\"bg\":\"120821\",\"progress_bar\":\"44af69\",\"list\":\"381863\",\"extra_1\":\"120821\",\"extra_2\":\"000002\",\"extra_0\":\"6a5d8c\",\"textbox_fg\":\"ffffff\",\"extra_5\":\"000005\",\"btn_fg\":\"ffffff\",\"extra_6\":\"000006\",\"bg_2\":\"381863\",\"extra_3\":\"000003\",\"extra_4\":\"000004\",\"name\":\"Dark\",\"extra_9\":\"000009\",\"list_fg\":\"ffffff\",\"extra_7\":\"000007\",\"fg_2\":\"ffffff\",\"extra_8\":\"000008\",\"btn\":\"381863\"}"));
            ThemeCollection.applyTheme(frame, theme);
        }

        try {
			ResultSet rs = Database.connection().executeQuery(new QueryBuilder().select("Font").from("Settings").build());
			rs.next();
            GUIMethods.changeGlobalFont(new Component[]{frame}, 4, rs.getString(1));
        } catch (SQLException ex) {
            Logger.getLogger(Frame.class.getName()).log(Level.SEVERE, null, ex);
        }

        return theme;
    }

    public static Theme setupDialog(JDialog dialog, Dimension dimension, String title) {
        Theme theme = null;

        dialog.setMinimumSize(dimension);
        dialog.setLocationRelativeTo(null); // center frame
        dialog.setTitle(title);

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(EditFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
			ResultSet rs = Database.connection().executeQuery(new QueryBuilder().select("Theme").from("Settings").build());
			rs.next();
            String themeName = rs.getString(1);
            ThemeCollection themes = GUIMethods.getThemes();
            theme = themes.matchTheme(themeName);
            ThemeCollection.applyTheme(dialog, theme);

        } catch (SQLException ex) {
            Logger.getLogger(SettingsFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
			ResultSet rs = Database.connection().executeQuery(new QueryBuilder().select("Font").from("Settings").build());
			rs.next();
            GUIMethods.changeGlobalFont(new Component[]{dialog}, 4, rs.getString(1));
		} catch (SQLException ex) {
            Logger.getLogger(Frame.class.getName()).log(Level.SEVERE, null, ex);
        }

        return theme;
    }

    public static void changeFont(Component component, int fontSize, String font) {
        Font f = new Font(font, Font.PLAIN, 12);
        component.setFont(new Font(f.getName(), f.getStyle(), f.getSize() + fontSize));
        if (component instanceof Container container) {
            for (Component child : container.getComponents()) {
                changeFont(child, fontSize, font);
            }
        }
    }

    public static void changeGlobalFont(Component[] c, int size, String font) {
        for (Component c1 : c) {
            GUIMethods.changeFont(c1, size, font);
        }
    }

    public static ArrayList<Integer> revoveDups(ArrayList<Integer> arr) {
        Set<Integer> s = new HashSet<>();
        s.addAll(arr);
        arr = new ArrayList<>();
        arr.addAll(s);

        return arr;
    }

    public static JDialog dialog(String message, String subtitleMessage, String title) {
        JDialog dialog = new JDialog();

        Theme selectedTheme = null;
        ThemeCollection tc = new ThemeCollection();
        Font font = new Font("sans-serif", Font.PLAIN, 14);
        Color textColor = new Color(0, 0, 0);
        Color bgColor = new Color(255, 255, 255);
        JLabel errorLabel = new JLabel();
        JLabel subtitle = new JLabel(subtitleMessage);
        JPanel bg = new JPanel();
        FlowLayout layout = new FlowLayout(FlowLayout.CENTER, 100, 20);

        dialog.setSize(300, 200);
        dialog.setTitle(title);
        dialog.setResizable(false);
        dialog.add(bg);
        dialog.setLocationRelativeTo(null);

        bg.setName("background");
        bg.add(subtitle);
        bg.add(errorLabel);
        bg.setBackground(bgColor);
        bg.setLayout(layout);

        errorLabel.setName("label");
        errorLabel.setText(breakString(message, 2));
        errorLabel.setAlignmentY(200);
        errorLabel.setForeground(textColor);
        errorLabel.setFont(font);

        subtitle.setForeground(Color.RED);
        subtitle.setFont(font.deriveFont(Font.BOLD));

        try {
   			ResultSet rs = Database.connection().executeQuery(new QueryBuilder().select("Theme").from("Settings").build());
			rs.next();
            String themeName = rs.getString(1);
            System.out.println(themeName);
            ThemeCollection themes = new ThemeCollection();
            themes.loadThemes(new File(System.getProperty("user.dir").replaceAll(Pattern.quote("\\"), "/") + "/themes/"));
            selectedTheme = themes.matchTheme(themeName);

        } catch (SQLException ex) {
            Logger.getLogger(SettingsFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

        ThemeCollection.applyTheme(dialog, selectedTheme);
        dialog.setVisible(true);

        return dialog;
    }

    public static void dialogError(String error) {
        // dialog(error, "An error has occured!", "Error");
        JOptionPane.showMessageDialog(null, error, "Error", JOptionPane.ERROR_MESSAGE, null);
    }

    public static String breakString(String text, int rows) {
        if (text.length() == 0) {
            return " ";
        }

        ArrayList<Integer> temp = new ArrayList<>();
        for (int i = 0; i < text.length(); i++) {
            temp.add(text.indexOf(' ', i));
        }
        ArrayList<Integer> spaces = GUIMethods.revoveDups(temp);
        Collections.sort(spaces);
        spaces.remove(0);

        if (spaces.isEmpty()) {
            return text;
        }

        switch (rows) {
            case 1 -> {
                return text;
            }
            case 2 -> {
                return "<html>" + text.substring(0, spaces.get(spaces.size() / 2)) + "<br>"
                        + text.substring(spaces.get(spaces.size() / 2) + 1, text.length()) + "</html>";
            }
            case 3 -> {
                return "<html>" + text.substring(0, spaces.get(spaces.size() / 3)) + "<br>"
                        + text.substring(spaces.get(spaces.size() / 3), spaces.get(spaces.size() * 2 / 3)) + "<br>"
                        + text.substring(spaces.get(spaces.size() * 2 / 3), text.length()) + "</html>";
            }
            case 4 -> {
                return "<html>" + text.substring(0, spaces.get(spaces.size() / 4)) + "<br>"
                        + text.substring(spaces.get(spaces.size() / 4), spaces.get(spaces.size() * 2 / 4)) + "<br>"
                        + text.substring(spaces.get(spaces.size() * 2 / 4), spaces.get(spaces.size() * 3 / 4)) + "<br>"
                        + text.substring(spaces.get(spaces.size() * 3 / 4), text.length()) + "</html>";
            }

            default ->
                System.out.println("Invalid row num");
        }
        return " ";
    }

    public static void hackilySetColor(JSpinner spinner, Color color) {
        int n = spinner.getComponentCount();
        for (int i = 0; i < n; i++) {
            Component c = spinner.getComponent(i);
            if (c instanceof BasicArrowButton) {
                try {
                    Field field = BasicArrowButton.class.getDeclaredField("darkShadow");
                    field.setAccessible(true);
                    field.set(c, color);
                    field.setAccessible(false);
                } catch (IllegalAccessException | IllegalArgumentException | NoSuchFieldException
                        | SecurityException e) {
                }
            }
        }
    }

    public static String rgbToHex(int R, int G, int B) {
        return String.format("#%02x%02x%02x", R, G, B);
    }

    public static String rgbToHex(Color c) {
        return String.format("%02x%02x%02x", c.getRed(), c.getGreen(), c.getBlue());
    }

    public static Color hexToColor(String hex) {
        hex = "#" + hex;
        return Color.decode(hex);
    }

    public static BufferedImage resizeImageWidth(BufferedImage originalImage, int targetWidth) {
        return Scalr.resize(originalImage, targetWidth);
    }

    public static BufferedImage resizeImageHeight(BufferedImage originalImage, int targetWidth) {
        return Scalr.resize(originalImage, targetWidth);
    }

    public static BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) {
        return Scalr.resize(originalImage, Scalr.Method.AUTOMATIC, Scalr.Mode.AUTOMATIC, targetWidth, targetHeight,
                Scalr.OP_ANTIALIAS);
    }

    public static ThemeCollection getThemes() {
        ThemeCollection themeCollection = new ThemeCollection();
        themeCollection.loadThemes(new File(System.getProperty("user.dir").replaceAll(Pattern.quote("\\"), "/") + "/themes/"));

        return themeCollection;
    }
}
