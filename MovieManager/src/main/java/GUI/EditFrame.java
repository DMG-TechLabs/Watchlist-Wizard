package GUI;

import Exceptions.DatabaseStringOverflowException;
import Database.Database;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import main.MovieCollection;

/**
 *
 * @author Konstantinos
 */
public class EditFrame extends javax.swing.JFrame {

        static final String TITLE = "Edit";
        Theme theme;

        Frame f;
        MovieCollection movies;
        int index;

        public EditFrame() {
                initComponents();
                this.setResizable(false);
                this.theme = GUIMethods.setupFrame(this, this.getPreferredSize(), TITLE);
                this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        }

        public EditFrame(Frame f, int index) {
                //Setup Frame
                initComponents();
                this.setResizable(false);
                this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                this.theme = GUIMethods.setupFrame(this, this.getPreferredSize(), TITLE);

                //Setup Components
                this.f = f;
                this.index = index;
                this.movies = f.getMovies();
                initValues(index);
        }

        private void initValues(int index) throws NumberFormatException {
                if (movies.getMovies().get(index).getTitle() == null) {
                        titleField.setText("");
                } else {
                        titleField.setText(movies.getMovies().get(index).getTitle());
                }
                if (movies.getMovies().get(index).getDirector() == null) {
                        directorField.setText("");
                } else {
                        directorField.setText(movies.getMovies().get(index).getDirector());
                }
                if (movies.getMovies().get(index).getWriter() == null) {
                        writersField.setText("");
                } else {
                        writersField.setText(movies.getMovies().get(index).getWriter());
                }
                if (movies.getMovies().get(index).getLanguage() == null) {
                        langField.setText("");
                } else {
                        langField.setText(movies.getMovies().get(index).getLanguage());
                }
                if (movies.getMovies().get(index).getPlot() == null) {
                        plotArea.setText("");
                } else {
                        plotArea.setText(movies.getMovies().get(index).getPlot());
                }
                if (movies.getMovies().get(index).getYear() == null || "".equals(movies.getMovies().get(index).getYear())) {
                        yearSpinner.setValue(0);
                } else {
                        yearSpinner.setValue(Integer.valueOf(movies.getMovies().get(index).getYear()));
                }
                if (movies.getMovies().get(index).getAwards() == null) {
                        awardsField.setText("");
                } else {
                        awardsField.setText(movies.getMovies().get(index).getAwards());
                }
                if (movies.getMovies().get(index).getRated() == null) {
                        ratedField.setText("");
                } else {
                        ratedField.setText(movies.getMovies().get(index).getRated());
                }
                if (movies.getMovies().get(index).getRuntime() == null) {
                        runtimeField.setText("");
                } else {
                        runtimeField.setText(movies.getMovies().get(index).getRuntime());
                }
                if (movies.getMovies().get(index).getActors() == null) {
                        actorsField.setText("");
                } else {
                        actorsField.setText(movies.getMovies().get(index).getActors());
                }
                if (movies.getMovies().get(index).getCountry() == null) {
                        countryField.setText("");
                } else {
                        countryField.setText(movies.getMovies().get(index).getCountry());
                }
                if (movies.getMovies().get(index).getImdbRating() == null) {
                        imdbratingField.setText("");
                } else {
                        imdbratingField.setText(movies.getMovies().get(index).getImdbRating());
                }
                if (movies.getMovies().get(index).getImdbID() == null) {
                        imdbidField.setText("");
                } else {
                        imdbidField.setText(movies.getMovies().get(index).getImdbID());
                }
        }

        /**
         * This method is called from within the
         * constructor to initialize the form.
         * WARNING: Do NOT modify this code. The
         * content of this method is always
         * regenerated by the Form Editor.
         */
        @SuppressWarnings("unchecked")
        // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
        private void initComponents() {

                background = new javax.swing.JPanel();
                jLabel1 = new javax.swing.JLabel();
                titleField = new javax.swing.JTextField();
                jLabel3 = new javax.swing.JLabel();
                yearSpinner = new javax.swing.JSpinner();
                jLabel4 = new javax.swing.JLabel();
                directorField = new javax.swing.JTextField();
                jLabel5 = new javax.swing.JLabel();
                writersField = new javax.swing.JTextField();
                jLabel6 = new javax.swing.JLabel();
                langField = new javax.swing.JTextField();
                jLabel2 = new javax.swing.JLabel();
                jScrollPane1 = new javax.swing.JScrollPane();
                plotArea = new javax.swing.JTextArea();
                jLabel7 = new javax.swing.JLabel();
                ratedField = new javax.swing.JTextField();
                jLabel8 = new javax.swing.JLabel();
                runtimeField = new javax.swing.JTextField();
                jLabel9 = new javax.swing.JLabel();
                actorsField = new javax.swing.JTextField();
                jLabel11 = new javax.swing.JLabel();
                countryField = new javax.swing.JTextField();
                jLabel12 = new javax.swing.JLabel();
                awardsField = new javax.swing.JTextField();
                jLabel13 = new javax.swing.JLabel();
                imdbratingField = new javax.swing.JTextField();
                jLabel14 = new javax.swing.JLabel();
                imdbidField = new javax.swing.JTextField();
                deleteButton = new RoundedPanel();
                jLabel15 = new javax.swing.JLabel();
                cancelButton = new RoundedPanel();
                jLabel16 = new javax.swing.JLabel();
                applyButton = new RoundedPanel();
                jLabel17 = new javax.swing.JLabel();

                setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
                setName("frame"); // NOI18N

                background.setName("background"); // NOI18N

                jLabel1.setText("Title: ");
                jLabel1.setName("label"); // NOI18N

                titleField.setName("textbox"); // NOI18N

                jLabel3.setText("Year:");
                jLabel3.setName("label"); // NOI18N

                yearSpinner.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));
                yearSpinner.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                yearSpinner.setName("spinner"); // NOI18N

                jLabel4.setText("Director: ");
                jLabel4.setName("label"); // NOI18N

                directorField.setName("textbox"); // NOI18N

                jLabel5.setText("Writers:");
                jLabel5.setName("label"); // NOI18N

                writersField.setName("textbox"); // NOI18N

                jLabel6.setText("Language: ");
                jLabel6.setName("label"); // NOI18N

                langField.setName("textbox"); // NOI18N

                jLabel2.setText("Plot:");
                jLabel2.setName("label"); // NOI18N

                plotArea.setColumns(20);
                plotArea.setRows(5);
                plotArea.setName("textbox"); // NOI18N
                jScrollPane1.setViewportView(plotArea);

                jLabel7.setText("Rated:");
                jLabel7.setName("label"); // NOI18N

                ratedField.setName("textbox"); // NOI18N
                ratedField.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                ratedFieldActionPerformed(evt);
                        }
                });

                jLabel8.setText("Runtime:");
                jLabel8.setName("label"); // NOI18N

                runtimeField.setName("textbox"); // NOI18N

                jLabel9.setText("Actors:");
                jLabel9.setName("label"); // NOI18N

                actorsField.setName("textbox"); // NOI18N

                jLabel11.setText("Country:");
                jLabel11.setName("label"); // NOI18N

                countryField.setName("textbox"); // NOI18N
                countryField.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                countryFieldActionPerformed(evt);
                        }
                });

                jLabel12.setText("Awards:");
                jLabel12.setName("label"); // NOI18N

                awardsField.setName("textbox"); // NOI18N

                jLabel13.setText("IMDb Rating:");
                jLabel13.setName("label"); // NOI18N

                imdbratingField.setName("textbox"); // NOI18N
                imdbratingField.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                imdbratingFieldActionPerformed(evt);
                        }
                });

                jLabel14.setText("IMDb ID:");
                jLabel14.setName("label"); // NOI18N

                imdbidField.setName("textbox"); // NOI18N

                deleteButton.setBackground(new java.awt.Color(68, 68, 68));
                deleteButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                deleteButton.setName("button"); // NOI18N
                deleteButton.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                deleteButtonMouseClicked(evt);
                        }
                        public void mouseEntered(java.awt.event.MouseEvent evt) {
                                deleteButtonMouseEntered(evt);
                        }
                        public void mouseExited(java.awt.event.MouseEvent evt) {
                                deleteButtonMouseExited(evt);
                        }
                });

                jLabel15.setForeground(new java.awt.Color(255, 255, 255));
                jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel15.setText("Delete");
                jLabel15.setName("buttonLabel"); // NOI18N

                javax.swing.GroupLayout deleteButtonLayout = new javax.swing.GroupLayout(deleteButton);
                deleteButton.setLayout(deleteButtonLayout);
                deleteButtonLayout.setHorizontalGroup(
                        deleteButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE)
                );
                deleteButtonLayout.setVerticalGroup(
                        deleteButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                );

                cancelButton.setBackground(new java.awt.Color(68, 68, 68));
                cancelButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                cancelButton.setName("button"); // NOI18N
                cancelButton.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                cancelButtonMouseClicked(evt);
                        }
                        public void mouseEntered(java.awt.event.MouseEvent evt) {
                                cancelButtonMouseEntered(evt);
                        }
                        public void mouseExited(java.awt.event.MouseEvent evt) {
                                cancelButtonMouseExited(evt);
                        }
                });

                jLabel16.setForeground(new java.awt.Color(255, 255, 255));
                jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel16.setText("Cancel");
                jLabel16.setName("buttonLabel"); // NOI18N

                javax.swing.GroupLayout cancelButtonLayout = new javax.swing.GroupLayout(cancelButton);
                cancelButton.setLayout(cancelButtonLayout);
                cancelButtonLayout.setHorizontalGroup(
                        cancelButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE)
                );
                cancelButtonLayout.setVerticalGroup(
                        cancelButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                );

                applyButton.setBackground(new java.awt.Color(68, 68, 68));
                applyButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                applyButton.setName("button"); // NOI18N
                applyButton.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                applyButtonMouseClicked(evt);
                        }
                        public void mouseEntered(java.awt.event.MouseEvent evt) {
                                applyButtonMouseEntered(evt);
                        }
                        public void mouseExited(java.awt.event.MouseEvent evt) {
                                applyButtonMouseExited(evt);
                        }
                });

                jLabel17.setForeground(new java.awt.Color(255, 255, 255));
                jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel17.setText("Apply");
                jLabel17.setName("buttonLabel"); // NOI18N

                javax.swing.GroupLayout applyButtonLayout = new javax.swing.GroupLayout(applyButton);
                applyButton.setLayout(applyButtonLayout);
                applyButtonLayout.setHorizontalGroup(
                        applyButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
                );
                applyButtonLayout.setVerticalGroup(
                        applyButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, applyButtonLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                );

                javax.swing.GroupLayout backgroundLayout = new javax.swing.GroupLayout(background);
                background.setLayout(backgroundLayout);
                backgroundLayout.setHorizontalGroup(
                        backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(backgroundLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(backgroundLayout.createSequentialGroup()
                                                .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE)
                                                        .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(langField)
                                                        .addComponent(countryField)
                                                        .addComponent(awardsField)
                                                        .addComponent(writersField, javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(actorsField, javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(imdbratingField)
                                                        .addComponent(imdbidField)
                                                        .addComponent(jScrollPane1)
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                .addComponent(titleField, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(runtimeField, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(ratedField, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(directorField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(yearSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, backgroundLayout.createSequentialGroup()
                                                .addComponent(deleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(applyButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
                );
                backgroundLayout.setVerticalGroup(
                        backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(backgroundLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel1)
                                        .addComponent(titleField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel3)
                                        .addComponent(yearSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel7)
                                        .addComponent(ratedField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel8)
                                        .addComponent(runtimeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel4)
                                        .addComponent(directorField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel5)
                                        .addComponent(writersField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel9)
                                        .addComponent(actorsField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel6)
                                        .addComponent(langField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel11)
                                        .addComponent(countryField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel12)
                                        .addComponent(awardsField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel13)
                                        .addComponent(imdbratingField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel14)
                                        .addComponent(imdbidField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel2)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 149, Short.MAX_VALUE)
                                .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(deleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(applyButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap())
                );

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                getContentPane().setLayout(layout);
                layout.setHorizontalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(background, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                );
                layout.setVerticalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(background, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                );

                pack();
        }// </editor-fold>//GEN-END:initComponents

        private void ratedFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ratedFieldActionPerformed
                // TODO add your handling code here:
        }//GEN-LAST:event_ratedFieldActionPerformed

        private void countryFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_countryFieldActionPerformed
                // TODO add your handling code here:
        }//GEN-LAST:event_countryFieldActionPerformed

        private void imdbratingFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_imdbratingFieldActionPerformed
                // TODO add your handling code here:
        }//GEN-LAST:event_imdbratingFieldActionPerformed

        private void deleteButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deleteButtonMouseClicked
                JDialog deleteDialog = new JDialog(this);
                Dimension SIZE = new Dimension(390, 142);
                JLabel message = new JLabel();
                JPanel noButton = new RoundedPanel();
                JLabel noLabel = new JLabel();
                JLabel yesLabel = new JLabel();
                JPanel yesButton = new RoundedPanel();
                JPanel bg = new JPanel();

                bg.setName("background");

                message.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                message.setText("Are you sure you want to delete? (Only from database)");
                message.setName("label");

                noButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                noButton.setName("button");
                noButton.addMouseListener(new java.awt.event.MouseAdapter() {
                        @Override
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                noButtonMouseClicked(evt);
                        }

                        @Override
                        public void mouseEntered(java.awt.event.MouseEvent evt) {
                                noButtonMouseEntered(noButton);
                        }

                        @Override
                        public void mouseExited(java.awt.event.MouseEvent evt) {
                                noButtonMouseExited(noButton);
                        }
                });

                noLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                noLabel.setText("No");
                noLabel.setName("buttonLabel");

                javax.swing.GroupLayout noButtonLayout = new javax.swing.GroupLayout(noButton);
                noButton.setLayout(noButtonLayout);
                noButtonLayout.setHorizontalGroup(
                        noButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(noLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE)
                );
                noButtonLayout.setVerticalGroup(
                        noButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(noLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                );

                yesButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                yesButton.setName("button"); // NOI18N
                yesButton.addMouseListener(new java.awt.event.MouseAdapter() {
                        @Override
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                yesButtonMouseClicked(evt);
                        }

                        @Override
                        public void mouseEntered(java.awt.event.MouseEvent evt) {
                                yesButtonMouseEntered(yesButton);
                        }

                        @Override
                        public void mouseExited(java.awt.event.MouseEvent evt) {
                                yesButtonMouseExited(yesButton);
                        }
                });

                yesLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                yesLabel.setText("Yes");
                yesLabel.setName("buttonLabel"); // NOI18N

                javax.swing.GroupLayout yesButtonLayout = new javax.swing.GroupLayout(yesButton);
                yesButton.setLayout(yesButtonLayout);
                yesButtonLayout.setHorizontalGroup(
                        yesButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(yesLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE)
                );
                yesButtonLayout.setVerticalGroup(
                        yesButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(yesLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                );

                FlowLayout layout = new FlowLayout(FlowLayout.CENTER, 100, 20);
                bg.add(message);
                bg.add(noButton);
                bg.add(yesButton);
                bg.setLayout(layout);

                deleteDialog.add(bg);
                deleteDialog.setResizable(false);
                GUIMethods.setupDialog(deleteDialog, SIZE, "Delete");

                deleteDialog.setVisible(true);
        }//GEN-LAST:event_deleteButtonMouseClicked

        private void noButtonMouseClicked(java.awt.event.MouseEvent evt) {
                this.dispose();
        }

        private void noButtonMouseEntered(JPanel noButton) {
                noButton.setBackground(f.theme.getButtonHover());
        }

        private void noButtonMouseExited(JPanel noButton) {
                noButton.setBackground(f.theme.getButton());
        }

        private void yesButtonMouseClicked(java.awt.event.MouseEvent evt) {
                try {
                        f.movies.deleteMovie(f.movies.getMovies().get(index));
                } catch (SQLException ex) {
                        Logger.getLogger(EditFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
                f.refreshMoviesList();
                this.setVisible(false);
                GUIMethods.dialog("Movie deleted successfully only from our database", "Movie deleted", "Success");
        }

        private void yesButtonMouseEntered(JPanel yesButton) {
                yesButton.setBackground(f.theme.getButtonHover());
        }

        private void yesButtonMouseExited(JPanel yesButton) {
                yesButton.setBackground(f.theme.getButton());
        }

        private void deleteButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deleteButtonMouseEntered
                deleteButton.setBackground(theme.getButtonHover());
        }//GEN-LAST:event_deleteButtonMouseEntered

        private void deleteButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deleteButtonMouseExited
                deleteButton.setBackground(theme.getButton());
        }//GEN-LAST:event_deleteButtonMouseExited

        private void cancelButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelButtonMouseClicked
                this.setVisible(false);
        }//GEN-LAST:event_cancelButtonMouseClicked

        private void cancelButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelButtonMouseEntered
                cancelButton.setBackground(theme.getButtonHover());
        }//GEN-LAST:event_cancelButtonMouseEntered

        private void cancelButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelButtonMouseExited
                cancelButton.setBackground(theme.getButton());
        }//GEN-LAST:event_cancelButtonMouseExited

        private void applyButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_applyButtonMouseClicked
                System.out.println(index);
                String title = titleField.getText();
                String director = directorField.getText();
                String writers = writersField.getText();
                String plot = plotArea.getText();
                String language = langField.getText();
                String year = yearSpinner.getValue().toString();
                String rated = ratedField.getText();
                String runtime = runtimeField.getText();
                String actors = actorsField.getText();
                String country = countryField.getText();
                String awards = awardsField.getText();
                String imdbrating = imdbratingField.getText();
                String imdbid = imdbidField.getText();

                if (title.length() > 255 || director.length() > 255 || writers.length() > 255 /*|| plot.length() > 255*/ || language.length() > 255 || rated.length() > 255 || runtime.length() > 255 || actors.length() > 255 || country.length() > 255 || awards.length() > 255 || imdbrating.length() > 255 || imdbid.length() > 255) {
                        GUIMethods.dialogError("String with more than 255 characters");
                        throw new DatabaseStringOverflowException("String with more than 255 characters");
                }

                if (yearSpinner.getValue().toString().equals("0")) {
                        year = "";
                }

                try {
                        Database db = new Database();
                        Statement s = Database.db().getStatement();
                        String query = "UPDATE Movies SET Title = \'" + title + "\', Director = \'" + director + "\', Writers = \'" + writers + "\', Plot = \'" + plot + "\', Completion_Year = \'" + year + "\', Language = \'" + language + "\', Actors = \'" + actors + "\', Rated = \'" + rated + "\', Runtime = \'" + runtime + "\', Country = \'" + country + "\', Awards = \'" + awards + "\', IMDb_Rating = \'" + imdbrating + "\', IMDb_ID = \'" + imdbid + "\' WHERE Title = \'" + movies.getMovies().get(index).getTitle() + "\'";
                        String query1 = "UPDATE Filepaths SET Title = \'" + titleField.getText() + "\' WHERE Title = \'" + movies.getMovies().get(index).getTitle() + "\'";

                        s.executeUpdate(query);
                        s.executeUpdate(query1);
                } catch (SQLException ex) {
                        Logger.getLogger(EditFrame.class.getName()).log(Level.SEVERE, null, ex);
                }

                f.refreshMoviesList();

                this.setVisible(false);
        }//GEN-LAST:event_applyButtonMouseClicked

        private void applyButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_applyButtonMouseEntered
                applyButton.setBackground(theme.getButtonHover());
        }//GEN-LAST:event_applyButtonMouseEntered

        private void applyButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_applyButtonMouseExited
                applyButton.setBackground(theme.getButton());
        }//GEN-LAST:event_applyButtonMouseExited

        /**
         * @param args the command line arguments
         * @throws
         * java.lang.ClassNotFoundException
         * @throws
         * java.lang.InstantiationException
         * @throws
         * java.lang.IllegalAccessException
         * @throws
         * javax.swing.UnsupportedLookAndFeelException
         */
        public static void main(String args[]) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

                java.awt.EventQueue.invokeLater(new Runnable() {
                        public void run() {
                                new EditFrame().setVisible(true);
                        }
                });
        }

        public Theme getTheme() {
                return theme;
        }

        public void setTheme(Theme theme) {
                this.theme = theme;
        }

        // Variables declaration - do not modify//GEN-BEGIN:variables
        private javax.swing.JTextField actorsField;
        private javax.swing.JPanel applyButton;
        private javax.swing.JTextField awardsField;
        private javax.swing.JPanel background;
        private javax.swing.JPanel cancelButton;
        private javax.swing.JTextField countryField;
        private javax.swing.JPanel deleteButton;
        private javax.swing.JTextField directorField;
        private javax.swing.JTextField imdbidField;
        private javax.swing.JTextField imdbratingField;
        private javax.swing.JLabel jLabel1;
        private javax.swing.JLabel jLabel11;
        private javax.swing.JLabel jLabel12;
        private javax.swing.JLabel jLabel13;
        private javax.swing.JLabel jLabel14;
        private javax.swing.JLabel jLabel15;
        private javax.swing.JLabel jLabel16;
        private javax.swing.JLabel jLabel17;
        private javax.swing.JLabel jLabel2;
        private javax.swing.JLabel jLabel3;
        private javax.swing.JLabel jLabel4;
        private javax.swing.JLabel jLabel5;
        private javax.swing.JLabel jLabel6;
        private javax.swing.JLabel jLabel7;
        private javax.swing.JLabel jLabel8;
        private javax.swing.JLabel jLabel9;
        private javax.swing.JScrollPane jScrollPane1;
        private javax.swing.JTextField langField;
        private javax.swing.JTextArea plotArea;
        private javax.swing.JTextField ratedField;
        private javax.swing.JTextField runtimeField;
        private javax.swing.JTextField titleField;
        private javax.swing.JTextField writersField;
        private javax.swing.JSpinner yearSpinner;
        // End of variables declaration//GEN-END:variables
}
