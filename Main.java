
import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import java.util.*;
/*<applet code="Main" width=200 height=400>
</applet>*/

class MyCar implements Runnable, KeyListener {

    Main main;
    int x, y;
    Thread t;
    Image img;
    boolean gameOver;

    MyCar(Main main) {
        this.main = main;
        t = new Thread(this);
        gameOver = false;
        x = 100;
        y = 300;
    }

    void reset() {
        gameOver = false;
        x = 100;
        y = 300;
    }

    void drawCar(Graphics g) {

        if (gameOver == false && main.dv.end == false) {
            img = main.getImage(main.getCodeBase(), "car.gif");
            g.drawImage(img, x, y, main);
        }
    }

    public void run() {
        main.addKeyListener(this);
    }

    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT) {
            if (x > 4) {
                x = x - 10;
            }
        }
        if (key == KeyEvent.VK_RIGHT) {
            if (x < 174) {
                x = x + 10;
            }
        }
        if (key == KeyEvent.VK_UP) {
            if (y > 4) {
                y = y - 10;
            }
        }
        if (key == KeyEvent.VK_DOWN) {
            if (y < 364) {
                y = y + 10;
            }
        }
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
    }
}

class Divider implements Runnable {

    Main main;
    int width;
    int height;
    Thread t;
    int x1, y1, x2, y2, x3, y3;
    boolean remove;
    boolean end;
    int i;
    boolean runThread;
    boolean runLoop;

    Divider(Main main, int pos) {
        this.main = main;
        this.x1 = this.x2 = this.x3 = pos;
        width = 3;
        height = 100;
        t = new Thread(this);
        y1 = 0;
        y2 = 135;
        y3 = 265;
        remove = false;
        end = false;
        runThread = true;
        runLoop = true;
    }

    void reset() {
        y1 = 0;
        y2 = 135;
        y3 = 265;
        remove = false;
        end = false;
        i = 0;
        runLoop = true;
    }

    void draw(Graphics g) {
        if (!remove == true) {
            g.setColor(Color.WHITE);
            g.fillRect(x1, y1, width, height);
            g.fillRect(x1, y1 + 400, width, height);
            g.setColor(Color.WHITE);
            g.fillRect(x2, y2, width, height);
            g.fillRect(x2, y2 + 400, width, height);
            g.setColor(Color.WHITE);
            g.fillRect(x3, y3, width, height);
            g.fillRect(x3, y3 + 400, width, height);
        }
    }

    public void run() {
        while (runThread == true) {

            for (i = 0; i < 1000;) {

                if (y1 == 300) {
                    y1 = -100;
                }
                if (y2 == 300) {
                    y2 = -100;
                }
                if (y3 == 300) {
                    y3 = -100;
                }
                y1++;
                y2++;
                y3++;
                try {
                    Thread.sleep(24);
                } catch (InterruptedException ie) {
                }
                main.repaint();
                if (runLoop == true) {
                    i++;
                }
            }
            end = true;
        }
    }
}

class Car implements Runnable {

    Main main;
    int x1, y1, x2, y2, x3, y3;
    Image img;
    Image endgame;
    Thread t;
    Random r;
    int random;
    int obw1, obw2, obw3, obh1, obh2, obh3;
    int i;
    boolean runThread;
    boolean runLoop;

    public Car(Main main) {

        this.main = main;
        t = new Thread(this);
        r = new Random();
        obw1 = 21;
        obh1 = 32;
        obw2 = 21;
        obh2 = 32;
        obw3 = 21;
        obh3 = 32;
        y1 = -32;
        y2 = -140;
        y3 = -227;
        x1 = 35;
        x2 = 70;
        x3 = 105;
        runThread = true;
        runLoop = true;
    }

    void reset() {
        y1 = -32;
        y2 = -140;
        y3 = -227;
        x1 = 35;
        x2 = 70;
        x3 = 105;
        runLoop = true;
    }

    void drawOpp(Graphics g) {
        if (main.dv.end == true) {
            main.dv.remove = true;
            endgame = main.getImage(main.getCodeBase(), "yw.gif");
            g.drawImage(endgame, 40, 140, main);
            g.setColor(Color.red);
            main.dv.runLoop = false;
            main.op.runLoop = false;
        } else if (main.mycar.gameOver == true) {
            main.dv.remove = true;
            endgame = main.getImage(main.getCodeBase(), "go.gif");
            g.drawImage(endgame, 50, 150, main);
            g.setColor(Color.red);
            main.dv.runLoop = false;
            main.op.runLoop = false;
        } else {
            img = main.getImage(main.getCodeBase(), "car1.gif");
            g.drawImage(img, x1, y1, main);
            img = main.getImage(main.getCodeBase(), "car2.gif");
            g.drawImage(img, x2, y2, main);
            img = main.getImage(main.getCodeBase(), "car3.gif");
            g.drawImage(img, x3, y3, main);
        }
    }

    public void run() {
        while (runThread == true) {

            for (i = 0; i < 2000;) {
                if (y1 == 400) {
                    y1 = -32;
                    random = r.nextInt(160);

                    if ((((random < x2 + obw2)
                            && (random + 21 > x2))
                            || (((random < x3 + obw3)
                            && (random + 21 > x3))))) {
                        if (random + 35 < 174) {
                            x1 = random + 35;
                        }
                    } else {
                        x1 = random;
                    }

                }
                if (y2 == 400) {
                    y2 = -32;
                    random = r.nextInt(160);
                    if ((((random < x1 + obw1)
                            && (random + 21 > x1))
                            || (((random < x3 + obw3)
                            && (random + 21 > x3))))) {
                        if (random + 70 < 174) {
                            x2 = random + 70;
                        }
                    } else {
                        x2 = random;
                    }

                }
                if (y3 == 400) {
                    y3 = -32;
                    random = r.nextInt(160);
                    if ((((random < x1 + obw1)
                            && (random + 21 > x1))
                            || (((random < x2 + obw2)
                            && (random + 21 > x2))))) {
                        if (random + 105 < 174) {
                            x3 = random + 105;
                        }
                    } else {
                        x3 = random;
                    }
                }
                if ((((main.mycar.x < x1 + obw1)
                        && (main.mycar.x + 21 > x1))
                        && ((main.mycar.y < y1 + obh1)
                        && (main.mycar.y + 32 > y1)))
                        || (((main.mycar.x < x2 + obw2)
                        && (main.mycar.x + 21 > x2))
                        && ((main.mycar.y < y2 + obh2)
                        && (main.mycar.y + 32 > y2)))
                        || (((main.mycar.x < x3 + obw3)
                        && (main.mycar.x + 21 > x3))
                        && ((main.mycar.y < y3 + obh3)
                        && (main.mycar.y + 32 > y3)))) {
                    main.mycar.gameOver = true;
                }
                y1 += 1;
                y2 += 2;
                y3 += 3;
                try {
                    Thread.sleep(12);
                } catch (InterruptedException ie) {
                }
                main.repaint();
                if (runLoop == true) {
                    i++;
                }
            }
        }
    }
}

class PushButton {

    Main main;
    Image img;
    int x, y;

    PushButton(Main main) {
        this.main = main;
        x = 65;
        y = 160;
    }

    void drawStart(Graphics g) {
        img = main.getImage(main.getCodeBase(), "start.png");
        g.drawImage(img, x, y, main);
    }

    void drawRestart(Graphics g) {
        img = main.getImage(main.getCodeBase(), "restart.png");
        g.drawImage(img, x - 15, y + 150, main);
    }
}

public class Main extends Applet {

    MyCar mycar;
    Car op;
    boolean sep;
    Divider dv;
    Image buffer = null;
    int w, h;
    PushButton pb;
    boolean startPressed;

    public void init() {
        Dimension d = getSize();
        w = d.width;
        h = d.height;
        buffer = createImage(w, h);
        setBackground(Color.BLACK);

        mycar = new MyCar(this);
        op = new Car(this);
        dv = new Divider(this, 95);

        pb = new PushButton(this);
        addMouseListener(new MouseAdapter() {

            public void mousePressed(MouseEvent me) {

                if ((startPressed == false)
                        && (me.getX() > pb.x)
                        && (me.getX() < pb.x + 74)
                        && (me.getY() > pb.y)
                        && (me.getY() < pb.y + 36)) {
                    startPressed = true;
                    dv.t.start();
                    op.t.start();
                    mycar.t.start();
                    repaint();
                }
                if ((mycar.gameOver == true || dv.end == true)
                        && (me.getX() > pb.x - 15)
                        && (me.getX() < pb.x + 74 - 15)
                        && (me.getY() > pb.y + 150)
                        && (me.getY() < pb.y + 36 + 150)) {
                    dv.reset();
                    op.reset();
                    mycar.reset();
                }
            }
        });
    }

    public void start() {
    }

    public void paint(Graphics g) {
        Graphics sc = null;
        sc = g;
        g = buffer.getGraphics();
        g.clearRect(0, 0, w, h);

        if (mycar.gameOver == true || dv.end == true) {
            pb.drawRestart(g);
        }
        if (startPressed == false) {
            pb.drawStart(g);
        }
        if (startPressed == true) {
            dv.draw(g);
            op.drawOpp(g);
            mycar.drawCar(g);
        }
        sc.drawImage(buffer, 0, 0, this);
    }

    public void update(Graphics g) {
        paint(g);
    }
}
