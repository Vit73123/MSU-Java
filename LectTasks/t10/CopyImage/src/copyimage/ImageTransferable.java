package copyimage;

import java.awt.Image;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;

public class ImageTransferable {
    
    private Image myImage;
    
    public ImageTransferable(Image image) {
        myImage = image;
    }
    
    public DataFlavor[] getTransferDataFlavors() {
        return new DataFlavor[] { DataFlavor.imageFlavor }; // Вид передаваемого элемента
    }
    
    public boolean isDataFlavorSupported(DataFlavor flavor) {
        return flavor.equals(DataFlavor.imageFlavor);
    }
    
    public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException {
        if (flavor.equals(DataFlavor.imageFlavor)) {
            return myImage;
        } else {
            throw new UnsupportedFlavorException(flavor);
        }
    }
}
