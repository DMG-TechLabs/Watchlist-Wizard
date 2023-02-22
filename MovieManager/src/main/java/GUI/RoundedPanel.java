package GUI;

import java.awt.BasicStroke;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;

public class RoundedPanel extends JPanel {
        protected int strokeSize = 0;
        protected boolean highQuality = true;
        protected Dimension arcs;

        public RoundedPanel() {
                super();
                this.setOpaque(false);
                arcs = new Dimension(20, 20);
        }
        public RoundedPanel(int rad) {
                super();
                this.setOpaque(false);
                arcs = new Dimension(rad, rad);
        }

        @Override
        protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                int width = getWidth();
                int height = getHeight();

                Graphics2D graphics = (Graphics2D) g;

                //Sets antialiasing if HQ.
                if (highQuality) {
                        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                                RenderingHints.VALUE_ANTIALIAS_ON);
                }

                //Draws the rounded opaque panel with borders.
                graphics.setColor(getBackground());
                graphics.fillRoundRect(0, 0, width,
                        height, arcs.width, arcs.height);
                graphics.setColor(getForeground());
                graphics.setStroke(new BasicStroke(strokeSize));
                graphics.drawRoundRect(0, 0, width,
                        height, arcs.width, arcs.height);

                graphics.setColor(getForeground());
                graphics.setStroke(new BasicStroke(strokeSize));

                //Sets strokes to default, is better.
                graphics.setStroke(new BasicStroke());
        }
}
