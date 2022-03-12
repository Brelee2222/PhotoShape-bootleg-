/*
Has the functions to paint on the image
 */

import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.*;
import java.util.Arrays;

class PaintBrush implements MouseInputListener {

    PhotoshapeCanvas canvas;
    PhotoshapeCanvas.PhotoshapeGraphics canvasGraphics;
    int brushSize = 15;

    public PaintBrush(PhotoshapeCanvas canvas) {
        this.canvas = canvas;
        this.canvasGraphics = canvas.photoshapeGraphics;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        draw(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        draw(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    public void draw(MouseEvent e) {
        BufferedImage image = canvas.image;
        Graphics pen = image.getGraphics();
        TranslationMultiplier translationMultiplier = canvasGraphics.translationMultiplier;
        int x = (int) (e.getX() / translationMultiplier.multiplierX) - brushSize;
        int y = (int) (e.getY() / translationMultiplier.multiplierY) - brushSize;
        int brushDiameter = 2*brushSize;
        pen.setColor(canvas.photoshapeGraphics.penColor);
        pen.fillOval(x, y, brushDiameter, brushDiameter);
        canvasGraphics.update(canvasGraphics.getPen());
        canvasGraphics.draw();
    }
}

class LineTool implements MouseInputListener {

    PhotoshapeCanvas canvas;
    PhotoshapeCanvas.PhotoshapeGraphics canvasGraphics;
    int lineThickness = 15;

    int initialX;
    int initialY;

    public LineTool(PhotoshapeCanvas canvas) {
        this.canvas = canvas;
        this.canvasGraphics = canvas.photoshapeGraphics;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        initialX = e.getX();
        initialY = e.getY();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Graphics2D pen = (Graphics2D) canvas.image.getGraphics();
        pen.setColor(canvas.photoshapeGraphics.penColor);
        pen.setStroke(new BasicStroke(15));
        pen.drawLine((int) (e.getX() / canvasGraphics.translationMultiplier.multiplierX), (int) (e.getY() / canvasGraphics.translationMultiplier.multiplierY), (int) (initialX / canvasGraphics.translationMultiplier.multiplierX), (int) (initialY / canvasGraphics.translationMultiplier.multiplierX));
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        draw(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    public void draw(MouseEvent e) {
        Graphics2D pen = canvasGraphics.getPen();
        int x = e.getX();
        int y = e.getY();

        pen.setColor(canvas.photoshapeGraphics.penColor);
        pen.setStroke(new BasicStroke(15));
        canvasGraphics.smoothDraw();
        pen.drawLine(x, y, initialX, initialY);
        canvasGraphics.display();
        canvasGraphics.update(pen);
    };
}

class Erase implements MouseInputListener {
    public int brushSize = 15;
    public int[] brushSizeList;
    PhotoshapeCanvas canvas;
    PhotoshapeCanvas.PhotoshapeGraphics canvasGraphics;

    public Erase(PhotoshapeCanvas canvas) {
        this.canvas = canvas;
        this.canvasGraphics = canvas.photoshapeGraphics;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //will be changed
        brushSizeList = new int[2*brushSize];
        Arrays.fill(brushSizeList, 0x00000000);
        draw(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        draw(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    public void draw(MouseEvent e) {
        int brushDiameterX = 2*brushSize;
        int brushDiameterY = brushDiameterX;
        BufferedImage image = canvas.image;
        TranslationMultiplier translationMultiplier = canvasGraphics.translationMultiplier;
        int x = (int) (e.getX() / translationMultiplier.multiplierX) - brushSize;
        int y = (int) (e.getY() / translationMultiplier.multiplierY) - brushSize;
        if(x < 0)
            x = 0;
        if(y < 0)
            y = 0;
        if(brushDiameterX + x > image.getWidth())
            brushDiameterX = image.getWidth() - x;
        if(brushDiameterY + y > image.getHeight())
            brushDiameterY = image.getHeight() - y;
        image.setRGB(x, y, brushDiameterX, brushDiameterY, brushSizeList, 0, 0);
        canvasGraphics.update(canvasGraphics.getPen());
        canvasGraphics.draw();
    }
}
