


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LibraryManagementPage extends JFrame {
    public LibraryManagementPage() {
        super("Gérer la Bibliothèque");

        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        AnimatedBackgroundPanel backgroundPanel = new AnimatedBackgroundPanel();
        backgroundPanel.setLayout(new GridLayout(2, 2, 10, 10));
        backgroundPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JButton gererAdherentsButton = createStyledButton("Gérer les Adhérents", Color.BLUE);
        gererAdherentsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openManageAdherentWindow();
            }
        });
        backgroundPanel.add(gererAdherentsButton);

        JButton gererLivresButton = createStyledButton("Gérer les Livres", Color.BLUE);
        gererLivresButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openManageBooksWindow();
            }
        });
        backgroundPanel.add(gererLivresButton);

        JButton gererAuteursButton = createStyledButton("Gérer les Auteurs", Color.BLUE);
        gererAuteursButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openManageAuthorsWindow();
            }
        });
        backgroundPanel.add(gererAuteursButton);

        JButton emprunterLivreButton = createStyledButton("Emprunter un Livre", Color.BLUE);
        emprunterLivreButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openEmpruntLivreWindow();
            }
        });
        backgroundPanel.add(emprunterLivreButton);

        add(backgroundPanel);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    private JButton createStyledButton(String text, Color backgroundColor) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(200, 50));
        button.setBackground(backgroundColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
        return button;
    }

    private void openManageAdherentWindow() {
        new ManageAdherentWindow();
    }

    private void openManageBooksWindow() {
        new ManageBooksWindow();
    }

    private void openManageAuthorsWindow() {
        new ManageAuthorsWindow();
    }

    private void openEmpruntLivreWindow() {
        new EmpruntLivreWindow();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new LibraryManagementPage();
            }
        });
    }
}

class AnimatedBackgroundPanel extends JPanel implements ActionListener {
    private int yOffset = 0;
    private Timer timer;

    public AnimatedBackgroundPanel() {
        timer = new Timer(10, this);
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        drawAnimatedBackground(g2d);
        g2d.dispose();
    }

    private void drawAnimatedBackground(Graphics2D g2d) {
        int width = getWidth();
        int height = getHeight();
        int waveHeight = 50;
        int waveLength = 150;
        int speed = 5;

        for (int i = 0; i < width + waveLength; i += waveLength) {
            int x1 = i - waveLength + yOffset % waveLength;
            int y1 = height - waveHeight;
            int x2 = i + waveLength - waveLength + yOffset % waveLength;
            int y2 = height - waveHeight;
            int[] xPoints = {x1, i, x2};
            int[] yPoints = {height, y1, y2};
            g2d.setColor(new Color(173, 216, 230)); // Bleu clair
            g2d.fillPolygon(xPoints, yPoints, 3);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        yOffset++;
        repaint();
    }
}

        
