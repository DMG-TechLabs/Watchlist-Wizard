package GUI;

import java.awt.Color;

public class Theme {
        private String name;
        private Color list;
        private Color listFocus;
        private Color listFg;
        private Color bg;
        private Color fg;
        private Color toolbar;
        private Color button;
        private Color buttonFg;
        private Color buttonHover;
        private Color textBox;
        private Color textBoxFg;
        private Color secBg;
        
        private String json;

        public Theme() {
                //Default colors
                
                name = "Default";
                list = new Color(55, 63, 81);
                listFocus = new Color(33, 38, 42);
                listFg = new Color(255, 255, 255);
                bg = new Color(240, 240, 240);
                fg = new Color(0, 0, 0);
                button = new Color(55, 63, 81);
                buttonFg = new Color(255, 255, 255);
                buttonHover = new Color(95, 101, 116);
                textBox = new Color(255, 255, 255);
                textBoxFg = new Color(0, 0, 0);
                toolbar = new Color(68,94,147);
                secBg = new Color(255, 255, 255);
                json = "{\"button\":\"373f51\",\"toolbar\":\"445e93\",\"fg\":\"000000\",\"buttonHover\":\"5f6574\",\"textBoxFg\":\"000000\",\"textBox\":\"ffffff\",\"bg\":\"f0f0f0\",\"secBg\":\"ffffff\",\"listFg\":\"ffffff\",\"buttonFg\":\"ffffff\",\"list\":\"373f51\",\"listFocus\":\"21262a\"}";
        }

        public Color getList() {
                return list;
        }

        public void setList(Color list) {
                this.list = list;
        }

        public Color getListFocus() {
                return listFocus;
        }

        public void setListFocus(Color listFocus) {
                this.listFocus = listFocus;
        }

        public Color getListForeground() {
                return listFg;
        }

        public void setListForeground(Color listForeground) {
                this.listFg = listForeground;
        }

        public Color getBackground() {
                return bg;
        }

        public void setBackground(Color background) {
                this.bg = background;
        }

        public Color getToolbar() {
                return toolbar;
        }

        public void setToolbar(Color toolbar) {
                this.toolbar = toolbar;
        }

        public Color getButton() {
                return button;
        }

        public void setButton(Color button) {
                this.button = button;
        }

        public Color getSearchbar() {
                return textBox;
        }

        public void setSearchbar(Color searchbar) {
                this.textBox = searchbar;
        }

        public Color getSearchbarForeground() {
                return textBoxFg;
        }

        public void setSearchbarForeground(Color searchbarForeground) {
                this.textBoxFg = searchbarForeground;
        }

        public Color getForeground() {
                return fg;
        }

        public void setForeground(Color foreground) {
                this.fg = foreground;
        }

        public Color getButtonForeground() {
                return buttonFg;
        }

        public void setButtonForeground(Color buttonForeground) {
                this.buttonFg = buttonForeground;
        }

        public Color getTextBox() {
                return textBox;
        }

        public void setTextBox(Color textBox) {
                this.textBox = textBox;
        }

        public Color getTextBoxForeground() {
                return textBoxFg;
        }

        public void setTextBoxForeground(Color textBoxForeground) {
                this.textBoxFg = textBoxForeground;
        }

        public Color getSecBackground() {
                return secBg;
        }

        public void setSecBackground(Color secBackground) {
                this.secBg = secBackground;
        }

        public Color getButtonHover() {
                return buttonHover;
        }

        public void setButtonHover(Color buttonHover) {
                this.buttonHover = buttonHover;
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public String getJson() {
                return json;
        }

        public void setJson(String json) {
                this.json = json;
        }
        
        @Override
        public String toString() {
                return "Theme{" + "name=" + name + '}';
        }
        
        
        
        
}
