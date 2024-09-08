import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javax.imageio.ImageIO;

class summa extends JFrame {
    private BufferedImage image;
    private JTextField topText;
    private JTextField bottomText;
    private JButton generateButton;
    private JButton resetPositionButton;
    private String imagePath;
    private JSlider topSizeSlider;
    private JSlider bottomSizeSlider;
    private JSlider topXSlider;
    private JSlider topYSlider;
    private JSlider bottomXSlider;
    private JSlider bottomYSlider;
    private JPanel previewPanel;

    public summa(String imagePath) {
        try {
            this.imagePath = imagePath;
            image = ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
            // JOptionPane.showMessageDialog(this, "Error loading the image", "Error", JOptionPane.ERROR_MESSAGE);
        }

        topText = new JTextField(20);
        bottomText = new JTextField(20);
        generateButton = new JButton("Generate Meme");
        resetPositionButton = new JButton("Reset Position");

        generateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                createMeme();
                saveImage();
                System.exit(0);
            }
        });

        resetPositionButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Reset the sliders to their original positions
                topSizeSlider.setValue(30);
                topXSlider.setValue(image.getWidth() / 2);
                topYSlider.setValue(50);
                bottomSizeSlider.setValue(30);
                bottomXSlider.setValue(image.getWidth() / 2);
                bottomYSlider.setValue(image.getHeight() - 20);
                previewPanel.repaint(); // Redraw the preview
            }
        });

        // Initialize the preview panel
        previewPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawPreview(g);
            }
        };
        previewPanel.setPreferredSize(new Dimension(image.getWidth(), image.getHeight()));

        // Initialize sliders
        topSizeSlider = new JSlider(10, 60, 30);
        bottomSizeSlider = new JSlider(10, 60, 30);
        topXSlider = new JSlider(0, image.getWidth(), image.getWidth() / 2);
        topYSlider = new JSlider(0, image.getHeight(), 50);
        bottomXSlider = new JSlider(0, image.getWidth(), image.getWidth() / 2);
        bottomYSlider = new JSlider(0, image.getHeight(), image.getHeight() - 20);

        // Add the preview panel to the layout
        add(previewPanel, BorderLayout.CENTER);

        // Add text fields and buttons to the layout
        JPanel inputPanel = new JPanel(new FlowLayout());
        inputPanel.add(topText);
        inputPanel.add(bottomText);
        inputPanel.add(generateButton);

        // Add input and button panels to the NORTH position
        add(inputPanel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(resetPositionButton);

        // Add the button panel to the NORTH position
        add(buttonPanel, BorderLayout.NORTH);

        // Add sliders to the SOUTH position
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

        add(slidersPanel, BorderLayout.SOUTH);
    }

    private void drawPreview(Graphics g) {
        String topTextStr = topText.getText();
        String bottomTextStr = bottomText.getText();

        // Get slider values
        int topSize = topSizeSlider.getValue();
        int topX = topXSlider.getValue();
        int topY = topYSlider.getValue();
        int bottomSize = bottomSizeSlider.getValue();
        int bottomX = bottomXSlider.getValue();
        int bottomY = bottomYSlider.getValue();

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
    }

    private void createMeme() {
        // Create the final meme based on the preview
        Graphics2D g = image.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        // Draw the final meme using the same method as before
        drawPreview(g);

        g.dispose();

        repaint();
    }

    private void saveImage() {
        try {
            File outputImage = new File("generated_meme.png");
            ImageIO.write(image, "png", outputImage);
            System.out.println("Meme saved as: " + outputImage.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(image, 0, 0, this);
    }

    public static String selectImage()
    {
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
        ch=sc.nextInt();
        String img;
        switch(ch)
        {
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
