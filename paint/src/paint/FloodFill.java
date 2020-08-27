package paint;

import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.Queue;

public class FloodFill {
    /*метод для попиксельной заливки цветом, используя очередь из  точек*/
    public static void floodFill(BufferedImage image, int x, int y, Color color) { 
        int oldColor = image.getRGB(x, y);
        boolean[][] pixels = new boolean[image.getHeight()][image.getWidth()];
        Queue <Point> queue = new LinkedList<>();
        queue.add(new Point(x, y));
        while (!queue.isEmpty()) {
            Point point = queue.remove();
            if(floodFillDo(image, pixels, point.x, point.y, oldColor, color.getRGB())) { 
                queue.add(new Point(point.x + 1, point.y));
                queue.add(new Point(point.x - 1, point.y)); 
                queue.add(new Point(point.x, point.y + 1));
                queue.add(new Point(point.x, point.y - 1));
            }
        }
    }
    
    /*проверка координат пикселя на соответствие допустимым значениям*/
    private static boolean floodFillDo(BufferedImage image, boolean[][] pixels,int x, int y, int oldColor, int newColor){
        if (y < 0) 
            return false;
        if (x < 0) 
            return false;
        if (y > image.getHeight()-1) 
            return false;
        if (x > image.getWidth()-1) 
            return false;
        if (pixels[y][x]) 
            return false;
        if (image.getRGB(x, y)!=oldColor)
            return false;
        image.setRGB(x, y, newColor);
        pixels[y][x] = true;
        return true;
    }
    
}
