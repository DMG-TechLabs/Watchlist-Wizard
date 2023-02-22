package GUI;

import java.sql.SQLException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoadingFrame extends javax.swing.JFrame {
        public LoadingFrame() {
                this.setUndecorated(true);
                initComponents();
                GUIMethods.setupFrame(this, this.getPreferredSize(), "Loading...");
                progressBar.setBorderPainted(false);
                progressBar.setValue(0);

        }

        @SuppressWarnings("unchecked")
        // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
        private void initComponents() {

                background = new javax.swing.JPanel();
                icon = new RoundedPanel();
                progressBar = new javax.swing.JProgressBar();
                task = new javax.swing.JLabel();

                setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

                background.setBackground(new java.awt.Color(8, 32, 50));

                icon.setBackground(new java.awt.Color(255, 255, 255));
                icon.setPreferredSize(new java.awt.Dimension(355, 355));

                javax.swing.GroupLayout iconLayout = new javax.swing.GroupLayout(icon);
                icon.setLayout(iconLayout);
                iconLayout.setHorizontalGroup(
                        iconLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 0, Short.MAX_VALUE)
                );
                iconLayout.setVerticalGroup(
                        iconLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 355, Short.MAX_VALUE)
                );

                progressBar.setForeground(new java.awt.Color(50, 205, 50));
                progressBar.setValue(68);

                task.setForeground(new java.awt.Color(255, 255, 255));
                task.setText("Task in progress");

                javax.swing.GroupLayout backgroundLayout = new javax.swing.GroupLayout(background);
                background.setLayout(backgroundLayout);
                backgroundLayout.setHorizontalGroup(
                        backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(backgroundLayout.createSequentialGroup()
                                .addContainerGap(55, Short.MAX_VALUE)
                                .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(icon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(progressBar, javax.swing.GroupLayout.DEFAULT_SIZE, 355, Short.MAX_VALUE))
                                        .addComponent(task, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(55, Short.MAX_VALUE))
                );
                backgroundLayout.setVerticalGroup(
                        backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(backgroundLayout.createSequentialGroup()
                                .addGap(47, 47, 47)
                                .addComponent(icon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(69, 69, 69)
                                .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(task)
                                .addContainerGap(103, Short.MAX_VALUE))
                );

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                getContentPane().setLayout(layout);
                layout.setHorizontalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(background, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                );
                layout.setVerticalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(background, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                );

                pack();
        }// </editor-fold>//GEN-END:initComponents

        private void loadTasks() throws InterruptedException {
                task.setText("Setting Theme and Font...");
                Frame mainFrame = new Frame();
                mainFrame.setVisible(false);
                TimeUnit.MILLISECONDS.sleep(1500);
                progressBar.setValue(50);
                task.setText("Theme and Font set.");

                TimeUnit.MILLISECONDS.sleep(500);

                task.setText("Searching directory...");
                mainFrame.movies.refreshMovies();
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
                this.dispose();
        }

        /**
         * @param args the command line arguments
         */
        public static void main(String args[]) {
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
        private javax.swing.JProgressBar progressBar;
        private javax.swing.JLabel task;
        // End of variables declaration//GEN-END:variables
}
