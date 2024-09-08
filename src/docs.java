import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.Scanner;
public class docs extends JFrame {
        // Variables for GUI components and image manipulation
        private BufferedImage image;          // The meme image template
        private JTextField topText;           // Text field for top text
        private JTextField bottomText;        // Text field for bottom text
        private JButton generateButton;       // Button to generate and save the meme
        private JSlider topSizeSlider;        // Slider for top text size
        private JSlider bottomSizeSlider;     // Slider for bottom text size
        private JSlider topXSlider;           // Slider for top text X position
        private JSlider topYSlider;           // Slider for top text Y position
        private JSlider bottomXSlider;        // Slider for bottom text X position
        private JSlider bottomYSlider;        // Slider for bottom text Y position

        private String imagePath;             // Path to the selected meme template image
        private JButton resetPositionButton;  // Button to reset text position

        /**
         * Constructor for the MemeGenerator class.
         *
         * @param imagepath The path to the selected meme template image.
         */
        public docs(String imagepath) {
            try {
                this.imagePath = imagepath;
                image = ImageIO.read(new File(imagePath));
            } catch (IOException e) {
                e.printStackTrace();
                //JOptionPane.showMessageDialog(this, "Error loading the image", "Error", JOptionPane.ERROR_MESSAGE);
            }

            topText = new JTextField(20);
            bottomText = new JTextField(20);
            generateButton = new JButton("Generate Meme");

            // Action listener for the "Generate Meme" button
            generateButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    createMeme();
                    saveImage();
                    //System.exit(0);
                }
            });

            // Initialize sliders for text customization
            topSizeSlider = new JSlider(10, 60, 30);
            bottomSizeSlider = new JSlider(10, 60, 30);
            topXSlider = new JSlider(0, image.getWidth(), image.getWidth() / 2);
            topYSlider = new JSlider(0, image.getHeight(), 50);
            bottomXSlider = new JSlider(0, image.getWidth(), image.getWidth() / 2);
            bottomYSlider = new JSlider(0, image.getHeight(), image.getHeight() - 20);

            // Create a panel to contain the sliders
            JPanel slidersPanel = new JPanel(new GridLayout(2, 6));
            slidersPanel.add(new JLabel("Top Text Size:"));
            slidersPanel.add(topSizeSlider);
            slidersPanel.add(new JLabel("Top Text X:"));
            slidersPanel.add(topXSlider);
            slidersPanel.add(new JLabel("Top Text Y:"));
            slidersPanel.add(topYSlider);
            slidersPanel.add(new JLabel("Bottom Text Size:"));
            slidersPanel.add(bottomSizeSlider);
            slidersPanel.add(new JLabel("Bottom Text X:"));
            slidersPanel.add(bottomXSlider);
            slidersPanel.add(new JLabel("Bottom Text Y:"));
            slidersPanel.add(bottomYSlider);

            // Add the sliders panel to the bottom of the frame
            add(slidersPanel, BorderLayout.SOUTH);

            // Create a panel for text input and the "Generate Meme" button
            JPanel inputPanel = new JPanel(new FlowLayout());
            inputPanel.add(topText);
            inputPanel.add(bottomText);
            inputPanel.add(generateButton);

            // Add the input panel to the top of the frame
            add(inputPanel, BorderLayout.NORTH);
        }

        /**
         * Method to create and display the meme with customized text and settings.
         */
        private void createMeme() {
            String topTextStr = topText.getText();
            String bottomTextStr = bottomText.getText();
            int topSize = topSizeSlider.getValue();
            int topX = topXSlider.getValue();
            int topY = topYSlider.getValue();
            int bottomSize = bottomSizeSlider.getValue();
            int bottomX = bottomXSlider.getValue();
            int bottomY = bottomYSlider.getValue();

            // Create a graphics object for drawing text on the image
            Graphics2D g = image.createGraphics();
            g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

            // Customize font and color for top text
            Font topFont = new Font("Impact", Font.BOLD, topSize);
            g.setFont(topFont);
            g.setColor(Color.WHITE);

            // Draw top text at the specified position
            g.drawString(topTextStr, topX, topY);

            // Customize font and color for bottom text
            Font bottomFont = new Font("Impact", Font.BOLD, bottomSize);
            g.setFont(bottomFont);
            g.setColor(Color.WHITE);

            // Draw bottom text at the specified position
            g.drawString(bottomTextStr, bottomX, bottomY);

            // Dispose of the graphics object
            g.dispose();

            repaint();  // Redraw the image with the added text
        }

        /**
         * Method to save the generated meme as an image file.
         */
        private void saveImage() {
            try {
                File outputImage = new File("generated_meme.png");
                ImageIO.write(image, "png", outputImage);
                System.out.println("Meme saved as: " + outputImage.getAbsolutePath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        /**
         * Method to display the meme image within the frame.
         */
        public void paint(Graphics g) {
            super.paint(g);
            g.drawImage(image, 0, 150, this);
        }

        /**
         * Method to select a meme template image based on user input.
         *
         * @return The path to the selected meme template image.
         */
        public static String selectImage() {
            System.out.println("MEME TEMPLATES");
            System.out.println("1. Disaster Girl");
            System.out.println("2. Distracted Boyfriend");
            System.out.println("3. Woman Yelling At Cat");
            System.out.println("4. Two Buttons");
            System.out.println("5. Three SpiderMans");
            System.out.println("6. Dhanush");
            System.out.println("7. Jeeva");
            System.out.println("8. Rajini");
            System.out.println("9. Vadivelu");
            System.out.println("10. Vivek");
            System.out.println("Select your meme template: ");
            int ch;
            Scanner sc = new Scanner(System.in);
            ch = sc.nextInt();
            String img;
            switch (ch) {
                case 1:
                    img = "C:\\Users\\smrit\\Downloads\\SEM 3\\Java Package\\disaster_girl.PNG";
                    break;
                case 2:
                    img = "C:\\Users\\smrit\\Downloads\\SEM 3\\Java Package\\distracted_boyfriend.PNG";
                    break;
                case 3:
                    img = "C:\\Users\\smrit\\Downloads\\SEM 3\\Java Package\\woman_yelling_at_cat.PNG";
                    break;
                case 4:
                    img = "C:\\Users\\smrit\\Downloads\\SEM 3\\Java Package\\two_buttons.PNG";
                    break;
                case 5:
                    img = "C:\\Users\\smrit\\Downloads\\SEM 3\\Java Package\\spiderman3.png";
                    break;
                case 6:
                    img = "C:\\Users\\smrit\\Downloads\\SEM 3\\Java Package\\Dhanush.jpg";
                    break;
                case 7:
                    img = "C:\\Users\\smrit\\Downloads\\SEM 3\\Java Package\\Jeeva1.jpg";
                    break;
                case 8:
                    img = "C:\\Users\\smrit\\Downloads\\SEM 3\\Java Package\\Rajini.jpg";
                    break;
                case 9:
                    img = "C:\\Users\\smrit\\Downloads\\SEM 3\\Java Package\\Vadivelu.jpg";
                    break;
                case 10:
                    img = "C:\\Users\\smrit\\Downloads\\SEM 3\\Java Package\\Vivek.jpg";
                    break;
                default:
                    img = "NULL";
                    break;
            }
            return img;
        }

        /**
         * The main method that starts the Meme Generator application.
         *
         * @param args The command-line arguments (not used in this application).
         */
        public static void main(String[] args) {
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    System.out.println("   WELCOME TO MEME GENERATOR   ");
                    System.out.println("                    Done By:");
                    System.out.println("                          SMRITHI L - 22PD33");
                    System.out.println("                          KAVIYA NS - 22PD16");
                    String imagePath = selectImage();
                    MemeGenerator memeGenerator = new MemeGenerator(imagePath);
                    memeGenerator.setSize(800, 600);
                    memeGenerator.setTitle("Meme Generator");
                    memeGenerator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    memeGenerator.setVisible(true);
                }
            });
        }
    }
