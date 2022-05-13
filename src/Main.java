import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.Socket;

public class Main {
    public static void main(String[] args) throws IOException {
        String ip = "localhost";
        int port = 5432;
        Socket socket = new Socket(ip, port);

    }
}

class Login extends Frame {

}

class ChooseTable {

}

class ChessGame extends Frame {
    ChessBoard b = new ChessBoard( );
    public ChessGame( ) {
        setBackground(Color.lightGray);
        setLayout(new BorderLayout());
        add("Center", b);
        Panel panel = new Panel();
        Button surrender = new Button("Surrender");
        Button undo = new Button("Undo");
        panel.setLayout(new GridLayout(8, 1, 10, 10));
        panel.add(surrender);
        panel.add(undo);
        add("East", panel);
        setSize(500, 450);
        setVisible(true);
    }
}

class ChessBoard extends Canvas {
    int[][] chess = new int[19][19];
    int sx = 20, sy = 20;
    int w = 20;
    int cx = 50;
    int cy = 50;
    int player = 1;
    public ChessBoard() {
        this.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseMoved(MouseEvent e) {
                Graphics g = getGraphics();
                g.setXORMode(ChessBoard.this.getBackground());
                g.setColor(Color.red);
                g.fillRect(cx - w / 4, cy - w / 4, w / 2, w / 2);
                cx = sx + (e.getX() -sx+w/2) / w * w;
                cy = sy + (e.getY() -sy+w/2) / w * w;
                g.fillRect(cx - w / 4, cy - w / 4, w / 2, w / 2);
            }
        });
        this.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                Graphics g = getGraphics( );
                if (chess[(cx - sx)/w][(cy - sy)/w] == 0)
                    if (player == 1) {
                        g.setColor(Color.black);
                        chess[(cx - sx) / w][(cy - sy) / w] = 1;
                    } else {
                        g.setColor(Color.white);
                        chess[(cx - sx) / w][(cy - sy) / w] = 2;
                    }
                g.fillOval(cx - w / 2 + 1, cy - w / 2 + 1, w - 2, w - 2);
                player = (player + 1) % 2;
                g.setXORMode(ChessBoard.this.getBackground( ));
                g.setColor(Color.red);
                g.fillRect(cx - w / 4, cy - w / 4, w / 2, w / 2);
            }
        }); }
    public void paint(Graphics g) {
        for (int k = 0; k < 19; k++)
            g.drawLine(sx, sy + k * w, sx + w * 18, sy + k * w);
        for (int k = 0; k < 19; k++)
            g.drawLine(sx + k * w, sy, sx + k * w, sy + w * 18);
        for (int i = 0; i < chess.length; i++)
            for (int j = 0; j < chess[0].length; j++) {
                if (chess[i][j] == 1) {
                    g.setColor(Color.black);
                    g.fillOval(sx + i * w - w/2 + 1, sx + j * w - w/2 + 1,w - 2, w);
                } else if (chess[i][j] == 2) {
                    g.setColor(Color.white);
                    g.fillOval(sx + i * w - w/2 + 1, sx + j * w - w/2 + 1, w - 2, w - 2);
                }
            }
        g.setXORMode(this.getBackground( ));
        g.setColor(Color.red);
        g.fillRect(cx - w / 4, cy - w / 4, w / 2, w / 2);
    }
}