/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import java.awt.Cursor;
import java.awt.Font;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import kdesp73.themeLib.Theme;

/**
 *
 * @author konstantinos
 */
public class MovieSelectionFrame extends javax.swing.JFrame {
    Theme activeTheme;
    int selectedMovieIndex = 0;
    int movieListSize;
    ArrayList<HashMap<String, String>> movies;
    
    /**
     * Creates new form MovieSelectionFrame
     */
    public MovieSelectionFrame(ArrayList<HashMap<String, String>> movies) {
        initComponents();
        
       if(movies == null) {
           System.err.println("Null list");
           return;
       }
       
       if(movies.size() <= 0) {
           System.err.println("Empty list");
           return;
       }
        
        this.activeTheme = GUIMethods.setupFrame(this, this.getSize(),  "Select Movie");
        this.movieListSize = movies.size();
        this.movies = movies;
        
        this.titleLabel.setFont(new Font(this.getFont().getName(), Font.PLAIN, 25));
        this.yearLabel.setFont(new Font(this.getFont().getName(), Font.PLAIN, 25));
        
       
        showInfo(selectedMovieIndex);
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
        imagePanel = new RoundedPanel();
        imageLabel = new javax.swing.JLabel();
        infoPanel = new RoundedPanel();
        titleLabel = new javax.swing.JLabel();
        yearLabel = new javax.swing.JLabel();
        confirmButton = new RoundedPanel();
        confirmLabel = new javax.swing.JLabel();
        nextButton = new RoundedPanel();
        nextLabel = new javax.swing.JLabel();
        cancelButton = new RoundedPanel();
        cancelLabel = new javax.swing.JLabel();
        prevButton = new RoundedPanel();
        prevLabel = new javax.swing.JLabel();
        indexLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        background.setName("bg"); // NOI18N

        imagePanel.setBackground(new java.awt.Color(22, 82, 212));
        imagePanel.setName("bg_2"); // NOI18N

        javax.swing.GroupLayout imagePanelLayout = new javax.swing.GroupLayout(imagePanel);
        imagePanel.setLayout(imagePanelLayout);
        imagePanelLayout.setHorizontalGroup(
            imagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(imageLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 332, Short.MAX_VALUE)
        );
        imagePanelLayout.setVerticalGroup(
            imagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(imageLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        infoPanel.setBackground(new java.awt.Color(69, 166, 55));
        infoPanel.setName("bg_2"); // NOI18N

        titleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleLabel.setText("Title");
        titleLabel.setName("fg_2"); // NOI18N

        yearLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        yearLabel.setText("Year");
        yearLabel.setName("fg_2"); // NOI18N
        yearLabel.setPreferredSize(new java.awt.Dimension(26, 17));

        confirmButton.setName("bg"); // NOI18N
        confirmButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                confirmButtonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                confirmButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                confirmButtonMouseExited(evt);
            }
        });

        confirmLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        confirmLabel.setLabelFor(confirmButton);
        confirmLabel.setText("Confirm");
        confirmLabel.setName("fg"); // NOI18N

        javax.swing.GroupLayout confirmButtonLayout = new javax.swing.GroupLayout(confirmButton);
        confirmButton.setLayout(confirmButtonLayout);
        confirmButtonLayout.setHorizontalGroup(
            confirmButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(confirmLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        confirmButtonLayout.setVerticalGroup(
            confirmButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(confirmLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        nextButton.setName("bg"); // NOI18N
        nextButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nextButtonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                nextButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                nextButtonMouseExited(evt);
            }
        });

        nextLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nextLabel.setLabelFor(confirmButton);
        nextLabel.setText("Next");
        nextLabel.setName("fg"); // NOI18N

        javax.swing.GroupLayout nextButtonLayout = new javax.swing.GroupLayout(nextButton);
        nextButton.setLayout(nextButtonLayout);
        nextButtonLayout.setHorizontalGroup(
            nextButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(nextLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)
        );
        nextButtonLayout.setVerticalGroup(
            nextButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(nextLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        cancelButton.setName("bg"); // NOI18N
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

        cancelLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cancelLabel.setLabelFor(confirmButton);
        cancelLabel.setText("Cancel");
        cancelLabel.setName("fg"); // NOI18N

        javax.swing.GroupLayout cancelButtonLayout = new javax.swing.GroupLayout(cancelButton);
        cancelButton.setLayout(cancelButtonLayout);
        cancelButtonLayout.setHorizontalGroup(
            cancelButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(cancelLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)
        );
        cancelButtonLayout.setVerticalGroup(
            cancelButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(cancelLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        prevButton.setName("bg"); // NOI18N
        prevButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                prevButtonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                prevButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                prevButtonMouseExited(evt);
            }
        });

        prevLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        prevLabel.setLabelFor(confirmButton);
        prevLabel.setText("Prev");
        prevLabel.setName("fg"); // NOI18N

        javax.swing.GroupLayout prevButtonLayout = new javax.swing.GroupLayout(prevButton);
        prevButton.setLayout(prevButtonLayout);
        prevButtonLayout.setHorizontalGroup(
            prevButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(prevLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)
        );
        prevButtonLayout.setVerticalGroup(
            prevButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(prevLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        indexLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        indexLabel.setText("Movie #1");
        indexLabel.setName("fg_2"); // NOI18N

        javax.swing.GroupLayout infoPanelLayout = new javax.swing.GroupLayout(infoPanel);
        infoPanel.setLayout(infoPanelLayout);
        infoPanelLayout.setHorizontalGroup(
            infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(infoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(yearLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(titleLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, infoPanelLayout.createSequentialGroup()
                        .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 114, Short.MAX_VALUE)
                        .addComponent(prevButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nextButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(confirmButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(indexLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE))))
                .addContainerGap())
        );
        infoPanelLayout.setVerticalGroup(
            infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(infoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(yearLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 224, Short.MAX_VALUE)
                .addComponent(indexLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addGroup(infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(confirmButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nextButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cancelButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(prevButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout backgroundLayout = new javax.swing.GroupLayout(background);
        background.setLayout(backgroundLayout);
        backgroundLayout.setHorizontalGroup(
            backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backgroundLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(imagePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(infoPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        backgroundLayout.setVerticalGroup(
            backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backgroundLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(imagePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(infoPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
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

    private void confirmButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_confirmButtonMouseEntered
        this.confirmButton.setBackground(activeTheme.getExtra(0));
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_confirmButtonMouseEntered

    private void confirmButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_confirmButtonMouseExited
        this.confirmButton.setBackground(activeTheme.getBg());
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_confirmButtonMouseExited

    private void nextButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nextButtonMouseEntered
        this.nextButton.setBackground(activeTheme.getExtra(0));
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_nextButtonMouseEntered

    private void nextButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nextButtonMouseExited
        this.nextButton.setBackground(activeTheme.getBg());
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_nextButtonMouseExited

    private void cancelButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelButtonMouseEntered
        this.cancelButton.setBackground(activeTheme.getExtra(0));
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_cancelButtonMouseEntered

    private void cancelButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelButtonMouseExited
        this.cancelButton.setBackground(activeTheme.getBg());
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_cancelButtonMouseExited

    private void confirmButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_confirmButtonMouseClicked
        if(evt.getButton() == 3) return; // right click
        
        System.out.println("Selected index: " + this.selectedMovieIndex);
        
        // Add movie info to db
        System.out.println(movies.get(selectedMovieIndex));
        
        this.dispose();
    }//GEN-LAST:event_confirmButtonMouseClicked

    private void nextButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nextButtonMouseClicked
        if(evt.getButton() == 3) return; // right click^
        
        this.selectedMovieIndex = (this.selectedMovieIndex == this.movieListSize - 1) ? this.selectedMovieIndex = 0 : this.selectedMovieIndex + 1;
        System.out.println(this.selectedMovieIndex);
        this.indexLabel.setText("Movie #" + (selectedMovieIndex+1));
        showInfo(selectedMovieIndex);
    }//GEN-LAST:event_nextButtonMouseClicked

    private void cancelButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelButtonMouseClicked
        if(evt.getButton() == 3) return; // right click
       
        int choice = JOptionPane.showConfirmDialog(this, "Are you sure you want to cancel the movie selection?", "Cancel?", JOptionPane.YES_NO_OPTION);
       System.out.println(choice);
       
        switch (choice) {
            case 0: // yes
                this.dispose();
                break;
            case 1: // no
                return;
            default:
                throw new AssertionError();
        }
        
      

    }//GEN-LAST:event_cancelButtonMouseClicked

    private void prevButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_prevButtonMouseClicked
        if(evt.getButton() == 3) return; // right click
                
        this.selectedMovieIndex = (this.selectedMovieIndex == 0) ? this.selectedMovieIndex = this.movieListSize - 1 : this.selectedMovieIndex - 1;
        System.out.println(this.selectedMovieIndex);
        this.indexLabel.setText("Movie #" + (selectedMovieIndex+1));
        showInfo(selectedMovieIndex);
    }//GEN-LAST:event_prevButtonMouseClicked

    private void prevButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_prevButtonMouseEntered
        this.prevButton.setBackground(activeTheme.getExtra(0));
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_prevButtonMouseEntered

    private void prevButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_prevButtonMouseExited
        this.prevButton.setBackground(activeTheme.getBg());
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_prevButtonMouseExited

    private void showInfo(int index){
        if(index < 0 || index > movies.size() - 1) return;
        
        this.titleLabel.setText(movies.get(index).get("title"));
        this.yearLabel.setText(movies.get(index).get("year"));
        
        // TODO: load coresponding image
        GUIMethods.loadImage(this.imageLabel, System.getProperty("user.dir").replaceAll(Pattern.quote("\\"), "/") + "/assets/tmdbLogo.png");
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        ArrayList<HashMap<String, String>> movies = new ArrayList<>();
        
        HashMap<String, String> m1 = new HashMap<>();
        m1.put("title", "Se7en");
        m1.put("year", "1997");
        movies.add(m1);
        
        HashMap<String, String> m2 = new HashMap<>();
        m2.put("title", "V for Vendetta");
        m2.put("year", "2005");
        movies.add(m2);
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MovieSelectionFrame(movies).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel background;
    private javax.swing.JPanel cancelButton;
    private javax.swing.JLabel cancelLabel;
    private javax.swing.JPanel confirmButton;
    private javax.swing.JLabel confirmLabel;
    private javax.swing.JLabel imageLabel;
    private javax.swing.JPanel imagePanel;
    private javax.swing.JLabel indexLabel;
    private javax.swing.JPanel infoPanel;
    private javax.swing.JPanel nextButton;
    private javax.swing.JLabel nextLabel;
    private javax.swing.JPanel prevButton;
    private javax.swing.JLabel prevLabel;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JLabel yearLabel;
    // End of variables declaration//GEN-END:variables
}
