import java.awt.Color;

/*
 * This class contains methods to create and perform operations on a collage of images.
 * 
 * @author Mary Grlic
 */ 

public class Collage {

    // The orginal picture
    private Picture originalPicture;

    // The collage picture is made up of tiles.
    // Each tile consists of tileDimension X tileDimension pixels
    // The collage picture has collageDimension X collageDimension tiles
    private Picture collagePicture;

    // The collagePicture is made up of collageDimension X collageDimension tiles
    // Imagine a collagePicture as a 2D array of tiles
    private int collageDimension;

    // A tile consists of tileDimension X tileDimension pixels
    // Imagine a tile as a 2D array of pixels
    // A pixel has three components (red, green, and blue) that define the color 
    // of the pixel on the screen.
    private int tileDimension;
    
   /*
     * One-argument Constructor
     * 1. set default values of collageDimension to 4 and tileDimension to 150
     * 2. initializes originalPicture with the filename image
     * 3. initializes collagePicture as a Picture of tileDimension*collageDimension x tileDimension*collageDimension, 
     *    where each pixel is black (see constructors for the Picture class).
     * 4. update collagePicture to be a scaled version of original (see scaling filter on Week 9 slides)
     *
     * @param filename the image filename
     */
    public Collage (String filename) {
        this.collageDimension = 4; // default value of 4
        this.tileDimension = 150; // default value of 150

        this.originalPicture = new Picture(filename);

        int dimensionA = (this.tileDimension) * (this.collageDimension);
        int width, height = dimensionA; 
        this.collagePicture = new Picture(dimensionA, dimensionA);
        for (int i = 0; i < collagePicture.width(); i++) {
            for (int j = 0; j < collagePicture.height(); j++) {
                int colScale = (i * this.originalPicture.width()) / dimensionA;
                int rowScale = (j * this.originalPicture.height()) / dimensionA;
                Color color = this.originalPicture.get(colScale, rowScale);
                collagePicture.set(i, j, color);
            }
        }

    }

    /*
     * Three-arguments Constructor
     * 1. set default values of collageDimension to cd and tileDimension to td
     * 2. initializes originalPicture with the filename image
     * 3. initializes collagePicture as a Picture of tileDimension*collageDimension x tileDimension*collageDimension, 
     *    where each pixel is black (see all constructors for the Picture class).
     * 4. update collagePicture to be a scaled version of original (see scaling filter on Week 9 slides)
     * @param filename the image filename
     */    
    public Collage (String filename, int td, int cd) { 
        this.collageDimension = cd; // value is given to method
        this.tileDimension = td; // value is given to method
        this.originalPicture = new Picture(filename);
        int dimensionB = (this.tileDimension) * (this.collageDimension);
        this.collagePicture = new Picture(dimensionB, dimensionB);

        for (int i = 0; i < dimensionB; i++) { // go over columns
            for (int j = 0; j < dimensionB; j++) { // go over rows
                int colScale = (i * this.originalPicture.width()) / dimensionB;
                int rowScale = (j * this.originalPicture.height()) / dimensionB;
                Color color = this.originalPicture.get(colScale, rowScale);
                collagePicture.set(i, j, color);
            }
        }

    }


    /*
     * Scales the Picture @source into Picture @target size.
     * In another words it changes the size of @source to make it fit into
     * @target. Do not update @source. 
     *  
     * @param source is the image to be scaled.
     * @param target is the 
     */
    public static void scale (Picture source, Picture target) {
        int width = source.width();
        int height = source.height();

        for (int tempCol = 0; tempCol < width; tempCol++) {
            for (int tempRow = 0; tempRow < height; tempRow++) {
                int scol = (tempCol * source.width()) / width;
                int srow = (tempRow * source.height()) / height;
                Color color = source.get(scol, srow);
                target.set(tempCol, tempRow, color);
                }
            target.show();
            }
        }

     /*
     * Returns the collageDimension instance variable
     *
     * @return collageDimension
     */   
    public int getCollageDimension() {
        return collageDimension;
    }

    /*
     * Returns the tileDimension instance variable
     *
     * @return tileDimension
     */    
    public int getTileDimension() {
        return tileDimension;
    }

    /*
     * Returns original instance variable
     *
     * @return original
     */
    
    public Picture getOriginalPicture() {
        return originalPicture;
    }

    /*
     * Returns collage instance variable
     *
     * @return collage
     */
    
    public Picture getCollagePicture() {
        return collagePicture;
    }

    /*
     * Display the original image
     * Assumes that original has been initialized
     */    
    public void showOriginalPicture() {
        originalPicture.show();
    }

    /*
     * Display the collage image
     * Assumes that collage has been initialized
     */    
    public void showCollagePicture() {
	    collagePicture.show();
    }

    /*
     * Updates collagePicture to be a collage of tiles from original Picture.
     * collagePicture will have collageDimension x collageDimension tiles, 
     * where each tile has tileDimension X tileDimension pixels.
     */    
    public void makeCollage () {
        Picture scaled = new Picture(this.tileDimension, this.tileDimension);
        
        for (int col = 0; col < this.tileDimension; col++){
            for (int row = 0; row < this.tileDimension; row++){

                int scol = col * this.originalPicture.width() / this.tileDimension;
                int srow = row * this.originalPicture.height() / this.tileDimension;
                Color color = this.originalPicture.get(scol, srow);
                scaled.set(col, row, color);
            }
        }

        int fcol = 0;
        for (int col = 0; col < (this.tileDimension * this.collageDimension); col++){

            if (fcol == this.tileDimension){
                fcol = 0;
            }
            
            int frow = 0;
            for (int row = 0; row < (this.tileDimension * this.collageDimension); row++){

                if (frow == this.tileDimension){
                    frow = 0;
                }
                Color color = scaled.get(fcol, frow);
                this.collagePicture.set(col, row, color);
                frow++;
            }
            fcol++;
        }

    }

    /*
     * Colorizes the tile at (collageCol, collageRow) with component 
     * (see Week 9 slides, the code for color separation is at the 
     *  book's website)
     *
     * @param component is either red, blue or green
     * @param collageCol tile column
     * @param collageRow tile row
     */
    public void colorizeTile (String component,  int collageCol, int collageRow) {
        int r = 0;
        int g = 0;
        int b = 0;
        
        int replaceCol = collageCol * this.tileDimension;
        int replaceRow = collageRow * this.tileDimension;
        int colMax = replaceCol + this.tileDimension;
        int rowMax = replaceRow + this.tileDimension;

        for (int i = replaceCol; i < colMax; i++) { 
            for (int j = replaceRow; j < rowMax; j++) {
                Color color = collagePicture.get(i, j);
                if(component.equals("red")) { 
                    r = color.getRed();
                } else if(component.equals("green")) {
                    g = color.getGreen();
                } else if (component.equals("blue")) {
                    b = color.getBlue();
                }
                collagePicture.set(i, j, new Color(r, g, b));
            }
        }
    }

    /*
     * Replaces the tile at collageCol,collageRow with the image from filename
     * Tile (0,0) is the upper leftmost tile
     *
     * @param filename image to replace tile
     * @param collageCol tile column
     * @param collageRow tile row
     */
    public void replaceTile (String filename,  int collageCol, int collageRow) {

        int newCol = collageCol * this.tileDimension; 
        int newRow = collageRow * this.tileDimension; 

        Picture temp = new Picture(filename);
        Picture scaled = new Picture(this.tileDimension, this.tileDimension);

        for (int i = 0; i < this.tileDimension; i++) {
            for (int j = 0; j < this.tileDimension; j++) {
                int iCol = i * temp.width() / this.tileDimension;
                int jRow = j * temp.height() / this.tileDimension;
                Color color = temp.get(iCol, jRow);
                scaled.set(i, j, color);
            }
        }

        for (int i = 0; i < this.tileDimension; i++) {
            newRow = collageRow * this.tileDimension;
            for (int j = 0; j < this.tileDimension; j++) {
                Color color = scaled.get(i, j);
                collagePicture.set(newCol, newRow, color);
                newRow++;
            }
            newCol++;
        }
    }

    /*
     * Grayscale tile at (collageCol, collageRow)
     *
     * @param collageCol tile column
     * @param collageRow tile row
     */
    public void grayscaleTile (int collageCol, int collageRow) {
        int newCol = collageCol * this.tileDimension;
        int newRow = collageRow * this.tileDimension;
        int colMax = newCol + this.tileDimension;
        int rowMax = newRow + this.tileDimension;

        for (int i = newCol; i < colMax; i++) {
            for (int j = newRow; j < rowMax; j++) {
                Color color = collagePicture.get(i, j);
                Color gray = toGray(color);
                collagePicture.set(i, j, gray);
            }
        }
    }

    /**
     * Returns the monochrome luminance of the given color as an intensity
     * between 0.0 and 255.0 using the NTSC formula
     * Y = 0.299*r + 0.587*g + 0.114*b. If the given color is a shade of gray
     * (r = g = b), this method is guaranteed to return the exact grayscale
     * value (an integer with no floating-point roundoff error).
     *
     * @param color the color to convert
     * @return the monochrome luminance (between 0.0 and 255.0)
     */
    private static double intensity(Color color) {
        int r = color.getRed();
        int g = color.getGreen();
        int b = color.getBlue();
        if (r == g && r == b) return r;   // to avoid floating-point issues
        return 0.299*r + 0.587*g + 0.114*b;
    }

    /**
     * Returns a grayscale version of the given color as a {@code Color} object.
     *
     * @param color the {@code Color} object to convert to grayscale
     * @return a grayscale version of {@code color}
     */
    private static Color toGray(Color color) {
        int y = (int) (Math.round(intensity(color)));   // round to nearest int
        Color gray = new Color(y, y, y);
        return gray;
    }

    /*
     * Closes the image windows
     */
    public void closeWindow () {
        if ( originalPicture != null ) {
            originalPicture.closeWindow();
        }
        if ( collagePicture != null ) {
            collagePicture.closeWindow();
        }
    }
}