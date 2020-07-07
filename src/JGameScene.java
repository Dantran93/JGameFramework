import javafx.scene.canvas.Canvas;

/**
 * JGameScene
 *
 * Purpose: An abstract scene in the game. Create subclasses for the JGameSceneManager to use.
 */
public abstract class JGameScene extends Canvas
{
    /**
     * start ()
     *
     * Purpose: This method is called when JGame.goToNextScene() runs.
     *      Subclasses will independently determine how the scenes start.
     */
    public abstract void start ();


    /**
     * restart ()
     *
     * Purpose: This method is called when JGame.goToPreviousScene() runs.
     *      Subclasses will independently determine how the scenes restart.
     */
    public abstract void restart ();

} // abstract class JGameScene
