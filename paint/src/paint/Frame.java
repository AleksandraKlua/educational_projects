package paint;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class Frame extends JFrame {
    public int x1, x2;
    public int xf, yf;
    public int previousX, previousY;
    public int chosen;
    public JTextField bw;
    public JPanel paint_panel;
    public JButton filling, linen, ovaln, rectn, trianglen, brush, eraser;
    public Color select_color = Color.BLACK;
    public int line_width = 1;
    public Color fill_color;
    public Color p = Color.WHITE;
    public boolean pressed = false;
    public BufferedImage imag;
    
    public class Panel extends JPanel { 
    /*метод для создания области для рисования через параметры изображения из 
    буфера, получаемое как белый прямоугольник*/
        @Override
        public void paintComponent(Graphics g) {
            if (imag == null) {
                imag = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_RGB);
                Graphics2D gg = (Graphics2D) imag.createGraphics();
                gg.setColor(Color.white);
                gg.fillRect(0, 0, this.getWidth(), this.getHeight());
            }
            super.paintComponent(g);
            g.drawImage(imag, 0, 0, this);
        }
    }
    
    Frame () { /*создание интерфейса с рамками, расположением на экране, 
        именем и функцией выхода при нажатии на крестик*/
        setBounds (30, 10, 1300, 700);
        setTitle("Графический редактор");
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        /*создание панели с кнопками ластик, линия, овал, прямоугольник, треугольник, кисть, 
        цвет линий, заливка, толщина линий и текстового поля для ввода толщины. Присваивание
        каждой кнопке числа для последующего использования в операторе switch для методов, 
        обрабатывающих клики мышки*/
        paint_panel = new Panel();
        JPanel btn_bar = new JPanel(new GridLayout(10,1));
        paint_panel.setLayout(new BorderLayout());
        paint_panel.setBackground(Color.WHITE);
        add(paint_panel, BorderLayout.CENTER);
        add(btn_bar, BorderLayout.WEST);
        
        eraser = new JButton ("Ластик");
        eraser.addActionListener((ActionEvent e) -> {
                chosen = 0;
            });
        btn_bar.add(eraser);
        linen = new JButton ("Линия");
        linen.addActionListener((ActionEvent e) -> {
                chosen = 1;
            });
        btn_bar.add(linen);
        ovaln = new JButton ("Овал");
        ovaln.addActionListener((ActionEvent e) -> {
                chosen = 2;
            });
        btn_bar.add(ovaln);
        rectn = new JButton ("Прямоугольник");
        rectn.addActionListener((ActionEvent e) -> {
                chosen = 3;
            });
        btn_bar.add(rectn);
        trianglen = new JButton ("Треугольник");
        trianglen.addActionListener((ActionEvent e) -> {
                chosen = 4;
            });
        btn_bar.add(trianglen);
        
        brush = new JButton ("Кисть");
        brush.addActionListener((ActionEvent e) -> {
                chosen = 5;
            });
        btn_bar.add(brush);
        filling = new JButton ("Заливка"); 
        filling.addActionListener((ActionEvent e) -> { //вывод палитры цветов 
            fill_color = JColorChooser.showDialog(((Component) e.getSource()).getParent(), "Панель выбора цвета", fill_color);
                chosen = 6;
            });
        btn_bar.add(filling);
        JButton select_c = new JButton("Цвет линий");
        select_c.addActionListener((ActionEvent e) -> {
            select_color = JColorChooser.showDialog(((Component) e.getSource()).getParent(), "Панель выбора цвета", select_color);
            });
        btn_bar.add(select_c);
        
        JButton select_w = new JButton("Толщина линий");
        bw = new JTextField(); //поле для ввода толщины
        bw.setHorizontalAlignment(JTextField.CENTER); //расположение поля
        select_w.addActionListener((ActionEvent e) -> {
            String str = bw.getText();
            if (str.length()<=2) {
                line_width = Integer.parseInt(bw.getText());
            } else JOptionPane.showMessageDialog(null, "Не вводите буквы и символы! Не вводите число больше 99", "Ошибка", JOptionPane.ERROR_MESSAGE);
        }); //сообщение об ошибке, если вводимое число больше 99 или введены символы/буквы
        btn_bar.add(select_w);
        btn_bar.add(bw);
        
        paint_panel.addMouseMotionListener(new MouseMotionAdapter() { //добавление интерфейса для получения движения мыши
            public void mouseDragged(MouseEvent ev) { //событие, когда щелчок мыши удерживается
                if (pressed == true) {
                    Graphics2D g2 = (Graphics2D) imag.getGraphics();
                    // установка цвета
                    g2.setColor(select_color);
                    BasicStroke lw = new BasicStroke(line_width, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_BEVEL);
                    switch (chosen) {
                        case 5: //рисование кистью
                            g2.setStroke(lw);
                            g2.drawLine(previousX, previousY, ev.getX(), ev.getY());
                            break; 
                        case 0: //ластик (кисть белого цвета)
                            g2.setStroke(lw);
                            g2.setColor(Color.WHITE);
                            g2.drawLine(previousX, previousY, ev.getX(), ev.getY());
                            break;
                    }
                    previousX = ev.getX(); //получение координат мыши
                    previousY = ev.getY();
                }
                repaint();
            }
        });
        paint_panel.addMouseListener(new MouseAdapter(){ //методы для прослушивания событий мыши 
            public void mousePressed(MouseEvent e) { //событие при нажатии мышью
                previousY = e.getY(); //получение координат курсора
                previousX = e.getX();
                xf = e.getX();
                yf = e.getY();
                pressed = true;
                switch (chosen) {
                    case 6: //заливка 
                        FloodFill ff = new FloodFill();
                        ff.floodFill(imag, e.getX(),e.getY(), fill_color);
                        break;
                }
            }
            public void mouseReleased(MouseEvent e) { //события, когды мышь отпустили
                Graphics2D g2 = (Graphics2D) imag.getGraphics();
                g2.setColor(select_color);
                BasicStroke lw = new BasicStroke(line_width, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_BEVEL); 
                // исходная толщина линий
                int x1 = xf, x2 = previousX, y1 = yf, y2 = previousY;
                if (xf > previousX) {
                    x2 = xf;
                    x1 = previousX;
                }
                if (yf > previousY) {
                    y2 = yf;
                    y1 = previousY;
                }
                switch (chosen) {
                    case 1: //линия
                        g2.setStroke(lw);
                        g2.drawLine(xf, yf, e.getX(), e.getY());
                        break;
                    case 2: //овал
                        g2.setStroke(lw);
                        g2.drawOval(x1, y1, (x2 - x1), (y2 - y1));
                        break;
                    case 3:// прямоугольник
                        g2.setStroke(lw);
                        g2.drawRect(x1, y1, (x2 - x1), (y2 - y1));
                        break;
                    case 4: //треугольник
                        g2.setStroke(lw);
                        g2.drawPolygon(new int [] {x1, x2, e.getX()}, new int [] {y1, y2, x1}, 3);
                        break;
                }
                xf = 0;
                yf = 0;
                pressed = false;
                repaint();
            }
        });
    }
}
