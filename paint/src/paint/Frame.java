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
    /*����� ��� �������� ������� ��� ��������� ����� ��������� ����������� �� 
    ������, ���������� ��� ����� �������������*/
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
    
    Frame () { /*�������� ���������� � �������, ������������� �� ������, 
        ������ � �������� ������ ��� ������� �� �������*/
        setBounds (30, 10, 1300, 700);
        setTitle("����������� ��������");
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        /*�������� ������ � �������� ������, �����, ����, �������������, �����������, �����, 
        ���� �����, �������, ������� ����� � ���������� ���� ��� ����� �������. ������������
        ������ ������ ����� ��� ������������ ������������� � ��������� switch ��� �������, 
        �������������� ����� �����*/
        paint_panel = new Panel();
        JPanel btn_bar = new JPanel(new GridLayout(10,1));
        paint_panel.setLayout(new BorderLayout());
        paint_panel.setBackground(Color.WHITE);
        add(paint_panel, BorderLayout.CENTER);
        add(btn_bar, BorderLayout.WEST);
        
        eraser = new JButton ("������");
        eraser.addActionListener((ActionEvent e) -> {
                chosen = 0;
            });
        btn_bar.add(eraser);
        linen = new JButton ("�����");
        linen.addActionListener((ActionEvent e) -> {
                chosen = 1;
            });
        btn_bar.add(linen);
        ovaln = new JButton ("����");
        ovaln.addActionListener((ActionEvent e) -> {
                chosen = 2;
            });
        btn_bar.add(ovaln);
        rectn = new JButton ("�������������");
        rectn.addActionListener((ActionEvent e) -> {
                chosen = 3;
            });
        btn_bar.add(rectn);
        trianglen = new JButton ("�����������");
        trianglen.addActionListener((ActionEvent e) -> {
                chosen = 4;
            });
        btn_bar.add(trianglen);
        
        brush = new JButton ("�����");
        brush.addActionListener((ActionEvent e) -> {
                chosen = 5;
            });
        btn_bar.add(brush);
        filling = new JButton ("�������"); 
        filling.addActionListener((ActionEvent e) -> { //����� ������� ������ 
            fill_color = JColorChooser.showDialog(((Component) e.getSource()).getParent(), "������ ������ �����", fill_color);
                chosen = 6;
            });
        btn_bar.add(filling);
        JButton select_c = new JButton("���� �����");
        select_c.addActionListener((ActionEvent e) -> {
            select_color = JColorChooser.showDialog(((Component) e.getSource()).getParent(), "������ ������ �����", select_color);
            });
        btn_bar.add(select_c);
        
        JButton select_w = new JButton("������� �����");
        bw = new JTextField(); //���� ��� ����� �������
        bw.setHorizontalAlignment(JTextField.CENTER); //������������ ����
        select_w.addActionListener((ActionEvent e) -> {
            String str = bw.getText();
            if (str.length()<=2) {
                line_width = Integer.parseInt(bw.getText());
            } else JOptionPane.showMessageDialog(null, "�� ������� ����� � �������! �� ������� ����� ������ 99", "������", JOptionPane.ERROR_MESSAGE);
        }); //��������� �� ������, ���� �������� ����� ������ 99 ��� ������� �������/�����
        btn_bar.add(select_w);
        btn_bar.add(bw);
        
        paint_panel.addMouseMotionListener(new MouseMotionAdapter() { //���������� ���������� ��� ��������� �������� ����
            public void mouseDragged(MouseEvent ev) { //�������, ����� ������ ���� ������������
                if (pressed == true) {
                    Graphics2D g2 = (Graphics2D) imag.getGraphics();
                    // ��������� �����
                    g2.setColor(select_color);
                    BasicStroke lw = new BasicStroke(line_width, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_BEVEL);
                    switch (chosen) {
                        case 5: //��������� ������
                            g2.setStroke(lw);
                            g2.drawLine(previousX, previousY, ev.getX(), ev.getY());
                            break; 
                        case 0: //������ (����� ������ �����)
                            g2.setStroke(lw);
                            g2.setColor(Color.WHITE);
                            g2.drawLine(previousX, previousY, ev.getX(), ev.getY());
                            break;
                    }
                    previousX = ev.getX(); //��������� ��������� ����
                    previousY = ev.getY();
                }
                repaint();
            }
        });
        paint_panel.addMouseListener(new MouseAdapter(){ //������ ��� ������������� ������� ���� 
            public void mousePressed(MouseEvent e) { //������� ��� ������� �����
                previousY = e.getY(); //��������� ��������� �������
                previousX = e.getX();
                xf = e.getX();
                yf = e.getY();
                pressed = true;
                switch (chosen) {
                    case 6: //������� 
                        FloodFill ff = new FloodFill();
                        ff.floodFill(imag, e.getX(),e.getY(), fill_color);
                        break;
                }
            }
            public void mouseReleased(MouseEvent e) { //�������, ����� ���� ���������
                Graphics2D g2 = (Graphics2D) imag.getGraphics();
                g2.setColor(select_color);
                BasicStroke lw = new BasicStroke(line_width, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_BEVEL); 
                // �������� ������� �����
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
                    case 1: //�����
                        g2.setStroke(lw);
                        g2.drawLine(xf, yf, e.getX(), e.getY());
                        break;
                    case 2: //����
                        g2.setStroke(lw);
                        g2.drawOval(x1, y1, (x2 - x1), (y2 - y1));
                        break;
                    case 3:// �������������
                        g2.setStroke(lw);
                        g2.drawRect(x1, y1, (x2 - x1), (y2 - y1));
                        break;
                    case 4: //�����������
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
