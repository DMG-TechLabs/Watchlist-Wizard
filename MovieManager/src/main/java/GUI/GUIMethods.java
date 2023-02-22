package GUI;

import Database.Database;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.basic.BasicArrowButton;
import org.imgscalr.Scalr;

public class GUIMethods {

        
        public static Theme setupFrame(JFrame frame, Dimension dimension, String title) {
                Theme theme = null;

                frame.setMinimumSize(dimension);
                frame.setLocationRelativeTo(null); //center frame
                frame.setTitle(title);

                try {
                        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                        Logger.getLogger(EditFrame.class.getName()).log(Level.SEVERE, null, ex);
                }

                try {
                        String themeName = (String)Database.db().SELECT("Settings", "Theme").get(0);
                        theme = new ThemeCollection().matchThemes(themeName);
                        ThemeCollection.implementTheme(frame, theme);

                } catch (SQLException ex) {
                        Logger.getLogger(SettingsFrame.class.getName()).log(Level.SEVERE, null, ex);
                }

                try {
                        GUIMethods.changeGlobalFont(new Component[]{frame}, 0, (String)Database.db().SELECT("Settings", "Font").get(0));
                } catch (SQLException ex) {
                        Logger.getLogger(Frame.class.getName()).log(Level.SEVERE, null, ex);
                }

                return theme;
        }

        public static Theme setupDialog(JDialog dialog, Dimension dimension, String title) {
                Theme theme = null;

                dialog.setMinimumSize(dimension);
                dialog.setLocationRelativeTo(null); //center frame
                dialog.setTitle(title);

                try {
                        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                        Logger.getLogger(EditFrame.class.getName()).log(Level.SEVERE, null, ex);
                }

                try {
                        String themeName = (String)Database.db().SELECT("Settings", "Theme").get(0);
                        theme = new ThemeCollection().matchThemes(themeName);
                        ThemeCollection.implementTheme(dialog, theme);

                } catch (SQLException ex) {
                        Logger.getLogger(SettingsFrame.class.getName()).log(Level.SEVERE, null, ex);
                }

                try {
                        GUIMethods.changeGlobalFont(new Component[]{dialog}, 0, (String)Database.db().SELECT("Settings", "Font").get(0));
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

        public static void dialogError(String error) {
                JDialog dialog = new JDialog();

                Theme selectedTheme = null;
                ThemeCollection tc = new ThemeCollection();
                Font font = new Font("sans-serif", Font.PLAIN, 14);
                Color textColor = new Color(0, 0, 0);
                Color bgColor = new Color(255, 255, 255);
                JLabel errorLabel = new JLabel();
                JLabel subtitle = new JLabel("An error has occured!");
                JPanel bg = new JPanel();
                FlowLayout layout = new FlowLayout(FlowLayout.CENTER, 100, 20);

                dialog.setSize(300, 200);
                dialog.setTitle("Error");
                dialog.setResizable(false);
                dialog.add(bg);
                dialog.setLocationRelativeTo(null);

                bg.setName("background");
                bg.add(subtitle);
                bg.add(errorLabel);
                bg.setBackground(bgColor);
                bg.setLayout(layout);

                errorLabel.setName("label");
                errorLabel.setText(breakString(error, 2));
                errorLabel.setAlignmentY(200);
                errorLabel.setForeground(textColor);
                errorLabel.setFont(font);

                subtitle.setForeground(Color.RED);
                subtitle.setFont(font.deriveFont(Font.BOLD));

                try {
                        String themeName = (String)Database.db().SELECT("Settings", "Font").get(0);
                        selectedTheme = new ThemeCollection().matchThemes(themeName);

                } catch (SQLException ex) {
                        Logger.getLogger(SettingsFrame.class.getName()).log(Level.SEVERE, null, ex);
                }

                ThemeCollection.implementTheme(dialog, selectedTheme);
                dialog.setVisible(true);
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
                                return "<html>" + text.substring(0, spaces.get(spaces.size() / 2)) + "<br>" + text.substring(spaces.get(spaces.size() / 2) + 1, text.length()) + "</html>";
                        }
                        case 3 -> {
                                return "<html>" + text.substring(0, spaces.get(spaces.size() / 3)) + "<br>" + text.substring(spaces.get(spaces.size() / 3), spaces.get(spaces.size() * 2 / 3)) + "<br>" + text.substring(spaces.get(spaces.size() * 2 / 3), text.length()) + "</html>";
                        }
                        case 4 -> {
                                return "<html>" + text.substring(0, spaces.get(spaces.size() / 4)) + "<br>" + text.substring(spaces.get(spaces.size() / 4), spaces.get(spaces.size() * 2 / 4)) + "<br>" + text.substring(spaces.get(spaces.size() * 2 / 4), spaces.get(spaces.size() * 3 / 4)) + "<br>" + text.substring(spaces.get(spaces.size() * 3 / 4), text.length()) + "</html>";
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
                                } catch (IllegalAccessException | IllegalArgumentException | NoSuchFieldException | SecurityException e) {
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
                return Scalr.resize(originalImage, Scalr.Method.AUTOMATIC, Scalr.Mode.AUTOMATIC, targetWidth, targetHeight, Scalr.OP_ANTIALIAS);
        }
}
