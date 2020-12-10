
/**
 * Write a description of class Pos here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Pos
{
    int x, y, width, height;
    
    public Pos(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    
    public int x() {
        return this.x;
    }
    
    public int y() {
        return this.y;
    }
    
    public int width() {
        return this.width;
    }
    
    public int height() {
        return this.height;
    }
}
