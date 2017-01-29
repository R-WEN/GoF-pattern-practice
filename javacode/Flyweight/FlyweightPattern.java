import java.util.*;
import java.lang.ref.*;

interface Style {
    final static int PLAIN = 1;
    final static int BOLD = 2;
    final static int ITALIC = 3;
}

class Font {
    final String name;
    final int style;
    final int size;
    
    Font(String name, int style, int size) {
        this.name = name;
        this.style = style;
        this.size = size;
    }
    
    public boolean equals(Object other) {
        if (other instanceof Font) {
            if (other == this) {
                return true;
            }
            Font font = (Font) other;
            return font.style == style 
                   && font.size == size
                   && font.name.equals(this.name);
        }
        return false;
        
    }
    public int hashCode() {
        return (style * 37 + size * 13) * name.hashCode();
    }
}

class FontFactory {
    private static Map<Font, WeakReference<Font>> flyweights =
        new WeakHashMap<Font, WeakReference<Font>>();
       
    static Font create(String name, int style, int size) {
        Font font = new Font(name, style, size);
        if(!flyweights.containsKey(font)) {
            flyweights.put(font, new WeakReference<Font>(font));
        }
        return flyweights.get(font).get();
    }
}

public class FlyweightPattern {
    public static void main(String[] args) {
        Font font1 = FontFactory.create("細明體", Style.BOLD, 12);
        Font font2 = FontFactory.create("細明體", Style.BOLD, 12);
        System.out.println(font1 == font2);
    }
}