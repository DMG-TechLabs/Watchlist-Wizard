package GUI;

import Database.Database;
import Utils.Utils;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.HeadlessException;
import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import kdesp73.themeLib.*;

public class CustomThemeFrame extends javax.swing.JFrame {

	static final String TITLE = "Create a custom Theme";
	Theme theme;

	Frame f;
	Color list;
	Color listFocus;
	Color listForeground;
	Color background;
	Color foreground;
	Color toolbar;
	Color button;
	Color buttonForeground;
	Color buttonHover;
	Color textBox;
	Color textBoxForeground;
	Color secBackground;

	public CustomThemeFrame() {
		initComponents();
		this.setResizable(false);
		this.theme = GUIMethods.setupFrame(this, this.getPreferredSize(), TITLE);
	}

	public CustomThemeFrame(Frame f) {
		// Setup Frame
		initComponents();
		this.setExtendedState(this.getExtendedState() | JFrame.MAXIMIZED_BOTH);
		// this.setResizable(false);

		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.theme = GUIMethods.setupFrame(this, this.getPreferredSize(), TITLE);

		// Setup Components
		this.f = f;
		this.buttonHover = f.sf.themeCollection.getThemes().get(0).getExtras().get(0);
		this.button = f.sf.themeCollection.getThemes().get(0).getBtn();

		refreshThemeCombo();
		initValues(f.sf.themeCollection.getThemes().get(0));
	}

	public void refreshThemeCombo() {
		ArrayList<String> themeNames = new ArrayList<>();

		try {
			ArrayList<Object> objectList = Database.db().SELECT("Themes", "Theme_Name");
			for (Object obj : objectList) {
				themeNames.add((String) obj);
			}
		} catch (SQLException ex) {
			Logger.getLogger(SettingsFrame.class.getName()).log(Level.SEVERE, null, ex);
		}

		themeNames.add(0, "Default");

		themesToLoadCombo.setModel(new DefaultComboBoxModel(themeNames.toArray()));
	}

	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated
	// Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		backgroundPanel = new javax.swing.JPanel();
		previewPanel = new javax.swing.JPanel();
		toolbarPrev = new javax.swing.JPanel();
		textboxPrev = new javax.swing.JPanel();
		textboxFgPrev = new javax.swing.JLabel();
		listPrev = new javax.swing.JPanel();
		listFocusPrev = new javax.swing.JPanel();
		listFgPrev = new javax.swing.JLabel();
		listFgPrev1 = new javax.swing.JLabel();
		bgPrev = new javax.swing.JPanel();
		secBgPrev = new RoundedPanel();
		fgPrev = new javax.swing.JLabel();
		buttonPrev = new RoundedPanel();
		buttonFgPrev = new javax.swing.JLabel();
		colorBlock = new RoundedPanel();
		bgHex = new javax.swing.JLabel();
		bgColor = new RoundedPanel();
		jLabel1 = new javax.swing.JLabel();
		colorBlock1 = new RoundedPanel();
		fgHex = new javax.swing.JLabel();
		fgColor = new RoundedPanel();
		jLabel2 = new javax.swing.JLabel();
		colorBlock2 = new RoundedPanel();
		listHex = new javax.swing.JLabel();
		listColor = new RoundedPanel();
		jLabel3 = new javax.swing.JLabel();
		colorBlock3 = new RoundedPanel();
		listFgHex = new javax.swing.JLabel();
		listFgColor = new RoundedPanel();
		jLabel4 = new javax.swing.JLabel();
		colorBlock4 = new RoundedPanel();
		listFocusHex = new javax.swing.JLabel();
		listFocusColor = new RoundedPanel();
		jLabel5 = new javax.swing.JLabel();
		colorBlock5 = new RoundedPanel();
		toolbarHex = new javax.swing.JLabel();
		toolbarColor = new RoundedPanel();
		jLabel6 = new javax.swing.JLabel();
		colorBlock8 = new RoundedPanel();
		buttonHex = new javax.swing.JLabel();
		buttonColor = new RoundedPanel();
		jLabel7 = new javax.swing.JLabel();
		colorBlock9 = new RoundedPanel();
		buttonFgHex = new javax.swing.JLabel();
		buttonFgColor = new RoundedPanel();
		jLabel8 = new javax.swing.JLabel();
		colorBlock10 = new RoundedPanel();
		buttonHoverHex = new javax.swing.JLabel();
		buttonHoverColor = new RoundedPanel();
		jLabel9 = new javax.swing.JLabel();
		colorBlock11 = new RoundedPanel();
		textboxHex = new javax.swing.JLabel();
		textboxColor = new RoundedPanel();
		jLabel10 = new javax.swing.JLabel();
		colorBlock12 = new RoundedPanel();
		textboxFgHex = new javax.swing.JLabel();
		textboxFgColor = new RoundedPanel();
		jLabel11 = new javax.swing.JLabel();
		colorBlock13 = new RoundedPanel();
		secBgHex = new javax.swing.JLabel();
		secBgColor = new RoundedPanel();
		jLabel12 = new javax.swing.JLabel();
		resetButton = new RoundedPanel();
		buttonFgPrev2 = new javax.swing.JLabel();
		saveButton = new RoundedPanel();
		buttonFgPrev3 = new javax.swing.JLabel();
		loadThemeButton = new RoundedPanel();
		buttonFgPrev4 = new javax.swing.JLabel();
		themesToLoadCombo = new javax.swing.JComboBox<>();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		backgroundPanel.setBackground(new java.awt.Color(230, 230, 230));
		backgroundPanel.setName(""); // NOI18N

		toolbarPrev.setBackground(new java.awt.Color(68, 94, 147));

		textboxPrev.setBackground(new java.awt.Color(255, 255, 255));
		textboxPrev.setPreferredSize(new java.awt.Dimension(164, 18));

		textboxFgPrev.setText("Search");

		javax.swing.GroupLayout textboxPrevLayout = new javax.swing.GroupLayout(textboxPrev);
		textboxPrev.setLayout(textboxPrevLayout);
		textboxPrevLayout.setHorizontalGroup(
				textboxPrevLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(textboxPrevLayout.createSequentialGroup()
								.addContainerGap()
								.addComponent(textboxFgPrev, javax.swing.GroupLayout.PREFERRED_SIZE, 50,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addContainerGap(247, Short.MAX_VALUE)));
		textboxPrevLayout.setVerticalGroup(
				textboxPrevLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addComponent(textboxFgPrev, javax.swing.GroupLayout.DEFAULT_SIZE, 18, Short.MAX_VALUE));

		javax.swing.GroupLayout toolbarPrevLayout = new javax.swing.GroupLayout(toolbarPrev);
		toolbarPrev.setLayout(toolbarPrevLayout);
		toolbarPrevLayout.setHorizontalGroup(
				toolbarPrevLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(toolbarPrevLayout.createSequentialGroup()
								.addGap(318, 318, 318)
								.addComponent(textboxPrev, javax.swing.GroupLayout.PREFERRED_SIZE, 309,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		toolbarPrevLayout.setVerticalGroup(
				toolbarPrevLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(toolbarPrevLayout.createSequentialGroup()
								.addGap(34, 34, 34)
								.addComponent(textboxPrev, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addContainerGap(34, Short.MAX_VALUE)));

		listPrev.setBackground(new java.awt.Color(55, 63, 81));
		listPrev.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

		listFocusPrev.setBackground(new java.awt.Color(33, 38, 42));

		listFgPrev.setForeground(new java.awt.Color(255, 255, 255));
		listFgPrev.setText("   Selected Item");

		javax.swing.GroupLayout listFocusPrevLayout = new javax.swing.GroupLayout(listFocusPrev);
		listFocusPrev.setLayout(listFocusPrevLayout);
		listFocusPrevLayout.setHorizontalGroup(
				listFocusPrevLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(listFocusPrevLayout.createSequentialGroup()
								.addComponent(listFgPrev, javax.swing.GroupLayout.PREFERRED_SIZE, 96,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(0, 133, Short.MAX_VALUE)));
		listFocusPrevLayout.setVerticalGroup(
				listFocusPrevLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addComponent(listFgPrev, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE));

		listFgPrev1.setForeground(new java.awt.Color(255, 255, 255));
		listFgPrev1.setText("   Item");

		javax.swing.GroupLayout listPrevLayout = new javax.swing.GroupLayout(listPrev);
		listPrev.setLayout(listPrevLayout);
		listPrevLayout.setHorizontalGroup(
				listPrevLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addComponent(listFocusPrev, javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(listFgPrev1, javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
		listPrevLayout.setVerticalGroup(
				listPrevLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(listPrevLayout.createSequentialGroup()
								.addComponent(listFocusPrev, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(0, 0, 0)
								.addComponent(listFgPrev1, javax.swing.GroupLayout.PREFERRED_SIZE, 46,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addContainerGap(611, Short.MAX_VALUE)));

		secBgPrev.setBackground(new java.awt.Color(255, 255, 255));

		fgPrev.setText("Info");

		javax.swing.GroupLayout secBgPrevLayout = new javax.swing.GroupLayout(secBgPrev);
		secBgPrev.setLayout(secBgPrevLayout);
		secBgPrevLayout.setHorizontalGroup(
				secBgPrevLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(secBgPrevLayout.createSequentialGroup()
								.addContainerGap()
								.addComponent(fgPrev, javax.swing.GroupLayout.PREFERRED_SIZE, 51,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addContainerGap(366, Short.MAX_VALUE)));
		secBgPrevLayout.setVerticalGroup(
				secBgPrevLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(secBgPrevLayout.createSequentialGroup()
								.addContainerGap()
								.addComponent(fgPrev)
								.addContainerGap(235, Short.MAX_VALUE)));

		buttonPrev.setBackground(new java.awt.Color(55, 63, 81));
		buttonPrev.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		buttonPrev.setPreferredSize(new java.awt.Dimension(59, 23));
		buttonPrev.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				buttonPrevMouseClicked(evt);
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				buttonPrevMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				buttonPrevMouseExited(evt);
			}
		});

		buttonFgPrev.setForeground(new java.awt.Color(255, 255, 255));
		buttonFgPrev.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		buttonFgPrev.setText("Button");

		javax.swing.GroupLayout buttonPrevLayout = new javax.swing.GroupLayout(buttonPrev);
		buttonPrev.setLayout(buttonPrevLayout);
		buttonPrevLayout.setHorizontalGroup(
				buttonPrevLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addComponent(buttonFgPrev, javax.swing.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE));
		buttonPrevLayout.setVerticalGroup(
				buttonPrevLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addComponent(buttonFgPrev, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE));

		javax.swing.GroupLayout bgPrevLayout = new javax.swing.GroupLayout(bgPrev);
		bgPrev.setLayout(bgPrevLayout);
		bgPrevLayout.setHorizontalGroup(
				bgPrevLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(bgPrevLayout.createSequentialGroup()
								.addContainerGap(320, Short.MAX_VALUE)
								.addGroup(bgPrevLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addComponent(secBgPrev, javax.swing.GroupLayout.Alignment.TRAILING,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(buttonPrev, javax.swing.GroupLayout.Alignment.TRAILING,
												javax.swing.GroupLayout.PREFERRED_SIZE, 92,
												javax.swing.GroupLayout.PREFERRED_SIZE))
								.addContainerGap()));
		bgPrevLayout.setVerticalGroup(
				bgPrevLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(bgPrevLayout.createSequentialGroup()
								.addContainerGap()
								.addComponent(secBgPrev, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(18, 18, 18)
								.addComponent(buttonPrev, javax.swing.GroupLayout.PREFERRED_SIZE, 27,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addContainerGap(287, Short.MAX_VALUE)));

		javax.swing.GroupLayout previewPanelLayout = new javax.swing.GroupLayout(previewPanel);
		previewPanel.setLayout(previewPanelLayout);
		previewPanelLayout.setHorizontalGroup(
				previewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addComponent(toolbarPrev, javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(previewPanelLayout.createSequentialGroup()
								.addComponent(listPrev, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(0, 0, 0)
								.addComponent(bgPrev, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		previewPanelLayout.setVerticalGroup(
				previewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(previewPanelLayout.createSequentialGroup()
								.addComponent(toolbarPrev, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(0, 0, 0)
								.addGroup(previewPanelLayout
										.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addComponent(listPrev, javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(bgPrev, javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE))
								.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

		colorBlock.setBackground(new java.awt.Color(68, 94, 147));
		colorBlock.setName(""); // NOI18N

		bgHex.setForeground(new java.awt.Color(255, 255, 255));
		bgHex.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		bgHex.setText("#00000");

		bgColor.setBackground(new java.awt.Color(0, 0, 0));
		bgColor.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		bgColor.setPreferredSize(new java.awt.Dimension(32, 32));
		bgColor.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				bgColorMouseClicked(evt);
			}
		});

		javax.swing.GroupLayout bgColorLayout = new javax.swing.GroupLayout(bgColor);
		bgColor.setLayout(bgColorLayout);
		bgColorLayout.setHorizontalGroup(
				bgColorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGap(0, 32, Short.MAX_VALUE));
		bgColorLayout.setVerticalGroup(
				bgColorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGap(0, 32, Short.MAX_VALUE));

		jLabel1.setForeground(new java.awt.Color(255, 255, 255));
		jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		jLabel1.setText("Background");

		javax.swing.GroupLayout colorBlockLayout = new javax.swing.GroupLayout(colorBlock);
		colorBlock.setLayout(colorBlockLayout);
		colorBlockLayout.setHorizontalGroup(
				colorBlockLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(colorBlockLayout.createSequentialGroup()
								.addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 121,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(bgColor, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(18, 18, 18)
								.addComponent(bgHex, javax.swing.GroupLayout.PREFERRED_SIZE, 63,
										javax.swing.GroupLayout.PREFERRED_SIZE)));
		colorBlockLayout.setVerticalGroup(
				colorBlockLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)
						.addGroup(colorBlockLayout.createSequentialGroup()
								.addContainerGap()
								.addComponent(bgColor, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addComponent(bgHex, javax.swing.GroupLayout.Alignment.TRAILING,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE));

		colorBlock1.setBackground(new java.awt.Color(68, 94, 147));
		colorBlock1.setName(""); // NOI18N

		fgHex.setForeground(new java.awt.Color(255, 255, 255));
		fgHex.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		fgHex.setText("#00000");

		fgColor.setBackground(new java.awt.Color(0, 0, 0));
		fgColor.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		fgColor.setPreferredSize(new java.awt.Dimension(32, 32));
		fgColor.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				fgColorMouseClicked(evt);
			}
		});

		javax.swing.GroupLayout fgColorLayout = new javax.swing.GroupLayout(fgColor);
		fgColor.setLayout(fgColorLayout);
		fgColorLayout.setHorizontalGroup(
				fgColorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGap(0, 32, Short.MAX_VALUE));
		fgColorLayout.setVerticalGroup(
				fgColorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGap(0, 32, Short.MAX_VALUE));

		jLabel2.setForeground(new java.awt.Color(255, 255, 255));
		jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		jLabel2.setText("Foreground");

		javax.swing.GroupLayout colorBlock1Layout = new javax.swing.GroupLayout(colorBlock1);
		colorBlock1.setLayout(colorBlock1Layout);
		colorBlock1Layout.setHorizontalGroup(
				colorBlock1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(colorBlock1Layout.createSequentialGroup()
								.addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 121,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(fgColor, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(18, 18, 18)
								.addComponent(fgHex, javax.swing.GroupLayout.PREFERRED_SIZE, 63,
										javax.swing.GroupLayout.PREFERRED_SIZE)));
		colorBlock1Layout.setVerticalGroup(
				colorBlock1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(colorBlock1Layout.createSequentialGroup()
								.addContainerGap()
								.addComponent(fgColor, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addComponent(fgHex, javax.swing.GroupLayout.Alignment.TRAILING,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)
						.addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

		colorBlock2.setBackground(new java.awt.Color(68, 94, 147));
		colorBlock2.setName(""); // NOI18N

		listHex.setForeground(new java.awt.Color(255, 255, 255));
		listHex.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		listHex.setText("#00000");

		listColor.setBackground(new java.awt.Color(0, 0, 0));
		listColor.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		listColor.setPreferredSize(new java.awt.Dimension(32, 32));
		listColor.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				listColorMouseClicked(evt);
			}
		});

		javax.swing.GroupLayout listColorLayout = new javax.swing.GroupLayout(listColor);
		listColor.setLayout(listColorLayout);
		listColorLayout.setHorizontalGroup(
				listColorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGap(0, 32, Short.MAX_VALUE));
		listColorLayout.setVerticalGroup(
				listColorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGap(0, 32, Short.MAX_VALUE));

		jLabel3.setForeground(new java.awt.Color(255, 255, 255));
		jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		jLabel3.setText("List");

		javax.swing.GroupLayout colorBlock2Layout = new javax.swing.GroupLayout(colorBlock2);
		colorBlock2.setLayout(colorBlock2Layout);
		colorBlock2Layout.setHorizontalGroup(
				colorBlock2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(colorBlock2Layout.createSequentialGroup()
								.addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 121,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(listColor, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(18, 18, 18)
								.addComponent(listHex, javax.swing.GroupLayout.PREFERRED_SIZE, 63,
										javax.swing.GroupLayout.PREFERRED_SIZE)));
		colorBlock2Layout.setVerticalGroup(
				colorBlock2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(colorBlock2Layout.createSequentialGroup()
								.addContainerGap()
								.addComponent(listColor, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addComponent(listHex, javax.swing.GroupLayout.Alignment.TRAILING,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)
						.addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

		colorBlock3.setBackground(new java.awt.Color(68, 94, 147));
		colorBlock3.setName(""); // NOI18N

		listFgHex.setForeground(new java.awt.Color(255, 255, 255));
		listFgHex.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		listFgHex.setText("#00000");

		listFgColor.setBackground(new java.awt.Color(0, 0, 0));
		listFgColor.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		listFgColor.setPreferredSize(new java.awt.Dimension(32, 32));
		listFgColor.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				listFgColorMouseClicked(evt);
			}
		});

		javax.swing.GroupLayout listFgColorLayout = new javax.swing.GroupLayout(listFgColor);
		listFgColor.setLayout(listFgColorLayout);
		listFgColorLayout.setHorizontalGroup(
				listFgColorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGap(0, 32, Short.MAX_VALUE));
		listFgColorLayout.setVerticalGroup(
				listFgColorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGap(0, 32, Short.MAX_VALUE));

		jLabel4.setForeground(new java.awt.Color(255, 255, 255));
		jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		jLabel4.setText("List Foreground");

		javax.swing.GroupLayout colorBlock3Layout = new javax.swing.GroupLayout(colorBlock3);
		colorBlock3.setLayout(colorBlock3Layout);
		colorBlock3Layout.setHorizontalGroup(
				colorBlock3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(colorBlock3Layout.createSequentialGroup()
								.addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 121,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(listFgColor, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(18, 18, 18)
								.addComponent(listFgHex, javax.swing.GroupLayout.PREFERRED_SIZE, 63,
										javax.swing.GroupLayout.PREFERRED_SIZE)));
		colorBlock3Layout.setVerticalGroup(
				colorBlock3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(colorBlock3Layout.createSequentialGroup()
								.addContainerGap()
								.addComponent(listFgColor, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addComponent(listFgHex, javax.swing.GroupLayout.Alignment.TRAILING,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)
						.addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

		colorBlock4.setBackground(new java.awt.Color(68, 94, 147));
		colorBlock4.setName(""); // NOI18N

		listFocusHex.setForeground(new java.awt.Color(255, 255, 255));
		listFocusHex.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		listFocusHex.setText("#00000");

		listFocusColor.setBackground(new java.awt.Color(0, 0, 0));
		listFocusColor.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		listFocusColor.setPreferredSize(new java.awt.Dimension(32, 32));
		listFocusColor.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				listFocusColorMouseClicked(evt);
			}
		});

		javax.swing.GroupLayout listFocusColorLayout = new javax.swing.GroupLayout(listFocusColor);
		listFocusColor.setLayout(listFocusColorLayout);
		listFocusColorLayout.setHorizontalGroup(
				listFocusColorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGap(0, 32, Short.MAX_VALUE));
		listFocusColorLayout.setVerticalGroup(
				listFocusColorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGap(0, 32, Short.MAX_VALUE));

		jLabel5.setForeground(new java.awt.Color(255, 255, 255));
		jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		jLabel5.setText("List Focus");

		javax.swing.GroupLayout colorBlock4Layout = new javax.swing.GroupLayout(colorBlock4);
		colorBlock4.setLayout(colorBlock4Layout);
		colorBlock4Layout.setHorizontalGroup(
				colorBlock4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(colorBlock4Layout.createSequentialGroup()
								.addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 121,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(listFocusColor, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(18, 18, 18)
								.addComponent(listFocusHex, javax.swing.GroupLayout.PREFERRED_SIZE, 63,
										javax.swing.GroupLayout.PREFERRED_SIZE)));
		colorBlock4Layout.setVerticalGroup(
				colorBlock4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(colorBlock4Layout.createSequentialGroup()
								.addContainerGap()
								.addComponent(listFocusColor, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addComponent(listFocusHex, javax.swing.GroupLayout.Alignment.TRAILING,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)
						.addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

		colorBlock5.setBackground(new java.awt.Color(68, 94, 147));
		colorBlock5.setName(""); // NOI18N

		toolbarHex.setForeground(new java.awt.Color(255, 255, 255));
		toolbarHex.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		toolbarHex.setText("#00000");

		toolbarColor.setBackground(new java.awt.Color(0, 0, 0));
		toolbarColor.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		toolbarColor.setPreferredSize(new java.awt.Dimension(32, 32));
		toolbarColor.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				toolbarColorMouseClicked(evt);
			}
		});

		javax.swing.GroupLayout toolbarColorLayout = new javax.swing.GroupLayout(toolbarColor);
		toolbarColor.setLayout(toolbarColorLayout);
		toolbarColorLayout.setHorizontalGroup(
				toolbarColorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGap(0, 32, Short.MAX_VALUE));
		toolbarColorLayout.setVerticalGroup(
				toolbarColorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGap(0, 32, Short.MAX_VALUE));

		jLabel6.setForeground(new java.awt.Color(255, 255, 255));
		jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		jLabel6.setText("Toolbar");

		javax.swing.GroupLayout colorBlock5Layout = new javax.swing.GroupLayout(colorBlock5);
		colorBlock5.setLayout(colorBlock5Layout);
		colorBlock5Layout.setHorizontalGroup(
				colorBlock5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(colorBlock5Layout.createSequentialGroup()
								.addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 121,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(toolbarColor, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(18, 18, 18)
								.addComponent(toolbarHex, javax.swing.GroupLayout.PREFERRED_SIZE, 63,
										javax.swing.GroupLayout.PREFERRED_SIZE)));
		colorBlock5Layout.setVerticalGroup(
				colorBlock5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(colorBlock5Layout.createSequentialGroup()
								.addContainerGap()
								.addComponent(toolbarColor, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addComponent(toolbarHex, javax.swing.GroupLayout.Alignment.TRAILING,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)
						.addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

		colorBlock8.setBackground(new java.awt.Color(68, 94, 147));
		colorBlock8.setName(""); // NOI18N

		buttonHex.setForeground(new java.awt.Color(255, 255, 255));
		buttonHex.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		buttonHex.setText("#00000");

		buttonColor.setBackground(new java.awt.Color(0, 0, 0));
		buttonColor.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		buttonColor.setPreferredSize(new java.awt.Dimension(32, 32));
		buttonColor.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				buttonColorMouseClicked(evt);
			}
		});

		javax.swing.GroupLayout buttonColorLayout = new javax.swing.GroupLayout(buttonColor);
		buttonColor.setLayout(buttonColorLayout);
		buttonColorLayout.setHorizontalGroup(
				buttonColorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGap(0, 32, Short.MAX_VALUE));
		buttonColorLayout.setVerticalGroup(
				buttonColorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGap(0, 32, Short.MAX_VALUE));

		jLabel7.setForeground(new java.awt.Color(255, 255, 255));
		jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		jLabel7.setText("Button");

		javax.swing.GroupLayout colorBlock8Layout = new javax.swing.GroupLayout(colorBlock8);
		colorBlock8.setLayout(colorBlock8Layout);
		colorBlock8Layout.setHorizontalGroup(
				colorBlock8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(colorBlock8Layout.createSequentialGroup()
								.addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 121,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(buttonColor, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(18, 18, 18)
								.addComponent(buttonHex, javax.swing.GroupLayout.PREFERRED_SIZE, 63,
										javax.swing.GroupLayout.PREFERRED_SIZE)));
		colorBlock8Layout.setVerticalGroup(
				colorBlock8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(colorBlock8Layout.createSequentialGroup()
								.addContainerGap()
								.addComponent(buttonColor, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addComponent(buttonHex, javax.swing.GroupLayout.Alignment.TRAILING,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)
						.addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

		colorBlock9.setBackground(new java.awt.Color(68, 94, 147));
		colorBlock9.setName(""); // NOI18N

		buttonFgHex.setForeground(new java.awt.Color(255, 255, 255));
		buttonFgHex.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		buttonFgHex.setText("#00000");

		buttonFgColor.setBackground(new java.awt.Color(0, 0, 0));
		buttonFgColor.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		buttonFgColor.setPreferredSize(new java.awt.Dimension(32, 32));
		buttonFgColor.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				buttonFgColorMouseClicked(evt);
			}
		});

		javax.swing.GroupLayout buttonFgColorLayout = new javax.swing.GroupLayout(buttonFgColor);
		buttonFgColor.setLayout(buttonFgColorLayout);
		buttonFgColorLayout.setHorizontalGroup(
				buttonFgColorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGap(0, 32, Short.MAX_VALUE));
		buttonFgColorLayout.setVerticalGroup(
				buttonFgColorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGap(0, 32, Short.MAX_VALUE));

		jLabel8.setForeground(new java.awt.Color(255, 255, 255));
		jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		jLabel8.setText("Button Foreground");

		javax.swing.GroupLayout colorBlock9Layout = new javax.swing.GroupLayout(colorBlock9);
		colorBlock9.setLayout(colorBlock9Layout);
		colorBlock9Layout.setHorizontalGroup(
				colorBlock9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(colorBlock9Layout.createSequentialGroup()
								.addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 121,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(4, 4, 4)
								.addComponent(buttonFgColor, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(18, 18, 18)
								.addComponent(buttonFgHex, javax.swing.GroupLayout.PREFERRED_SIZE, 63,
										javax.swing.GroupLayout.PREFERRED_SIZE)));
		colorBlock9Layout.setVerticalGroup(
				colorBlock9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(colorBlock9Layout.createSequentialGroup()
								.addContainerGap()
								.addComponent(buttonFgColor, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addComponent(buttonFgHex, javax.swing.GroupLayout.Alignment.TRAILING,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)
						.addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

		colorBlock10.setBackground(new java.awt.Color(68, 94, 147));
		colorBlock10.setMaximumSize(new java.awt.Dimension(250, 58));
		colorBlock10.setName(""); // NOI18N
		colorBlock10.setVerifyInputWhenFocusTarget(false);

		buttonHoverHex.setForeground(new java.awt.Color(255, 255, 255));
		buttonHoverHex.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		buttonHoverHex.setText("#00000");

		buttonHoverColor.setBackground(new java.awt.Color(0, 0, 0));
		buttonHoverColor.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		buttonHoverColor.setPreferredSize(new java.awt.Dimension(32, 32));
		buttonHoverColor.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				buttonHoverColorMouseClicked(evt);
			}
		});

		javax.swing.GroupLayout buttonHoverColorLayout = new javax.swing.GroupLayout(buttonHoverColor);
		buttonHoverColor.setLayout(buttonHoverColorLayout);
		buttonHoverColorLayout.setHorizontalGroup(
				buttonHoverColorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGap(0, 32, Short.MAX_VALUE));
		buttonHoverColorLayout.setVerticalGroup(
				buttonHoverColorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGap(0, 32, Short.MAX_VALUE));

		jLabel9.setForeground(new java.awt.Color(255, 255, 255));
		jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		jLabel9.setText("Button Hover");

		javax.swing.GroupLayout colorBlock10Layout = new javax.swing.GroupLayout(colorBlock10);
		colorBlock10.setLayout(colorBlock10Layout);
		colorBlock10Layout.setHorizontalGroup(
				colorBlock10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(colorBlock10Layout.createSequentialGroup()
								.addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 121,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(buttonHoverColor, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(18, 18, 18)
								.addComponent(buttonHoverHex, javax.swing.GroupLayout.PREFERRED_SIZE, 63,
										javax.swing.GroupLayout.PREFERRED_SIZE)));
		colorBlock10Layout.setVerticalGroup(
				colorBlock10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addComponent(buttonHoverHex, javax.swing.GroupLayout.Alignment.TRAILING,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)
						.addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(colorBlock10Layout.createSequentialGroup()
								.addContainerGap()
								.addComponent(buttonHoverColor, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

		colorBlock11.setBackground(new java.awt.Color(68, 94, 147));
		colorBlock11.setMaximumSize(new java.awt.Dimension(250, 58));
		colorBlock11.setName(""); // NOI18N

		textboxHex.setForeground(new java.awt.Color(255, 255, 255));
		textboxHex.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		textboxHex.setText("#00000");

		textboxColor.setBackground(new java.awt.Color(0, 0, 0));
		textboxColor.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		textboxColor.setPreferredSize(new java.awt.Dimension(32, 32));
		textboxColor.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				textboxColorMouseClicked(evt);
			}
		});

		javax.swing.GroupLayout textboxColorLayout = new javax.swing.GroupLayout(textboxColor);
		textboxColor.setLayout(textboxColorLayout);
		textboxColorLayout.setHorizontalGroup(
				textboxColorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGap(0, 32, Short.MAX_VALUE));
		textboxColorLayout.setVerticalGroup(
				textboxColorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGap(0, 32, Short.MAX_VALUE));

		jLabel10.setForeground(new java.awt.Color(255, 255, 255));
		jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		jLabel10.setText("Text Box");

		javax.swing.GroupLayout colorBlock11Layout = new javax.swing.GroupLayout(colorBlock11);
		colorBlock11.setLayout(colorBlock11Layout);
		colorBlock11Layout.setHorizontalGroup(
				colorBlock11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(colorBlock11Layout.createSequentialGroup()
								.addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 121,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(textboxColor, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(18, 18, Short.MAX_VALUE)
								.addComponent(textboxHex, javax.swing.GroupLayout.PREFERRED_SIZE, 51,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addContainerGap()));
		colorBlock11Layout.setVerticalGroup(
				colorBlock11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addComponent(textboxHex, javax.swing.GroupLayout.Alignment.TRAILING,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)
						.addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, colorBlock11Layout.createSequentialGroup()
								.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(textboxColor, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addContainerGap()));

		colorBlock12.setBackground(new java.awt.Color(68, 94, 147));
		colorBlock12.setMaximumSize(new java.awt.Dimension(250, 58));
		colorBlock12.setName(""); // NOI18N

		textboxFgHex.setForeground(new java.awt.Color(255, 255, 255));
		textboxFgHex.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		textboxFgHex.setText("#00000");

		textboxFgColor.setBackground(new java.awt.Color(0, 0, 0));
		textboxFgColor.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		textboxFgColor.setPreferredSize(new java.awt.Dimension(32, 32));
		textboxFgColor.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				textboxFgColorMouseClicked(evt);
			}
		});

		javax.swing.GroupLayout textboxFgColorLayout = new javax.swing.GroupLayout(textboxFgColor);
		textboxFgColor.setLayout(textboxFgColorLayout);
		textboxFgColorLayout.setHorizontalGroup(
				textboxFgColorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGap(0, 32, Short.MAX_VALUE));
		textboxFgColorLayout.setVerticalGroup(
				textboxFgColorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGap(0, 32, Short.MAX_VALUE));

		jLabel11.setForeground(new java.awt.Color(255, 255, 255));
		jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		jLabel11.setText("Text Box Foreground");

		javax.swing.GroupLayout colorBlock12Layout = new javax.swing.GroupLayout(colorBlock12);
		colorBlock12.setLayout(colorBlock12Layout);
		colorBlock12Layout.setHorizontalGroup(
				colorBlock12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(colorBlock12Layout.createSequentialGroup()
								.addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 121,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(textboxFgColor, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(18, 18, 18)
								.addComponent(textboxFgHex, javax.swing.GroupLayout.PREFERRED_SIZE, 63,
										javax.swing.GroupLayout.PREFERRED_SIZE)));
		colorBlock12Layout.setVerticalGroup(
				colorBlock12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(colorBlock12Layout.createSequentialGroup()
								.addContainerGap()
								.addComponent(textboxFgColor, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addComponent(textboxFgHex, javax.swing.GroupLayout.Alignment.TRAILING,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)
						.addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

		colorBlock13.setBackground(new java.awt.Color(68, 94, 147));
		colorBlock13.setMaximumSize(new java.awt.Dimension(250, 58));
		colorBlock13.setName(""); // NOI18N

		secBgHex.setForeground(new java.awt.Color(255, 255, 255));
		secBgHex.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		secBgHex.setText("#00000");

		secBgColor.setBackground(new java.awt.Color(0, 0, 0));
		secBgColor.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		secBgColor.setPreferredSize(new java.awt.Dimension(32, 32));
		secBgColor.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				secBgColorMouseClicked(evt);
			}
		});

		javax.swing.GroupLayout secBgColorLayout = new javax.swing.GroupLayout(secBgColor);
		secBgColor.setLayout(secBgColorLayout);
		secBgColorLayout.setHorizontalGroup(
				secBgColorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGap(0, 32, Short.MAX_VALUE));
		secBgColorLayout.setVerticalGroup(
				secBgColorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGap(0, 32, Short.MAX_VALUE));

		jLabel12.setForeground(new java.awt.Color(255, 255, 255));
		jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		jLabel12.setText("2ry Background");

		javax.swing.GroupLayout colorBlock13Layout = new javax.swing.GroupLayout(colorBlock13);
		colorBlock13.setLayout(colorBlock13Layout);
		colorBlock13Layout.setHorizontalGroup(
				colorBlock13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(colorBlock13Layout.createSequentialGroup()
								.addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 121,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(secBgColor, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(18, 18, 18)
								.addComponent(secBgHex, javax.swing.GroupLayout.PREFERRED_SIZE, 63,
										javax.swing.GroupLayout.PREFERRED_SIZE)));
		colorBlock13Layout.setVerticalGroup(
				colorBlock13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(colorBlock13Layout.createSequentialGroup()
								.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(secBgColor, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addContainerGap())
						.addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(secBgHex, javax.swing.GroupLayout.Alignment.TRAILING,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE));

		resetButton.setBackground(new java.awt.Color(55, 63, 81));
		resetButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		resetButton.setPreferredSize(new java.awt.Dimension(59, 23));
		resetButton.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				resetButtonMouseClicked(evt);
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				resetButtonMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				resetButtonMouseExited(evt);
			}
		});

		buttonFgPrev2.setForeground(new java.awt.Color(255, 255, 255));
		buttonFgPrev2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		buttonFgPrev2.setText("Reset");

		javax.swing.GroupLayout resetButtonLayout = new javax.swing.GroupLayout(resetButton);
		resetButton.setLayout(resetButtonLayout);
		resetButtonLayout.setHorizontalGroup(
				resetButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addComponent(buttonFgPrev2, javax.swing.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE));
		resetButtonLayout.setVerticalGroup(
				resetButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addComponent(buttonFgPrev2, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE));

		saveButton.setBackground(new java.awt.Color(55, 63, 81));
		saveButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		saveButton.setPreferredSize(new java.awt.Dimension(59, 23));
		saveButton.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				saveButtonMouseClicked(evt);
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				saveButtonMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				saveButtonMouseExited(evt);
			}
		});

		buttonFgPrev3.setForeground(new java.awt.Color(255, 255, 255));
		buttonFgPrev3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		buttonFgPrev3.setText("Save Theme");

		javax.swing.GroupLayout saveButtonLayout = new javax.swing.GroupLayout(saveButton);
		saveButton.setLayout(saveButtonLayout);
		saveButtonLayout.setHorizontalGroup(
				saveButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addComponent(buttonFgPrev3, javax.swing.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE));
		saveButtonLayout.setVerticalGroup(
				saveButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addComponent(buttonFgPrev3, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE));

		loadThemeButton.setBackground(new java.awt.Color(55, 63, 81));
		loadThemeButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		loadThemeButton.setPreferredSize(new java.awt.Dimension(29, 16));
		loadThemeButton.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				loadThemeButtonMouseClicked(evt);
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				loadThemeButtonMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				loadThemeButtonMouseExited(evt);
			}
		});

		buttonFgPrev4.setForeground(new java.awt.Color(255, 255, 255));
		buttonFgPrev4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		buttonFgPrev4.setText("Load");

		javax.swing.GroupLayout loadThemeButtonLayout = new javax.swing.GroupLayout(loadThemeButton);
		loadThemeButton.setLayout(loadThemeButtonLayout);
		loadThemeButtonLayout.setHorizontalGroup(
				loadThemeButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addComponent(buttonFgPrev4, javax.swing.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE));
		loadThemeButtonLayout.setVerticalGroup(
				loadThemeButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addComponent(buttonFgPrev4, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE));

		themesToLoadCombo.setModel(
				new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

		javax.swing.GroupLayout backgroundPanelLayout = new javax.swing.GroupLayout(backgroundPanel);
		backgroundPanel.setLayout(backgroundPanelLayout);
		backgroundPanelLayout.setHorizontalGroup(
				backgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(backgroundPanelLayout.createSequentialGroup()
								.addContainerGap()
								.addGroup(backgroundPanelLayout
										.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addComponent(colorBlock10, javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(colorBlock11, javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGroup(backgroundPanelLayout
												.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
												.addComponent(colorBlock8, javax.swing.GroupLayout.Alignment.LEADING,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(colorBlock5, javax.swing.GroupLayout.Alignment.LEADING,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(colorBlock4, javax.swing.GroupLayout.Alignment.LEADING,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(colorBlock3, javax.swing.GroupLayout.Alignment.LEADING,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(colorBlock2, javax.swing.GroupLayout.Alignment.LEADING,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(colorBlock1, javax.swing.GroupLayout.Alignment.LEADING,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(colorBlock, javax.swing.GroupLayout.Alignment.LEADING,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
										.addComponent(colorBlock9, javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGroup(backgroundPanelLayout
												.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
												.addComponent(colorBlock12, javax.swing.GroupLayout.Alignment.LEADING,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(colorBlock13, javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 100,
										Short.MAX_VALUE)
								.addGroup(backgroundPanelLayout
										.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addComponent(previewPanel, javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGroup(backgroundPanelLayout.createSequentialGroup()
												.addComponent(themesToLoadCombo, javax.swing.GroupLayout.PREFERRED_SIZE,
														92, javax.swing.GroupLayout.PREFERRED_SIZE)
												.addGap(18, 18, 18)
												.addComponent(saveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 92,
														javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGroup(backgroundPanelLayout.createSequentialGroup()
												.addComponent(loadThemeButton, javax.swing.GroupLayout.PREFERRED_SIZE,
														92, javax.swing.GroupLayout.PREFERRED_SIZE)
												.addGap(18, 18, 18)
												.addComponent(resetButton, javax.swing.GroupLayout.PREFERRED_SIZE, 92,
														javax.swing.GroupLayout.PREFERRED_SIZE)))
								.addGap(34, 34, 34)));
		backgroundPanelLayout.setVerticalGroup(
				backgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(backgroundPanelLayout.createSequentialGroup()
								.addGap(34, 34, 34)
								.addGroup(backgroundPanelLayout
										.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
										.addGroup(backgroundPanelLayout.createSequentialGroup()
												.addComponent(colorBlock, javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
												.addComponent(colorBlock1, javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
												.addComponent(colorBlock2, javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
												.addComponent(colorBlock3, javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
												.addComponent(colorBlock4, javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
												.addComponent(colorBlock5, javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
												.addComponent(colorBlock8, javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
												.addComponent(colorBlock9, javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
												.addComponent(colorBlock10, javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
												.addComponent(colorBlock11, javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
										.addComponent(previewPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 0,
												Short.MAX_VALUE))
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
								.addGroup(backgroundPanelLayout
										.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addGroup(backgroundPanelLayout.createSequentialGroup()
												.addComponent(colorBlock12, javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addGap(14, 14, 14)
												.addComponent(colorBlock13, javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGroup(backgroundPanelLayout.createSequentialGroup()
												.addGroup(backgroundPanelLayout
														.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(themesToLoadCombo,
																javax.swing.GroupLayout.PREFERRED_SIZE, 29,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(saveButton,
																javax.swing.GroupLayout.PREFERRED_SIZE, 29,
																javax.swing.GroupLayout.PREFERRED_SIZE))
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
												.addGroup(backgroundPanelLayout
														.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(loadThemeButton,
																javax.swing.GroupLayout.PREFERRED_SIZE, 29,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(resetButton,
																javax.swing.GroupLayout.PREFERRED_SIZE, 29,
																javax.swing.GroupLayout.PREFERRED_SIZE))))
								.addContainerGap(164, Short.MAX_VALUE)));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addComponent(backgroundPanel, javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addComponent(backgroundPanel, javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

		pack();
	}// </editor-fold>//GEN-END:initComponents

	private void buttonPrevMouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_buttonPrevMouseEntered
		buttonPrev.setBackground(buttonHover);
	}// GEN-LAST:event_buttonPrevMouseEntered

	private void buttonPrevMouseExited(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_buttonPrevMouseExited
		buttonPrev.setBackground(button);
	}// GEN-LAST:event_buttonPrevMouseExited

	private void buttonPrevMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_buttonPrevMouseClicked
		JDialog dialog = new JDialog();

		Font font = new Font("sans-serif", Font.PLAIN, 14);
		Color textColor = new Color(0, 0, 0);
		Color dialogColor = new Color(240, 240, 240);
		JLabel lbl = new JLabel();

		JPanel bg = new JPanel();
		FlowLayout layout = new FlowLayout(FlowLayout.CENTER, 100, 20);

		dialog.setSize(150, 120);
		dialog.setTitle("Hello World!");
		dialog.setResizable(false);
		dialog.add(bg);
		dialog.setLocationRelativeTo(null);

		bg.add(lbl);
		bg.setBackground(dialogColor);
		bg.setLayout(layout);

		lbl.setText("You pressed me!");
		lbl.setAlignmentY(200);
		lbl.setForeground(textColor);
		lbl.setFont(font);

		dialog.setVisible(true);
	}// GEN-LAST:event_buttonPrevMouseClicked

	private void bgColorMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_bgColorMouseClicked
		Color newColor = createChooser(f.sf.themeCollection.getThemes().get(0).getBg());

		if (newColor == null) {
			return;
		}

		bgPrev.setBackground(newColor);
		bgHex.setText("#" + GUIMethods.rgbToHex(newColor));
		bgColor.setBackground(newColor);
		background = newColor;
	}// GEN-LAST:event_bgColorMouseClicked

	private void fgColorMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_fgColorMouseClicked
		Color newColor = createChooser(f.sf.themeCollection.getThemes().get(0).getFg());

		if (newColor == null) {
			return;
		}

		fgPrev.setForeground(newColor);
		fgHex.setText("#" + GUIMethods.rgbToHex(newColor));
		fgColor.setBackground(newColor);
		foreground = newColor;
	}// GEN-LAST:event_fgColorMouseClicked

	private void listColorMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_listColorMouseClicked
		Color newColor = createChooser(f.sf.themeCollection.getThemes().get(0).getList());

		if (newColor == null) {
			return;
		}

		listPrev.setBackground(newColor);
		listHex.setText("#" + GUIMethods.rgbToHex(newColor));
		listColor.setBackground(newColor);
		list = newColor;
	}// GEN-LAST:event_listColorMouseClicked

	private void listFgColorMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_listFgColorMouseClicked
		Color newColor = createChooser(f.sf.themeCollection.getThemes().get(0).getList_fg());

		if (newColor == null) {
			return;
		}

		listFgPrev.setForeground(newColor);
		listFgPrev1.setForeground(newColor);
		listFgHex.setText("#" + GUIMethods.rgbToHex(newColor));
		listFgColor.setBackground(newColor);
		listForeground = newColor;
	}// GEN-LAST:event_listFgColorMouseClicked

	private void listFocusColorMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_listFocusColorMouseClicked
		Color newColor = createChooser(f.sf.themeCollection.getThemes().get(0).getList_focus());

		if (newColor == null) {
			return;
		}

		listFocusPrev.setBackground(newColor);
		listFocusHex.setText("#" + GUIMethods.rgbToHex(newColor));
		listFocusColor.setBackground(newColor);
		listFocus = newColor;
	}// GEN-LAST:event_listFocusColorMouseClicked

	private void toolbarColorMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_toolbarColorMouseClicked
		Color newColor = createChooser(f.sf.themeCollection.getThemes().get(0).getExtras().get(1));

		if (newColor == null) {
			return;
		}

		toolbarPrev.setBackground(newColor);
		toolbarHex.setText("#" + GUIMethods.rgbToHex(newColor));
		toolbarColor.setBackground(newColor);
		toolbar = newColor;
	}// GEN-LAST:event_toolbarColorMouseClicked

	private void buttonColorMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_buttonColorMouseClicked
		Color newColor = createChooser(f.sf.themeCollection.getThemes().get(0).getBtn());

		if (newColor == null) {
			return;
		}

		buttonPrev.setBackground(newColor);
		buttonHex.setText("#" + GUIMethods.rgbToHex(newColor));
		buttonColor.setBackground(newColor);
		button = newColor;
	}// GEN-LAST:event_buttonColorMouseClicked

	private void buttonFgColorMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_buttonFgColorMouseClicked
		Color newColor = createChooser(f.sf.themeCollection.getThemes().get(0).getBtn_fg());

		if (newColor == null) {
			return;
		}

		buttonFgPrev.setForeground(newColor);
		buttonFgHex.setText("#" + GUIMethods.rgbToHex(newColor));
		buttonFgColor.setBackground(newColor);
		buttonForeground = newColor;
	}// GEN-LAST:event_buttonFgColorMouseClicked

	private void buttonHoverColorMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_buttonHoverColorMouseClicked
		Color newColor = createChooser(f.sf.themeCollection.getThemes().get(0).getExtras().get(0));

		if (newColor == null) {
			return;
		}

		buttonHoverHex.setText("#" + GUIMethods.rgbToHex(newColor));
		buttonHoverColor.setBackground(newColor);
		buttonHover = newColor;
	}// GEN-LAST:event_buttonHoverColorMouseClicked

	private void textboxColorMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_textboxColorMouseClicked
		Color newColor = createChooser(f.sf.themeCollection.getThemes().get(0).getTextbox());

		if (newColor == null) {
			return;
		}

		textboxPrev.setBackground(newColor);
		textboxHex.setText("#" + GUIMethods.rgbToHex(newColor));
		textboxColor.setBackground(newColor);
		textBox = newColor;
	}// GEN-LAST:event_textboxColorMouseClicked

	private void textboxFgColorMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_textboxFgColorMouseClicked
		Color newColor = createChooser(f.sf.themeCollection.getThemes().get(0).getTextbox_fg());

		if (newColor == null) {
			return;
		}

		textboxFgPrev.setForeground(newColor);
		textboxFgHex.setText("#" + GUIMethods.rgbToHex(newColor));
		textboxFgColor.setBackground(newColor);
		textBoxForeground = newColor;
	}// GEN-LAST:event_textboxFgColorMouseClicked

	private void secBgColorMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_secBgColorMouseClicked
		Color newColor = createChooser(f.sf.themeCollection.getThemes().get(0).getBg_2());

		if (newColor == null) {
			return;
		}

		secBgPrev.setBackground(newColor);
		secBgHex.setText("#" + GUIMethods.rgbToHex(newColor));
		secBgColor.setBackground(newColor);
		secBackground = newColor;
	}// GEN-LAST:event_secBgColorMouseClicked

	private void resetButtonMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_resetButtonMouseClicked
		initValues(f.sf.themeCollection.getThemes().get(0));
	}// GEN-LAST:event_resetButtonMouseClicked

	private void resetButtonMouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_resetButtonMouseEntered
		resetButton.setBackground(f.sf.themeCollection.getThemes().get(0).getExtras().get(0));
	}// GEN-LAST:event_resetButtonMouseEntered

	private void resetButtonMouseExited(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_resetButtonMouseExited
		resetButton.setBackground(f.sf.themeCollection.getThemes().get(0).getBtn());
	}// GEN-LAST:event_resetButtonMouseExited

	private void saveButtonMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_saveButtonMouseClicked
		Theme newTheme = new Theme();

		newTheme.setBg(background);
		newTheme.setBtn(button);
		newTheme.setBtn_fg(buttonForeground);
		newTheme.setExtra(0, buttonHover);
		newTheme.setExtra(1, toolbar);
		newTheme.setFg(foreground);
		newTheme.setList(list);
		newTheme.setList_focus(listFocus);
		newTheme.setList_fg(listForeground);
		newTheme.setTextbox(textBox);
		newTheme.setTextbox_fg(textBoxForeground);
		newTheme.setBg_2(secBackground);
		newTheme.generateJson();

		JDialog nameThemeDialog = new JDialog(this);
		JPanel bg = new JPanel();
		JPanel save = new RoundedPanel();
		JLabel label = new JLabel("Save");
		JTextField input = new JTextField();
		Dimension SIZE = new Dimension(279, 120);

		bg.setName("background");

		input.setText("Name your Theme!");
		input.setName("textbox");
		input.setSize(108, 22);
		input.addFocusListener(new java.awt.event.FocusAdapter() {
			@Override
			public void focusGained(java.awt.event.FocusEvent evt) {
				input.setText("");
			}
		});
		input.addKeyListener(new java.awt.event.KeyAdapter() {
			@Override
			public void keyPressed(java.awt.event.KeyEvent evt) {
				if (evt.getKeyCode() != 10) {
					return;
				}

				saveTheme(nameThemeDialog, newTheme, input);
			}
		});

		save.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		save.setName("button"); // NOI18N
		save.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				saveTheme(nameThemeDialog, newTheme, input);
			}

			@Override
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				save.setBackground(theme.getExtras().get(0));
			}

			@Override
			public void mouseExited(java.awt.event.MouseEvent evt) {
				save.setBackground(theme.getBtn());
			}
		});

		label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		label.setText("Save");
		label.setName("buttonLabel"); // NOI18N

		javax.swing.GroupLayout saveButtonLayout = new javax.swing.GroupLayout(save);
		save.setLayout(saveButtonLayout);
		saveButtonLayout.setHorizontalGroup(
				saveButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addComponent(label, javax.swing.GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE));
		saveButtonLayout.setVerticalGroup(
				saveButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addComponent(label, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE));

		javax.swing.GroupLayout backgroundLayout = new javax.swing.GroupLayout(bg);
		bg.setLayout(backgroundLayout);
		backgroundLayout.setHorizontalGroup(
				backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(backgroundLayout.createSequentialGroup()
								.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addGroup(
										backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(input, javax.swing.GroupLayout.Alignment.TRAILING,
														javax.swing.GroupLayout.PREFERRED_SIZE, 247,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(save, javax.swing.GroupLayout.Alignment.TRAILING,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addContainerGap()));
		backgroundLayout.setVerticalGroup(
				backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(backgroundLayout.createSequentialGroup()
								.addContainerGap()
								.addComponent(input, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(18, 18, 18)
								.addComponent(save, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addContainerGap(35, Short.MAX_VALUE)));

		// javax.swing.GroupLayout layout = new
		// javax.swing.GroupLayout(getContentPane());
		// getContentPane().setLayout(layout);
		// layout.setHorizontalGroup(
		// layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		// .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE,
		// javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		// );
		// layout.setVerticalGroup(
		// layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		// .addComponent(bg, javax.swing.GroupLayout.PREFERRED_SIZE,
		// javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
		// );
		nameThemeDialog.add(bg);
		GUIMethods.setupDialog(nameThemeDialog, SIZE, "Name Theme");
		nameThemeDialog.setVisible(true);
		nameThemeDialog.setResizable(false);
	}// GEN-LAST:event_saveButtonMouseClicked

	private void saveTheme(JDialog dialog, Theme newTheme, JTextField textField) {
		String themeName = textField.getText();
		if (themeName == null || themeName.isBlank()) {
			GUIMethods.dialogError("Empty Name!");
			dialog.dispose();
			return;
		}
		for (int i = 0; i < f.sf.themeCollection.getThemes().size(); i++) {
			if (themeName.equals(f.sf.themeCollection.getThemes().get(i).getName())) {
				GUIMethods.dialogError("This name is already taken. Please pick another one");
				dialog.dispose();
				return;
			}
		}
		int MAX = 30;
		if (themeName.length() > MAX) {
			GUIMethods.dialogError("Name too long. Try a shorter one");
			dialog.dispose();
			return;
		}
		newTheme.setName(themeName);
		for (int i = 0; i < f.sf.themeCollection.getThemes().size(); i++) {
			// TODO: compare themes
			if (newTheme.generateJson().getJson()
					.equals(f.sf.themeCollection.getThemes().get(i).getJson().replaceAll(", ", ","))) {
				GUIMethods.dialogError("This theme already exists with another name: "
						+ f.sf.themeCollection.getThemes().get(i).getName());
				dialog.dispose();
				return;
			}
		}
		f.sf.themeCollection.addTheme(newTheme);
		System.out.println("Theme added");
		f.sf.refreshThemeCombo();
		f.sf.tf.refreshThemeCombo();
		dialog.dispose();
	}

	private void saveButtonMouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_saveButtonMouseEntered
		saveButton.setBackground(f.sf.themeCollection.getThemes().get(0).getExtras().get(0));
	}// GEN-LAST:event_saveButtonMouseEntered

	private void saveButtonMouseExited(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_saveButtonMouseExited
		saveButton.setBackground(f.sf.themeCollection.getThemes().get(0).getBtn());
	}// GEN-LAST:event_saveButtonMouseExited

	private void loadThemeButtonMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_loadThemeButtonMouseClicked
		ThemeCollection themes = new ThemeCollection();
		themes.loadThemes(new File(System.getProperty("user.dir").replaceAll(Pattern.quote("\\"), "/") +  "/themes"));
		initValues(themes.matchTheme(themesToLoadCombo.getSelectedItem().toString()));
	}// GEN-LAST:event_loadThemeButtonMouseClicked

	private void loadThemeButtonMouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_loadThemeButtonMouseEntered
		loadThemeButton.setBackground(f.sf.themeCollection.getThemes().get(0).getExtras().get(0));
	}// GEN-LAST:event_loadThemeButtonMouseEntered

	private void loadThemeButtonMouseExited(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_loadThemeButtonMouseExited
		loadThemeButton.setBackground(f.sf.themeCollection.getThemes().get(0).getBtn());
	}// GEN-LAST:event_loadThemeButtonMouseExited

	private Color createChooser(Color c) throws HeadlessException {
		JColorChooser chooser = new JColorChooser();

		chooser.setSize(400, 400);
		chooser.setVisible(true);
		chooser.setFont(f.getFont());

		return JColorChooser.showDialog(chooser, "Select a color", c);
	}

	private void initValues(Theme themeToLoad) {
		bgColor.setBackground(themeToLoad.getBg());
		bgHex.setText("#" + GUIMethods.rgbToHex(themeToLoad.getBg()));
		bgPrev.setBackground(themeToLoad.getBg());
		background = themeToLoad.getBg();

		fgColor.setBackground(themeToLoad.getFg());
		fgHex.setText("#" + GUIMethods.rgbToHex(themeToLoad.getFg()));
		fgPrev.setForeground(themeToLoad.getFg());
		foreground = themeToLoad.getFg();

		listColor.setBackground(themeToLoad.getList());
		listHex.setText("#" + GUIMethods.rgbToHex(themeToLoad.getList()));
		listPrev.setBackground(themeToLoad.getList());
		list = themeToLoad.getList();

		listFgColor.setBackground(themeToLoad.getList_fg());
		listFgHex.setText("#" + GUIMethods.rgbToHex(themeToLoad.getList_fg()));
		listFgPrev.setForeground(themeToLoad.getList_fg());
		listFgPrev1.setForeground(themeToLoad.getList_fg());
		listForeground = themeToLoad.getList_fg();

		listFocusColor.setBackground(themeToLoad.getList_focus());
		listFocusHex.setText("#" + GUIMethods.rgbToHex(themeToLoad.getList_focus()));
		listFocusPrev.setBackground(themeToLoad.getList_focus());
		listFocus = themeToLoad.getList_focus();

		toolbarColor.setBackground(themeToLoad.getExtras().get(1));
		toolbarHex.setText("#" + GUIMethods.rgbToHex(themeToLoad.getExtras().get(1)));
		toolbarPrev.setBackground(themeToLoad.getExtras().get(1));
		toolbar = themeToLoad.getExtras().get(1);

		buttonColor.setBackground(themeToLoad.getBtn());
		buttonHex.setText("#" + GUIMethods.rgbToHex(themeToLoad.getBtn()));
		buttonPrev.setBackground(themeToLoad.getBtn());
		button = themeToLoad.getBtn();

		buttonFgColor.setBackground(themeToLoad.getBtn_fg());
		buttonFgHex.setText("#" + GUIMethods.rgbToHex(themeToLoad.getBtn_fg()));
		buttonFgPrev.setForeground(themeToLoad.getBtn_fg());
		buttonForeground = themeToLoad.getBtn_fg();

		buttonHoverColor.setBackground(themeToLoad.getExtras().get(0));
		buttonHoverHex.setText("#" + GUIMethods.rgbToHex(themeToLoad.getExtras().get(0)));
		buttonHover = themeToLoad.getExtras().get(0);

		textboxColor.setBackground(themeToLoad.getTextbox());
		textboxHex.setText("#" + GUIMethods.rgbToHex(themeToLoad.getTextbox()));
		textboxPrev.setBackground(themeToLoad.getTextbox());
		textBox = themeToLoad.getTextbox();

		textboxFgColor.setBackground(themeToLoad.getTextbox_fg());
		textboxFgHex.setText("#" + GUIMethods.rgbToHex(themeToLoad.getTextbox_fg()));
		textboxFgPrev.setForeground(themeToLoad.getTextbox_fg());
		textBoxForeground = themeToLoad.getTextbox_fg();

		secBgColor.setBackground(themeToLoad.getBg_2());
		secBgHex.setText("#" + GUIMethods.rgbToHex(themeToLoad.getBg_2()));
		secBgPrev.setBackground(themeToLoad.getBg_2());
		secBackground = themeToLoad.getBg_2();

	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException ex) {
			Logger.getLogger(CustomThemeFrame.class.getName()).log(Level.SEVERE, null, ex);
		}

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new CustomThemeFrame().setVisible(true);
			}
		});
	}

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JPanel backgroundPanel;
	private javax.swing.JPanel bgColor;
	private javax.swing.JLabel bgHex;
	private javax.swing.JPanel bgPrev;
	private javax.swing.JPanel buttonColor;
	private javax.swing.JPanel buttonFgColor;
	private javax.swing.JLabel buttonFgHex;
	private javax.swing.JLabel buttonFgPrev;
	private javax.swing.JLabel buttonFgPrev2;
	private javax.swing.JLabel buttonFgPrev3;
	private javax.swing.JLabel buttonFgPrev4;
	private javax.swing.JLabel buttonHex;
	private javax.swing.JPanel buttonHoverColor;
	private javax.swing.JLabel buttonHoverHex;
	private javax.swing.JPanel buttonPrev;
	private javax.swing.JPanel colorBlock;
	private javax.swing.JPanel colorBlock1;
	private javax.swing.JPanel colorBlock10;
	private javax.swing.JPanel colorBlock11;
	private javax.swing.JPanel colorBlock12;
	private javax.swing.JPanel colorBlock13;
	private javax.swing.JPanel colorBlock2;
	private javax.swing.JPanel colorBlock3;
	private javax.swing.JPanel colorBlock4;
	private javax.swing.JPanel colorBlock5;
	private javax.swing.JPanel colorBlock8;
	private javax.swing.JPanel colorBlock9;
	private javax.swing.JPanel fgColor;
	private javax.swing.JLabel fgHex;
	private javax.swing.JLabel fgPrev;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel10;
	private javax.swing.JLabel jLabel11;
	private javax.swing.JLabel jLabel12;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JLabel jLabel6;
	private javax.swing.JLabel jLabel7;
	private javax.swing.JLabel jLabel8;
	private javax.swing.JLabel jLabel9;
	private javax.swing.JPanel listColor;
	private javax.swing.JPanel listFgColor;
	private javax.swing.JLabel listFgHex;
	private javax.swing.JLabel listFgPrev;
	private javax.swing.JLabel listFgPrev1;
	private javax.swing.JPanel listFocusColor;
	private javax.swing.JLabel listFocusHex;
	private javax.swing.JPanel listFocusPrev;
	private javax.swing.JLabel listHex;
	private javax.swing.JPanel listPrev;
	private javax.swing.JPanel loadThemeButton;
	private javax.swing.JPanel previewPanel;
	private javax.swing.JPanel resetButton;
	private javax.swing.JPanel saveButton;
	private javax.swing.JPanel secBgColor;
	private javax.swing.JLabel secBgHex;
	private javax.swing.JPanel secBgPrev;
	private javax.swing.JPanel textboxColor;
	private javax.swing.JPanel textboxFgColor;
	private javax.swing.JLabel textboxFgHex;
	private javax.swing.JLabel textboxFgPrev;
	private javax.swing.JLabel textboxHex;
	private javax.swing.JPanel textboxPrev;
	private javax.swing.JComboBox<String> themesToLoadCombo;
	private javax.swing.JPanel toolbarColor;
	private javax.swing.JLabel toolbarHex;
	private javax.swing.JPanel toolbarPrev;
	// End of variables declaration//GEN-END:variables
}
