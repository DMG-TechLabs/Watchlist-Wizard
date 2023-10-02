package GUI;

import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.swing.ImageIcon;

import Database.Database;
import kdesp73.databridge.connections.DatabaseConnection;
import kdesp73.databridge.helpers.QueryBuilder;

public class LoadingFrame extends javax.swing.JFrame {
	public LoadingFrame() {
		this.setUndecorated(true);
		initComponents();
		GUIMethods.setupFrame(this, this.getPreferredSize(), "Loading...");
		progressBar.setBorderPainted(false);
		progressBar.setValue(0);

		iconLabel.setIcon(new ImageIcon(System.getProperty("user.dir").replaceAll(Pattern.quote("\\"), "/")
					+ "/assets/ww-logo-png-355-purple.png"));
		title.setFont(new Font("Matura Script MT Capitals", Font.PLAIN, 36));

	}

	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated
	// Code">//GEN-BEGIN:initComponents
	private void initComponents() {
		background = new javax.swing.JPanel();
		icon = new RoundedPanel();
		iconLabel = new javax.swing.JLabel();
		progressBar = new javax.swing.JProgressBar();
		task = new javax.swing.JLabel();
		title = new javax.swing.JLabel();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		background.setBackground(new java.awt.Color(43, 30, 74));

		icon.setBackground(new java.awt.Color(255, 255, 255));
		icon.setPreferredSize(new java.awt.Dimension(355, 355));

		javax.swing.GroupLayout iconLayout = new javax.swing.GroupLayout(icon);
		icon.setLayout(iconLayout);
		iconLayout.setHorizontalGroup(
				iconLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(iconLabel, javax.swing.GroupLayout.DEFAULT_SIZE,
					javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
		iconLayout.setVerticalGroup(
				iconLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(iconLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 355, Short.MAX_VALUE));

		progressBar.setForeground(new java.awt.Color(254, 211, 107));
		progressBar.setValue(68);

		task.setForeground(new java.awt.Color(255, 255, 255));
		task.setText("Task in progress");

		title.setFont(new java.awt.Font("Matura MT Script Capitals", 0, 36)); // NOI18N
		title.setForeground(new java.awt.Color(255, 255, 255));
		title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		title.setText("Watchlist Wizard");

		javax.swing.GroupLayout backgroundLayout = new javax.swing.GroupLayout(background);
		background.setLayout(backgroundLayout);
		backgroundLayout.setHorizontalGroup(
				backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(backgroundLayout.createSequentialGroup()
					.addContainerGap(55, Short.MAX_VALUE)
					.addGroup(backgroundLayout
						.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
						.addComponent(title, javax.swing.GroupLayout.DEFAULT_SIZE, 355, Short.MAX_VALUE)
						.addGroup(backgroundLayout
							.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
							.addGroup(backgroundLayout
								.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING,
									false)
								.addComponent(icon, javax.swing.GroupLayout.DEFAULT_SIZE,
									javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(progressBar, javax.swing.GroupLayout.DEFAULT_SIZE,
									355, Short.MAX_VALUE))
							.addComponent(task, javax.swing.GroupLayout.PREFERRED_SIZE, 226,
								javax.swing.GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(55, Short.MAX_VALUE)));
		backgroundLayout.setVerticalGroup(
				backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(backgroundLayout.createSequentialGroup()
					.addGap(47, 47, 47)
					.addComponent(icon, javax.swing.GroupLayout.PREFERRED_SIZE,
						javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
					.addGap(18, 18, 18)
					.addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, 74,
						javax.swing.GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28,
						Short.MAX_VALUE)
					.addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, 19,
						javax.swing.GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
					.addComponent(task)
					.addGap(52, 52, 52)));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(background, javax.swing.GroupLayout.DEFAULT_SIZE,
					javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(background, javax.swing.GroupLayout.DEFAULT_SIZE,
					javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

		pack();
	}// </editor-fold>//GEN-END:initComponents

	private void loadTasks() throws InterruptedException {
		DatabaseConnection db = Database.connection();
		task.setText("Setting Theme and Font...");
		Frame mainFrame = new Frame();
		mainFrame.setVisible(false);
		TimeUnit.MILLISECONDS.sleep(1500);
		progressBar.setValue(50);
		task.setText("Theme and Font set.");

		TimeUnit.MILLISECONDS.sleep(500);

		task.setText("Searching directory...");
		String dir = "";
		ResultSet rs = db.executeQuery(new QueryBuilder().select("Directory").from("Settings").build());
		try {
			rs.next();
			dir = rs.getString(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		System.out.println(dir);
		if(dir != "" || dir != null){
			mainFrame.movies.refreshMovies();
		}

		TimeUnit.MILLISECONDS.sleep(1000);
		progressBar.setValue(75);

		TimeUnit.MILLISECONDS.sleep(500);

		task.setText("Loading movies from Database...");
		try {
			mainFrame.movies.load();
		} catch (SQLException ex) {
			Logger.getLogger(LoadingFrame.class.getName()).log(Level.SEVERE, null, ex);
		}
		mainFrame.refreshMoviesList();
		TimeUnit.MILLISECONDS.sleep(1000);
		progressBar.setValue(95);
		task.setText("Movies loaded.");
		TimeUnit.MILLISECONDS.sleep(500);
		task.setText("Loading done.");
		progressBar.setValue(100);

		TimeUnit.MILLISECONDS.sleep(1000);

		mainFrame.setVisible(true);
		db.close();
		this.dispose();
	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		System.setProperty("sun.java2d.uiScale", "1");
		LoadingFrame lf = new LoadingFrame();
		lf.setVisible(true);

		try {
			lf.loadTasks();
		} catch (InterruptedException ex) {
			Logger.getLogger(LoadingFrame.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JPanel background;
	private javax.swing.JPanel icon;
	private javax.swing.JLabel iconLabel;
	private javax.swing.JProgressBar progressBar;
	private javax.swing.JLabel task;
	private javax.swing.JLabel title;
	// End of variables declaration//GEN-END:variables
}
