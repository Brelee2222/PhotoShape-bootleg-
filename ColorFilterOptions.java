import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class ColorFilterOptions extends Menu {

    PhotoshapeCanvas canvas;


    public ColorFilterOptions(String title, PhotoshapeCanvas canvas) {
        super(title);
        this.canvas = canvas;

        /*I'm leaving the variables here in case I need to add anything
        MenuItem grayScale = add(new GrayScaler("Gray Scale"));
        MenuItem invertedColor = add(new ColorInverter("Inverted Color"));
         */
        add(new GrayScaler("Gray Scale"));
        add(new ColorInverter("Inverted Color"));
    }

    class GrayScaler extends MenuItem implements ActionListener {
        ColorChanger colorChanger = new Grayify();
        PhotoshapeCanvas.PhotoshapeGraphics canvasGraphics;

        {
            addActionListener(this);
        }

        GrayScaler(String title) {
            super(title);

            canvasGraphics = canvas.photoshapeGraphics;
        }


        @Override
        public void actionPerformed(ActionEvent e) {
            BufferedImage image = canvas.image;
            long startingTime = System.currentTimeMillis();
            for(int i = 0; i < image.getWidth(); i++)
                for(int j = 0; j < image.getHeight(); j++) {
                    image.setRGB(i, j, colorChanger.filter(image.getRGB(i, j)));
                }
            System.out.println(System.currentTimeMillis() - startingTime);
            canvasGraphics.update(canvasGraphics.getPen());
            canvasGraphics.draw();
        }
    }

    class ColorInverter extends MenuItem implements ActionListener {
        ColorChanger colorChanger = new ColorInvert();
        PhotoshapeCanvas.PhotoshapeGraphics canvasGraphics;

        {
            addActionListener(this);
        }

        ColorInverter(String title) {
            super(title);

            canvasGraphics = canvas.photoshapeGraphics;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            /**
             * Previous Inverter
             */
            /*
            BufferedImage image = canvas.image;
            Graphics pen = image.getGraphics();

            pen.setXORMode(new Color(0, 0, 0, 0));
            pen.fillRect(0, 0, image.getWidth(), image.getHeight());
            canvasGraphics.update(image.getGraphics());
            canvasGraphics.draw();
             */

            BufferedImage image = canvas.image;

            for(int i = 0; i < image.getWidth(); i++)
                for(int j = 0; j < image.getHeight(); j++) {
                    image.setRGB(i, j, colorChanger.filter(image.getRGB(i, j)));
                }
            canvasGraphics.update(canvasGraphics.getPen());
            canvasGraphics.draw();
        }
    }
}
