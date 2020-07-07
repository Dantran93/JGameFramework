import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.Random;

/**
 * JGame
 *
 * Purpose: Main class in the JGame framework. Create an instance of this class
 *      and call the any of the launch() methods to start the game.
 */
public final class JGame extends Application
{
    private static Pane root = null;
    private static JGameWindowSettings windowSettings = null;
    private static JGameScene firstGameScene = null;


    /**
     * launch ()
     *
     * Purpose: Launches the game with the default window settings.
     */
    public void launch ()
    {
        windowSettings = new JGameWindowSettings();
        Application.launch();
    } // launch()


    /**
     *
     */
    public void launch (final JGameScene firstScene)
    {
        firstGameScene = firstScene;
        Application.launch();
    }


    /**
     * launch (JGameWindowSettings)
     *
     * Purpose: Launches the game with the given window settings.
     */
    public void launch (final JGameWindowSettings newWindowSettings, final JGameScene firstScene)
    {
        windowSettings = newWindowSettings;
        firstGameScene = firstScene;
        Application.launch();
    } // launch (JGameWindowSettings)


    /**
     * start (Stage)
     *
     * Purpose: Runs when launch() is called. Prepares and starts the game.
     */
    @Override
    public void start (final Stage primaryStage)
    {
        initWindow(primaryStage);
        if (firstGameScene == null) goToNextScene(new DefaultJGameScene());
        else goToNextScene(firstGameScene);
        primaryStage.show();
    } // start (Stage)


    /**
     * initWindow (Stage)
     *
     * Purpose: Initializes the window with the current window settings.
     */
    private void initWindow (final Stage window)
    {
        root = new Pane();
        final Scene scene = new Scene(root);
        window.setTitle(windowSettings.getWindowTitle());
        window.setWidth(windowSettings.getWindowWidth());
        window.setHeight(windowSettings.getWindowHeight());
        window.setResizable(false);
        if (!windowSettings.hasWindowDecorations())
            window.initStyle(StageStyle.UNDECORATED);
        window.setScene(scene);
    } // initWindow (Stage)


    /**
     * goToNextScene (JGameScene)
     *
     * Purpose: Starts the given next scene.
     */
    public static void goToNextScene (final JGameScene nextScene)
    {
        nextScene.setWidth(windowSettings.getWindowWidth());
        nextScene.setHeight(windowSettings.getWindowHeight());
        JGameSceneManager.addScene(nextScene);
        root.getChildren().add(nextScene);
        JGameSceneManager.getActiveScene().start();
    } // goToNextScene (JGameScene)


    /**
     * goToPreviousScene ()
     *
     * Purpose: Returns back to the previous scene.
     */
    public static void goToPreviousScene ()
    {
        JGameSceneManager.removeScene();
        root.getChildren().remove(root.getChildren().size()-1);
        JGameSceneManager.getActiveScene().restart();
    } // goToPreviousScene ()


    /**
     * DefaultJGameScene
     *
     * Purpose: If the programmer does not provide a first scene, this scene is displayed.
     */
    private final class DefaultJGameScene extends JGameScene
    {
        private final DefaultJGameSceneAnimation animation = new DefaultJGameSceneAnimation();


        /**
         * start()
         *
         * Purpose: Starts the DefaultJGameScene.
         */
        @Override
        public void start () { animation.start(); }


        /**
         * restart()
         *
         * Purpose: Does nothing.
         */
        @Override
        public void restart () { /* Do Nothing */ }


        /**
         * DefaultJGameSceneAnimation
         *
         * Purpose: Runs the animation during the DefaultJGameScene.
         */
        private final class DefaultJGameSceneAnimation extends AnimationTimer
        {
            private double boxSize;
            private double boxX, boxY;
            private double xBound, yBound;
            private boolean boxGoingDown, boxGoingRight;
            private Random rng;
            private Color boxColor;


            /**
             * DefaultJGameSceneAnimation ()
             *
             * Purpose: Initializes the DefaultJGameSceneAnimation.
             */
            private DefaultJGameSceneAnimation ()
            {
                this.boxSize = (windowSettings.getWindowWidth()+windowSettings.getWindowHeight())/20;
                this.boxGoingDown = this.boxGoingRight = true;
                this.rng = new Random();
                this.boxColor = null;
                this.boxColor = getRandomColor(rng);
                this.boxX = this.rng.nextInt((int)(windowSettings.getWindowWidth()-boxSize));
                this.boxY = this.rng.nextInt((int)(windowSettings.getWindowHeight()-boxSize));
                this.xBound = windowSettings.getWindowWidth()-boxSize-(windowSettings.hasWindowDecorations() ? 5 : 0);
                this.yBound = windowSettings.getWindowHeight()-boxSize-(windowSettings.hasWindowDecorations() ? 26 : 0);
                getGraphicsContext2D().setTextAlign(TextAlignment.CENTER);
                getGraphicsContext2D().setFont(new Font("arial", (windowSettings.getWindowWidth()+windowSettings.getWindowHeight())/25));
            } // DefaultJGameSceneAnimation ()


            /**
             * handle (long)
             *
             * Purpose: Draws a single frame of the DefaultJGameSceneAnimation.
             */
            @Override
            public void handle (long now)
            {
                // Draw background
                getGraphicsContext2D().setFill(Color.BLACK);
                getGraphicsContext2D().fillRect(0, 0, windowSettings.getWindowWidth(), windowSettings.getWindowHeight());

                // Draw square
                getGraphicsContext2D().setFill(boxColor);
                if (boxX <= 0) {
                    boxGoingRight = true;
                    boxColor = getRandomColor(rng);
                } else if (boxX >= xBound) {
                    boxGoingRight = false;
                    boxColor = getRandomColor(rng);
                }
                if (boxY <= 0) {
                    boxGoingDown = true;
                    boxColor = getRandomColor(rng);
                } else if (boxY >= yBound) {
                    boxGoingDown = false;
                    boxColor = getRandomColor(rng);
                }
                boxX = boxGoingRight ? boxX+1 : boxX-1;
                boxY = boxGoingDown ? boxY+1 : boxY-1;
                getGraphicsContext2D().fillRect(boxX, boxY, boxSize, boxSize);

                // Draw text
                getGraphicsContext2D().setFill(Color.YELLOW);
                getGraphicsContext2D().fillText("- Coming Soon -", windowSettings.getWindowWidth()/2, (windowSettings.getWindowHeight()/2)-(windowSettings.getWindowWidth()+windowSettings.getWindowHeight())/20);
                getGraphicsContext2D().fillText("Your Game!", windowSettings.getWindowWidth()/2, windowSettings.getWindowHeight()/2);
            } // handle (long)


            /**
             * getRandomColor (Random)
             *
             * Purpose: Given a random number generator, a random color is returned.
             *      Possible values -- RED, ORANGE, YELLOW, GREEN, BLUE, PURPLE.
             */
            private Color getRandomColor (final Random rng)
            {
                Color newColor = this.boxColor;
                while (newColor == this.boxColor)
                {
                    final int colorID = rng.nextInt(5);
                    switch (colorID)
                    {
                        case 0: newColor = Color.RED; break;
                        case 1: newColor = Color.ORANGE; break;
                        case 2: newColor = Color.GREEN; break;
                        case 3: newColor = Color.BLUE; break;
                        case 4: newColor = Color.PURPLE; break;
                    }
                }
                return newColor;
            } // getRandomColor (Random)

        } // final class DefaultJGameSceneAnimation

    } // final class DefaultJGameScene

} // final class JGame
